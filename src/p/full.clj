(ns p.full
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn p-valued
  [hand changed trio-value pair-value]
  (let [trio-in-hand (calc/count-value hand trio-value)
        pair-in-hand (calc/count-value hand pair-value)
        trio-needed (max (- 3 trio-in-hand) 0)
        pair-needed (max (- 2 pair-in-hand) 0)
        draws (count changed)
        trio-in-deck (- 4 trio-in-hand (calc/count-value changed trio-value))
        pair-in-deck (- 4 pair-in-hand (calc/count-value changed pair-value))]
    (cond (or (> (+ trio-needed pair-needed) draws)
              (> trio-needed trio-in-deck)
              (> pair-needed pair-in-deck))
          0
          (= trio-needed pair-needed 0)
          1
          :else
          (/ (* (calc/C trio-in-deck trio-needed)
                (calc/C pair-in-deck pair-needed))
             (precalc/comb47 draws)))))

(defn p-trio-valued
  [hand changed trio-value]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (if (= (first value) trio-value)
          p
          (+ p (p-valued hand changed trio-value (first value))))
        (rest value)))))

(defn p
  [hand changed]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (+ p (p-trio-valued hand changed (first value)))
        (rest value)))))

(defn p-pair-valued
  [hand changed pair-value]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (if (= (first value) pair-value)
          p
          (+ p (p-valued hand changed (first value) pair-value)))
        (rest value)))))
