(ns sudoku-solver-clj.core-test
  (:require [clojure.test :refer :all]
            [sudoku-solver-clj.core :refer :all]))

(def board [[1 0 5 8 0 2 0 0 0]
            [0 9 0 0 7 6 4 0 5]
            [2 0 0 4 0 0 8 1 9]
            [0 1 9 0 0 7 3 0 6]
            [7 6 2 0 8 3 0 9 0]
            [0 0 0 0 6 1 0 5 0]
            [0 0 7 6 0 0 0 3 0]
            [4 3 0 0 2 0 5 0 1]
            [6 0 0 3 0 8 9 0 0]])

(deftest parsing
  (testing "Creating a 2 dimensional vector from input string"
    (is (= board
         (str-to-grid "105802000090076405200400819019007306762083090000061050007600030430020501600308900" 9)))))

(deftest board-access
  (testing "Getting column"
    (is (= [1 0 2 0 7 0 0 4 6]
         (column-for [0 0] board))))
  (testing "Getting box"
    (is (= [3 0 6 0 9 0 0 5 0]
          (box-for [7 4] board)))
    (is (= [1 0 5 0 9 0 2 0 0]
          (box-for [0 0] board))))
  (testing "Getting row"
    (is (= [0 9 0 0 7 6 4 0 5]
         (row-for [1 1] board)))))
