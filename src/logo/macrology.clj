(ns logo.core
  (:use [clojure.contrib.pprint]
        [rosado.processing.applet]))


;; This macro allows us to define an applet as many times as we want
;; without having tons of annoying windows all over the desktop

(defmacro re-defapplet [app-name & opts]
  `(do
     (try
       ;; we use eval here because without it, the compiler will catch
       ;; the fact that app-name isn't defined the first time this is
       ;; run.  in effect we are using eval to circumvent compiler
       ;; correctness checking, since this is wrapped in a macro, i'm
       ;; not too concerned
       (stop (eval (symbol (str '~app-name))))
       (catch Exception e#
         ;(println e#)
         ;(println "aha you haven't started anything yet")
         ))
       (defapplet ~app-name ~@opts)))
(defmacro rerun-defapplet [app-name & opts]
  `(do
     (re-defapplet ~app-name ~@opts)

     (. Thread (sleep 200)) ;sleep here allows stumpwm to catchup 
     (run ~app-name)))


;(println "")
;(pprint (macroexpand-1 `(re-defapplet2 logo-play3 :title "logoemulation")))
;(re-defapplet2 logo-play5 :title "restartplay")
;(run logo-play5)
