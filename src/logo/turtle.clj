(ns logo.turtle
  (:use
   [clojure.contrib.pprint]
   [logo.turtle-prim]
   [logo.draw]
   ;[rosado.processing.applet]
   )

  ;(:require [clojure.contrib.generic.math-functions :as generic])
  )
  


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

(comment
  " a try at a macro-defining macro"
(defmacro make-destructive2 [func]
  (let [mac-name (symbol (str (name func) "!"))]
    `(defmacro ~mac-name [a-turtle operand]
       `(reset! ~a-turtle (~~func @~a-turtle ~distance)))))

(pprint (macroexpand-1 '(make-destructive2 set-direction )))
(make-destructive2 forward)
)