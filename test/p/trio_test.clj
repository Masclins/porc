(ns p.trio-test
  (:require [clojure.test :refer :all]
            [p.trio :as trio]))

(deftest check-probabilities
  (testing "Probability trio"
    (is (= 0 
           (trio/p [0 0 0 0 1] [])))
    (is (= (/ 46 47)
           (trio/p [0 0 0 1] [1])))
    (is (= (/ 1035 1081)
           (trio/p [2 2 2] [3 1])))
    (is (= (/ 1980 16215)
           (trio/p [4 4] [1 2 3])))))

