; futures run the code on another thread
; later code won't be blocked
(defn basic-future
  []
  (future (Thread/sleep 4000)
          (println "Coming after 4s"))
  (println "Coming Now!"))

(basic-future)

; deref will be blocked if the future isn't done
(defn future-deref
  []
  (let [result (future (Thread/sleep 1000)
                       (+ 1 1))]
    (println "deref:" (deref result))
    (println "@:" @result)))

(future-deref)

; setting maximum wait and default value for deref
(defn future-deref-default
  []
  (let [result (future (Thread/sleep 1000)
                       (+ 1 1))]
    (println "deref:" (deref result 500 0))
    (println "@:" @result)))

(future-deref-default)

;; delays
(def jackson-5-delay
  (delay (let [message "Just call"]
           (println "1st deref:" message)
           message)))

@jackson-5-delay

;; delays example
;; sending an email only once because it's in a delay
(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])

(defn email-user
  [email-address]
  (println "Sending headshot notification to" email-address))

(defn upload-document
  [headshot]
  true)

(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
            (force notify))))

;; promise
;; you create a promise then deliver a value to it
(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})

(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})

(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  "If the butter meets our criteria, return the butter, else return false"
  [butter]
  (and (<= (:price butter) 100)
       (>= (:smoothness butter) 97)
       butter))

(time
 (some (comp satisfactory? mock-api-call)
       [yak-butter-international butter-than-nothing baby-got-yak]))

(time
  (let [butter-promise (promise)]
    (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
      (future (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
                (deliver butter-promise satisfactory-butter))))
    (println "The winner is..." @butter-promise)))
