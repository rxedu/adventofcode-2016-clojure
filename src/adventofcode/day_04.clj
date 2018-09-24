(ns adventofcode.day-04
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(defn parse-room
  [room]
  {:id ((comp parse/integer first) (string/split (last (string/split room #"-")) #"\["))
   :letters ((comp
              (partial map str)
              flatten
              (partial map vec)
              butlast)
             (string/split room #"-"))
   :hash ((comp (partial apply str) drop-last last) (string/split room #"\["))})

(defn get-top-groups
  [{hash :hash xs :letters}]
  (let [freq (frequencies xs)
        top-counts (set ((comp (partial take 5) reverse sort vals) freq))
        groups (group-by
                (fn [l] (let [n (freq l)
                              top (contains? top-counts n)]
                          (if top n :drop))) (set xs))]
    (dissoc groups :drop)))

(defn get-hash
  [room]
  (let [groups (get-top-groups room)
        sorted-groups (into (sorted-map-by >) groups)
        sorted-vals (map sort (vals sorted-groups))]
    (apply str ((comp (partial take 5) flatten) sorted-vals))))

(defn room?
  [room]
  (let [expected (get-hash room)
        actual (:hash room)]
    (= actual expected)))

(def sum-ids
  (let [xform (comp (filter room?) (map :id))]
    (partial transduce xform + 0)))

(def parse-rooms
  (partial parse/map-lines parse-room))

(def parse-and-sum-ids
  (comp sum-ids parse-rooms))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-sum-ids)
   input))
