(ns tdc-write-service.rules
  (:require [ring.util.response :as ring-res]
            [tdc-write-service.support-rules :as support-rules]
            [clara.rules :as clara]))

(defn run-rules-handler [request]
  (->
   (clara/mk-session 'tdc-write-service.support-rules)
   (clara/insert (support-rules/->ClientRepresentative "Alice" "Acme")
                 (support-rules/->SupportRequest "Acme" :high))
   (clara/fire-rules))
  (ring-res/response "Ran the support ruules"))

; -- commands + model

(defonce routes {"run-rules" run-rules-handler})