(ns sudoku-solver-clj.core-test
  (:require [clojure.test :refer :all]
            [sudoku-solver-clj.core :refer :all]))

(deftest parsing
  (testing "Creates a 2 dimensional vector from input string"
    (is (= [[1 0 5 8 0 2 0 0 0] 
            [0 9 0 0 7 6 4 0 5]
            [2 0 0 4 0 0 8 1 9]
            [0 1 9 0 0 7 3 0 6]
            [7 6 2 0 8 3 0 9 0]
            [0 0 0 0 6 1 0 5 0]
            [0 0 7 6 0 0 0 3 0]
            [4 3 0 0 2 0 5 0 1]
            [6 0 0 3 0 8 9 0 0]]
         (str-to-grid "105802000090076405200400819019007306762083090000061050007600030430020501600308900" 9)))))
