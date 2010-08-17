(ns logo.draw
  (:use
   ;[rosado.processing]
   [rosado.processing :only [background-float point
                             frame-count stroke-weight
                             no-stroke stroke-float
                             smooth
                             ]]
   [logo.turtle-prim :only [set-direction forward clockwise anti-clockwise]]
   ))


(defn draw-point [a-point]
  (if (map? a-point)
    (point (a-point :x) (a-point :y)))
  (if (vector? a-point)
    (point (nth a-point 0) (nth a-point 1))))


(defmacro forward! [turtle distance]
  `(reset! ~turtle (forward @~turtle ~distance)))

(defmacro set-direction! [turtle distance]
  `(reset! ~turtle (set-direction @~turtle ~distance)))

(defmacro clockwise! [turtle distance]
  `(reset! ~turtle (clockwise @~turtle ~distance)))

(defmacro anti-clockwise! [turtle distance]
  `(reset! ~turtle (anti-clockwise @~turtle ~distance)))

(defmacro draw-turtle [turtle]
  `(draw-point (@~turtle :position)))
