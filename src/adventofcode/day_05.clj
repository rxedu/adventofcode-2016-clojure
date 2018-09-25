(ns adventofcode.day-05
  (:require [clojure.string :as string]
            [digest]
            [adventofcode.parse :as parse]))

(def batch-size 8)
(def pass-length 8)
(def hash-index 5)
(def hash-prefix "00000")

(defn get-hash [door idx] (digest/md5 (str door idx)))

(def target-hash? #(string/starts-with? % hash-prefix))

(def get-pass-char #(nth % hash-index))

(defn decrypt-simple
  [door idx]
  (let [h (get-hash door idx)]
    (if (target-hash? h)
      (get-pass-char h)
      nil)))

(defn find-pass-chars
  [door idxs]
  (let [decrypt (partial decrypt-simple door)
        results (pmap decrypt idxs)]
    (remove nil? results)))

(defn get-password
  ([door] (get-password door pass-length 0))
  ([door length start]
   (reduce
    (fn [pass idxs]
      (if
       (>= (count pass) length)
        (reduced pass)
        (concat pass (find-pass-chars door idxs))))
    []
    (partition batch-size (iterate inc start)))))

(def parse-and-find-password
  (comp string/join get-password string/trim))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-find-password)
   input))
