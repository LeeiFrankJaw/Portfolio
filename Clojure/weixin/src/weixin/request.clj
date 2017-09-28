(ns weixin.request
  (:require [clojure.data.json :as json]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.core.memoize :as memo]
            [clj-http.client :as http]
            [weixin.config :as c]
            [weixin.event-log :refer :all]))

(defn gen-url
  "generate url using the base and the params."
  [base params]
  (->> params
       (map #(str (-> % first name) "=" (second %)))
       (s/join "&")
       (str base)))

(defn- do-get-request
  "make a GET request with the given base url and params."
  [base params]
  (-> (gen-url base params)
      slurp
      (json/read-str :key-fn keyword)))

;; 获取access token
;; http请求方式: GET
;; https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

;; 参数		是否必须	说明
;; grant_type	是	获取access_token填写client_credential
;; appid	是	第三方用户唯一凭证
;; secret	是	第三方用户唯一凭证密钥，即appsecret

(defn- token*
  "Requests AccessToken from
  https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
  return: {'access_token':'ACCESS_TOKEN','expires_in':7200}
  "
  []
  (do-get-request "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&"
                  {:appid c/app-id
                   :secret c/app-secret}))

(def token (memo/ttl token* :ttl/threshold 3600000))

;; 获取用户基本信息
;; http请求方式: GET
;; https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN

;; 参数		是否必须	说明
;; access_token	是	调用接口凭证
;; openid	是	普通用户的标识，对当前公众号唯一
;; lang		否	返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语

(defn user-info
  "request user info from
  https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
  using given openid"
  [openid]
  (do-get-request "https://api.weixin.qq.com/cgi-bin/user/info?"
                  {:access_token (:access_token (token))
                   :openid openid}))

(defn- do-post-request
  "make a POST request with the given base url, params, and req."
  [base params req]
  (-> (http/post (gen-url base params) req)
      :body
      (json/read-str :key-fn keyword)))

(defn send-text
  "send text message to the user specified by to-user openid"
  [to-user text]
  (do-post-request "https://api.weixin.qq.com/cgi-bin/message/custom/send?"
                   {:access_token (:access_token (token))}
                   {:body (json/write-str
                           {:touser to-user
                            :msgtype "text"
                            :text {:content text}}
                           :escape-unicode false)}))

;; 上传多媒体文件
;; 公众号可调用本接口来上传图片、语音、视频等文件到微信服务器，上传后服务
;; 器会返回对应的media_id，公众号此后可根据该media_id来获取多媒体。**请注意，
;; media_id是可复用的，调用该接口需http协议。**

;; http请求方式: POST/FORM
;; http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
;; 调用示例（使用curl命令，用FORM表单方式上传一个多媒体文件）：
;; curl -F media=@test.jpg "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"

(defn upload-media
  "upload the media file to the tencent servers."
  [type media-file]
  (let [result
        (do-post-request "http://file.api.weixin.qq.com/cgi-bin/media/upload?"
                         {:access_token (:access_token (token))
                          :type type}
                         {:multipart [{:name "media"
                                       :content media-file}]})]
    (if (contains? result :created_at)
      (log-media result (.getName media-file))
      (log-error result))))

;; 下载多媒体文件
;; 公众号可调用本接口来获取多媒体文件。**请注意，视频文件不支持下载，调用该接口需http协议。**

;; http请求方式: GET
;; http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
;; 请求示例（示例为通过curl命令获取多媒体文件）
;; curl -I -G "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID"

(defn download-media
  "download the media file to the local server"
  [media-id]
  (let [result (-> (gen-url "http://file.api.weixin.qq.com/cgi-bin/media/get?"
                            {:access_token (:access_token (token))
                             :media_id media-id})
                   (http/get {:as :byte-array}))
        bytes (:body result)
        cdispos (get-in result [:headers "Content-disposition"])
        contype (get-in result [:headers "Content-Type"])
        filename (if cdispos (subs cdispos 22 (- (.length cdispos) 1)))]
    (if filename
      (log-file bytes filename contype)
      (log-error (json/read-str (String. bytes) :key-fn keyword)))))

;; 目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
;; 一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”
;; 代替。请注意，创建自定义菜单后，由于微信客户端缓存，需要**24**小时微信
;; 客户端才会展现出来。建议测试时可以尝试取消关注公众账号后再次关注，
;; 则可以看到创建后的效果。

;; 自定义菜单接口可实现多种类型按钮，如下：

;; 1、click：点击推事件
;; 用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为
;; event 的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写
;; 的key值，开发者可以通过自定义的key值与用户进行交互；

;; 2、view：跳转URL
;; 用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页
;; URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。

;; 3、scancode_push：扫码推事件
;; 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描
;; 结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可
;; 以下发消息。

;; 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
;; 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码
;; 的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，
;; 随后可能会收到开发者下发的消息。

;; 5、pic_sysphoto：弹出系统拍照发图
;; 用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄
;; 的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能
;; 会收到开发者下发的消息。

;; 6、pic_photo_or_album：弹出拍照或者相册发图
;; 用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手
;; 机相册选择”。用户选择后即走其他两种流程。

;; 7、pic_weixin：弹出微信相册发图器
;; 用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的
;; 相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可
;; 能会收到开发者下发的消息。

;; 8、location_select：弹出地理位置选择器
;; 用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，
;; 将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可
;; 能会收到开发者下发的消息。

;; 请注意，**3**到**8**的所有事件，仅支持微信**iPhone5.4.1**以上版本，
;; 和**Android5.4**以上版本的微信用户，旧版本微信用户点击后将没有回应，
;; 开发者也不能正常接收到事件推送。

;; 接口调用请求说明

;; http请求方式：POST（请使用https协议）
;; https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN

;; 星畅动态：关于星畅 
;; 解决方案：流程管理、知识管理、信息门户、移动办公、系统集成
;; 试用申请：（姓名、单位名称、联系手机、联系座机、电子邮件、所在城市、需求说明）

(def default-menu
  {:button
   [{:name "星畅动态"
     :sub_button	[{:type "view"
                          :name "关于星畅"
                          :url "http://www.starworking.com/about_us?id=4ead6c6aeef76182a51d544c"}]}
    {:name "解决方案"
     :sub_button	[{:type "view"
                          :name "流程管理"
                          :url "http://www.starworking.com/products/show?id=514e92848f64029df3ca82d8"}

                         {:type "view"
                          :name "移动办公"
                          :url "http://www.starworking.com/products/show?id=4eaa9c0aeef76182a51d5429"}

                         {:type "view"
                          :name "解决方案"
                          :url "http://www.starworking.com/industrySoluations/show?id=50e4599d8f649c5fbe02a31c"}]}
    {:type "view"
     :name "使用申请"
     :url "http://www.starworking.com/contact_us"}]})

(defn set-menu
  "set the menu using the given clojure map"
  [menu]
  (do-post-request "https://api.weixin.qq.com/cgi-bin/menu/create?"
                   {:access_token (:access_token (token))}
                   {:body (json/write-str menu :escape-unicode false)}))

(defn set-default-menu
  "set the default menu"
  []
  (set-menu default-menu))

;; 使用接口创建自定义菜单后，开发者还可使用接口查询自定义菜单的结构。

;; 请求说明
;; http请求方式：GET
;; https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN

(defn get-menu
  "get the current effective menu"
  []
  (do-get-request "https://api.weixin.qq.com/cgi-bin/menu/get?"
                  {:access_token (:access_token (token))}))

;; 使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。

;; 请求说明
;; http请求方式：GET
;; https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN

(defn delete-menu
  "delete the current effective menu"
  []
  (do-get-request "https://api.weixin.qq.com/cgi-bin/menu/delete?"
                  {:access_token (:access_token (token))}))

;; 在公众平台网站上，为订阅号提供了每天一条的群发权限，为服务号提供每
;; 月（自然月）4条的群发权限。而对于某些具备开发能力的公众号运营者，可
;; 以通过高级群发接口，实现更灵活的群发能力。

;; 请注意：

;; 1、对于认证订阅号，**群发接口每天可成功调用1次，此次群发可选择发送给
;; 全部用户或某个分组**；
;; 2、对于认证服务号虽然开发者使用高级群发接口的每日调用限制为100次，
;; 但是用户每月只能接收4条，**无论在公众平台网站上，还是使用接口群发，用
;; 户每月只能接收4条群发消息，多于4条的群发将对该用户发送失败**；
;; 3、具备微信支付权限的公众号，在使用群发接口上传、群发图文消息类型时，
;; 可使用<a>标签加入外链；
;; 4、开发者可以使用预览接口校对消息样式和排版，通过预览接口可发送编辑
;; 好的消息给指定用户校验效果。

;; http请求方式: POST
;; https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN

(def test-article {:thumb_media_id "8LBMw2448s10hbDR9HqFxpYg8J0jBvrFbqU7X974PVSZGwG0AyaW-HynL9NTLzVh"
                   :author "林晴"
                   :title "星畅科技－专业的企业管理解决方案供应商"
                   :content_source_url "www.starworking.com"
                   :content (slurp "resources/public/test.html")
                   :digest (str "快速的业务流程建模，灵活的用户界面定制。\n"
                                "帮助企业优化业务流程，快速提升工作效率和响应能力，降低总体拥有成本。")
                   :show_cover_pic "1"})

(def test-articles {:articles [test-article]})

(defn upload-news
  "Upload news to the tencent server"
  [news]
  (let [result
        (do-post-request "https://api.weixin.qq.com/cgi-bin/media/uploadnews?"
                         {:access_token (:access_token (token))}
                         {:body (json/write-str news :escape-unicode false)})]
    (if (contains? result :created_at)
      (log-news result)
      (log-error result))))

;; 根据分组进行群发【订阅号与服务号认证后均可用】
;; http请求方式: POST
;; https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN

(defn mp-news [media-id] {:filter {:is_to_all true}
                          :mpnews {:media_id media-id}
                          :msgtype "mpnews"})

(defn mp-video [media-id] {:filter {:is_to_all true}
                           :mpvideo {:media_id media-id}
                           :msgtype "mpvideo"})

(defn send-all
  "send news to all subscribed users"
  [mp]
  (let [result (do-post-request "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?"
                                {:access_token (:access_token (token))}
                                {:body (json/write-str mp :escape-unicode false)})]
    (if (= (:errcode result) 0)
      (log-mp result)
      (log-error result))))

;; 视频
;; 请注意，此处视频的media_id需通过POST请求到下述接口特别地得到：
;; https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN
;; POST数据如下（此处media_id需通过基础支持中的上传下载多媒体文件来得
;; 到）：

(defn video [media-id title description] {:media_id    media-id
                                          :title       title
                                          :description description})

(defn upload-videio [media-id title description]
  (let [result
        (do-post-request "http://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?"
                         {:access_token (:access_token (token))}
                         {:body (json/write-str (video media-id title description)
                                                :escape-unicode false)})]
  (if (contains? result :created_at)
    (log-video result)
    (log-error result))))
