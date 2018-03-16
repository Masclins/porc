(ns p.four-test
  (:require [clojure.test :refer :all]
            [p.four :as four]))

(deftest check-probabilities
  (testing "Probability four"
    (is (= 1 
           (four/p [0 0 0 0 1] [])))
    (is (= 1 
           (four/p [0 0 0 0] [1])))
    (is (= (/ 1 47)
           (four/p [0 0 0 1] [1])))
    (is (= 0
           (four/p [0 0 0 1] [0])))
    (is (= (/ 46 1081)
           (four/p [0 0 0] [1 1])))
    (is (= (/ 45 16215)
           (four/p [0 0] [1 1 1])))
    (is (= (/ 46 1081)
           (four/p [1 1 1] [2 0])))))

