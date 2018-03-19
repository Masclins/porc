(ns p.royal-test
  (:require [clojure.test :refer :all]
            [p.royal :as royal]))

(deftest check-probabilities
  (testing "Probability royal straight"
    (is (= 1 
           (royal/p [{:v 12 :s 0} {:v 10 :s 0} {:v 11 :s 0} {:v 8 :s 0} {:v 9 :s 0}] [])))
    (is (= 0
           (royal/p [{:v 12 :s 1} {:v 8 :s 0} {:v 11 :s 0} {:v 10 :s 0} {:v 9 :s 0}] [])))
    (is (= 0
           (royal/p [{:v 1 :s 0} {:v 8 :s 0} {:v 11 :s 0} {:v 10 :s 0} {:v 9 :s 0}] [])))
    (is (= 0
           (royal/p [{:v 12 :s 0} {:v 8 :s 0} {:v 11 :s 0} {:v 10 :s 0}]
                    [{:v 9 :s 0}])))
    (is (= (/ 1 47)
           (royal/p [{:v 12 :s 0} {:v 8 :s 0} {:v 11 :s 0} {:v 10 :s 0}]
                    [{:v 9 :s 1}])))
    (is (= (/ 3 1533939)
           (royal/p [] [{:v 12 :s 0} {:v 10 :s 0} {:v 11 :s 0} {:v 8 :s 0} {:v 9 :s 0}])))
    (is (= (/ 4 1533939)
           (royal/p [] [{:v 6 :s 0} {:v 0 :s 3} {:v 0 :s 1} {:v 2 :s 0} {:v 7 :s 0}])))
    (is (= (/ 1 16215)
           (royal/p [{:v 9 :s 2} {:v 12 :s 2}]
                    [{:v 0 :s 0} {:v 10 :s 1} {:v 1 :s 2}])))))

