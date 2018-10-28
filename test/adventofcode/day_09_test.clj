(ns adventofcode.day-09-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-09 :refer
             [decompress]]))

(deftest decompress-test
  (is (= "(3x3)ABC(3x3)ABCYT" (decompress 8 2 "(3x3)ABCYT"))))
