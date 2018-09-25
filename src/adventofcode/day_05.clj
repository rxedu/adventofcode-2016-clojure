(ns adventofcode.day-05
  (:require [clojure.string :as string]
            [digest]
            [adventofcode.parse :as parse]))

(def batch-size 8)
(def pass-length 8)
(def hash-index 5)
(def hash-pos-index 5)
(def hash-char-index 6)
(def min-pos 0)
(def max-pos 7)
(def hash-prefix "00000")

(defn get-hash [door idx] (digest/md5 (str door idx)))

(def target-hash? #(string/starts-with? % hash-prefix))

(def get-pass-char #(nth % hash-index))

(def get-pass-kv
  #(hash-map
    :k (parse/integer (str (nth % hash-pos-index)))
    :v (nth % hash-char-index)))

(defn valid-pos?
  [{k :k} pass]
  (and (not (nil? k))
       (<= min-pos k max-pos)
       (not (some #{k} (map :k pass)))))

(defn decrypt-1
  ([door pass idx]
   (let [h (get-hash door idx)]
     (if (target-hash? h)
       (get-pass-char h)
       nil))))

(defn find-pass-1
  [door pass idxs]
  (let [decrypt-door (partial decrypt-1 door pass)
        results (pmap decrypt-door idxs)]
    (remove nil? results)))

(defn decrypt-2
  ([door pass idx]
   (let [h (get-hash door idx)
         pos (get-pass-kv h)]
     (if (and (target-hash? h) (valid-pos? pos pass))
       pos
       nil))))

(defn find-pass-2
  [door pass idxs]
  (let [decrypt-door (partial decrypt-2 door)
        reducer (fn [p idx]
                  (remove nil? (conj p (decrypt-door (concat p pass) idx))))
        results (reduce reducer [] idxs)]
    results))

(defn get-password
  ([find-pass-seq door] (get-password find-pass-seq door pass-length 0))
  ([find-pass-seq door length start]
   (reduce
    (fn [pass idxs]
      (if
       (>= (count pass) length)
        (reduced (take length pass))
        (concat pass (find-pass-seq door pass idxs))))
    []
    (partition batch-size (iterate inc start)))))

(def get-pass-chars (partial get-password find-pass-1))
(def get-pass-pos (partial get-password find-pass-2))

(def read-pass-pos (comp (partial map :v) (partial into (sorted-map))))

(def parse-and-find-password
  (comp string/join get-pass-chars string/trim))

(def parse-and-find-password-2
  (comp string/join read-pass-pos get-pass-pos string/trim))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-find-password
    parse-and-find-password-2)
   input))
