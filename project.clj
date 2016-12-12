(defproject bootcamp3 "0.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]

                 ; Common libs:
                 [prismatic/schema "1.1.3"]
                 [prismatic/plumbing "0.5.3"]
                 [metosin/potpuri "0.4.0"]

                 ; Ring:
                 [ring/ring "1.5.0" :exclusions [org.clojure/tools.reader clj-time commons-codec]]
                 [ring/ring-defaults "0.2.1"]

                 ; Rest API:
                 [metosin/compojure-api "1.1.9"]
                 [metosin/ring-http-response "0.8.0"]

                 ; Swagger UI
                 [metosin/ring-swagger-ui "2.2.5-0"]

                 ; HTTP and HTML
                 [clj-http "3.4.1"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.6"]

                 ; Async:
                 [org.clojure/core.async "0.2.395"]

                 ; ClojureScript:
                 [org.clojure/clojurescript "1.9.293"]
                 [prismatic/dommy "1.1.0"]]

  :source-paths ["src" "cljs-src"]

  :profiles {:dev {:dependencies [[flare "0.2.9"]
                                  [ring-mock "0.1.5"]]
                   :resource-paths ["target/generated"]
                   :injections [(require 'flare.clojure-test)
                                (flare.clojure-test/install!)]
                   :plugins [[lein-cljsbuild "1.1.5"]]}
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
