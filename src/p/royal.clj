(ns p.royal
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn count-royals
  [cards suit]
  (loop [n 0
         values (:values cards)
         suits (:suits cards)]
    (if (empty? values)
      n
      (recur
        (if (and (= (first suits) suit)
                 (or (= (first values) 0)
                     (and (>= (first values) 9)
                          (<= (first values) 12))))
          (inc n)
          n)
        (rest values)
        (rest suits)))))

(defn p-suited
  "Returns probability of Royal of specific suit given kept and changed cards"
  [hand changed suit]
  (let [in-hand (count-royals hand suit)
        needed (- 5 in-hand)
        draws (count (:values changed))]
    (cond (= in-hand 5)
          1
          (or (> needed draws)
              (> (count-royals changed suit) 0))
          0
          :else
          (/ 1
             (precalc/comb47 draws)))))


(defn p
  "Returns probability of Royal given kept and changed cards"
  [hand changed]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-suited hand changed (first suit)))
        (rest suit)))))
          
