(ns bootcamp.ring
  (:require [clojure.string :as string]
            [ring.util.http-response :refer [ok] :as resp]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [schema.core :as s]
            [bootcamp.jetty-server :as jetty]))

(defn handler [request]
  (ok "Hello, world!!"))

; wrap-defaults: https://github.com/ring-clojure/ring-defaults
; wrap-restful-format: https://github.com/metosin/ring-middleware-format

(comment
  (jetty/start-server #'handler))

(comment
  (jetty/stop-server))
