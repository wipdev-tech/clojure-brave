(require '[clojure.core.async :as a])

(def echo-chan (a/chan))      ;; create channel
(a/go (prn (a/<! echo-chan))) ;; process that waits to `take` a val from channel
(a/>!! echo-chan "ketchup")   ;; `put` val to channel

(def buf-chan (a/chan 2))
(a/>!! buf-chan "ketchup")    ;; will run 2 times but blocks forever at 3rd

(def slide-buf-chan           ;; sliding buffers prevent infinite blocking
  (a/chan (a/sliding-buffer 2)))
(a/>!! slide-buf-chan "ketchup")

(def hi-chan (a/chan (a/sliding-buffer 2)))
(doseq [n (range 1000)]
  (a/go (a/>! hi-chan (str "hi " n))))
(a/<! hi-chan)

;; <!! is a blocking put - blocks the thread before running another process
;; <!  is a parking put - "parks" a process to start another

(defn hot-dog-machine []
  ;; creates two channels/processes for putting in something
  ;; and returning a hot dog
  (let [in (a/chan)
        out (a/chan)]
    (a/go (a/<! in)
          (a/>! out "hot dog"))
    [in out]))

;; only runs once and shuts down
(let [[in out] (hot-dog-machine)]
  (a/>!! in "pocket lint")
  (a/<!! out))

(defn hot-dog-machine-v2 [hot-dog-count]
  (let [in (a/chan)
        out (a/chan)]
    (a/go (loop [hc hot-dog-count]
            (if (> hc 0)
              (let [input (a/<! in)]
                (if (= 3 input)
                  (do (a/>! out "hot dog")
                      (recur (dec hc)))
                  (do (a/>! out "wilted lettuce")
                      (recur hc))))
              (do (a/close! in)
                  (a/close! out)))))
    [in out]))

(let [[in out] (hot-dog-machine-v2 2)]
  (a/>!! in "pocket lint")
  (println (a/<!! out))
  (a/>!! in 3)
  (println (a/<!! out))
  (a/>!! in 3)
  (println (a/<!! out))
  (a/>!! in 3)
  (a/<!! out))

(slurp "http://www.braveclojure.com/random-quote")
