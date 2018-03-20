(ns p.four
  (:require [precalc.combinations :as comb]
            [p.calc :as calc])
  (:gen-class))

(defn p-valued
  "Returns probability of specific Four of a Kind given kept and changed cards"
  [hand changed value]
  (let [in-hand (calc/count-contains value hand)
        needed (- 4 in-hand)
        draws (count changed)
        in-deck (- 4 in-hand (calc/count-contains value changed))]
    (cond (or (> needed draws)
              (> needed in-deck))
          0
          (= needed 0)
          1
          :else
          (/ (calc/C (- 47 in-deck) (- draws needed))
             (comb/comb47 draws)))))

(defn p
  "Returns probability of Four of a Kind given kept and changed cards"
  [hand changed]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (+ p (p-valued hand changed (first value)))
        (rest value)))))
          
