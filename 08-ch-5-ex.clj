(ns ch-5-ex.core)

;; Ex 1: attr
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defn attr
  [attr-keyword]
  (comp attr-keyword :attributes))

((attr :intelligence) character)
((attr :dexterity) character)

;; Ex 2: comp
(defn my-comp
  [& fns]
  (reduce (fn [comp-fn next-fn]
            #(next-fn (comp-fn %)))
          identity
          (reverse fns)))

((my-comp / inc) 1)

;; Ex 3: assoc-in
(defn my-assoc-in
  [map [k & ks] val]
  (if (empty? ks)
    (assoc map k val)
    (assoc map k (my-assoc-in (k map) ks val))))

(my-assoc-in {:a 1 :b {:c 2}} [:b :c] 15)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
