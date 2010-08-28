(ns logo.macrology
  (:use
   [clojure.contrib.pprint]
   [clojure.test]
   [rosado.processing.applet]))


;; This macro allows us to define an applet as many times as we want
;; without having tons of annoying windows all over the desktop


;(println "")
;(pprint (macroexpand-1 `(re-defapplet2 logo-play3 :title "logoemulation")))
;(re-defapplet2 logo-play5 :title "restartplay")
;(run logo-play5)



(defn assert-direction  [direction turtle]
  (is (= direction (:direction turtle))))
(defn assert-position  [position turtle]
  (is (= position (:position turtle))))
