(ns p.pair
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.four :as four]
            [p.trio :as trio])
  (:gen-class))

(defn p-valued
  [hand changed value]
  (let [needed (- 2 (calc/count-value hand value))
        draws (count changed)
        in-deck (+ 1 (- needed (calc/count-value changed value)))]
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
  (loop [p-max 0
         value (range 13)]
    (if (empty? value)
      p-max
      (recur
        (max p-max (p-valued hand changed (first value)))
        (rest value)))))
          
