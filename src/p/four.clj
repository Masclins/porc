(ns p.four
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn p-valued
  [hand changed value]
  (let [needed (- 4 (calc/count-value hand value))
        draws (count changed)
        in-deck (- needed (calc/count-value changed value))]
    (cond (or (> needed draws)
              (> needed in-deck))
          0
          (= needed 0)
          1
          :else
          (/ (calc/C (- 47 in-deck) (- draws needed))
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
          
