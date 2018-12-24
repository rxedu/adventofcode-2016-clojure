(ns adventofcode.day-09-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-09 :refer
             [decompress
              decompress-first
              decompress-substr
              parse-marker
              split-on-first-marker]]))

(deftest decompress-test
  (is (= "GTYP" (decompress "GTYP")))
  (is (= "ABBBBBC" (decompress "A(1x5)BC")))
  (is (= "ABCBCDEFEFG" (decompress "A(2x2)BCD(2x2)EFG"))))

(deftest decompress-first-test
  (is (= ["" ""] (decompress-first "")))
  (is (= ["GTYP" ""] (decompress-first "GTYP")))
  (is (= ["ABBBBB" "C"] (decompress-first "A(1x5)BC")))
  (is (= ["ABCBC" "D(2x2)EFG"] (decompress-first "A(2x2)BCD(2x2)EFG"))))

(deftest decompress-substr-test
  (is (= ["ABCABC" "YT"] (decompress-substr 3 2 "ABCYT")))
  (is (= ["" ""] (decompress-substr 0 0 ""))))

(deftest split-on-first-marker-test
  (is (= ["" "(3x3)ABCYT"] (split-on-first-marker "(3x3)ABCYT")))
  (is (= ["GTYPW" "(3x3)ABCYT"] (split-on-first-marker "GTYPW(3x3)ABCYT")))
  (is (= ["GTYPW" "(3x3)ABCYT(4x7)JJSB"] (split-on-first-marker "GTYPW(3x3)ABCYT(4x7)JJSB")))
  (is (= ["" ""] (split-on-first-marker "")))
  (is (= ["AFGT" ""] (split-on-first-marker "AFGT"))))

(deftest parse-marker-test
  (is (= [0 0 ""] (parse-marker "")))
  (is (= [3 4 "ABCYT"] (parse-marker "(3x4)ABCYT")))
  (is (= [3 4 "ABCYT(4x5)THNS"] (parse-marker "(3x4)ABCYT(4x5)THNS")))
  (is (= [30 433 "ABCYT"] (parse-marker "(30x433)ABCYT"))))
