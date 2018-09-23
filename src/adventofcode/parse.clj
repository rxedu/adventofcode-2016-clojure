(ns adventofcode.parse
  (:require [clojure.string :as string]))

(defn integer [s]
  (try (Integer/parseInt s)
       (catch Exception e nil)))

(def lines (comp string/split-lines string/trim-newline))
