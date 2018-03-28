(ns rtp.calculator
  (:require [rtp.play :as play])
  (:gen-class))

(defn rtp-small
  "Returns the RTP of the game when given the equivalence hands"
  [eq-hands]
  (loop [win 0
         analyzed-hands 0
         eq-hands eq-hands]
    (if (empty? eq-hands)
      {:win win :times analyzed-hands}
      (recur
        (+ win
           (* (play/find-best-rtp {:values (:values (first eq-hands))
                                   :suits  (:suits  (first eq-hands))})
              (:times (first eq-hands))))
        (+ analyzed-hands (:times (first eq-hands)))
        (rest eq-hands)))))

(defn rtp
  "Divides eq-hands in smalls parts and Returns the RTP"
  [eq-hands]
  (let [size-part (int (/ (count eq-hands) 2))
        eq-hands (vec eq-hands)
        part-0 (future (rtp-small (subvec eq-hands 0 size-part)))
        part-1 (future (rtp-small (subvec eq-hands size-part)))]
    (/ (+ (:win @part-0)
          (:win @part-1))
       (+ (:times @part-0)
          (:times @part-1)))))


                          

