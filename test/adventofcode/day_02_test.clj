(ns adventofcode.day-02-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-02 :refer
             [move-on-allowed-grid
              move-to-button
              move-for-sequence-to-button
              find-code
              parse-input
              parse-directions-and-get-code]]))

(deftest move-on-allowed-grid-test
  (is (= [-1 -1] (move-on-allowed-grid [0 0] [-1 -1])))
  (is (= [1 0] (move-on-allowed-grid [1 0] [1 0]))))

(deftest move-to-button-test
  (is (= 2 (move-to-button 5 "U")))
  (is (= 2 (move-to-button 2 "U"))))

(deftest move-for-sequence-to-button-test
  (is (= 7 (move-for-sequence-to-button 5 ["U" "U" "D" "L" "L" "D"]))))

(deftest find-code-test
  (is (= [1 7] (find-code 5 [["U" "L"] ["D" "D"]]))))

(deftest parse-input-test
  (is (= [["U" "U"] ["D"] ["L" "L"]] (parse-input "UU\nD\nLL\n"))))

(deftest parse-directions-and-get-code-test
  (is (= [1 7] (parse-directions-and-get-code "UL\nDD\n"))))
