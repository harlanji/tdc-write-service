(ns tdc-write-service.about
  (:require [ring.util.response :as ring-res]))


; -- routes


(defn about-handler [request]
  (let [message (if-let [author (-> request :route-params :id)]
                  (str "Hello, " author "!")
                  "Hello, stranger :)")]
    (ring-res/response message)))

; -- run

(defonce routes {["about/" :id ".html"] about-handler})