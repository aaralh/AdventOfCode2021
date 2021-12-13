(ns advent-of-code.days.day4.day4
  (:require [clojure.string :as str]))

(def boards
  (str/split (slurp "./src/advent_of_code/days/day4/input.txt") #"\n\n"))

(def numbers [23 30 70 61 79 49 19 37 64 48 72 34 69 53 15 74 89 38 46 36 28 32 45 2 39 58 11 62 97 40 14 87 96 94 91 92 80 99 6 31 57 98 65 10 33 63 42 17 47 66 26 22 73 27 7 0 55 8 56 29 86 25 4 12 51 60 35 50 5 75 95 44 16 93 21 3 24 52 77 76 43 41 9 84 67 71 83 88 59 68 85 82 1 18 13 78 20 90 81 54])

(defn getRowNumbers
  [row]
  (str/split (str/trim(clojure.string/replace row #"  " " ")) #" "))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" (str s))))

(defn getRows
  [board]
  (for [row (str/split board #"\n")]
    (getRowNumbers row)))

(defn getRow
  [board
   index]
  (getRowNumbers (nth (getRows board) index)))

(defn getColumn
  [board
   index]
  (for [row (getRows board)
        :let [number (nth row index)]] number))

(defn getColumns
  [board]
  (for [index (range 5)
        :let [column (getColumn board index)]]
    column))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (def found
    (some #(= elm %) coll))
  (if (= found nil) false found))

(defn checkRowsForBingo
  [rows
   numbers]
  (loop [rows rows
         results []]
    (if-let [row (first rows)]
      (do
        (def result
          (for [number row]
            (do
              (in? numbers (parse-int number)))))
        (if (in? result false)
          (do
            (recur (next rows) (conj results (in? result false))))
          true)
        ))))

(defn hasBoardBingo
  [board
   numbers]
  (if (or
   (checkRowsForBingo (getRows board) numbers)
   (checkRowsForBingo (getColumns board) numbers)) true false))

(defn getFirstWinningBoard
  [boards]
  (loop [boards boards
         numbers (next numbers)
         usedNumbers (list (first numbers))
         winner nil
         currentBoardIndex 0
         currentNumber nil]
    (if (and (= winner nil) (not= numbers nil))
      (do
        (def isBingo
          (hasBoardBingo (nth boards currentBoardIndex) usedNumbers))
        (if (not= isBingo true)
          (recur
           boards
           (if (and (= currentBoardIndex (- (count boards) 1)) (not isBingo)) (next numbers) numbers)
           (if (and (= currentBoardIndex (- (count boards) 1)) (not isBingo)) (conj usedNumbers (first numbers)) usedNumbers)
           (if isBingo (nth boards currentBoardIndex) nil)
           (if (< currentBoardIndex (- (count boards) 1))
             (inc currentBoardIndex) 0)
           (first usedNumbers))
          (list (nth boards currentBoardIndex)  usedNumbers currentNumber))
        ))))

(defn getLastWinningBoard
  [boards])



(defn solve
  "https://adventofcode.com/2021/day/4"
  [& args]
  (println "Day4")
  (println "")
  (def result (getFirstWinningBoard boards))
  (println result)
  (println (flatten(getRows (first result))))
  (println "")
  (println (type (first (flatten(getRows (first result))))) (type (first (second result))))
  (println (set (map parse-int(flatten (getRows (first result))))))
  (println (remove (set (second result)) (map parse-int (flatten (getRows (first result))))))
  (println (*  32 (reduce +
   (remove (set (second result)) (map parse-int (flatten (getRows (first result))))))))

  (println (parse-int (nth result 2)))
  (println (reduce +
   (remove (set (second result)) (map parse-int (flatten (getRows (first result)))))))
"  (def test 
    (list 50 98 65 14 47))
  (println (hasBoardBingo (first boards) test))"

  (println ""))

