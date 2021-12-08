(ns advent-of-code.days.day4.day4)(ns advent-of-code.days.day3.day3
  (:require [clojure.string :as str]))

(def boards 
    (str/split-lines  (slurp "./src/advent_of_code/days/day4/input.txt")))

(def numbers [23 30 70 61 79 49 19 37 64 48 72 34 69 53 15 74 89 38 46 36 28 32 45 2 39 58 11 62 97 40 14 87 96 94 91 92 80 99 6 31 57 98 65 10 33 63 42 17 47 66 26 22 73 27 7 0 55 8 56 29 86 25 4 12 51 60 35 50 5 75 95 44 16 93 21 3 24 52 77 76 43 41 9 84 67 71 83 88 59 68 85 82 1 18 13 78 20 90 81 54])

(defn parse-int [s]
  (Integer. (re-find  #"\d+" (str s))))
  
(defn solve
  "https://adventofcode.com/2021/day/3"
  [& args]
  (println "Day4")
  (println data)

  (println ""))
