(ns adventofcode.day-09
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(defn decompress
  [num-chars num-times s]
  (let [[substr rest-s] (split-at num-chars s)
        substrs (repeat num-times (string/join substr))
        xs (reverse (conj substrs (apply str rest-s)))]
    (string/join xs)))
