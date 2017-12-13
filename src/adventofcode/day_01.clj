(ns adventofcode.day-01)

(require 'clojure.core.matrix)

(clojure.core.matrix/set-current-implementation :vectorz)

(def rotations
  {:left [[0 -1] [1 0]]
   :right [[0 1] [-1 0]]})

(def start {:dir [0, 1], :pos [0, 0]})

(defn parse-move
  [move]
  (let [dirs {"L" :left "R" :right}
        dir (first move)
        n (clojure.string/join (rest move))]
    (assoc {} :rot (dirs (str dir)) :steps (Integer/parseInt n))))

(defn parse-input
  [input]
  (let [moves (clojure.string/split (clojure.string/trim-newline input) #", ")]
    (map parse-move moves)))

(defn rotate
  "Rotates a unit vector left or right 90 degrees."
  [direction vect]
  (let [rotation (direction rotations)]
    (map int (clojure.core.matrix/mmul rotation vect))))

(defn move
  "Move n steps in a direction"
  [direction steps position]
  (let [movement (map * (vector steps steps) direction)]
    (map + position movement)))

(defn distance
  "Compute taxicab distance from start."
  [position]
  (let [offset (map - position (:pos start))]
    (reduce + (map #(max % (- %)) offset))))

(defn rotate-and-move
  "Rotate and move."
  ([{:keys [dir pos]} {:keys [rot steps]}]
   (let [new-dir (rotate rot dir)
         new-pos (move new-dir steps pos)]
     (assoc {} :dir new-dir :pos new-pos))))

(def parse-move-and-get-distance
  (comp
   distance
   (partial :pos)
   (partial reduce rotate-and-move start)
   parse-input))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [(parse-move-and-get-distance input)])
