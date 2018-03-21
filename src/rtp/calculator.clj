(ns rtp.calculator
  (:require [rtp.play :as play])
  (:gen-class))

(defn rtp
  "Returns the RTP of the game when given the equivalence hands"
  [eq-hands]
  (loop [win 0
         eq-hands eq-hands]
    (if (empty? eq-hands)
      (/ win 2598960)
      (recur
        (+ win
           (* (play/find-best-rtp {:values (:values (first eq-hands))
                                   :suits  (:suits  (first eq-hands))})
              (:times (first eq-hands)))) 
        (rest eq-hands)))))

