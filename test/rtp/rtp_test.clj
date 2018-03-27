(ns rtp.rtp-test
  (:require [clojure.test :refer :all]
            [rtp.calculator :as rtp]))

(deftest final-test
  (testing "RTP of some weird hands"
    (is (= (/ 1625 3)
          (rtp/rtp [{:values [0 9 10 11 12]
                     :suits  [0 0 0 0 0]
                     :times  2}
                    {:values [0 0 0 0 1]
                     :suits  [0 1 2 3 1]
                     :times  1}])))
    (is (= (/ 880336 632385)
           (rtp/rtp [{:values [0 11 10 9 8]
                      :suits  [0 1  0  0 0]
                      :times 1}
                     {:values [5 6 10 5 12]
                      :suits  [0 1 1  2 0]
                      :times 1}
                     {:values [10 9 10 6 0]
                      :suits  [0  1 2  2 2]
                      :times 12}
                     {:values [1 2 5 7 12]
                      :suits  [0 1 2 3 0]
                      :times 1}
                     {:values [1 1 0 12 11]
                      :suits  [1 0 0 0  0]
                      :times 24}])))))

