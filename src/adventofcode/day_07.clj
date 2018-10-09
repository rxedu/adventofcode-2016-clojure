(ns adventofcode.day-07
  (:require [clojure.string :as string]
            clojure.set
            [adventofcode.parse :as parse]))

(def abba?
  (every-pred
   #(= (count %) 4)
   #(= (first %) (last %))
   #(= (nth % 1) (nth % 2))
   #(not= (first %) (nth % 1))))

(def aba?
  (every-pred
   #(= (count %) 3)
   #(= (first %) (last %))
   #(not= (first %) (nth % 1))))

(defn aba-to-bab [xs] (vector (nth xs 1) (first xs) (nth xs 1)))

(def find-supernets #(string/split % #"\[[^\[]+\]"))

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

(defn collect-aba
  [s]
  (first
   (reduce
    (fn [[abas cur] n]
      (let [next-s (drop n s)
            candidate (take 3 cur)]
        (if (aba? candidate)
          (vector (conj abas candidate) next-s)
          [abas next-s])))
    (vector #{} s)
    (range (count s)))))

(defn tls?
  [s]
  (let [supernets (find-supernets s)
        hypernets (find-hypernets s)]
    (and (some has-abba? supernets)
         (not-any? has-abba? hypernets))))

(defn ssl?
  [s]
  (let [supernets (find-supernets s)
        hypernets (find-hypernets s)
        supernet-aba (mapcat collect-aba supernets)
        hypernet-aba (mapcat collect-aba hypernets)
        hypernet-bab (map aba-to-bab hypernet-aba)
        matches (clojure.set/intersection (set supernet-aba) (set hypernet-bab))]
    (> (count matches) 0)))

(defn parse-and-count
  [f]
  (let [xform (comp
               (map f)
               (filter true?))
        pipeline (partial into [] xform)]
    (comp count pipeline parse/lines)))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    (parse-and-count tls?)
    (parse-and-count ssl?))
   input))
