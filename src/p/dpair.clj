(ns p.dpair
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn p-valued
  [hand changed value1 value2]
  (let [in-hand1 (calc/count-value hand value1)
        in-hand2 (calc/count-value hand value2)
        needed1 (max (- 2 in-hand1) 0)
        needed2 (max (- 2 in-hand2) 0)
        draws (count changed)
        in-deck1 (- 4 in-hand1 (calc/count-value changed value1))
        in-deck2 (- 4 in-hand2 (calc/count-value changed value2))]
    (if (or (> (+ needed1 needed2) draws)
            (> needed1 in-deck1)
            (> needed2 in-deck2))
      0
      (/ (* (calc/C in-deck1 needed1)
            (calc/C in-deck2 needed2)
            (calc/C (- 47 in-deck1 in-deck2) (- draws needed1 needed2)))
         (precalc/comb47 draws)))))

(defn p
  [hand changed]
  (loop [p 0
         value1 (range 13)]
    (if (= 1 (count value1))
      p
      (recur
        (loop [p2 0
               value2 (rest value1)]
          (if (empty? value2)
            (+ p p2)
            (recur
              (+ p2 (p-valued hand changed (first value1) (first value2)))
              (rest value2))))
        (rest value1)))))

(defn p-pair-valued
  [hand changed value1]
  (loop [p 0
         value2 (range 13)]
    (if (empty? value2)
      p
      (recur
        (if (= value1 (first value2))
          p
          (+ p (p-valued hand changed value1 (first value2))))
        (rest value2)))))
