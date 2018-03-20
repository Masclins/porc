(ns p.straight-test
  (:require [clojure.test :refer :all]
            [p.straight :as straight]))

(deftest count-values
  (testing "Count cards for every step well"
    (is (= [0 1 2 0 1]
           (straight/count-values [3 5 6 6 8]
                                  [4 5 6 7 8])))))

(deftest check-probabilities
  (testing "Probability straight"
    (is (= 1 
           (straight/p {:values [7 10 11 8 9]
                        :suits  [1 3 0 0 0]}
                       {:values [] :suits []})))
    (is (= 0
           (straight/p {:values [0 9 11 12 10]
                        :suits  [2 2 2 2 2]}
                       {:values [] :suits []})))
    (is (= 1
           (straight/p {:values [0 9 10 11 12]
                        :suits  [1 2 3 0 1]}
                       {:values [] :suits []})))
    (is (= 0
           (straight/p {:values [1]
                        :suits  [0]}
                       {:values [2 2 2 2]
                        :suits  [0 1 2 3]})))
    (is (= 0
           (straight/p {:values [2 7]
                        :suits  [0 0]}
                       {:values [12 12 11]
                        :suits  [0 1 2]})))
    (is (= (/ 6 47)
           (straight/p {:values [5 6 7 8]
                        :suits  [0 0 0 0]}
                       {:values [0]
                        :suits  [1]})))
    (is (= (/ 22 1081)
           (straight/p {:values [9 10 12]
                        :suits  [1 1 1]}
                       {:values [3 11]
                        :suits  [0 0]})))
    (is (= (/ 48 1081)
           (straight/p {:values [5 6 7]
                        :suits  [2 1 1]}
                       {:values [0 0]
                        :suits  [2 3]})))))

