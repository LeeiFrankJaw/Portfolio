(ns weixin.request-specs
  (:require [clojure.test :refer :all]
            [weixin.request :as request]
            [weixin.config :as config]))

(deftest test-request-token
  (testing "success got an token"
    (let [ret (request/request-token config/app-id config/app-secret)]
      (is (not-empty ret))
      (is (= "ACCESS_TOKEN" (ret "access_token"))))))

(deftest test-upload-media
  (testing "upload a media file"
    (let [access-token (request/request-token)
          ret (request/upload-media access-token "image" config/app-secret)]
      (is (not-empty ret)))))

;;(deftest test-download-media
;;  (testing "download a media file"
;;    (let [ret (request/download-media

