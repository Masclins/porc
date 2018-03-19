(ns p.royal
  (:require [precalculated :as precalc]
            [p.calc :as calc])
  (:gen-class))

(defn count-royals
  [cards suit]
  (loop [n 0
         cards cards]
    (if (empty? cards)
      n
      (recur
        (if (or (= (first cards) {:v 12 :s suit})
                (= (first cards) {:v 11 :s suit})
                (= (first cards) {:v 10 :s suit})
                (= (first cards) {:v 9 :s suit})
                (= (first cards) {:v 8 :s suit}))
          (inc n)
          n)
        (rest cards)))))        

(defn p-valued
  [hand changed suit]
  (let [in-hand (count-royals hand suit)
        needed (- 5 in-hand)
        draws (count changed)]
    (cond (= in-hand 5)
          1
          (or (> needed draws)
              (> (count-royals changed suit) 0))
          0
          :else
          (/ 1
             (precalc/comb47 draws)))))


(defn p
  [hand changed]
  (loop [p 0
         suit (range 4)]
    (if (empty? suit)
      p
      (recur
        (+ p (p-valued hand changed (first suit)))
        (rest suit)))))
          
