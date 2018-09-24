(ns adventofcode.day-04
  (:require [clojure.string :as string]
            [adventofcode.parse :as parse]))

(def hash-length 5)
(def target-room "northpole object storage")

(defn parse-room
  [room]
  (let [xs ((comp (partial map vec) butlast)
            (string/split room #"-"))]
    {:id ((comp parse/integer first)
          (string/split (last (string/split room #"-")) #"\["))
     :name-parts (map vec xs)
     :letters ((comp (partial map str) flatten) xs)
     :hash ((comp (partial apply str) drop-last last)
            (string/split room #"\["))}))

(def inc-char (comp char inc int))
(defn inc-alph [c] (if (= c \z) \a (inc-char c)))

(defn shift-char
  [n c]
  (nth (iterate inc-alph c) n))

(defn get-name
  [id name-parts]
  (let [shift-part (partial map (partial shift-char id))
        dec-name-parts (map shift-part name-parts)]
    (string/join " " (map (partial apply str) dec-name-parts))))

(defn get-top-groups
  [{xs :letters}]
  (let [freq (frequencies xs)
        top-counts (set ((comp
                          (partial take hash-length)
                          reverse
                          sort
                          vals)
                         freq))
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
    (apply str ((comp (partial take hash-length) flatten) sorted-vals))))

(defn room?
  [room]
  (let [expected (get-hash room)
        actual (:hash room)]
    (= actual expected)))

(def xnames
  (comp
   (filter room?)
   (map #(hash-map :id (:id %) :name (get-name (:id %) (:name-parts %))))
   (filter #(= target-room (:name %)))
   (map :id)))

(def sum-ids
  (let [xform (comp (filter room?) (map :id))]
    (partial transduce xform + 0)))

(def parse-rooms
  (partial parse/map-lines parse-room))

(def parse-and-sum-ids
  (comp sum-ids parse-rooms))

(def parse-and-get-name
  (comp first (partial into [] xnames) parse-rooms))

(defn solve
  "Given the input for the day, returns the solution."
  [input]
  ((juxt
    parse-and-sum-ids
    parse-and-get-name)
   input))
