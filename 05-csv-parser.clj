(ns csv-parser.core
  (:gen-class)
  (:require clojure.string))

(defn split-file
  "Takes a filename, returns a sequence of lines."
  [filename]
  (clojure.string/split (slurp filename) #"\n"))

(defn split-lines
  "Takes a sequence of lines, splits each line with comma delimiters."
  [lines]
  (map #(clojure.string/split % #",") lines))

(defn mapify
  "Takes a sequence of vectors, creates a map from each vector."
  [rows]
  (reduce (fn [maps [name index]]
            (conj maps {:name name
                        :glitter-index (Integer. index)}))
          []
          rows))

(def read-csv (comp mapify split-lines split-file))

(defn glitter-filter
  [min-glitter vamps]
  (map :name
       (filter #(>= (:glitter-index %) min-glitter) vamps)))

(defn validate-map
  [keys map]
  (let [not-nil? (complement nil?)]
    (and (= (count keys) (count map))
         (every? (fn [key] (not-nil? (key map))) keys))))

(defn append
  [vamps new-vamp]
  (if (validate-map [:name :glitter-index] new-vamp)
    (conj vamps new-vamp)
    vamps))

(defn stringify-vamp
  [vamp]
  (clojure.string/join "," [(:name vamp) (:glitter-index vamp)]))

(defn write-csv
  [vamps filename]
  (spit filename
        (reduce
         (fn [string vamp]
           (str string (stringify-vamp vamp) "\n"))
         ""
         vamps)))

(defn -main
  "I don't do a whole lot ... yet."
  []
  (let [new-vamps (append (read-csv "data.csv")
                          {:name "hamada" :glitter-index 0})]
    (write-csv new-vamps "ay7aga.csv")))
