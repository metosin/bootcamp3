(ns bootcamp.compojure
  (:require [compojure.api.sweet :refer [defapi context GET* POST*] :as c]
            [ring.util.http-response :refer [ok] :as resp]
            [bootcamp.jetty-server :as jetty]
            [schema.core :as s]))

;;
;; REST API for storing user messages.
;;

; Schema for message:
(def Message {:sender  s/Str
              :message s/Str})

; Storage for our messages:
(def db (atom []))

; Ring handler:
(defapi handler
  (c/swagger-ui)
  (c/swagger-docs :title "Bootcamp sample application")
  (c/swaggered "bootcamp" :description "Bootcamp API"
    (context "/api" []
      (GET* "/" []
        :return [Message]
        (ok @db))
      (GET* "/:sender" []
        :path-params [sender :- s/Str]
        :return [Message]
        (ok (filter (fn [m] (= (:sender m) sender)) @db)))
      (POST* "/" []
        :body [message Message]
        :return [Message]
        (ok (swap! db conj message))))))

(defn start-server []
  (jetty/start-server #'handler))
