(ns hobbit-smacker.core
  (:gen-class))

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
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrizer
  [parts]
  (loop [parts-to-symmetrize parts
         symmetrized []]
    (if (empty? parts-to-symmetrize)
      symmetrized
      (let [[part & remaining] parts-to-symmetrize]
        (recur remaining
               (into symmetrized (set [part (matching-part part)])))))))

(defn symmetrizer-2
  [parts]
  (reduce
   (fn [acc part] (into acc (set [part (matching-part part)])))
   []
   parts))

(defn -main
  "I don't do a whole lot ... yet."
  []
  (println (symmetrizer asym-hobbit-body-parts))
  (println (symmetrizer-2 asym-hobbit-body-parts))
  (println (= (symmetrizer-2 asym-hobbit-body-parts)
              (symmetrizer asym-hobbit-body-parts))))

(-main)
