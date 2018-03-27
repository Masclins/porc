(ns rtp.generator-test
  (:require [clojure.test :refer :all]
            [rtp.generator :as gen]
            [rtp.value-generator :as vgen]
            [precalc.equivalence-classes :as eq]))

(defn count-times
  [eq-hands]
  (reduce + (map :times eq-hands)))

; This tests aren't enough to ensure generator works
; Any new test will be appreciated
(deftest check-value-generator
  (testing "Five distinct denominations generated values"
    (is (= 1287 (count vgen/dist)))
    (is (vector? (first vgen/dist))))
  (testing "Pair generated values"
    (is (= 2860 (count vgen/pair)))
    (is (vector? (first vgen/pair))))
  (testing "Two pairs generated values"
    (is (= 858 (count vgen/dpair)))
    (is (vector? (first vgen/dpair))))
  (testing "Trio generated values"
    (is (= 858 (count vgen/trio)))
    (is (vector? (first vgen/trio))))
  (testing "Full House generated values"
    (is (= 156 (count vgen/full)))
    (is (vector? (first vgen/full))))
  (testing "Four generated values"
    (is (= 156 (count vgen/four)))
    (is (vector? (first vgen/four)))))
(deftest check-generator
  (testing "All generated values"
    (is (= [{:values [0 0] :suits [13 13] :times 100}
            {:values [0 0] :suits [15 15] :times 101}
            {:values [1 1] :suits [13 13] :times 100}
            {:values [1 1] :suits [15 15] :times 101}]
           (gen/merge-value-class [[0 0] [1 1]]
                                  {:suits [[13 13] [15 15]]
                                   :times [100 101]})))
    (is (= 134459 (count gen/eq-hands))))
  (testing "Some specific cases"
    (is (= {:values [4 3 2 1 0] :suits [0 0 0 0 0] :times 4}
           (nth gen/eq-hands 0)))
    (is (= {:values [1 1 0 0 2] :suits [0 1 0 1 0] :times 12}
           (nth gen/eq-hands 122837)))
    (is (= {:values [0 0 0 5 5] :suits [0 1 2 0 3] :times 12}
           (nth gen/eq-hands 134000))))
  (testing "Total hands"
    (is (= 1317888
           (count-times (gen/merge-value-class vgen/dist eq/dist))))
    (is (= 1098240
           (count-times (gen/merge-value-class vgen/pair eq/pair))))
    (is (= 123552
           (count-times (gen/merge-value-class vgen/dpair eq/dpair))))
    (is (= 54912 
           (count-times (gen/merge-value-class vgen/trio eq/trio))))
    (is (= 3744
           (count-times (gen/merge-value-class vgen/full eq/full))))
    (is (= 624
           (count-times (gen/merge-value-class vgen/four eq/four))))
    (is (= 2598960
           (count-times gen/eq-hands)))))
