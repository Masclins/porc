(ns p.trio
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.full :as full])
  (:gen-class))

(defn p-valued
  "Returns probability of specific Trio given kept and changed cards"
  [hand changed value]
  (let [in-hand (calc/count-contains value hand)
        needed (- 3 in-hand)
        draws (count changed)
        in-deck (- 4 in-hand (calc/count-contains value changed))]
    (if (or (> needed draws)
              (> needed in-deck))
      0
      (- (/ (* (calc/C in-deck needed)
               (calc/C (- 47 in-deck) (- draws needed)))
            (precalc/comb47 draws))
         (full/p-trio-valued hand changed value)))))

(defn p
  "Returns probability of Trio given kept and changed cards"
  [hand changed]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (+ p (p-valued hand changed (first value)))
        (rest value)))))
          
