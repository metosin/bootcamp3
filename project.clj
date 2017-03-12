(defproject bootcamp3 "0.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]

                 ; Common libs:
                 [prismatic/schema "1.1.3"]
                 [prismatic/plumbing "0.5.3"]
                 [metosin/potpuri "0.4.0"]

                 ; Ring:
                 [ring/ring-core "1.5.1"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-jetty-adapter "1.5.1"]

                 ; Rest API:
                 [metosin/compojure-api "1.1.10"]
                 [metosin/ring-http-response "0.8.2"]

                 ; Swagger UI
                 [metosin/ring-swagger-ui "2.2.8"]

                 ; HTTP and HTML
                 [clj-http "3.4.1"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.6"]

                 ; Async:
                 [org.clojure/core.async "0.3.441"]

                 ; ClojureScript:
                 [org.clojure/clojurescript "1.9.456"]
                 [org.clojure/tools.reader "1.0.0-beta4"]
                 [prismatic/dommy "1.1.0"]

                 ; Logging:
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/jcl-over-slf4j "1.7.24"]
                 [org.slf4j/jul-to-slf4j "1.7.24"]
                 [org.slf4j/log4j-over-slf4j "1.7.24"]
                 [ch.qos.logback/logback-classic "1.2.1" :exclusions [org.slf4j/slf4j-api]]]

  :source-paths ["src" "cljs-src"]

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]
                   :resource-paths ["target/generated"]
                   :plugins [[lein-cljsbuild "1.0.5"]]}
             :uberjar {:main  bootcamp.main
                       :aot   [bootcamp.main]
                       :uberjar-name "bootcamp.jar"}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["cljs-src"]
                        :compiler     {:main            "bootcamp.hello-cljs-world"
                                       :asset-path      "js/out"
                                       :output-to       "target/generated/public/js/bootcamp.js"
                                       :output-dir      "target/generated/public/js/out"
                                       :source-map      true
                                       :optimizations   :none
                                       :cache-analysis  true
                                       :pretty-print    true}}]})
