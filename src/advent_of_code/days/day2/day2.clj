(ns advent-of-code.days.day2.day2
  (:require [clojure.string :as str]))

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn calculatePosition
    ""
    [data]
    (loop [data data
           y 0
           x 0]
      (if-let [item (first data)]
        (do
          (recur
         (next data)
         (if (= (first (clojure.string/split item #"\s+")) "down") (+ y (parse-int (second (clojure.string/split item #"\s+")))) (if (= (first (clojure.string/split item #"\s+")) "up") (- y (parse-int (second (clojure.string/split item #"\s+")))) y))
         (if (= (first (clojure.string/split item #"\s+")) "forward") (+ x (parse-int (second (clojure.string/split item #"\s+")))) x)))
        [x y])))

(defn calculatePositionWithAim
    ""
    [data]
    (loop [data data
           aim 0
           y 0
           x 0]
      (if-let [item (first data)]
        (do
          (recur
           (next data)
           (if (= (first (clojure.string/split item #"\s+")) "down") (+ aim (parse-int (second (clojure.string/split item #"\s+")))) (if (= (first (clojure.string/split item #"\s+")) "up") (- aim (parse-int (second (clojure.string/split item #"\s+")))) aim))
           (if (= (first (clojure.string/split item #"\s+")) "forward") (+ y (* aim (parse-int (second (clojure.string/split item #"\s+"))))) y)
           (if (= (first (clojure.string/split item #"\s+")) "forward") (+ x (parse-int (second (clojure.string/split item #"\s+")))) x)))
        [x y])))

(def data 
    (str/split-lines  (slurp "./src/advent_of_code/days/day2/input.txt")))

(defn solve
  "https://adventofcode.com/2021/day/2"
  [& args]
  (println "Day2")

  (println (calculatePosition data))
  (println (calculatePositionWithAim data))
  (println ""))