;; multimethods
(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))

(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and murder"))

(defmethod full-moon-behavior :simmons
  [were-creature]
  (str (:name were-creature) " will encourage people w keda"))

(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will do nothing"))

(full-moon-behavior {:were-type nil
                     :name      "hamada"})

;; protocols
(defprotocol Psychodynamics
  (thoughts [x] "The data type's thoughts?")
  (feelings-about [x] [x y] "Feelings about whatever"))

(extend-type java.lang.String
  Psychodynamics
  (thoughts
    [x]
    (str x " has some deep thoughts"))
  (feelings-about
    ([x]   (str x " is longing for a simpler life"))
    ([x y] (str x " is longing for a simpler life like " y))))

(extend-type java.lang.Object
  Psychodynamics
  (thoughts
    [x]
    (str x " thinks \"w ba3den?\""))
  (feelings-about
    ([x]   (str x " is feeling a little meh"))
    ([x y] (str x " is feeling a little meh about " y))))

(thoughts 123)
(feelings-about "xo" "xy")
(feelings-about :eh)

; records
(defrecord Werewolf [name title])
(Werewolf. "David" "London Tourist")
(->Werewolf "Zeke" "Plumber")
(map->Werewolf {:name "Ayman" :title "3awatly"})

(def jacob (->Werewolf "Jacob" "(Confidential)"))
(.name jacob)
(class (assoc jacob :salary "$0"))

