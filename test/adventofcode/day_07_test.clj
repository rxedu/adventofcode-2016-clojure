(ns adventofcode.day-07-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-07 :refer
             [abba?
              aba?
              tls?
              ssl?
              has-abba?
              find-hypernets
              collect-aba
              aba-to-bab
              find-supernets]]))

(deftest abba?-test
  (is (true? (abba? "abba")))
  (is (true? (abba? "xyyx")))
  (is (false? (abba? "adft")))
  (is (false? (abba? "xyzx")))
  (is (false? (abba? "xyyz")))
  (is (false? (abba? "xxyx")))
  (is (false? (abba? "aaaa"))))

(deftest aba?-test
  (is (true? (aba? "aba")))
  (is (true? (aba? "xyx")))
  (is (false? (aba? "adft")))
  (is (false? (aba? "xxx")))
  (is (false? (aba? "xyz")))
  (is (false? (aba? "aaaa"))))

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

(deftest collect-aba-test
  (is (= #{[\a \b \a] [\c \d \c]} (collect-aba "abacdc")))
  (is (= #{[\a \b \a]} (collect-aba "aba")))
  (is (= #{} (collect-aba "xxxxxx")))
  (is (= #{[\y \v \y]} (collect-aba "xxxxyvy"))))

(deftest aba-to-bab-test
  (is (= [\b \a \b] (aba-to-bab [\a \b \a]))))

(deftest ssl?-test
  (is (true? (ssl? "aba[bab]xyz")))
  (is (false? (ssl? "xyx[xyx]xyx"))))

(deftest tls?-test
  (is (true? (tls? "abba[mnop]qrst")))
  (is (false? (tls? "abcd[bddb]xyyx"))))
