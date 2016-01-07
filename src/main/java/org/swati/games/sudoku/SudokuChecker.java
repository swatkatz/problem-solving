package org.swati.games.sudoku;

import java.util.Arrays;

/**
 * Check if the given sudoku board is valid or not
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SudokuChecker {

    public boolean isBoardValid(Integer[][] board) {
        if (board[0].length != 9 && board.length != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
            Integer[] eachRowArr = board[i].clone();
            Integer[] eachSquareArr = new Integer[9];
            Integer[] eachColArr = new Integer[9];
            for (int col = 0; col < 9; col++) {
                eachColArr[col] = board[col][i];
                /**
                 * For row expr:
                 * basically I want i = 0,1,2 to have the starting as 0
                 * i=3,4,5 to have starting as 3
                 * i=6,7,8 to have starting as 6
                 *
                 * For col expr:
                 * I want the cols to always go from 0,1,2 when i=0,3 and 6
                 * cols to go from 3,4,5 when i=1,4 and 7
                 */
                eachSquareArr[col] = board[(i / 3) * 3 + col / 3][col % 3 + i * 3 % 9];
            }
            if (!(isValid(eachColArr) && isValid(eachSquareArr) && isValid(eachRowArr))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(Integer[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < 9 ; i++) {
            if (arr[i] == null) {
                return false;
            }

            if (arr[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        Integer[][] board = {

                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        SudokuChecker sudokuChecker = new SudokuChecker();
        System.out.println("is the board valid " + sudokuChecker.isBoardValid(board));
    }

}
