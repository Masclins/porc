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
                           :suits  [1]})))
    (is (= (/ 25 47)
           (play/find-rtp {:values [0 11 10 9]
                           :suits  [0 1 0 0]}
                          {:values [8]
                           :suits  [0]})))
    (is (= (/ 38 47)
           (play/find-rtp {:values [11 10 9 8]
                           :suits  [1 0 0 0]}
                          {:values [0]
                           :suits  [0]})))
    (is (= (/ 60 47)
           (play/find-rtp {:values [0 10 9 8]
                           :suits  [0 0 0 0]}
                          {:values [11]
                           :suits  [1]})))
    (is (= (/ 703 1081)
           (play/find-rtp {:values [10 9 8]
                           :suits  [0 0 0]}
                          {:values [0 11]
                           :suits  [0 1]})))
    (is (= (/ 1372 1081)
           (play/find-rtp {:values [0 10 9]
                           :suits  [0 0 0]}
                          {:values [11 8]
                           :suits  [1 0]}))))
  (testing "Find RTP following best strategy"
    (is (= 800
           (play/find-best-rtp {:values [0 9 10 11 12]
                                :suits  [0 0 0 0 0]})))
    (is (= 25
           (play/find-best-rtp {:values [0 0 0 0 1]
                                :suits  [0 1 2 3 1]})))
    (is (= (/ 60 47)
           (play/find-best-rtp {:values [0 11 10 9 8]
                                :suits  [0 1 0 0 0]})))
    (is (= (/ 1391 1081)
           (play/find-best-rtp {:values [0 10 9 8 5]
                                :suits  [0 0 0 0 1]})))
    (is (= (/ 1426 1081)
           (play/find-best-rtp {:values [0 11 10 9 8]
                                :suits  [0 1 0 0 2]})))
    (is (= (/ 4452 5405)
           (play/find-best-rtp {:values [5 6 10 5 12]
                                :suits  [0 1 1 2 0]})))
    (is (= (/ 1661 1081)
           (play/find-best-rtp {:values [10 9 10 6 0]
                                :suits  [0 1 2 2 2]})))
    (is (= (/ 1532 3243)
           (play/find-best-rtp {:values [1 2 5 7 12]
                                :suits  [0 1 2 3 0]})))
    (is (= (/ 1499 1081)
           (play/find-best-rtp {:values [1 1 0 12 11]
                                :suits  [1 0 0 0 0]})))))
    
    
