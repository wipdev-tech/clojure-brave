(ns clojure-noob.core
  (:gen-class))

; Arity overloading
; Function body depends on the number of arguments (ie, arity)
; (defn x-chop
;   "Describe chop"
;   ([name chop-type]
;    (str "I " chop-type " chop " name "! Take that!"))
;   ([name]
;    (x-chop name "karate")))

; (defn basic-stuff
;   []
;   ; Basic stuff
;   (println (+ 1 2 3) "\n")
;   (println (str "I_" "AM_" "ASURGEON") "\n")
;   (if (> 1 2)
;     (println "1 is larger than 2")
;     (println "1 is not larger than 2 ._." "\n"))
;
;   ; When
;   (when 0
;     (println "'when' works like 'if' but with only true condition"
;              "\n"
;              "Only falsy values are `false` and `nil`"
;              "\n"))
;
;   ; Maps
;   ; The cool feature of using :a as function works only for keywords
;   (println (= (get {:a 1 :b 2} :a)
;               (:a {:a 1 :b 2}))
;            "\n")
;
;   ; All these expressions print 6
;   (println
;    ((or + -) 1 2 3)
;    ((and (> 4 3) +) 1 2 3)
;    ((first [+ - 0]) 1 2 3)
;    "\n")
;
;   (println (x-chop "Kanye" "Slap"))
;   (println (x-chop "West")) "\n")

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) "^left-" "right-")
   :size (:size part)})

(defn -main
  "I don't do a whole lot ... yet."
  []
  ; (basic-stuff)
  (def asym-hobbit-body-parts [{:name "head" :size 3}
                               {:name "left-eye" :size 1}
                               {:name "left-ear" :size 1}
                               {:name "mouth" :size 1}
                               {:name "nose" :size 1}
                               {:name "neck" :size 2}
                               {:name "left-shoulder" :size 3}
                               {:name "left-upper-arm" :size 3}
                               {:name "chest" :size 10}
                               {:name "back" :size 10}
                               {:name "left-forearm" :size 3}
                               {:name "abdomen" :size 6}
                               {:name "left-kidney" :size 1}
                               {:name "left-hand" :size 2}
                               {:name "left-knee" :size 2}
                               {:name "left-thigh" :size 4}
                               {:name "left-lower-leg" :size 3}
                               {:name "left-achilles" :size 1}
                               {:name "left-foot" :size 2}]))
