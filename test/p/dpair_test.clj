(ns p.dpair-test
  (:require [clojure.test :refer :all]
            [p.dpair :as dpair]))

(deftest check-probabilities
  (testing "Probability double pair"
    (is (= 0 
           (dpair/p [0 0 0 0 1] [])))
    (is (= 1
           (dpair/p [0 0 1 1 2] [])))
    (is (= (/ 43 47)
           (dpair/p [0 0 1 1] [2])))
    (is (= (/ 2592 16215)
           (dpair/p [0 0] [1 2 3])))
    (is (= (/ 186 1081)
           (dpair/p [0 0 1] [2 3])))))
