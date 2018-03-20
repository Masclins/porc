(ns p.full
  (:require [precalc.combinations :as comb]
            [p.calc :as calc])
  (:gen-class))

(defn p-valued
  "Returns probability of specific Full House given kept and changed value of cards"
  [hand changed trio-value pair-value]
  (let [trio-in-hand (calc/count-contains trio-value hand)
        pair-in-hand (calc/count-contains pair-value hand)
        trio-needed (max (- 3 trio-in-hand) 0)
        pair-needed (max (- 2 pair-in-hand) 0)
        draws (count changed)
        trio-in-deck (- 4 trio-in-hand (calc/count-contains trio-value changed))
        pair-in-deck (- 4 pair-in-hand (calc/count-contains pair-value changed))]
    (cond (or (> (+ trio-needed pair-needed) draws)
              (> trio-needed trio-in-deck)
              (> pair-needed pair-in-deck))
          0
          (= trio-needed pair-needed 0)
          1
          :else
          (/ (* (calc/C trio-in-deck trio-needed)
                (calc/C pair-in-deck pair-needed))
             (comb/comb47 draws)))))

(defn p-trio-valued
  "Returns probability of Full House with specific trio given kept and changed value of cards"
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

(defn p-pair-valued
  "Returns probability of Full House with specific pair given kept and changed value of cards"
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

(defn p
  "Returns probability of Full House given kept and changed value of cards"
  [hand changed]
  (loop [p 0
         value (range 13)]
    (if (empty? value)
      p
      (recur
        (+ p (p-trio-valued hand changed (first value)))
        (rest value)))))

