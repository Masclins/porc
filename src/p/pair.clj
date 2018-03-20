(ns p.pair
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.full :as full]
            [p.dpair :as dpair])
  (:gen-class))

(defn p-valued
  [hand changed value]
  (let [in-hand (calc/count-value hand value)
        needed (- 2 in-hand) 
        draws (count changed)
        in-deck (- 4 in-hand (calc/count-value changed value))]
    (if (or (> needed draws)
            (> needed in-deck))
      0
      (- (/ (* (calc/C in-deck needed)
               (calc/C (- 47 in-deck) (- draws needed)))
            (precalc/comb47 draws))
         (dpair/p-pair-valued hand changed value)
         (full/p-pair-valued hand changed value)))))

(defn p
  ([hand changed]
    (p hand changed 10))
  ([hand changed min-value] ; 0 is Ace and will always count
    (loop [p 0
           value (concat [0] (range min-value 13))]
      (if (empty? value)
        p
        (recur
          (+ p (p-valued hand changed (first value)))
          (rest value))))))
          
