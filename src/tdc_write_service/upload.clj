(ns tdc-write-service.upload
  (:require [ring.util.response :as ring-res]
            [hiccup.page :refer [html5]]
            [ring.middleware.file :refer [file-request]]
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
  (if (contains? (:params request) "file")
    (do-upload request)
    (ring-res/response "Not a POST with file= :(")))
; -- run

(defn upload-app-handler [request]
  (let [rewrite-uri #(str/replace % #"^/upload(.*)" "$1")
        app-resource-request (update request :uri rewrite-uri)]
    (or
     (file-request app-resource-request filez-path)
     (file-request app-resource-request "upload-app/resources/public"))))

(defonce routes {#"upload.*" {:post upload-handler
                              :get upload-app-handler}})
