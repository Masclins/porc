(ns p.flush
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.royal :as royal]
            [p.strflush :as strflush])
  (:gen-class))

(defn p-suited
  "Returns probability of Flush of specific suit given kept and changed cards"
  [hand changed suit]
  (let [in-hand (calc/count-contains suit (:suits hand))
        needed (- 5 in-hand)
        draws (count (:values changed))
        in-deck (- 13 in-hand (calc/count-contains suit (:suits changed)))]
    (if (> needed draws)
        0
        (- (/ (calc/C in-deck needed)
              (precalc/comb47 draws))
           (royal/p-suited hand changed suit)
           (strflush/p-suited hand changed suit)))))


(defn p
  "Returns probability of Flush given kept and changed cards"
  [hand changed]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-suited hand changed (first suit)))
        (rest suit)))))
          
