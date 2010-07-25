(ns logo.core
    (:use [rosado.processing]
        [rosado.processing.applet]))



;; we use eval here because without it, the compiler will catch the
;; fact thaat logo-play isn't defined the first time this is run at
;; the repl this way we will only ever have one window for logo-play
;; open, eventually I will make this into a macro wrapping defapplet

(try
  (stop (eval (symbol "logo-play")))
  (catch Exception e
    (println "aha you haven't started anything yet")))


;(defstruct logo-point :x :y)

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
;  (let [[x y] [400 200]]
;    (point x y))
;  (Thread/sleep 1000)
;  (point 100 800)
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

(defapplet logo-play :title "logoemulation"
  :size [200 200]

  :setup setup :draw draw)

(run logo-play)

