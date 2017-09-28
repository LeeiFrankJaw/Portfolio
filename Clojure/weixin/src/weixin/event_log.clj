(ns weixin.event-log
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.gridfs :as gfs :refer [store-file make-input-file filename content-type]]
            ;; [clojure.pprint :refer [pprint]]
            )
  (:import org.bson.types.ObjectId))

;; the ObjectId isn't used as per boss's request.

(defn- log*
  "log any into database named dbname and returns nil."
  [action coll query any dbname options]
  (let [conn  	(mg/connect)
        db    	(mg/get-db conn dbname)]

    (cond (= action :insert)
          (mc/insert db coll any)
          
          (= action :update)
          (mc/update db coll query any options)

          (= action :file)
          (let [fs (mg/get-gridfs conn dbname)
                {bytes :bytes name :name contype :contype} options]
            (store-file (make-input-file fs bytes)
                        (filename name)
                        (content-type contype)))

          :else
          (println "Invalid action"))

    (mg/disconnect conn)))

(defn- log
  [action coll query any options]
  (log* action coll query any "weixin" options))

(defn- log-insert
  "Insert any into database dbname."
  [coll any]
  (log :insert coll nil any nil))

(defn- log-update
  "update the query result in database dbname to any with options."
  ([coll query any options]
   (log :update coll query any options))

  ([coll query any]
   (log :update coll query any nil)))

(defn- log-upsert
  "upsert the query result in database dbname to any"
  [coll query any]
  (log-update coll query any {:upsert true}))

(defn log-request [req]
  (log-insert "requests" req))

(defn log-response [resp]
  (log-insert "responses" resp))

(defn log-user [user openid]
  (log-upsert "users" {:openid openid} user))

(defn log-file [bytes name contype]
  (log :file nil nil nil {:bytes bytes
                          :name name
                          :contype contype}))

(defn log-form [form-params]
  (log-insert "forms" form-params))

(defn log-error [error]
  (log-insert "errors" error))

(defn log-media [media filename]
  (log-insert "medias" (merge media {:filename filename})))

(defn log-news [news]
  (log-insert "news" news))

(defn log-video [video]
  (log-insert "videos" video))

(defn log-mp [mp]
  (log-insert "mps" mp))
