(ns p.strflush
  (:require [precalc.combinations :as comb]
            [p.calc :as calc])
  (:gen-class))

(defn count-steps
  "Counts how many steps of a straight are present
  for a given set of cards, suit and start of sequence"
  [cards suit begin]
  (let [values (subvec [0 1 2 3 4 5 6 7 8 9 10 11 12 0]
                       begin
                       (+ 5 begin))]
    (count
      (filter
        #(and (= (second %) suit)
              (not= -1 (.indexOf values (first %))))
        (map vector (:values cards) (:suits cards))))))

(defn p-suited-valued
  "Returns probability of a specific Straight Flush given kept and changed cards"
  [hand changed suit begin]
  (let [in-hand (count-steps hand suit begin)
        needed (- 5 in-hand)
        draws (count (:values changed))]
    (cond
      (= in-hand 5) 1
      (or (> needed draws)
          (> (count-steps changed suit begin) 0)) 0
      :else (/ 1 (comb/comb47 draws)))))

(defn p-suited
  "Returns probability of Straight Flush of specific suit given kept and changed cards"
  [hand changed suit]
  (reduce
    #(+ %1 (p-suited-valued hand changed suit %2))
    0
    (range 9)))

(defn p-valued
  "Returns probability of Straight Flush starting at specific point given kept and changed cards"
  [hand changed begin]
  (reduce
    #(+ %1 (p-suited-valued hand changed %2 begin))
    0
    (range 4)))

(defn p
  "Returns probability of Straight Flush given kept and changed cards"
  [hand changed]
  (reduce
    #(+ %1 (p-suited hand changed %2))
    0
    (range 4)))
