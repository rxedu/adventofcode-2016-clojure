(ns adventofcode.day-06
  (:require [clojure.string :as string]
            [clojure.core.matrix :as matrix]
            [adventofcode.parse :as parse]))

(defn x-key [f] (comp key (partial apply f val)))
(def top-key (x-key max-key))
(def bot-key (x-key min-key))

(defn find-message
  [f messages]
  (let [chars-in-pos (matrix/transpose messages)
        freq (map frequencies chars-in-pos)
        top-chars (map f freq)]
    (string/join top-chars)))

(def parse-messages (partial parse/map-lines #(clojure.string/split % #"")))

(def parse-and-find-message-1
  (comp (partial find-message top-key) parse-messages))

(def parse-and-find-message-2
  (comp (partial find-message bot-key) parse-messages))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-find-message-1
    parse-and-find-message-2)
   input))
