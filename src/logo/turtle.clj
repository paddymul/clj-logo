(ns logo.turtle
  (:use
   [clojure.test]
   [clojure.contrib.def]
   ;[rosado.processing.applet]
   ))


(defstruct logo-turtle :position :direction)

(defnk mk-turtle [:position {:x 0 :y 0}
                  :direction 0]
  (struct logo-turtle position direction))

(println (mk-turtle))


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


(defn clockwise [turtle delta-angle]
  (set-direction turtle (+ (:direction turtle) delta-angle )))

(defn anti-clockwise [turtle delta-angle]
  (set-direction turtle (- (:direction turtle) delta-angle )))

(defn set-direction [turtle angle]
  (let [corrected-angle (mod angle 360)]
    (mk-turtle :positon (turtle :position)
               :direction  corrected-angle)))

(defmacro assert-direction [direction turtle]
  `(is (= ~direction (:direction ~turtle))))

(deftest test-set-direction
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 359)]
    (is (= 80  (:direction (set-direction t 80))))
    (is (= 355 (:direction (set-direction t -5))))
    (is (= 0   (:direction (set-direction t 360))))))

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