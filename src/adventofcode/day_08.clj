(ns adventofcode.day-08
  (:require [clojure.string :as string]
            [clojure.core.matrix :as matrix]
            [adventofcode.parse :as parse]))

(def screen (matrix/new-matrix 50 6))

(defn rect-screen
  [m {:keys [x y]}]
  (matrix/emap-indexed
   (fn [[i j] el] (int (if (and (< i x) (< j y)) 1 el)))
   m))

(defn rotate-screen
  [m {:keys [dim idx n]}]
  (let [get-fn (get {0 matrix/get-row 1 matrix/get-column} dim)
        set-fn (get {0 matrix/set-row 1 matrix/set-column} dim)
        v (get-fn m idx)
        new-v (reverse (matrix/rotate (reverse v) 0 n))]
    (set-fn m idx new-v)))

(def actions {:rect rect-screen :rotate rotate-screen})

(defn parse-rect
  [s]
  (let [parts (string/split s #" ")
        [x y] (string/split (last parts) #"x")]
    {:type :rect
     :x (parse/integer x) :y (parse/integer y)}))

(defn parse-rotate
  [s]
  (let [parts (string/split s #" ")
        [dim idx] (string/split (nth parts 2) #"=")
        n (last parts)
        dims {"x" 0 "y" 1}]
    {:type :rotate
     :dim (dims dim) :idx (parse/integer idx) :n (parse/integer n)}))

(defn parse-instruction
  [s]
  (if (string/starts-with? s "rect")
    (parse-rect s)
    (parse-rotate s)))

(def animate
  (partial
   reduce
   (fn [s {t :type :as i}] ((t actions) s i))
   screen))

(defn parse-and-x
  [f]
  (comp
   f
   animate
   (partial parse/map-lines parse-instruction)))

(def display
  (comp
   #(doseq [x %] (println x))
   (partial matrix/transpose)
   (partial matrix/emap {0 " " 1 "#"})))

(def parse-and-count (parse-and-x (partial matrix/ereduce +)))

(def parse-and-display (parse-and-x display))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-count
    parse-and-display)
   input))
