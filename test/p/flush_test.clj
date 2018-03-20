(ns p.flush-test
  (:require [clojure.test :refer :all]
            [p.flush :as flush]))

(deftest check-probabilities
  (testing "Probability flush"
    (is (= 0
           (flush/p {:values [0 10 11 12 9]
                     :suits  [0 0 0 0 0]}
                    {:values [] :suits []})))
    (is (= 0
           (flush/p {:values [7 10 11 8 9]
                     :suits  [0 0 0 0 0]}
                    {:values [] :suits []})))
    (is (= 1
           (flush/p {:values [0 9 4 12 10]
                     :suits  [2 2 2 2 2]}
                    {:values [] :suits []})))
    (is (= (/ 7 47)
           (flush/p {:values [5 6 7 8]
                     :suits  [0 0 0 0]}
                    {:values [0]
                     :suits  [1]})))
    (is (= (/ 45 1081)
           (flush/p {:values [0 6 7]
                     :suits  [1 1 1]}
                    {:values [0 0]
                     :suits  [2 3]})))
    (is (= (/ 118 16215)
           (flush/p {:values [4 6]
                     :suits  [0 0]}
                    {:values [2 0 2]
                     :suits  [0 1 1]})))))

