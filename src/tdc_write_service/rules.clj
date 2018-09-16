(ns tdc-write-service.rules
  (:require [ring.util.response :as ring-res]
            [tdc-write-service.support-rules :as support-rules]
            [clara.rules :as clara]))

(defn run-rules-handler [request]
  (let [support-rules (clara/mk-session 'tdc-write-service.support-rules)]
   (support-rules/run-alice-example support-rules))
  (ring-res/response "Ran the support ruules"))

; -- commands + model

(defonce routes {"run-rules" run-rules-handler})