(ns adventofcode.day-06-test
  (:require [clojure.test :refer [deftest is]]
            [adventofcode.day-06 :refer
             [top-key
              find-message]]))

(def messages
  (map #(clojure.string/split % #"")
       ["eedadn"
        "drvtee"
        "eandsr"
        "raavrd"
        "atevrs"
        "tsrnev"
        "sdttsa"
        "rasrtv"
        "nssdts"
        "ntnada"
        "svetve"
        "tesnvt"
        "vntsnd"
        "vrdear"
        "dvrsen"
        "enarar"]))

(deftest find-message-test
  (is (= "easter" (find-message top-key messages))))
