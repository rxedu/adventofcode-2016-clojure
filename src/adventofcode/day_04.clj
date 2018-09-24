(ns adventofcode.day-04
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(defn parse-room
  [room]
  {:id ((comp parse/integer first) (string/split (last (string/split room #"-")) #"\["))
   :letters ((comp
              (partial map str)
              flatten
              (partial map vec)
              butlast)
             (string/split room #"-"))
   :hash ((comp (partial apply str) drop-last last) (string/split room #"\["))})
