(ns adventofcode.parse
  (:require [clojure.string :as string]))

(defn integer [s]
  (try (Integer/parseInt s)
       (catch Exception e nil)))

(def lines (comp string/split-lines string/trim-newline))

(defn map-lines [f xs] (map f (lines xs)))

(defn split-first [re s]
  (string/split s re 2))
