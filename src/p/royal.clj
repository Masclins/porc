(ns p.royal
  (:require [p.strflush :as strflush]
            [p.calc :as calc])
  (:gen-class))

(defn p-suited
  "Returns probability of Royal of specific suit given kept and changed cards"
  [hand changed suit]
  (strflush/p-suited-valued hand changed suit 9))


(defn p
  "Returns probability of Royal given kept and changed cards"
  [hand changed]
  (reduce
    #(+ %1 (p-suited hand changed %2))
    0
    (range 4)))
