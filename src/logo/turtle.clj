(ns logo.turtle
  (:use
   [clojure.test]
   [clojure.contrib.def]
   [logo.macrology]
   ;[rosado.processing.applet]
   )
  (:require [clojure.contrib.generic.math-functions :as generic]))
  


(defstruct logo-turtle :position :direction)

(defn correct-angle [angle]
  (mod angle 360))

(defnk mk-turtle [:position {:x 0 :y 0}
                  :direction 0]
  (struct logo-turtle position (correct-angle direction)))



(defn move-point
  [old-point offset]
  {
   :x (+ (old-point :x) (offset :x))
    :y (+ (old-point :y) (offset :y))})

(println (move-point {:x 100 :y 100} {:x 20 :y 20}))

(defn set-direction [turtle angle]
  (mk-turtle :positon (turtle :position)
             :direction  (correct-angle angle)))


(defn clockwise [turtle delta-angle]
  (set-direction turtle (+ (:direction turtle) delta-angle)))

(defn anti-clockwise [turtle delta-angle]
  (set-direction turtle (- (:direction turtle) delta-angle )))

;; i'm not sure what to do here, I decided to put correct-angle in
;; mk-turtle also, hmmm

(deftest test-set-direction2
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 359)]
    (assert-direction 80 (set-direction t 80))
    (assert-direction 355  (set-direction t -5))
    (assert-direction 0    (set-direction t 360))))


(deftest test-rotate
    (let [t (mk-turtle :position {:x 100 :y 100}  :direction 100)]
      (assert-direction  120 (clockwise t 20))
      (assert-direction  80  (anti-clockwise t 20))
      (assert-direction  355 (anti-clockwise t 105))
      (assert-direction  5   (clockwise t 265))
      ))
(defn sin [angle]
  (. java.lang.Math sin angle))

(defn cos [angle]
  (. java.lang.Math cos angle))

(defn sqrt [num]
  (. java.lang.Math sqrt num))

(defn toRadians [angle]
  (. java.lang.Math toRadians angle))

(defn sinR [angle]
  (sin (toRadians angle)))

(defn cosR [angle]
  (cos (toRadians angle)))


(defn forward
  [tur distance]
  (let
      [old-point (tur :position)
       angle     (tur :direction)
       ]
    (mk-turtle
     ;:position (move-point old-point { :x distance :y 0})
     :position (move-point old-point { :x (* distance (sinR angle))  :y (* distance (cosR (+ 180 angle)))})
     :direction (tur :direction)
     )))


(deftest test-movement
  ;; since :x 0, :y 0 is the upper lefthand corner of the screen, at a
  ;; heading of 0 (pointing straight up) moving up 20 should decrease
  ;; y by that much
  
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 0)]
    (assert-position  {:x 100 :y 80} (forward t 20)))

  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 45)]
    (assert-position  {:x 102 :y 98} (forward t (sqrt 8))))
    
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 90)]
    (assert-position  {:x 120 :y 100.0} (forward t 20)))

  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 180)]
    (assert-position  {:x 100 :y 120.0} (forward t 20)))

  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 270)]
    (assert-position {:x 80 :y 100} (forward t 20)))
  
  
  )

(comment
  (= (sin x) (/ opposite hypoenuese))
  (= (* hypoenuese (sin x)) opposite)
  

  )
  