(ns logo.math
  (:use
   [clojure.test]
   [clojure.contrib.math]
   )


  )

(defn correct-angle [angle]
  (mod angle 360))

(defn -c [x y]
  (correct-angle (- x y)))

(defn angle-negative [angle]
  " given an angle between 0 and 360 returns an angle between -180 and
  180"
  (if (> angle 180)
    (- angle 360)
    angle))

(deftest angle-negative-test
  (is (= -30 (angle-negative 330)))
  (is (= 30 (angle-negative 30))))

(defn angle-diff [from to]
  (angle-negative (-c to from)))

(deftest angle-diff-test
  (is (= -30 (angle-diff 100 70)))
  (is (= 30 (angle-diff  70 100)))
  (is (= 30 (angle-diff  350 20))))

(defn sin [angle]
  (. java.lang.Math sin angle))

(defn cos [angle]
  (. java.lang.Math cos angle))

;(defn tan [angle]
;  (. java.lang.Math tan angle))

(defn asin [opp-hyp]
  (. java.lang.Math asin opp-hyp))

(defn acos [adj-hyp]
  (. java.lang.Math acos adj-hyp))

(defn atan [opp-adj]
  (. java.lang.Math atan opp-adj))

(defn atan2 [y x]
  (. java.lang.Math atan2 y x))

;(defn sqrt [num]
;  (. java.lang.Math sqrt num))

;;(defn abs [num]
;;  (. java.lang.Math abs num))

;;(defn round [num]
;;  (. java.lang.Math round num))

(defn toRadians [angle]
  (. java.lang.Math toRadians angle))

(defn toDegrees [angle]
  (. java.lang.Math toDegrees angle))

(defn sinR [angle]
  (sin (toRadians angle)))

(defn cosR [angle]
  (cos (toRadians angle)))

;(defn tanR [angle]
;  (tan (toRadians angle)))

(defn asinD [opp-hyp]
  (toDegrees (asin opp-hyp)))

(defn acosD [adj-hyp]
  (toDegrees (acos adj-hyp)))

(defn atanD [opp-adj]
  (correct-angle (toDegrees (atan opp-adj))))

(defn atan2D [y x]
  (correct-angle (toDegrees (atan2 y x))))
