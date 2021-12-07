(ns advent-of-code.core
  (:gen-class)
  (:require [advent-of-code.days.day1 :as day1])
  (:require [advent-of-code.days.day2.day2 :as day2])
  (:require [advent-of-code.days.day3.day3 :as day3]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (day1/solve)
  (day2/solve)
  (day3/solve))
