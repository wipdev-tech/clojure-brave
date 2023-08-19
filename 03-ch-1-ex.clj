(ns ch-1-ex.core
  (:gen-class))

(defn ex-1
  []
  (println (str 1 "hi" 'sym :keyw))
  (println (vector 1 "hi" 'sym :keyw))
  (println (list 1 "hi" 'sym :keyw))
  (println (hash-map 1 "hi" 1 'sym  1 :keyw))
  (println (hash-set 1 "hi" 1 'sym 1 :keyw)))

(defn ex-2
  []
  (let [add-100 #(+ 100 %)]
    (println (add-100 15))))

(defn ex-3
  []
  (let [dec-maker (fn [by] #(- % by))
        dec9 (dec-maker 9)]
    (println (dec9 10))))

(defn ex-4
  []
  (let [mapset #(set (map %1 %2))]
    (println (mapset inc [1 1 2 2]))))

(defn symmetrize-alien
  ([parts n-sides]
   (let [sides (map inc (range n-sides))]
     (reduce
      (fn [acc part]
        (into acc (map #(str part "-" %) sides)))
      []
      parts))))

(defn symmetrize-human
  ([parts]
   (let [sides ["left" "right"]]
     (reduce
      (fn [acc part]
        (into acc (map #(str % "-" part) sides)))
      []
      parts))))

(defn symmetrize
  [parts n-sides]
  (if (= n-sides 2)
    (symmetrize-human parts)
    (symmetrize-alien parts n-sides)))

(defn ex-5
  []
  (let [parts ["arm" "eye"]]
    (println (symmetrize-alien parts 5))))

(defn ex-6
  []
  (let [parts ["arm" "eye"]]
    (println (symmetrize parts 2))))

(defn -main
  "I don't do a whole lot ... yet."
  []
  (ex-1) (newline)
  (ex-2) (newline)
  (ex-3) (newline)
  (ex-4) (newline)
  (ex-5) (newline)
  (ex-6))
