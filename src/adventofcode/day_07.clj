(ns adventofcode.day-07
  (:require [clojure.string :as string]
            [clojure.core.matrix :as matrix]
            [adventofcode.parse :as parse]))

(def abba?
  (every-pred
   #(= (count %) 4)
   #(= (first %) (last %))
   #(= (nth % 1) (nth % 2))
   #(not= (first %) (nth % 1))))

(def find-substrs #(string/split % #"\[[^\[]+\]"))

(def find-hypernets
  (let [xform (comp
               (map (partial drop 1))
               (map drop-last))]
    (comp
     (partial map string/join)
     (partial into [] xform)
     #(re-seq #"\[[^\[]+\]" %))))

(defn has-abba?
  [s]
  (first
   (reduce
    (fn [[state cur] n]
      (if (abba? (take 4 cur))
        (reduced [true])
        [state (drop n s)]))
    [false s]
    (range (count s)))))

(defn tls?
  [s]
  (let [substrs (find-substrs s)
        hypernets (find-hypernets s)]
    (and (some has-abba? substrs)
         (not-any? has-abba? hypernets))))

(def parse-and-count-tls
  (let [xform (comp
               (map tls?)
               (filter true?))
        pipeline (partial into [] xform)]
    (comp count pipeline parse/lines)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-count-tls)
   input))
