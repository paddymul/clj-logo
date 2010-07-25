(ns logo.core
  (:use
   [rosado.processing]
   ;[rosado.processing.applet]
   ))

(defstruct logo-turtle :position :direction)

(defn move-point
  [old-point offset]
  [ (+ (nth old-point 0) (nth offset 0))
    (+ (nth old-point 1) (nth offset 1))])


(defn move-point
  [old-point offset]
  {
   :x (+ (old-point :x) (offset :x))
    :y (+ (old-point :y) (offset :y))})


(defn forward
  [turtle distance]
  (let
      [old-point (turtle :positon)]
    (struct logo-turtle
            (move-point
             old-point
             { :x distance :y 0})
            :direction (turtle :direction))))

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
    (stop))                             
  )



(defn setup []
  (framerate 8)
  (smooth)
  (no-stroke))

(rerun-defapplet logo-play :title "logoemulation"
  :size [200 200]
  :setup setup :draw draw)


