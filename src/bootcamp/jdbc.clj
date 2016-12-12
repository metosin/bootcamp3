(ns bootcamp.jdbc
  (:require [clojure.java.jdbc :as j]))

(def db
  {:dbtype "mysql"
   :dbname "kikka"
   :user "root"})

(comment

  (dotimes [n 100]
    (j/insert! db "kikka.kikka"
               {:name (str "a" n)
                :size (rand-int 100)
                :description "random"}))

  (defn find-kikkas [db n]
    (j/query
      db
      ["select * from kikka.kikka limit ?" n]))

  (find-kikkas db 10))
