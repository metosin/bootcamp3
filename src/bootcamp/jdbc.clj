(ns bootcamp.jdbc
  (:require [clojure.java.jdbc :as j]))

(def db
  {:dbtype "mysql"
   :dbname "kikka"
   :user "root"})

