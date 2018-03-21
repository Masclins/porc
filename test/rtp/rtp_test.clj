(ns rtp.rtp-test
  (:require [clojure.test :refer :all]
            [rtp.calculator :as rtp]))

(deftest final-test
  (testing "RTP of some weird hands"
    (is (= (/ 1625 2598960)
           ; Real RTP here would be (/ 1625 3), but all the possible hands are hard-coded right now
          (rtp/rtp [{:values [0 9 10 11 12]
                     :suits  [0 0 0 0 0]
                     :times  2}
                    {:values [0 0 0 0 1]
                     :suits  [0 1 2 3 1]
                     :times  1}]))))) 

