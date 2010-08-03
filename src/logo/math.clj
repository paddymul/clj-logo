(ns logo.math
  (:use
   [clojure.test]
   [clojure.contrib.math]
   )


  )

(defn correct-angle [angle]
  (mod angle 360))

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
