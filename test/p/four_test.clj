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
    (is (= (/ 45 16215)
           (four/p [10 10] [7 2 0])))
    (is (= (/ 46 1081)
           (four/p [1 1 1] [2 0])))
    (is (= (/ 344 1533939)
           (four/p [] [0 1 2 3 4])))
    (is (= (/ 52 178365)
           (four/p [0] [1 2 3 4])))))


