(ns p.full-test
  (:require [clojure.test :refer :all]
            [p.full :as full]))

(deftest check-probabilities
  (testing "Probability full house"
    (is (= 1 
           (full/p [0 0 0 1 1] [])))
    (is (= 0
           (full/p [0 1] [0 0 0])))
    (is (= (/ 2 47)
           (full/p [0 0 0 1] [1])))
    (is (= (/ 3 47)
           (full/p [0 0 0 1] [2])))
    (is (= (/ 67 1081)
           (full/p [0 0 0] [1 1])))
    (is (= (/ 66 1081)
           (full/p [0 0 0] [1 2])))
    (is (= (/ 4 47)
           (full/p [0 0 1 1] [2])))))

