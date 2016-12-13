(ns bootcamp.multi)

(defmulti attack :type)
(defmulti eat :type)

(def miu {:type :cat
          :name "miu"})

(def buzz {:type :dog
           :name "buzz"})

;;
;; add dispatch-methods for the attack
;;

(defmethod attack :cat [{:keys [name]}]
  (str name " claws"))

(defmethod attack :dog [{:keys [name]}]
  (str name " doesn't attack"))

(defmethod attack :default [{:keys [name] :or {name "UNKNOWN"}}]
  (str name " giggles"))

(attack miu)
; "miu claws"

(attack buzz)
; "buzz doesn't attack"

(attack {:type :moose})
; "UNKNOWN giggles"
