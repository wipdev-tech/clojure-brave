(ns read-eval.core
  (:gen-class)
  (:require [clojure.math]))

(defn prob->odds
  [prob]
  (/ prob (- 1 prob)))

(def logit (comp clojure.math/log prob->odds))

(logit 0.6)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
