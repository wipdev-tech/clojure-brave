(ns funprog.core
  (:gen-class))

(all-ns)

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int #(-> % :attributes :intelligence))

(c-int character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(def spell-slots-2 (comp int inc #(/ % 2) c-int))

(defn spell-slots-3
  [c]
  (-> c
      c-int
      (#(/ % 2))
      inc
      int))

(= (spell-slots character)
   (spell-slots-2 character)
   (spell-slots-3 character))

(defn sleep-identity
  [x]
  (Thread/sleep 1000)
  x)

(sleep-identity 1)

(def mem-sleep-identity (memoize sleep-identity))
(mem-sleep-identity 2)

(defn -main
  "I don't do a whole lot ... yet."
  []
  (println "Hello, World!"))
