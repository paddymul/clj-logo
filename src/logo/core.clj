(ns logo.core
  (:use
                                        ;[rosado.processing]
   [logo.processing-util :only [setup rerun-defapplet]]
   [logo.turtle-prim :only [mk-turtle]]
   [logo.draw :only [forward! clockwise! draw-turtle draw-point]]
   [rosado.processing :only [background-float point
                             frame-count stroke-weight
                             no-stroke stroke-float
                             smooth
                             ]]))





(def angle-a (atom 180))
(def pointA (atom {:x 50, :y  30}))

(def turtle-a (atom (mk-turtle :position {:x 300 :y 300} :direction 215)))
;(reset! turtle-a (set-direction @turtle-a @angle-a))
(defn draw  []
  (background-float 25)
  (stroke-weight 80)
  (stroke-float 90)
  ;(swap!  pointA  move5)
  ;;(swap! turtle-a forward 5)
  (swap! angle-a (fn [num] (+ num 40)))
  ;(println  @turtle-a)
  ;(reset! turtle-a (forward @turtle-a 2))
  (forward! turtle-a 2)
  (clockwise! turtle-a 4)
  ;(reset! turtle-a (clockwise @turtle-a 4))


  (draw-point @pointA)
  ;(draw-point (@turtle-a :position))
  (draw-turtle turtle-a)
  
  (when (> (frame-count) 100)
    (/ 1 0)))


(rerun-defapplet logo-play :title "logoemulation"
  :size [800 800]
  :setup setup :draw draw)


