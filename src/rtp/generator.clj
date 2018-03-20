(ns rtp.generator
  (:require [precalc.equivalence-classes :as eq]
            [rtp.value-generator :as gen])
  (:gen-class))

(defn merge-value-class
  [values eq-class]
  (loop [hands []
         values values]
    (if (empty? values)
      hands
      (recur
        (loop [hands hands
               suits (:suits eq-class)
               times (:times eq-class)]
          (if (empty? suits)
            hands
            (recur
              (conj hands
                    {:values (first values)
                     :suits  (first suits)
                     :times  (first times)})
              (rest suits)
              (rest times))))
        (rest values)))))

(def eq-hands
  (concat (merge-value-class gen/dist  eq/dist)
          (merge-value-class gen/pair  eq/pair)
          (merge-value-class gen/dpair eq/dpair)
          (merge-value-class gen/trio  eq/trio)
          (merge-value-class gen/full  eq/full)
          (merge-value-class gen/four  eq/four)))
