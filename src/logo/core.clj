(ns logo.core
  (:use
   [rosado.processing]
   [logo.macrology]
   [logo.turtle]
   ;[rosado.processing.applet]
   ))

(defn draw-point [a-point]
  (if (map? a-point)
    (point (a-point :x) (a-point :y)))
  (if (vector? a-point)
    (point (nth a-point 0) (nth a-point 1))))



(def pointA (atom {:x 50, :y  30}))

(defn move5 [aPoint]
  (move-point aPoint  {:x 5, :y 7}))

(defn draw  []
  (background-float 125)
  (stroke-weight 80)
  (stroke-float 10)
  (swap!  pointA  move5)
  (draw-point @pointA)
  (when (> (frame-count) 100)
    (/ 1 0)))

(defn setup []
  (framerate 8)
  (smooth)
  (no-stroke))

(rerun-defapplet logo-play :title "logoemulation"
  :size [200 200]
  :setup setup :draw draw)


