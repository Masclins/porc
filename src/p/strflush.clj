(ns p.strflush
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn count-steps
  [cards suit begin]
  (loop [n 0
         values (:values cards)
         suits (:suits cards)]
    (if (empty? values)
      n
      (recur
        (if (and (= (first suits) suit)
                 (>= (first values) begin)
                 (<= (first values) (+ begin 4)))
          (inc n)
          n)
        (rest values)
        (rest suits)))))

(defn p-suited-valued
  [hand changed suit begin]
  (let [in-hand (count-steps hand suit begin)
        needed (- 5 in-hand)
        draws (count (:values changed))]
    (cond (= in-hand 5)
          1
          (or (> needed draws)
              (> (count-steps changed suit begin) 0))
          0
          :else
          (/ 1
             (precalc/comb47 draws)))))

(defn p-suited
  [hand changed suit]
  (loop [p 0
         begin (range 9)]
    (if (empty? begin)
      p
      (recur
        (+ p (p-suited-valued hand changed suit (first begin)))
        (rest begin)))))

(defn p-valued
  [hand changed begin]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-suited-valued hand changed (first suit) begin))
        (rest suit)))))

(defn p
  [hand changed]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-suited hand changed (first suit)))
        (rest suit)))))
