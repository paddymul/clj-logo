(ns logo.turtle
  (:use
   [clojure.test]
   [clojure.contrib.def]
   [logo.macrology]
   ;[rosado.processing.applet]
   ))


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

(defn forward
  [turtle distance]
  (let
      [old-point (turtle :positon)]
    (mk-turtle
     :position (move-point
                old-point
                { :x distance :y 0})
     :direction (turtle :direction))))

(deftest test-movement
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 0)]
    (assert-position  {:x 100 :y 100} t)))