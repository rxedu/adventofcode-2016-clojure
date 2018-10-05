(ns adventofcode.day-06
  (:require [clojure.string :as string]
            [clojure.core.matrix :as matrix]
            [adventofcode.parse :as parse]))

(def top-key (comp key (partial apply max-key val)))

(defn find-message
  [messages]
  (let [chars-in-pos (matrix/transpose messages)
        freq (map frequencies chars-in-pos)
        top-chars (map top-key freq)]
    (string/join top-chars)))

(def parse-messages (partial parse/map-lines #(clojure.string/split % #"")))
(def parse-and-find-message (comp find-message parse-messages))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-find-message)
   input))
