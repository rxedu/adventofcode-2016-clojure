(ns adventofcode.day-05-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-05 :refer
             [get-hash
              get-pass-char
              target-hash?
              get-password]]))

(deftest get-hash-test
  (is (= "71c0a2a39d825b4b4ac6a74257fc6eda" (get-hash "adb" 5)))
  (is (= "00000155f8105dff7f56ee10fa9b9abd" (get-hash "abc" 3231929))))

(deftest target-hash?-test
  (is (false? (target-hash? "71c0a2a39d825b4b4ac6a74257fc6eda")))
  (is (true? (target-hash? "00000155f8105dff7f56ee10fa9b9abd")))
  (is (true? (target-hash? "00000a39d825b4b4ac6a74257fc6eda"))))

(deftest get-pass-char-test
  (is (= \1 (get-pass-char "00000155f8105dff7f56ee10fa9b9abd"))))

(deftest get-password-test
  (is (= [\1 \8] (get-password "abc" 2 3231925))))
