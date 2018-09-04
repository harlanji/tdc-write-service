(ns tdc-write-service.core
  (:require [bidi.ring :as bidi-r]
            [ring.util.response :as ring-res]
            [hiccup.page :refer [html5]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [clojure.string :as str]
            [clojure.java.io :as jio]))


; -- commands + model

(def filez-path "/filez")

(defn do-upload [request]
  (let [file (get (-> request :params) "file")
         temp-file (:tempfile file)
         dest-file (jio/as-file (str filez-path "/" (:filename file)))]
  (jio/copy temp-file dest-file)
  
  (ring-res/response "A posted file! :)")))



; -- routes

(defn upload-handler [request]
  (if (and (= :post (:request-method request))
            (contains? (:params request) "file"))
  (do-upload request)
  (ring-res/response "Not a POST with file= :(")))

(defn index-handler [request]
  (ring-res/response (html5 [:head [:meta {:charset "UTF-8"}]]
                            [:body [:p (str/join (keys (-> request :params)))]])))

(defn about-handler [request]
  (ring-res/response (if-let [author (-> request :route-params :id)]
    (str "Hello, " author "!")
    "Hello, stranger :)")))


; -- run

(defonce routes ["/" 
                 {"index.html" index-handler
                  "upload" upload-handler
                  ["about/" :id ".html"] about-handler}])

(defonce ring-handler (-> (bidi-r/make-handler routes)
                                         wrap-params
                                         wrap-multipart-params))