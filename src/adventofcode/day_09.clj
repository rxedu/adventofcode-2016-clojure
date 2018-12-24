(ns adventofcode.day-09
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(defn decompress-substr
  [num-chars num-times s]
  (let [[substr rest-s] (split-at num-chars s)
        substrs (repeat num-times (string/join substr))
        xs (reverse (conj substrs (apply str rest-s)))]
    [(string/join (butlast xs)) (last xs)]))

(defn parse-marker [x]
  (if (empty? x) [0 0 ""]
      (let [y (string/split x #"\)")
            [a b] (string/split (first y) #"x")
            n (parse/integer (string/join (rest a)))
            m (parse/integer b)]
        [n m (string/join ")" (rest y))])))

(defn split-on-first-marker [x]
  (let [a (parse/split-first #"\(" x)]
    (if (= (count a) 1)
      (conj a "")
      (let [b (parse/split-first #"\)" (last a))
            c (str "(" (first b) ")" (last b))]
        (vector (first a) c)))))

(defn decompress-first [x]
  (let [[prv nxt] (split-on-first-marker x)
        [n m s] (parse-marker nxt)
        decompressed (decompress-substr n m s)
        done (string/join [prv (first decompressed)])]
    [done (last decompressed)]))

(defn decompress
  [x]
  (reduce
   (fn [v i]
     (let [acc (first v)
           s (last v)]
       (if (empty? s) (reduced acc)
           (let [decompressed (decompress-first s)]
             (vector
              (str acc (first decompressed))
              (last decompressed))))))
   (vector "" x)
   (range (count x))))

(def decompressed-length (comp count string/trim decompress))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    decompressed-length)
   input))
