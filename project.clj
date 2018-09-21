(defproject adventofcode-2016 "1.0.0"
  :description "Solutions to the Advent of Code 2016 puzzles."
  :url "https://github.com/rxedu/adventofcode-2016"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [net.mikera/core.matrix "0.62.0"]
                 [net.mikera/vectorz-clj "0.48.0"]]
  :main ^:skip-aot adventofcode.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
