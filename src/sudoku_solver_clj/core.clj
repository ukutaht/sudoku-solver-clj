(ns sudoku-solver-clj.core
  (:require [clojure.set :refer [union difference]])
  (:gen-class))

(defmacro dbg[x] `(let [x# ~x] (println "dbg:" '~x "=" x#) x#))

(defn row-for [[x y] grid]
  (nth grid y))

(defn column-for [[x y] grid]
  (map #(nth % x) grid))

(defn box-for [[x y] grid]
  (let [min-x (* (quot x 3) 3)
        min-y (* (quot y 3) 3)]
    (flatten (map #(subvec % min-x (+ min-x 3)) (subvec grid min-y (+ min-y 3))))))

(defn solved? [grid]
  (not (some zero? (flatten grid))))

(defn solve-cell [x y grid]
  (if-not (zero? (nth (nth grid y) x))
    (nth (nth grid y) x)
    (let [peers (remove zero? (clojure.set/union (set (row-for [x y] grid))
                                   (set (column-for [x y] grid))
                                   (set (box-for [x y] grid))))
          dif    (clojure.set/difference (set (range 1 10)) peers)]

          (if (= (count dif) 1)
            (first dif)
             0))))

(defn solve-row [n grid]
  (loop [row (nth grid n)
         acc []
         i    0]
    (if (empty? row)
      acc
      (recur (rest row) (conj acc (solve-cell i n grid)) (inc i)))))

(defn solve [grid]
    (loop [grid grid
          incer (cycle (range 9))]
      (if (solved? grid)
        grid
        (recur (assoc grid (first incer) (solve-row (first incer) grid)) (rest incer)))))

(defn explode-to-ints [string]
  (map #(Character/digit %1 10) string))

(defn str-to-grid [string grid-size]
  "Converts the input string to a 2-dimensional vector"
  (loop [string string
         result []]
    (if (empty? string)
      (reverse result)
      (recur (drop grid-size string) (cons (explode-to-ints (take grid-size string)) result)))))

(defn -main
  [& args]
    (solve (str-to-grid (first args) (Math/sqrt (count (first args))))))