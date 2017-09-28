(ns weixin.utils
  (:require [clojure.data.json :as json]
            [weixin.config :as c]))

;; Note: This file requires org.clojure/data.json parser, you should
;; add [org.clojure/data.json "0.2.5"] to project.clj file.

;; Documentation for data.json can be found here.
;; https://github.com/clojure/data.json

;; 获取access token
;; http请求方式: GET
;; https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

;; 参数		是否必须	说明
;; grant_type	是	获取access_token填写client_credential
;; appid	是	第三方用户唯一凭证
;; secret	是	第三方用户唯一凭证密钥，即appsecret

;; (def query-str "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&")

;; (defn query-url
;;   "Given an id and secret, generate the query url"
;;   [id secret]
;;   (str query-str
;;        "appid="  id "&"
;;        "secret=" secret))

;; (defn access-token
;;   "Requests AccessToken from
;;   https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
;;   using id and secret from the config.clj"
;;   []
;;   (let [result
;;         (->> [c/app-id c/app-secret]
;;              (apply query-url)
;;              slurp
;;              json/read-str)
;;         access-token (result "access_token")]
;;     (if access-token
;;       access-token
;;       (throw (Throwable.
;;               (str \newline
;;                    "errcode: " (result "errcode") \newline
;;                    "errmsg: " (result "errmsg")))))))
