(ns sudoku-solver-clj.core
  (:gen-class))

(defn row-for [[x y] board]
  (nth board y))

(defn column-for [[x y] board]
  (map #(nth % x) board))

(defn box-for [[x y] board]
  (let [min-x (* (quot x 3) 3)
        min-y (* (quot y 3) 3)]
    (flatten (map #(subvec % min-x (+ min-x 3)) (subvec board min-y (+ min-y 3))))))

(defn solve [board]
  board)

(defn explode-to-ints [string]
  (map #(Character/digit %1 10) string))

(defn str-to-grid [string board-size]
  "Converts the input string to a 2-dimensional vector"
  (loop [string string
         result []]
    (if (empty? string)
      (reverse result)
      (recur (drop board-size string) (cons (explode-to-ints (take board-size string)) result)))))

(defn -main
  [& args]
    (solve (str-to-grid (first args) (Math/sqrt (count (first args))))))
