(ns adventofcode.day-04-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-04 :refer
             [room?
              get-hash
              sum-ids
              parse-room]]))

(def good-room (parse-room "aaaaa-bbb-z-y-x-123[abxyz]"))
(def bad-room (parse-room "qqqq-bbb-zz-t-y-y-x-123[abxyz]"))

(deftest get-hash-test
  (is (= "abxyz" (get-hash good-room)))
  (is (= "qbyzt" (get-hash bad-room))))

; (deftest room?-test
;   (is (true? (room? good-room)))
;   (is (false? (room? bad-room))))

(deftest sum-ids-test
  (is (= 246 (sum-ids [good-room good-room bad-room]))))

(deftest parse-room-test
  (is (= {:letters ["a" "a" "a" "a" "a" "b" "b" "b" "z" "y" "y" "x"]
          :hash "abxyz"
          :id 123}
         (parse-room "aaaaa-bbb-z-y-y-x-123[abxyz]"))))
