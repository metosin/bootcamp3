(ns bootcamp.random-users
  (:require [clj-http.client :as client]
            [clojure.string :as str]))

(require '[clj-http.client :as client])

;first 5 female users, list name, surname, location, something like:
; {:full-name "titta lotta"
;  :location {:street "7879 3rd st",
;             :city "sherbrooke",
;             :state "british columbia",
;             :postcode 60602}}

(defn female? [user] (= "female" (:gender user)))

(defn transform [{:keys [location name]}]
  {:location location
   :full-name (str/join " " [(:first name)
                             (:last name)])})

;;
;; v1: with let
;;

(let [response (client/get "https://randomuser.me/api?results=2" {:as :json})
      users (get-in response [:body :results])
      females (filter female? users)]
  (map transform females))

;;
;; v2: with functions
;;

(map
  transform
  (filter
    female?
    (get-in
      (client/get
        "https://randomuser.me/api?results=2"
        {:as :json})
      [:body :results])))

;;
;; v3: threading
;;

(defn get-some-females [q]
  (as-> (client/get
          "https://randomuser.me/api"
          {:as :json, :query-params {:results q}}) $
        (get-in $ [:body :results])
        (filter female? $)
        (map transform $)))

(get-some-females 10)
