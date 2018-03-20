(ns rtp.generator-test
  (:require [clojure.test :refer :all]
            [rtp.generator :as gen]))

; This tests aren't enough to ensure generator works
; Any new test will be appreciated
(deftest check-generator
  (testing "Five distinct denominations generated values"
    (is (= 1287 (count gen/dist))))
  (testing "Pair generated values"
    (is (= 2860 (count gen/pair))))
  (testing "Two pairs generated values"
    (is (= 858 (count gen/dpair))))
  (testing "Trio generated values"
    (is (= 858 (count gen/trio))))
  (testing "Full House generated values"
    (is (= 156 (count gen/full))))
  (testing "Four generated values"
    (is (= 156 (count gen/four)))))
