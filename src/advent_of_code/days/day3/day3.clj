(ns advent-of-code.days.day3.day3
  (:require [clojure.string :as str]))

(def data 
    (str/split-lines  (slurp "./src/advent_of_code/days/day3/input.txt")))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" (str s))))

(defn getMostCommonOnIndex
  ""
  [arrayOfArrays
   index]
  (loop [data arrayOfArrays
         i0 0
         i1 0]
    (if-let [array (first data)]

        (if-let [item (nth array index)]

          (recur
           (next data)
           (if (= (parse-int item) 0) (inc i0) i0)
           (if (= (parse-int item) 1) (inc i1) i1)))
      (if (<= i0 i1) 1 0))))

(defn getMostUsedBits
  ""
  [data]
  (loop [
        data data
        indexes [0 1 2 3 4 5 6 7 8 9 10 11]
        items [] 
  ]
    (if-let [index (first indexes)]
      (recur data (next indexes) (conj items (getMostCommonOnIndex data index)))
    items)))

(defn getLeastCommonOnIndex
  ""
  [arrayOfArrays
   index]
  (loop [data arrayOfArrays
         i0 0
         i1 0]
    (if-let [array (first data)]

        (if-let [item (nth array index)]

          (recur
           (next data)
           (if (= (parse-int item) 0) (inc i0) i0)
           (if (= (parse-int item) 1) (inc i1) i1)))
      (if (<= i0 i1) 0 1))))


(defn getLeastUsedBits
  ""
  [data]
  (loop [
        data data
        indexes [0 1 2 3 4 5 6 7 8 9 10 11]
        items [] 
  ]
    (if-let [index (first indexes)]
      (recur data (next indexes) (conj items (getLeastCommonOnIndex data index)))
    items)))

(def hasCorrectBit?
  (fn
    [item correctBit index]
    (= (parse-int (nth item index)) (parse-int correctBit))))

(defn get-co2-rating

  [data]
  (loop [
         data data
         indexes [0 1 2 3 4 5 6 7 8 9 10 11]] 
    (if-let [index (first indexes)]
      (do
        (def correctBits
          (getLeastUsedBits data))
        (def filteredItems
          (filter #(hasCorrectBit? % (nth correctBits index) index) data))
        (if
         (< 1 (count filteredItems))
          (recur filteredItems (next indexes))
        filteredItems)))))

(defn get-oxygen-rating

  [data]
  (loop [
                data data
                indexes [0 1 2 3 4 5 6 7 8 9 10 11]
  ] (if-let [index (first indexes)]
      (do
        (def correctBits
          (getMostUsedBits data))
        (def filteredItems
          (filter #(hasCorrectBit? % (nth correctBits index) index) data))
        (if
         (< 1 (count filteredItems))
          (recur filteredItems (next indexes))
        filteredItems)))))
  
(defn solve
  "https://adventofcode.com/2021/day/3"
  [& args]
  (println "Day3")

  [1 1 1 0 1 1 0 1 0 1 0 1]
  [0 0 0 1 0 0 1 0 1 0 1 0]

  (println data)
  (println (getMostUsedBits data))
  (println (getLeastUsedBits data))
  (println (get-oxygen-rating data))
  (println (get-co2-rating data))
  (println (* 298 3797))
  (println (* 4089 1923))
  (println ""))

