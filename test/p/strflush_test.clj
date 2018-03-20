(ns p.strflush-test
  (:require [clojure.test :refer :all]
            [p.strflush :as strflush]))

(deftest check-probabilities
  (testing "Probability straight flush"
    (is (= 1 
           (strflush/p {:values [7 10 11 8 9]
                        :suits  [0 0 0 0 0]}
                       {:values [] :suits []})))
    (is (= 0
           (strflush/p {:values [0 9 11 12 10]
                        :suits  [2 2 2 2 2]}
                       {:values [] :suits []})))
    (is (= (/ 2 47)
           (strflush/p {:values [5 6 7 8]
                        :suits  [0 0 0 0]}
                       {:values [0]
                        :suits  [1]})))
    (is (= (/ 3 1081)
           (strflush/p {:values [5 6 7]
                        :suits  [1 1 1]}
                       {:values [0 0]
                        :suits  [2 3]})))
    (is (= (/ 2 16215)
           (strflush/p {:values [4 6]
                        :suits  [0 0]}
                       {:values [2 0 2]
                        :suits  [0 1 1]})))))

