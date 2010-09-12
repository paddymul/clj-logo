(clojure.core/use 'nstools.ns)
(ns+   logo.turtle-prim
  (:clone nstools.generic-math)
  (:from units dimension? in-units-of)
  (:use
   [clojure.contrib.pprint]
   [clojure.test]
   [clojure.contrib.def]
   ;;[clojure.contrib.math]
   [logo.macrology]
   [logo.math :only [correct-angle sinR cosR atan2D]]
   ;[rosado.processing.applet]
   )

  ;(:require [clojure.contrib.generic.math-functions :as generic])
  )
  


(defstruct logo-turtle :position :direction)

(defnk mk-turtle [:position {:x 0 :y 0}
                  :direction 0]
  (struct logo-turtle position (correct-angle direction)))

(defn move-point
  [old-point offset]
  {
   :x (+ (old-point :x) (offset :x))
   :y (+ (old-point :y) (offset :y))})

(defn move-point-dir [old-point angle  distance]
  (move-point old-point
              { :x (* distance (sinR angle))
               :y (* distance (cosR (+ 180 angle)))}))


(defn set-direction [turtle angle]
  (mk-turtle :position (turtle :position)
             :direction  (correct-angle angle)))

(deftest set-direction-test
  (let [t (mk-turtle :position {:x 100 :y 100}  :direction 0)]
    (assert-position  {:x 100 :y 100} (set-direction t 20))))

(defn clockwise [turtle delta-angle]
  (set-direction turtle (+ (:direction turtle) delta-angle)))

(defn anti-clockwise [turtle delta-angle]
  (set-direction turtle (- (:direction turtle) delta-angle )))



;; i'm not sure what to do here, I decided to put correct-angle in
;; mk-turtle also, hmmm
(defn forward
  [tur distance]
  (let
      [old-point (tur :position)
       angle     (tur :direction)
       ]
    (mk-turtle
     :position (move-point-dir old-point angle distance)
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
    (assert-position {:x 80 :y 100} (forward t 20))))



(defn bearing [from-point to-point]
  (let [a_x (-   (:x from-point) (:x to-point))
        a_y (-   (:y from-point) (:y to-point) )]

    (let [result (atan2D  a_y a_x)
          c-result (correct-angle (- result 90))]
      ;(println "a_x a_y  from-point to-point result"  a_x a_y  from-point to-point c-result)
      c-result)))


(def sqr32 (/ (sqrt 3) 2))
(def sqr10032 (* sqr32 100))

(defmacro assert-bearing [expected-bearing point]
  (let [actual-bearing#  (bearing {:x 100 :y 100} point)
        diff#  (correct-angle (- actual-bearing# expected-bearing))
        abs-diff# (abs (correct-angle diff#))]
    `(is (or (> 1 ~abs-diff#) (< 359 ~abs-diff#))
         '(~expected-bearing ~diff# ~abs-diff# ~actual-bearing# ~point )  )))


  (deftest test-bearings3
  (println "")
  (println "")

  (assert-bearing     0   {:x 100.1  :y 0})  
  (assert-bearing     0   {:x 100    :y 0})  
  (assert-bearing     0   {:x  99.9  :y 0})    
;  (assert-bearing    30   {:x  150   :y 24})

  (assert-bearing    45   {:x 149.9  :y 50})   
  (assert-bearing    45   {:x 150    :y 50})   
  (assert-bearing    45   {:x 150.1  :y 50})   


  (assert-bearing    90   {:x 200    :y 99.9}) 
  (assert-bearing    90   {:x 200    :y 100})  
  (assert-bearing    90   {:x 200    :y 100.1})
  (assert-bearing   135   {:x 149.9  :y 150})  
  (assert-bearing   135   {:x 150    :y 150})  
  (assert-bearing   135   {:x 150.1  :y 150})  

  (assert-bearing   180   {:x 100.1  :y 200})
  (assert-bearing   180   {:x 100    :y 200})
  (assert-bearing   180   {:x  99.9  :y 200})

  (assert-bearing   225   {:x  49.9  :y 150})  
  (assert-bearing   225   {:x  50    :y 150})
  (assert-bearing   225   {:x  50.1  :y 150})
  
  (assert-bearing   270   {:x   1    :y 100.1})  
  (assert-bearing   270   {:x   1    :y 100})   
  (assert-bearing   270   {:x   1    :y 99.9})
  
  (assert-bearing   315   {:x  49.9  :y  50})
  (assert-bearing   315   {:x  50    :y  50})
  (assert-bearing   315   {:x  50.1  :y  50}) 

  )

;    (correct-angle (+ 90 (atan2D a_y a_x)))))


(deftest assert-bearings4

  (assert-bearing     0 {:x 100    :y 1})  
  (assert-bearing    90 {:x 200    :y 100})
  (assert-bearing   180 {:x 100    :y 200})
  (assert-bearing   270 {:x   1    :y 100}))
(comment
  (= (sin x) (/ opposite hypoenuese))
  (= (* hypoenuese (sin x)) opposite)

  )

