(ns bootcamp.map-reduce-filter
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [bootcamp.data.books :as b]))

;;
;; Map, reduce and filter:
;;
;; Common functional programming involves:
;;   filter  - When you wan't to limit the elemets from seq
;;   map     - When you wan't to apply convert each element in some way
;;   reduce  - When you wan't to reduce a seq to a value
;;

;
; Filter (and remove):
; --------------------
;

(filter odd? [1 2 3 4])                                     ;=> (1 3)
(remove odd? [1 2 3 4])                                     ;=> (2 4)

; Excercise:
; ----------
;
; Take a look at the books in bootcamp.data.books namespace.

(type b/books)                                              ;=> clojure.lang.PersistentVector
(vector? b/books)                                           ;=> true
(count b/books)                                             ;=> 6

; Find out how many books are about clojure?



;
; Map:
; ----
;

; Take a function and apply it to each value in a seq

(inc 41)                                                    ;=> 42
(inc 1336)                                                  ;=> 1337

(map inc [41 1336])                                         ;=> (42 1337)

; map can take more than one seq too

(map + [41 1237] [1 100])                                   ;=> (42 1337)

; The above is:
;   take 41 and 1, apply them to +
;   take 1237 and 100, apply them to +
; In ither words:
;   (cons (+ 41 1) (cons (+ 1237 100)))

(subs "foobar" 3)                                           ;=> "bar"

(map subs
     ["foobar" "hello, world" "programming"]
     [3 7 8])                                               ;=> ("bar" "world" "ing")

; Excercise:
; ----------
;
; Continue with the books about clojure. This time, produce a seq
; of page counts from all the books that are about clojure:




;
; Reduce:
; -------
;

(reduce + 10 [1 2 3 4])                                      ;=> 20
; (+ 10 1)   => 11
; (+ 11 2)   => 13
; (+ 13 3)   => 16
; (+ 16 4)   => 20

(reduce + [1 2 3 4])                                        ;=> 10
; (+ 1 2)    => 3
; (+ 3 3)    => 6
; (+ 6 4)    => 10

(reduce (fn [acc value] (str acc ", " value))
        ["java" "python" "clojure"])                        ;=> "java, python, clojure"


; Excercise:
; ----------
;
; Continue with the previous example where we got the seq of number of pages.
; Now answer to questions: how many pages we have about clojure in total?


