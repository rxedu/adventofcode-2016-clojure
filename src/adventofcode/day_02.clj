(ns adventofcode.day-02
  (:require [clojure.set]))

(def starting-button 5)

(def square-button-coordinates
  {1 [-1, 1]
   2 [0 1]
   3 [1 1]
   4 [-1 0]
   5 [0 0]
   6 [1 0]
   7 [-1 -1]
   8 [0 -1]
   9 [1 -1]})

(def star-button-coordinates
  {1 [0, 2]
   2 [-1 1]
   3 [0 1]
   4 [1 1]
   5 [-2 0]
   6 [-1 0]
   7 [0 0]
   8 [1 0]
   9 [2 0]
   "A" [-1 -1]
   "B" [0 -1]
   "C" [1 -1]
   "D" [0 -2]})

(def direction-movements
  {"U" [0 1]
   "D" [0 -1]
   "L" [-1 0]
   "R" [1 0]})

(def get-coordinate-buttons clojure.set/map-invert)

(defn is-on-allowed-grid
  [button-coordinates point]
  (let [coordinate-buttons (get-coordinate-buttons button-coordinates)]
    (contains? coordinate-buttons point)))

(defn move-on-grid
  [point movement]
  (map + point movement))

(defn move-on-allowed-grid
  [button-coordinates point movement]
  (let [new-point (move-on-grid point movement)]
    (if (is-on-allowed-grid button-coordinates new-point) new-point point)))

(defn move-to-button
  [button-coordinates button direction]
  (let [coordinate-buttons (get-coordinate-buttons button-coordinates)
        point (button-coordinates button)
        movement (direction-movements direction)
        new-point (move-on-allowed-grid button-coordinates point movement)]
    (coordinate-buttons new-point)))

(defn move-for-sequence-to-button
  [button-coordinates & args]
  (apply (partial reduce (partial move-to-button button-coordinates)) args))

(defn find-code
  [button-coordinates button directions]
  (rest (reductions
         (partial move-for-sequence-to-button button-coordinates)
         starting-button
         directions)))

(defn parse-input
  "Parse list of directions into a list of lists"
  [input]
  (let [lines (clojure.string/split-lines (clojure.string/trim-newline input))]
    (map (partial map str) lines)))

(defn parse-directions-and-get-code
  [button-coordinates input]
  ((comp (partial find-code button-coordinates starting-button)
         parse-input)
   input))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    (partial parse-directions-and-get-code square-button-coordinates)
    (partial parse-directions-and-get-code star-button-coordinates))
   input))
