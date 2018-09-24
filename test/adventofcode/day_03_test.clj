(ns adventofcode.day-03-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-03 :refer
             [triangle?
              count-triangles
              get-vertical-triangles
              parse-triangle
              parse-input
              parse-and-count-triangles]]))

(deftest triangle?-test
  (is (true? (triangle? 3 4 5)))
  (is (false? (triangle? 5 10 25)))
  (is (false? (triangle? 10 5 5)))
  (is (false? (triangle? 10 4 5))))

(deftest parse-triangle-test
  (is (= [827 2 26] (parse-triangle "  827    2   26")))
  (is (= [7 2 26] (parse-triangle "    7  2  26"))))

(deftest parse-input-test
  (is (= [[827 272 126] [77 43 2]]
         (parse-input "  827  272  126\n   77   43    2"))))

(deftest count-triangles-test
  (is (= 1 (count-triangles [[3 4 5] [2 3 100] [827 272 126]]))))

(deftest parse-and-count-triangles-test
  (is (= 1 (parse-and-count-triangles
            "  827  272  126\n   77   43   22\n    3    4    5"))))

(deftest get-vertical-triangles-test
  (is (= (set [[827 77 55]
               [272 43 9]
               [126 2 11]
               [27 7 5]
               [72 3 9]
               [26 2 1]])
         (set (get-vertical-triangles
               [[827 272 126] [77 43 2] [55 9 11]
                [27 72 26] [7 3 2] [5 9 1]])))))
