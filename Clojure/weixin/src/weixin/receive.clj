(ns weixin.receive
  (require [clojure.data.xml :as xml]
           [weixin.auth :refer [check-signature]]
           [weixin.event-log :refer [log-request log-response log-user]]
           [weixin.request :refer [user-info]]))

;; ad hoc.
(defn to-map
  "convert an xml to clojure map."
  [xml]
  (into {}
        (for [{tag :tag content :content}
              (-> xml xml/parse :content)]
          {tag (first content)})))

;; ad hoc
(defn to-xml
  "convert a clojure map to xml"
  [m]
  (-> [:xml {}
       (for [[tag content] m]
         [tag {} (if (= tag :CreateTime)
                   content
                   [:-cdata content])])]
      xml/sexp-as-element
      xml/emit-str))

(defn event-subscribe
  "处理用户关注事件"
  [m]
  "")

(defn event-scan
  "处理扫描二维码事件"
  [m]
  "")

(defn event-location
  "处理上报地理位置事件"
  [m]
  "")

(defn event-click
  "处理点击菜单拉取消息的事件"
  [m]
  "")

(defn event-view
  "处理点击菜单跳转链接的事件"
  [m]
  "")

(defn event-unkonwn
  "处理未知事件"
  []
  "")

(defn process-event [m]
  (let [e (:Event m)]
    (cond
     (= e "subscribe")
     (event-subscribe m)

     (= e "SCAN")
     (event-scan m)

     (= e "LOCATION")
     (event-location m)

     (= e "CLICK")
     (event-click m)

     (= e "VIEW")
     (event-view m)

     :else
     (event-unkonwn m))))

(defn thanks [m]
  {:ToUserName		(:FromUserName m)
   :FromUserName	(:toUserName m)
   :CreateTime		(.getTime (java.util.Date.))
   :MsgType		"text"
   :Content		"收到，谢谢。"})

(defn process-text
  "处理文本消息"
  [m]
  (let [res (thanks m)]
    (log-response res)
    (to-xml res)))

;; to be upaded with saving the media into database functionality.
(defn process-image
  "处理图片消息"
  [m]
  (let [res (thanks m)]
    (log-request res)
    (to-xml res)))

(defn process-voice
  "处理语音消息"
  [m]
  (let [res (thanks m)]
    (log-request res)
    (to-xml res)))

(defn process-video
  "处理视频消息"
  [m]
  (let [res (thanks m)]
    (log-request res)
    (to-xml res)))

(defn process-location
  "处理地理位置消息"
  [m]
  (let [res (thanks m)]
    (log-request res)
    (to-xml res)))

(defn process-link
  "处理衔接消息"
  [m]
  (let [res (thanks m)]
    (log-request res)
    (to-xml res)))

(defn receive [req]
  (let [m 		(to-map (:body req))
        msg-type 	(:MsgType m)
        openid 		(:FromUserName m)
        user 		(user-info openid)]
    (-> req :params check-signature assert)
    (log-request m)
    (log-user user openid)
    (cond
     (= msg-type "event")
     (process-event m)

     (= msg-type "text")
     (process-text m)

     (= msg-type "image")
     (process-image m)

     (= msg-type "voice")
     (process-voice m)

     (= msg-type "video")
     (process-video m)
     
     (= msg-type "location")
     (process-location m)

     (= msg-type "link")
     (process-link m))))
