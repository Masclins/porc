(ns p.trio
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.four :as four])
  (:gen-class))

(defn p-valued
  [hand changed value]
  (let [in-hand (calc/count-value hand value)
        needed (- 3 in-hand)
        draws (count changed)
        in-deck (- 4 in-hand (calc/count-value changed value))]
    (cond (or (> needed draws)
              (> needed in-deck)
              (< needed 0))
          0
          (= needed 0)
          (- 1 (four/p-valued hand changed value))
          :else
          (/ (* (calc/C in-deck needed)
                (calc/C (- 47 in-deck) (- draws needed)))
             (precalc/comb47 draws)))))

(defn p
  [hand changed]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (+ p (p-valued hand changed (first value)))
        (rest value)))))
          
