(ns weixin.core.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [weixin.receive :as receive]))

(def event-subscribe "<xml>
  <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[FromUser]]></FromUserName>
  <CreateTime>123456789</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[subscribe]]></Event>
</xml>")


(deftest test-receive-event-subscribe  
  (let [request (mock/request :post "/weixin" event-subscribe)
        out (receive/receive request)]
      (is (= "thanks" out))))
