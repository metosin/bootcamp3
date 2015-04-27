(defproject bootcamp3 "0.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0-beta1"]

                 ; Common libs:
                 [prismatic/schema "0.4.0"]
                 [prismatic/plumbing "0.4.2"]
                 [metosin/potpuri "0.2.2"]

                 ; Ring:
                 [ring/ring "1.3.2" :exclusions [org.clojure/tools.reader clj-time commons-codec]]
                 [ring/ring-defaults "0.1.4"]

                 ; Rest API:
                 [metosin/compojure-api "0.19.3"]
                 [metosin/ring-http-response "0.6.1" :exclusions [ring/ring-core]]

                 ; Swagger UI
                 [metosin/ring-swagger-ui "2.1.0-M2-2"]

                 ; HTTP and HTML
                 [clj-http "1.1.1"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.5"]

                 ; Async:
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]

                 ; ClojureScript:
                 [org.clojure/tools.reader "0.9.2"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [prismatic/dommy "1.0.0"]]

  :source-paths ["src" "cljs-src"]

  :profiles {:dev {:dependencies [[flare "0.2.8"]
                                  [ring-mock "0.1.5"]]
                   :resource-paths ["target/generated"]
                   :injections [(require 'flare.clojure-test)
                                (flare.clojure-test/install!)]
                   :plugins [[lein2-eclipse "2.0.0" :exclusions [org.clojure/clojure]]
                             [lein-cljsbuild "1.0.5"]]}
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
