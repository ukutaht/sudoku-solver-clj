(ns sudoku-solver-clj.core
  (:gen-class))

(defn row-for [[x y] grid]
  (nth grid y))

(defn column-for [[x y] grid]
  (map #(nth % x) grid))

(defn box-for [[x y] grid]
  (let [min-x (* (quot x 3) 3)
        min-y (* (quot y 3) 3)]
    (flatten (map #(subvec % min-x (+ min-x 3)) (subvec grid min-y (+ min-y 3))))))

(defn solve [grid]
  grid)

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