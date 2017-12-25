(ns adventofcode.day-01)

(require 'clojure.core.matrix)

(clojure.core.matrix/set-current-implementation :vectorz)

(def rotations
  {:left [[0 -1] [1 0]]
   :right [[0 1] [-1 0]]})

(def start {:dir [0, 1], :pos [0, 0] :pts []})

(defn parse-move
  "Parse move into a map."
  [move]
  (let [dirs {"L" :left "R" :right}
        dir (first move)
        n (clojure.string/join (rest move))]
    (assoc {} :rot (dirs (str dir)) :steps (Integer/parseInt n))))

(defn parse-input
  "Parse list of moves into a list of maps."
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
  [{:keys [dir pos]} {:keys [rot steps]}]
  (let [new-dir (rotate rot dir)
        new-pts (map #(move new-dir % pos) (range 1 (inc steps)))
        new-pos (last new-pts)]
    (assoc {} :dir new-dir :pos new-pos :pts new-pts)))

(def trace-path
  (partial reduce #(conj %1 (rotate-and-move (last %1) %2)) [start]))

(defn stop-at-first-revisit
  [col]
  (reduce
   (fn [visited current]
     (let [next (conj visited current)]
       (if (some #{current} visited)
         (reduced next)
         next)))
   col))

(def parse-move-and-get-distance
  (comp
   distance
   (partial :pos)
   last
   trace-path
   parse-input))

(def parse-move-and-get-distance-to-first-revisit
  (comp
   distance
   first
   stop-at-first-revisit
   (partial apply concat)
   (partial map #(get % :pts))
   trace-path
   parse-input))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  [(parse-move-and-get-distance input)
   (parse-move-and-get-distance-to-first-revisit input)])
