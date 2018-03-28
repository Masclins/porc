(ns p.royal-test
  (:require [clojure.test :refer :all]
            [p.royal :as royal]))

(deftest check-probabilities
  (testing "Probability royal flush"
    (is (= 1 
           (royal/p {:values [12 10 11 0 9]
                     :suits  [0 0 0 0 0]}
                    {:values [] :suits []})))
    (is (= 0
           (royal/p {:values [12 0 11 10 9]
                     :suits  [1 0 0 0 0]}
                    {:values [] :suits []})))
    (is (= 0
           (royal/p {:values [1 0 11 10 9]
                     :suits  [0 0 0 0 0]}
                    {:values [] :suits []})))
    (is (= 0
           (royal/p {:values [12 0 11 10]
                     :suits  [0 0 0 0]}
                    {:values [9]
                     :suits  [0]})))
    (is (= (/ 1 47)
           (royal/p {:values [12 0 11 10]
                     :suits  [0 0 0 0]}
                    {:values [9]
                     :suits  [1]})))
    (is (= (/ 3 1533939)
           (royal/p {:values [] :suits []}
                    {:values [12 10 11 0 9]
                     :suits  [0 0 0 0 0]})))
    (is (= (/ 3 1533939)
           (royal/p {:values [] :suits []}
                    {:values [2 1 1 2 9]
                     :suits  [0 0 1 1 0]})))
    (is (= (/ 4 1533939)
           (royal/p {:values [] :suits []}
                    {:values [6 1 2 2 7]
                     :suits  [0 3 1 0 0]})))
    (is (= (/ 1 16215)
           (royal/p {:values [9 12]
                     :suits  [2 2]}
                    {:values [7 10 1]
                     :suits  [0 1 2]})))))

