(ns logo.draw
  (:use
   ;[rosado.processing]
   [rosado.processing :only [background-float point
                             frame-count stroke-weight
                             no-stroke stroke-float
                             smooth
                             ]]))


(defn draw-point [a-point]
  (if (map? a-point)
    (point (a-point :x) (a-point :y)))
  (if (vector? a-point)
    (point (nth a-point 0) (nth a-point 1))))

