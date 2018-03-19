(ns p.pair-test
  (:require [clojure.test :refer :all]
            [p.pair :as pair]))

(deftest check-probabilities
  (testing "Probability pair"
    (is (= 0 
           (pair/p [0 0 0 0 1] [])))
    (is (= 1
           (pair/p [10 10 0 5 3] [])))
    (is (= 0
           (pair/p [0 0 1 2 3] [])))
    (is (= 1
           (pair/p [0 0 1 2 3] [] 0)))
    (is (= 0
           (pair/p [10 10 0 0 1] [])))
    (is (= 0
           (pair/p [10 10 0 0] [1])))
    (is (= (/ 39 47)
           (pair/p [10 10 0 1] [2])))
    (is (= (/ 12 47)
           (pair/p [9 10 11 12] [0])))
    (is (= (/ 39 47)
           (pair/p [9 9 2 10] [1])))
    (is (= (/ 801 1081)
           (pair/p [11 11 2] [0 1])))
    (is (= (/ 11559 16215)
           (pair/p [9 9] [1 2 3])))))
