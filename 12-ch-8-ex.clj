;; when-valid macro

(defn error-messages-for
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value                               (get to-validate fieldname)
                  error-messages                      (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(defmacro when-valid
  [to-validate validations & then]
  `(when (seq (validate ~to-validate ~validations))
     ~@then))

(macroexpand
 '(when-valid order-details order-details-validations
              (println :success)
              (println :failure my-error-name)))

;; or macro
(defmacro my-or
  ([] nil)
  ([x] x)
  ([x & next]
   `(let [or# ~x]
      (if or# or# (my-or ~@next)))))

(macroexpand '(my-or false nil true))

(my-or false 2)
(or false 2)
