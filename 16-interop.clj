(System/getenv)                         ; System class
(System/getProperty "java.version")

(def date-literal #inst "2020-01-01")   ; Dates (TODO: search more in that topic)

(let [file (java.io.File. "/")]         ; I/O
  {:exists   (.exists file)
   :canWrite (.canWrite file)
   :path     (.getPath file)})

(spit "/tmp/hercules-todo.txt"
      "- kill lion brov
- chop up that nasty multi-headed snake thing")

(slurp "/tmp/hercules-todo.txt")

(let [s (java.io.StringWriter.)]
  (spit s "what is this?")
  (.toString s))

(let [s (java.io.StringReader. "whatevs")]
  (slurp s))

(with-open [todo-rdr (clojure.java.io/reader "/tmp/hercules-todo.txt")]
  (vec (line-seq todo-rdr)))
