(ns upload-app.core
  (:require-macros [cljs.core.async.macros :as async-m])
  (:require [reagent.core :as reagent :refer [atom]]
            [baking-soda.bootstrap3 :as bootstrap3]
            [cljs-http.client :as http]
            [cljs.core.async :as async]
            [cognitect.transit :as transit]
            [clara.rules :as clara]
            [tdc-write-service.support-rules :as support-rules]))

(enable-console-print!)

(println "This text is printed from src/upload-app/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(def json-reader (transit/reader :json))

(defn ping []
  (async-m/go
    (let [response (async/<! (http/post "/events"))])))

(defn clear []
  (async-m/go
    (let [response (async/<! (http/delete "/events"))])))

(defn get-events []
  (async-m/go
    (let [response (async/<! (http/get "/events"))]
      (js/alert (transit/read json-reader (:body response))))))


(clara/defsession support-rules 'tdc-write-service.support-rules)

(defn run-support-rules []
  (-> support-rules
   (clara/insert (support-rules/->ClientRepresentative "Alice" "Acme")
                 (support-rules/->SupportRequest "Acme" :high))
   (clara/fire-rules))
  (js/alert "Ran the support ruules"))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [bootstrap3/Button {:bs-style "primary"
                       :bs-size  "large"
                       :on-click #(ping)}
    "Ping"]
   [bootstrap3/Button {:bs-style "secondary"
                       :bs-size  "large"
                       :on-click #(get-events)}
    "Get Events"]
   [bootstrap3/Button {:bs-style "secondary"
                       :bs-size  "large"
                       :on-click #(clear)}
    "Clear"]
   [:h3 "Edit this and watch it change!"]
   
	[bootstrap3/Button {:on-click #(run-support-rules)} "Run rules"]   
   ])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
