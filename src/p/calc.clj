(ns p.calc
  (:require [clojure.math.combinatorics :as combo])
  (:gen-class))

(defn count-value
  [hand value]
  (reduce
    #(if (= %2 value)
       (inc %1)
       %1)
    0
    hand))

(defn C
  [x y]
  (combo/count-combinations (range x) y))

