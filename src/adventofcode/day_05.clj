(ns adventofcode.day-05
  (:require [clojure.string :as string]
            [digest]
            [adventofcode.parse :as parse]))

(def pass-length 8)
(def hash-index 5)
(def hash-prefix "00000")

(defn get-hash [door idx] (digest/md5 (str door idx)))

(def target-hash? #(string/starts-with? % hash-prefix))

(def get-pass-char #(nth % hash-index))

(defn search-next
  [pass door idx]
  (let [h (get-hash door idx)
        next-char (get-pass-char h)]
    (if (target-hash? h)
      (conj pass next-char)
      pass)))

(defn get-password
  ([door] (get-password door pass-length 0))
  ([door length start]
   (reduce
    (fn [pass idx]
      (if
       (= (count pass) length)
        (reduced pass)
        (search-next pass door idx)))
    []
    (range start))))

(def parse-and-find-password
  (comp string/join get-password string/trim))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-find-password)
   input))
