(ns adventofcode.day-04-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-04 :refer
             [parse-room]]))

(deftest parse-room-test
  (is (= {:letters ["a" "a" "a" "a" "a" "b" "b" "b" "z" "y" "x"]
          :hash "abxyz"
          :id 123}
         (parse-room "aaaaa-bbb-z-y-x-123[abxyz]"))))
