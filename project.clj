(defproject tdc-write-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring "1.6.3"]
                 [bidi "2.1.3"]
                 [hiccup "1.0.5"]
                 [com.cerner/clara-rules "0.18.0"]
                 [org.clojure/core.async  "0.4.474"]
                 [com.cognitect/transit-clj "0.8.313"]
                 ]

  :plugins [[lein-ring "0.12.4"]
            [lein-cljfmt "0.6.0"]]

  :ring {:handler tdc-write-service.core/ring-handler}

)
