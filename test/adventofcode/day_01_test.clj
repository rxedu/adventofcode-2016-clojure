(ns adventofcode.day-01-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-01 :refer :all]))

(deftest parse-move-test
  (is (= {:rot :left :steps 7} (parse-move "L7")))
  (is (= {:rot :right :steps 2} (parse-move "R2"))))

(deftest parse-input-test
  (is (= [{:rot :left :steps 4} {:rot :right :steps 365}]
         (parse-input "L4, R365"))))

(deftest rotate-test
  (is (= [-1 0] (rotate :left [0 1])))
  (is (= [1 0] (rotate :right [0 1])))
  (is (= [0 -1] (rotate :right [1 0])))
  (is (= [0 -1] (rotate :left [-1 0]))))

(deftest move-test
  (is (= [3 0] (move [1 0] 3 [0 0])))
  (is (= [4 -3] (move [0 -1] 2 [4 -1])))
  (is (= [10 4] (move [-1 0] 5 [15 4]))))

(deftest distance-test
  (is (= 5 (distance [2 3])))
  (is (= 5 (distance [-2 3])))
  (is (= 7 (distance [0 -7]))))

(deftest rotate-and-move-test
  (is (= {:dir [-1 0] :pos [-5 0] :pts [[-1 0] [-2 0] [-3 0] [-4 0] [-5 0]]}
         (rotate-and-move {:dir [0 1] :pos [0 0]} {:rot :left :steps 5}))))
