(ns seq-fns.core
  (:gen-class))

(defn inc-map-values
  [m]
  (reduce (fn [new-map [k v]] (assoc new-map k (inc v)))
          {}
          m))


  (defn mymap
    [f input-seq]
    (reduce (fn [new-seq value]
              (conj new-seq (f value)))
            []
            (seq input-seq)))

  (mymap dec '(1 2 3 -20))


(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(defn -main
  "I don't do a whole lot ... yet."
  []
  (println (inc-map-values {:a 2 :b 11 :c -3}))
  (println (mymap #(+ 3 %) [2 11 -3]))
  (println (= (filter #(< (:month %) 3) food-journal)
              (take-while #(< (:month %) 3) food-journal))))
