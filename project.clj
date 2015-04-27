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

                 ; HTML
                 [hiccup "1.0.5"]

                 ; Async:
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]

                 ; ClojureScript:
                 [org.clojure/tools.reader "0.9.1"]
                 [org.clojure/clojurescript "0.0-3208"]]

  :source-paths ["src" "cljs-src"]

  :profiles {:dev {:dependencies [[flare "0.2.8"]
                                  [ring-mock "0.1.5"]]
                   :injections [(require 'flare.clojure-test)
                                (flare.clojure-test/install!)]
                   :plugins [[lein2-eclipse "2.0.0" :exclusions [org.clojure/clojure]]
                             [lein-cljsbuild "1.0.5"]]}
             :uberjar {:main  bootcamp.main
                       :aot   [bootcamp.main]
                       :uberjar-name "bootcamp.jar"}}

  :cljsbuild {:builds [{:id "node"
                        :source-paths ["cljs-src"]
                        :compiler     {:output-to     "target/hello_world.js"
                                       :target        :nodejs
                                       :optimizations :simple}}]})
