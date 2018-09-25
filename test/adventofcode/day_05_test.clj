(ns adventofcode.day-05-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-05 :refer
             [get-hash
              get-pass-char
              get-pass-chars
              get-pass-pos
              valid-pos?
              read-pass-pos
              target-hash?]]))

(deftest get-hash-test
  (is (= "71c0a2a39d825b4b4ac6a74257fc6eda" (get-hash "adb" 5)))
  (is (= "00000155f8105dff7f56ee10fa9b9abd" (get-hash "abc" 3231929))))

(deftest target-hash?-test
  (is (false? (target-hash? "71c0a2a39d825b4b4ac6a74257fc6eda")))
  (is (true? (target-hash? "00000155f8105dff7f56ee10fa9b9abd")))
  (is (true? (target-hash? "00000a39d825b4b4ac6a74257fc6eda"))))

(deftest valid-pos?-test
  (is (true? (valid-pos? {:k 4 :v \b} [])))
  (is (false? (valid-pos? {:k 10 :v \b} [])))
  (is (false? (valid-pos? {:k 3 :v \b} [{:k 3 :v \q}])))
  (is (true? (valid-pos? {:k 3 :v \b} [{:k 4 :v \b}]))))

(deftest get-pass-char-test
  (is (= \1 (get-pass-char "00000155f8105dff7f56ee10fa9b9abd"))))

(deftest get-password-chars-test
  (is (= [\1] (get-pass-chars "abc" 1 3231925))))

(deftest get-password-pos-test
  (is (= [{:k 1 :v \5}] (get-pass-pos "abc" 1 3231928))))

(deftest read-pass-pos-test
  (is (= [\5 \a] (read-pass-pos [{:k 2 :v \a} {:k 1 :v \5}]))))
