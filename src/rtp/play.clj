(ns rtp.play
  (:require [configs.game-definition :as game-def]
            [precalc.combinations :as comb]
            [p.royal :as royal]
            [p.strflush :as strflush]
            [p.four :as four]
            [p.full :as full]
            [p.flush :as flush]
            [p.straight :as straight]
            [p.trio :as trio]
            [p.dpair :as dpair]
            [p.pair :as pair])
  (:gen-class))



(defn find-rtp
  "Returns expected payoff given kept and changed cards"
  [hand changed]
  (let [table game-def/prize-table]
    (+ (* (:royal table)    (royal/p hand changed))
       (* (:strflush table) (strflush/p hand changed))
       (* (:four table)     (four/p (:values hand) (:values changed)))
       (* (:full table)     (full/p (:values hand) (:values changed)))
       (* (:flush table)    (flush/p hand changed))
       (* (:straight table) (straight/p hand changed))
       (* (:trio table)     (trio/p (:values hand) (:values changed)))
       (* (:dpair table)    (dpair/p (:values hand) (:values changed)))
       (* (:pair table)     (pair/p (:values hand) (:values changed))))))

(defn find-best-rtp
  "Returns expected payoff given a hand. It finds best changing option"
  [hand]
  (loop [rtp 0
         comb comb/possiblehands]
    (if (empty? comb)
      rtp
      (recur
        (max rtp
             (find-rtp {:values (map (:values hand) (first  (first comb))) 
                        :suits  (map (:suits  hand) (first  (first comb)))}
                       {:values (map (:values hand) (second (first comb)))
                        :suits  (map (:suits  hand) (second (first comb)))}))
        (rest comb)))))

