(ns adventofcode.day-08-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-08 :refer
             [rect-screen
              rotate-screen
              parse-rect
              parse-rotate]]))

(deftest rect-screen-test
  (is (= [[1 1 0] [1 1 0]] (rect-screen [[0 1 0] [0 0 0]] {:x 2 :y 2}))))

(deftest rotate-screen-test
  (is (= [[1 0 1] [1 1 0]] (rotate-screen [[0 1 1] [1 1 0]] {:dim 0 :n 1 :idx 0}))))

(deftest parse-rect-test
  (is (= {:type :rect :x 3 :y 5}
         (parse-rect "rect 3x5"))))

(deftest parse-rotate-test
  (is (= {:type :rotate :dim 1 :idx 4 :n 5}
         (parse-rotate "rotate row y=4 by 5"))))
