(ns p.pair-test
  (:require [clojure.test :refer :all]
            [p.pair :as pair]))

(deftest check-probabilities
  (testing "Probability pair"
    (is (= 0 
           (pair/p [0 0 0 0 1] [])))
    (is (= 0
           (pair/p [0 0 0 1 2] [])))
    (is (= 1
           (pair/p [0 1 2 3 0] [])))))

