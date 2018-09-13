(ns tdc-write-service.rules
  (:require [ring.util.response :as ring-res]
            [clara.rules :as clara]))
            

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

(defn run-rules-handler [request]
  (-> 
    (clara/mk-session 'tdc-write-service.core)
    (clara/insert (->ClientRepresentative "Alice" "Acme")
                  (->SupportRequest "Acme" :high))
    (clara/fire-rules))
    (ring-res/response "Ran the ruules"))

; -- commands + model

(defonce routes {"run-rules" run-rules-handler})