(ns logo.core
  (:use
   ;[rosado.processing]
   [logo.macrology]
   [logo.turtle]
   [rosado.processing :only [background-float point
                             frame-count stroke-weight
                             no-stroke stroke-float
                             smooth
                             ]]
   ))
;(require 'rosado.processing)

(comment
(defn move-point
  [old-point offset]
  {
   :x (+ (old-point :x) (offset :x))
   :y (+ (old-point :y) (offset :y))})
)
(defn draw-point [a-point]
  (if (map? a-point)
    (point (a-point :x) (a-point :y)))
  (if (vector? a-point)
    (point (nth a-point 0) (nth a-point 1))))



(def pointA (atom {:x 50, :y  30}))

(defn move5 [aPoint]
  (move-point aPoint  {:x 5, :y 7}))

(defn draw  []
  (background-float 25)
  (stroke-weight 80)
  (stroke-float 90)
  (swap!  pointA  move5)
  (draw-point @pointA)
  (when (> (frame-count) 100)
    (/ 1 0)))

(defn setup []
  (rosado.processing/framerate 8)
  (rosado.processing/smooth)
  (rosado.processing/no-stroke))

(rerun-defapplet logo-play :title "logoemulation"
  :size [800 800]
  :setup setup :draw draw)


