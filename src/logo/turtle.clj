(ns logo.turtle
  (:use
   [clojure.contrib.pprint]
   [logo.turtle-prim]
   [logo.draw]
   [clojure.contrib.macro-utils]))
  


(defmacro forward! [turtle distance]
  `(reset! ~turtle (forward @~turtle ~distance)))

(defmacro set-direction! [turtle distance]
  `(reset! ~turtle (set-direction @~turtle ~distance)))

(defmacro clockwise! [turtle distance]
  `(reset! ~turtle (clockwise @~turtle ~distance)))

(defmacro anti-clockwise! [turtle distance]
  `(reset! ~turtle (clockwise @~turtle ~distance)))

(defmacro draw-turtle [turtle]
  `(draw-point (@~turtle :position)))

(defmacro make-destructive2 [func]
  (let [mac-name (symbol (str (name func) "!"))]
    `(defmacro ~mac-name [a-turtle# operand#]
       `(reset! ~a-turtle# (~~func @~a-turtle# ~operand#)))))

(pprint (mexpand-1 '(defmacro anti-clockwise! [turtle distance]
                        `(reset! ~turtle (clockwise @~turtle ~distance)))))
(println "---------")
(pprint (mexpand-1 '(make-destructive2 anti-clockwise)))


;;(defmacro with-turtle [turtle & exprs]
;;  (symbol-macrolet [forward!! [dist] `(forward! turtle ~dist)]
;;                    ~@exprs))

;;(pprint (mexpand-1 `(with-turtle t (forward!! 8))))
(comment
(defmacro abbrev [short long]
  `(defmacro ~short [& exprs#]
     `(~~long ~@exprs#)))


(pprint (mexpand-1 '(abbrev do progn)))
 



(abbrev do progn)
(progn
 (println "---------")
 (pprint (mexpand-1 '(abbrev do progn))))

  " a try at a macro-defining macro"
(defmacro make-destructive2 [func]
  (let [mac-name (symbol (str (name func) "!"))]
    `(defmacro ~mac-name [a-turtle operand]
       `(reset! ~a-turtle (~~func @~a-turtle ~distance)))))

(pprint (macroexpand-1 '(make-destructive2 set-direction )))
(make-destructive2 forward)

(defmacro symbol-macrolet-play [turtle & exprs]
  `(symbol-macrolet [forward!! [dist] '(+ 3 3)]
                    (forward!! 3)))

(println "-----------")
(pprint (mexpand-1 '(symbol-macrolet-play t (forward!! 8))))
(println "-----------")
(pprint (mexpand-all '(symbol-macrolet-play t (forward!! 8))))



(println "-----------")
(pprint (mexpand-1 '(symbol-macrolet-play2 t (forward!! 8))))
(println "-----------")
;(pprint (mexpand-all '(symbol-macrolet-play2 t (forward!! 8))))

(defmacro double-qoute [foo bar]
  `(let [baz# (+ ~foo ~bar)]
     `(+ ~~foo ~~bar ~baz#)))

(println "-----------")
(pprint (mexpand-1 '(double-qoute 8 7)))
(println "-----------")
(pprint (mexpand-all '(double-qoute 8 7)))
)