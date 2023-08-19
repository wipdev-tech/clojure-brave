; infix
(defmacro infix
  {:clj-kondo/ignore true}
  [[operand1 op operand2]]
  (list op operand1 operand2))

(infix (1 + 2))
(macroexpand '(infix (1 + 2)))

; using let macro
(defmacro printer
  [exp]
  (list 'let ['result exp]
        (list println 'result)
        'result))

; if examples
(defmacro my-when
  [test & body]
  (list 'if test
        (cons 'do body)))

(macroexpand '(my-when (= 1 1) "youwhaat"))

(defmacro unless
  [test & branches]
  (conj (reverse branches) test 'if))

(macroexpand '(unless (= 1 1) "youwhaat"))

; syntax quoting example

; (defmacro code-critic
;   {:clj-kondo/ignore true}
;   [bad good]
;   `(do
;      (println "Great squid of Madrid, this is bad code:"
;               (quote ~bad))
;      (println "Sweet gorilla of Manila, this is good code:"
;               (quote ~good))))

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  {:clj-kondo/ignore true}
  [bad good]
  `(do ~@(map #(apply criticize-code %)
              [["Great squid of Madrid, this is bad code:" bad]
               ["Sweet gorilla of Manila, this is good code:" good]])))

(macroexpand '(code-critic (1 + 1) (+ 1 1)))
