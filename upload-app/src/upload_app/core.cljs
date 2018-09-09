(ns upload-app.core
    (:require [reagent.core :as reagent :refer [atom]]
                   [baking-soda.bootstrap3 :as b3]    
    ))

(enable-console-print!)

(println "This text is printed from src/upload-app/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [b3/Button {:bs-style "primary"
                :bs-size  "large"
                :on-click #(js/alert "hi")}
      "Launch demo modal"]
   [:h3 "Edit this and watch it change!"]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
