(ns tdc-write-service.core
  (:require [bidi.ring :as bidi-r]
            [ring.util.response :as ring-res]
            [hiccup.page :refer [html5]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [clojure.string :as str]
            [tdc-write-service.rules :as rules]
            [tdc-write-service.upload :as upload]))


; -- routes


(defn index-handler [request]
  (let [response-body (html5 [:head
                              [:meta {:charset "UTF-8"}]]
                             [:body
                              [:p (str/join (keys (-> request :params)))]])]
    (ring-res/response response-body)))

(defn about-handler [request]
  (let [message (if-let [author (-> request :route-params :id)]
                  (str "Hello, " author "!")
                  "Hello, stranger :)")]
    (ring-res/response message)))

; -- run

(defonce routes ["/"
                 (merge rules/routes
                   upload/routes
                   {"index.html" index-handler
                    ["about/" :id ".html"] about-handler})])

(defonce ring-handler (-> (bidi-r/make-handler routes)

                          wrap-params
                          wrap-multipart-params))
