(ns p.pair
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.full :as full]
            [p.dpair :as dpair])
  (:gen-class))

(defn p-valued
  "Returns probability of specific Pair given kept and changed cards"
  [hand changed value]
  (let [in-hand (calc/count-contains value hand)
        needed (- 2 in-hand) 
        draws (count changed)
        in-deck (- 4 in-hand (calc/count-contains value changed))]
    (if (or (> needed draws)
            (> needed in-deck))
      0
      (- (/ (* (calc/C in-deck needed)
               (calc/C (- 47 in-deck) (- draws needed)))
            (precalc/comb47 draws))
         (dpair/p-pair-valued hand changed value)
         (full/p-pair-valued hand changed value)))))

(defn p
  "Returns probability of Pair greater than min-value given kept and changed cards. 0 will always count. min-value must be > 0"
  ([hand changed]
   ; Jacks or Better as default
    (p hand changed 10))
   ([hand changed min-value]
    (loop [p 0
           value (concat [0] (range min-value 13))]
      (if (empty? value)
        p
        (recur
          (+ p (p-valued hand changed (first value)))
          (rest value))))))
          
