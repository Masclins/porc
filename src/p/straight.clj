(ns p.straight
  (:require [precalculated :as precalc]
            [p.calc :as calc]
            [p.royal :as royal]
            [p.strflush :as strflush])
  (:gen-class))

(defn count-contains
      [elem collection]
      (count (filter #(= elem %) collection)))

(defn count-values
      [values reference]
      (map
        #(count-contains % values)
        reference))

(defn p-valued
  ([hand changed steps]
   (p-valued hand changed steps false))
  ([hand changed steps royal]
   (let [in-hand (count-values (:values hand) steps)
         draws (count (:values changed))]
     (if (not (and (every? #(<= % 1) in-hand)
                   (= (reduce + in-hand) (- 5 draws))))
        0
        (let [odds (loop [odds 1
                          in-hand in-hand
                          steps steps]
                     (if (empty? in-hand)
                       odds
                       (recur
                         (if (= 0 (first in-hand))
                           (* odds
                              (- 4 (count-contains (first steps)
                                                   (:values changed))))
                           odds)
                         (rest in-hand)
                         (rest steps))))]
          (- (/ odds
               (precalc/comb47 draws))
             (if royal
               (royal/p hand changed)
               (strflush/p-valued hand changed (first steps)))))))))

(defn p
  [hand changed]
  (let [p (loop [p 0
                 begin (range 9)]
            (if (empty? begin)
              p
              (recur
                (+ p (p-valued hand changed (range (first begin)
                                                   (+ (first begin) 5))))
                (rest begin))))]
    (+ p (p-valued hand changed [0 9 10 11 12] true))))
