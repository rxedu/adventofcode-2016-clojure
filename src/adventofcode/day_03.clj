(ns adventofcode.day-03
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(defn triangle-rule? [a b c] (< c (+ a b)))

(defn triangle? [a b c]
  (and
   (triangle-rule? b c a)
   (triangle-rule? a c b)
   (triangle-rule? a b c)))

(defn parse-triangle
  [triangle]
  (let [st (string/trim triangle)
        xs (string/split st #" +")]
    (map parse/integer xs)))

(defn count-triangles
  [triangles]
  (let [reducer (fn [n t] (if (apply triangle? t) (inc n) n))]
    (reduce reducer 0 triangles)))

(def parse-input
  (comp (partial map parse-triangle) rest parse/lines))

(def parse-and-count-triangles
  (comp count-triangles parse-input))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-count-triangles)
   input))
