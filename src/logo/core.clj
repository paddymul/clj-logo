(ns logo.core
  (:use
;   [logo.macrology]
   [rosado.processing]
   [rosado.processing.applet]))

(defstruct logo-turtle :position :direction)


(defn move-point
  [old-point offset]
  {
          :x (+ (old-point :x) (offset :x))
          :y (+ (old-point :y) (offset :y))})

(defn move-point
  [old-point offset]
  [ (+ (nth old-point 0) (nth offset 0))
    (+ (nth old-point 1) (nth offset 1))])

(defn forward
  [turtle distance]
  (let
      [old-point (turtle :positon)]
    (struct logo-turtle
            (move-point
             old-point
             { :x distance :y 0})
            :direction (turtle :direction))))

(def pointA (atom [ 50  30]))

(defn move5 [aPoint]
  (move-point aPoint  [5 7]))

(defn draw
  []
  (background-float 125)
  (stroke-weight 80)
  (stroke-float 10)
  (point 100 800)
  (reset! pointA  (move5 @pointA))
  ;(println pointA)
  (point (nth @pointA 0) (nth @pointA 1))
  )

(defn mouse-move [evt]  (swap! pointA move5))

(defn setup []
  (framerate 8)
  (smooth)
  (no-stroke))

(rerun-defapplet logo-play :title "logoemulation"
  :size [200 200]
  :setup setup :draw draw)


