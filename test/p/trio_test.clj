(ns p.trio-test
  (:require [clojure.test :refer :all]
            [p.trio :as trio]))

(deftest check-probabilities
  (testing "Probability trio"
    (is (= 0 
           (trio/p [0 0 0 0 1] [])))
    (is (= 0
           (trio/p [0 0 1 1 1] [])))
    (is (= 1
           (trio/p [1 1 1 2 3] [])))
    (is (= (/ 43 47)
           (trio/p [1 1 1 2] [3])))
    (is (= (/ 969 1081)
           (trio/p [1 1 1] [2 3])))
    (is (= (/ 1854 16215)
           (trio/p [0 0] [1 2 3])))
    (is (= (/ 84 1081)
           (trio/p [0 0 1] [2 3])))))
