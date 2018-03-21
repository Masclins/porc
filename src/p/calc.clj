(ns p.calc
  (:require [clojure.math.combinatorics :as combo])
  (:gen-class))

(defn count-contains
  "Count elem appearences in collection"
  [elem collection]
  (count (filter #(= elem %) collection)))

(defn C
  "Returns combinations xCy"
  [x y]
  (combo/count-combinations (range x) y))
