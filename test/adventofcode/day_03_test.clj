(ns adventofcode.day-03-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-03 :refer
             [triangle?
              count-triangles
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
         (parse-input "\n  827  272  126\n   77   43    2"))))

(deftest count-triangles-test
  (is (= 1 (count-triangles [[3 4 5] [2 3 100] [827 272 126]]))))

(deftest parse-and-count-triangles-test
  (is (= 1 (parse-and-count-triangles
            "\n  827  272  126\n   77   43   22\n    3    4    5"))))
