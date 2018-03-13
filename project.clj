(defproject bootcamp3 "0.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]

                 ; Common libs:
                 [prismatic/schema "1.1.7"]
                 [prismatic/plumbing "0.5.5"]
                 [metosin/schema-tools "0.9.1"]
                 [metosin/spec-tools "0.5.1"]
                 [metosin/potpuri "0.5.1"]

                 ; Ring:
                 [ring/ring-core "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-jetty-adapter "1.6.3"]

                 ; Rest API:
                 [metosin/compojure-api "1.1.11"]
                 [metosin/ring-http-response "0.9.0"]

                 ; Swagger UI
                 [metosin/ring-swagger-ui "3.0.17"]

                 ; Rest API:
                 [metosin/compojure-api "2.0.0-alpha16"]
                 [metosin/ring-http-response "0.9.0"]
                 [metosin/muuntaja "0.4.1"]

                 ; jsonista
                 [metosin/jsonista "0.1.0"]

                 ; Database
                 [hikari-cp "2.0.0"]
                 [org.postgresql/postgresql "42.1.4"]
                 [org.clojure/java.jdbc "0.7.4"]
                 [org.flywaydb/flyway-core "5.0.2"]
                 [com.layerware/hugsql "0.4.8"]

                 ; HTTP and HTML
                 [clj-http "2.3.0"]
                 [hiccup "1.0.5"]
                 [enlive "1.1.6"]

                 ; Async:
                 [org.clojure/core.async "0.3.465"]

                 ; ClojureScript:
                 [org.clojure/clojurescript "1.9.456"]
                 [org.clojure/tools.reader "1.1.1"]
                 [prismatic/dommy "1.1.0"]

                 ; Logging:
                 [org.clojure/tools.logging "0.4.0"]
                 [org.slf4j/jcl-over-slf4j "1.7.25"]
                 [org.slf4j/jul-to-slf4j "1.7.25"]
                 [org.slf4j/log4j-over-slf4j "1.7.25"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]]

  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj" "test/cljc"]

  :java-source-paths ["src/java"]

  :plugins [#_[lein-virgil "0.1.6"]
            [metosin/boot-alt-test "0.4.0-20171121.142027-5"]]

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]
                   :resource-paths ["target/generated"]
                   :plugins [[lein-cljsbuild "1.1.7"]]}
             :uberjar {:main  bootcamp.main
                       :aot   [bootcamp.main]
                       :uberjar-name "bootcamp.jar"}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :compiler     {:main            "bootcamp.hello-cljs-world"
                                       :asset-path      "js/out"
                                       :output-to       "target/generated/public/js/bootcamp.js"
                                       :output-dir      "target/generated/public/js/out"
                                       :source-map      true
                                       :optimizations   :none
                                       :cache-analysis  true
                                       :pretty-print    true}}]})
