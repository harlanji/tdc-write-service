(ns tdc-write-service.index
  (:require [ring.util.response :as ring-res]
            [hiccup.page :refer [html5]]
            [clojure.string :as str]))


; -- routes


(defn index-handler [request]
  (let [response-body (html5 [:head
                              [:meta {:charset "UTF-8"}]]
                             [:body
                              [:p (str/join (keys (-> request :params)))]])]
    (ring-res/response response-body)))

(defonce routes {"index.html" index-handler})