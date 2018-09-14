(ns tdc-write-service.events
  (:require [cognitect.transit :as transit])
  (:import [java.io ByteArrayInputStream ByteArrayOutputStream]))

(defonce events (atom []))

(defn read-events [request]
  (let [out (ByteArrayOutputStream. 4096)
        writer (transit/writer out :json)]
    (transit/write writer {:events @events})
    {:header {"Content-Type" "application/transit+json"}
     :body (.toString out)}))

(defn write-event [request]
  (swap! events conj [:ping (count @events)]))

(defn clear-events [request]
  (reset! events []))

(def routes {"events" {:get read-events
                       :post write-event
                       :delete clear-events}})