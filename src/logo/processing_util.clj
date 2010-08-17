(ns logo.processing-util
  (:use
   [rosado.processing :only [background-float point
                             frame-count stroke-weight
                             no-stroke stroke-float
                             smooth
                             ]]
    [rosado.processing.applet :only [defapplet]]
    ))


(defn setup []
  (rosado.processing/framerate 8)
  (rosado.processing/smooth)
  (rosado.processing/no-stroke))


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

