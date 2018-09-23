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

(def button-coordinates square-button-coordinates)

(def starting-coordinate (button-coordinates starting-button))
(def coordinate-buttons (clojure.set/map-invert button-coordinates))

(defn is-on-allowed-grid [point] (contains? coordinate-buttons point))

(defn move-on-grid
  [point movement]
  (map + point movement))

(defn move-on-allowed-grid
  [point movement]
  (let [new-point (move-on-grid point movement)]
    (if (is-on-allowed-grid new-point) new-point point)))

(defn move-to-button
  [button direction]
  (let [point (button-coordinates button)
        movement (direction-movements direction)
        new-point (move-on-allowed-grid point movement)]
    (coordinate-buttons new-point)))

(def move-for-sequence-to-button (partial reduce move-to-button))

(defn code-reducer
  [acc directions]
  (let [{button :button code :code} acc
        next-button (move-for-sequence-to-button button directions)]
    (hash-map :button next-button :code (conj code next-button))))

(defn find-code
  [button directions]
  (:code (reduce code-reducer {:button button :code []} directions)))

(defn parse-input
  "Parse list of directions into a list of lists"
  [input]
  (let [lines (clojure.string/split-lines (clojure.string/trim-newline input))]
    (map (partial map str) lines)))

(def parse-directions-and-get-code
  (comp (partial find-code starting-button) parse-input))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [(parse-directions-and-get-code input)
   (parse-directions-and-get-code input)])
