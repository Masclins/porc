(ns p.flush
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.royal :as royal]
            [p.strflush :as strflush])
  (:gen-class))

(defn count-suits
  [suits suit]
  (loop [n 0
         suits suits]
    (if (empty? suits)
      n
      (recur
        (if (= (first suits) suit)
          (inc n)
          n)
        (rest suits)))))

(defn p-suited
  [hand changed suit]
  (let [in-hand (count-suits (:suits hand) suit)
        needed (- 5 in-hand)
        draws (count (:values changed))
        in-deck (- 13 in-hand (count-suits (:suits changed) suit))]
    (if (> needed draws)
        0
        (- (/ (calc/C in-deck needed)
              (precalc/comb47 draws))
           (royal/p-suited hand changed suit)
           (strflush/p-suited hand changed suit)))))


(defn p
  [hand changed]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-suited hand changed (first suit)))
        (rest suit)))))
          
