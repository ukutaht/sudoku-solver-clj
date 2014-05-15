(ns sudoku-solver-clj.core-test
  (:require [clojure.test :refer :all]
            [sudoku-solver-clj.core :refer :all]))

(def easy-puzzle [[1 0 5 8 0 2 0 0 0]
                  [0 9 0 0 7 6 4 0 5]
                  [2 0 0 4 0 0 8 1 9]
                  [0 1 9 0 0 7 3 0 6]
                  [7 6 2 0 8 3 0 9 0]
                  [0 0 0 0 6 1 0 5 0]
                  [0 0 7 6 0 0 0 3 0]
                  [4 3 0 0 2 0 5 0 1]
                  [6 0 0 3 0 8 9 0 0]])

(defn no-zeros? [grid]
  (not (some zero? (flatten grid))))

(defn difference-in-non-zero-values? [coll1 coll2]
  (loop [coll1 coll1
         coll2 coll2]
    (if (empty? coll1) false)
    (if (and (not= (first coll1) 0) (not= (first coll1) (first coll2)))
      false
      (recur (rest coll1) (rest coll2)))))

(defn matches? [grid1 grid2]
  (loop [grid1 grid1
         grid2 grid2]
    (if (empty? grid1) true)
    (if (difference-in-non-zero-values? (first grid1) (first grid2))
      false
      (recur (rest grid1) (rest grid2)))))

(defn valid-solution? [solution initial-grid]
  (and (no-zeros? solution) (matches? solution initial-grid)))


(deftest parsing
  (testing "Creating a 2 dimensional vector from input string"
    (is (= easy-puzzle
         (str-to-grid "105802000090076405200400819019007306762083090000061050007600030430020501600308900" 9)))))

(deftest grid-access
  (testing "Getting column"
    (is (= [1 0 2 0 7 0 0 4 6]
         (column-for [0 0] easy-puzzle))))
  (testing "Getting box"
    (is (= [3 0 6 0 9 0 0 5 0]
          (box-for [7 4] easy-puzzle)))
    (is (= [1 0 5 0 9 0 2 0 0]
          (box-for [0 0] easy-puzzle))))
  (testing "Getting row"
    (is (= [0 9 0 0 7 6 4 0 5]
         (row-for [1 1] easy-puzzle)))))

(deftest solving
  (testing "Easy puzzle"
    (is (valid-solution? (solve easy-puzzle) easy-puzzle))))
