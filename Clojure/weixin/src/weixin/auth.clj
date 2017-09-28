(ns weixin.auth
  (:require [weixin.config :as c]))

(defn check-signature [{signature :signature 
                        timestamp :timestamp nonce :nonce}]
  (let [src-str (apply str (sort [c/token timestamp nonce]))
        sha1    (java.security.MessageDigest/getInstance "SHA-1")
        digest  (.digest sha1 (.getBytes src-str))
        hex-digest (apply str (map #(format "%02x" (bit-and % 0xff)) digest))]
    (= signature hex-digest)))

(defn authenticate [{echostr :echostr :as params}]
  (if (check-signature params)
    echostr
    "authentication failed"))
