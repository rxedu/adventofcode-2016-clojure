(ns adventofcode.day-07-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-07 :refer
             [abba?
              tls?
              has-abba?
              find-hypernets
              find-supernets]]))

(deftest abba?-test
  (is (true? (abba? "abba")))
  (is (true? (abba? "xyyx")))
  (is (false? (abba? "adft")))
  (is (false? (abba? "xyzx")))
  (is (false? (abba? "xyyz")))
  (is (false? (abba? "xxyx")))
  (is (false? (abba? "aaaa"))))

(deftest find-supernets-test
  (is (= ["abc" "qrrt"] (find-supernets "abc[dddjejid]qrrt[dd]")))
  (is (= ["" "qrrt"] (find-supernets "[dddjejid]qrrt[dd]")))
  (is (= ["qrrt"] (find-supernets "qrrt[dd]")))
  (is (= ["qrrtlkjlk"] (find-supernets "qrrtlkjlk")))
  (is (= [] (find-supernets "[qrrtlkjlk]")))
  (is (= ["" "qrrt" "ppp"] (find-supernets "[dddjejid]qrrt[dd]ppp"))))

(deftest find-hypernets-test
  (is (= ["dddjejid" "dd"] (find-hypernets "abc[dddjejid]qrrt[dd]")))
  (is (= ["dddjejid" "dd"] (find-hypernets "[dddjejid]qrrt[dd]")))
  (is (= ["dd"] (find-hypernets "qrrt[dd]")))
  (is (= [] (find-hypernets "qrrtlkjlk")))
  (is (= ["qrrtlkjlk"] (find-hypernets "[qrrtlkjlk]")))
  (is (= ["dddjejid" "dd"] (find-hypernets "[dddjejid]qrrt[dd]ppp"))))

(deftest has-abba?-test
  (is (true? (has-abba? "abba")))
  (is (true? (has-abba? "xxaabbabb")))
  (is (true? (has-abba? "abbaxyyx")))
  (is (false? (has-abba? "")))
  (is (false? (has-abba? "xx")))
  (is (false? (has-abba? "xjxllldjdjdj"))))

(deftest tls?-test
  (is (true? (tls? "abba[mnop]qrst")))
  (is (false? (tls? "abcd[bddb]xyyx"))))
