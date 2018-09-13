(ns tdc-write-service.core
  (:require [bidi.ring :as bidi-r]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [tdc-write-service.index :as index]
            [tdc-write-service.about :as about]
            [tdc-write-service.rules :as rules]
            [tdc-write-service.upload :as upload]))

(defonce routes ["/"
                 (merge rules/routes
                   upload/routes
                   index/routes
                   about/routes)])

(defonce ring-handler (-> (bidi-r/make-handler routes)

                          wrap-params
                          wrap-multipart-params))
