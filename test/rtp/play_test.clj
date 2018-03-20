(ns rtp.play-test
  (:require [clojure.test :refer :all]
            [rtp.play :as play]))

(deftest check-rtp
  (testing "RTP of specific plays"
    (is (= 800
           (play/find-rtp {:values [0 9 10 11 12]
                           :suits  [0 0 0 0 0]}
                          {:values [] :suits []})))
    (is (= 50
           (play/find-rtp {:values [1 2 3 4 5]
                           :suits  [1 1 1 1 1]}
                          {:values [] :suits []})))
    (is (= (/ 925 47) 
           (play/find-rtp {:values [9 10 11 12]
                           :suits  [0 0 0 0]}
                          {:values [5]
                           :suits  [1]}))))
  (testing "Find RTP following best strategy"
    (is (= 800
           (play/find-best-rtp {:values [0 9 10 11 12]
                                :suits  [0 0 0 0 0]})))
    (is (= 25
           (play/find-best-rtp {:values [0 0 0 0 1]
                                :suits  [0 1 2 3 1]})))
    (is (let [rtp (play/find-best-rtp {:values [0 11 10 9 8]
                                       :suits  [0 1 0 0 0]})]
          (and (> rtp 1.276595) (< rtp 1.276597))))
    (is (let [rtp (play/find-best-rtp {:values [5 6 10 5 12]
                                       :suits  [0 1 1 2 0]})]
          (and (> rtp 0.823681) (< rtp 0.823683))))
    (is (let [rtp (play/find-best-rtp {:values [10 9 10 6 0]
                                       :suits  [0 1 2 2 2]})]
          (and (> rtp 1.536539) (< rtp 1.536541))))))
