(ns tdc-write-service.support-rules
  (:require [clara.rules :as clara]))

(defrecord SupportRequest [client level])

(defrecord ClientRepresentative [name client])

(clara/defrule is-important
  "Find important support requests."
  [SupportRequest (= :high level)]
  =>
  (println "High support requested!"))

(clara/defrule notify-client-rep
  "Find the client representative and request support."
  [SupportRequest (= ?client client)]
  [ClientRepresentative (= ?client client) (= ?name name)]
  =>
  (println "Notify" ?name "that"
           ?client "has a new support request!"))
