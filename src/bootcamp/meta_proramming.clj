(ns bootcamp.meta-proramming
  (:require [clojure.test :refer :all]
            [bootcamp.data.books :as b]))

;
; Meta programming:
;

(defmacro infix->prefix [a op b]
  (list op a b))

(macroexpand-1 '(infix->prefix 5 + 6))                      ;=> (+ 5 6)

(infix->prefix 5 + 6)                                       ;=> 11

;
; Regular function evaluation order:
;

(+ (* 4 10) (inc 1))                                        ;=> 42

; Evaluated:
; (+ (* 4 10) (inc 1))
;    \------/
;       |
;       |
; (+    40    (inc 1))
;             \-----/
;                |
;                |
; (+    40       2   )
; \------------------/
;          |
;          |
;          42
;

; Macros are functions that are evaluated while "goind down" and
; thet get the s-expressions as arguments:

(infix->prefix (* 4 10) + (inc 1))                          ;=> 42

; Evaluated:
; (infix->prefix (* 4 10) + (inc 1))
;
; call infix-prefix:
;    a  =  (* 4 10)
;    op =  +
;    b  =  (inc 1)
;
; (+ (* 4 10) (inc 1))
;
; Now evaluation proceeds as before:

(+ (infix->prefix 4 * 10) (inc 1))                          ;=> 42

; (+ (infix->prefix 4 * 10) (inc 1))
; (+ \--------------------/ (inc 1))
;              |
;              |
; (+        (* 4 10)        (inc 1))
; (+        \------/        (inc 1))
;              |
;              |
; (+           40           (inc 1))
; (+           40           \-----/)
;                              |
;                              |
; (+           40              2   )
; \--------------------------------/
;               |
;               |
;               42

;;
;; Common macros:
;;

; defn

(macroexpand-1 '(defn foo [x] (+ x 5)))
;=> (def foo (clojure.core/fn ([x] (+ x 5))))

; Threading macros: -> and ->>

(reduce + (map :pages (filter (comp :clojure :langs) b/books)))         ;=> 924

; Joda talk: "Strong is Vader. Mind what you have learned. Save you it can."

; Use threading macros to translate Joda talk to human talk:

(->> b/books (filter (comp :clojure :langs)) (map :pages) (reduce +))   ;=> 924

(->> b/books
     (filter (comp :clojure :langs))
     (map :pages)
     (reduce +))                                            ;=> 924

; How the work:

(macroexpand-1 '(->  X (f1) (f2)))                          ;=> (f2 (f1 X))
(macroexpand-1 '(->> X (f1) (f2)))                          ;=> (f2 (f1 X))

(macroexpand-1 '(->  X (f1 a) (f2 b c)))                    ;=> (f2 (f1 X a) b c)
(macroexpand-1 '(->> X (f1 a) (f2 b c)))                    ;=> (f2 b c (f1 a X))

; Rule of thumb:
; Working with a collection, use ->
; Working with a seq, use ->>

(-> {:title  "Murach's Mainframe COBOL"
     :langs  #{}
     :read?  false}
    (assoc :pages 687)
    (update-in [:langs] conj :cobol)
    (dissoc :read?))
;=> {:title "Murach's Mainframe COBOL", :langs #{:cobol}, :pages 687}

(->> (range 10)
     (filter odd?)
     (map (partial * 2))
     (reduce +))
;=> 50

;
; some-> and some->>
;

(macroexpand-1 '(some-> X f1 f1))
;=> (clojure.core/let [G__29557 X
;                      G__29557 (if (clojure.core/nil? G__29557)
;                                  nil
;                                  (clojure.core/-> G__29557 f1))
;                      G__29557 (if (clojure.core/nil? G__29557)
;                                  nil
;                                 (clojure.core/-> G__29557 f1))]
;    G__29557)

(->> b/books
     (filter (comp :clojure :langs))
     (first)
     :title
     (.toUpperCase))
;=> "THE JOY OF CLOJURE"

(try
  (->> b/books
       (filter (comp :cobol :langs))
       (first)
       :title
       (.toUpperCase))
  (catch Exception e
    (str e)))
;=> "java.lang.NullPointerException"

(some->> b/books
         (filter (comp :cobol :langs))
         (first)
         :title
         (.toUpperCase))
;=> nil

