(ns weixin.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [clojure.java.io :as io]
            [weixin.auth :refer [authenticate]]
            [weixin.event-log :refer [log-form]]
            [weixin.receive :refer [receive]]
            [weixin.request :refer [set-default-menu]]))

;; (println "set default menu")
;; (println (set-default-menu))

(defroutes app-routes
  (GET "/" [] (io/file "resources/htmls/form.html"))
  (POST "/submit" {form-params :form-params} (log-form form-params) "提交成功")
  (GET "/weixin" {params :params} (authenticate params))
  (POST "/weixin" req (receive req))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
