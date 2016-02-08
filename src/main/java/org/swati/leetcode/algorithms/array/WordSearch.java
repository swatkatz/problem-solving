package org.swati.leetcode.algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell,
 where "adjacent" cells are those horizontally or vertically neighboring.
 The same letter cell may not be used more than once.

 For example,
 Given board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(i, j, word, row, col, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs (int i, int j, String word, int row, int col, char[][] board) {
        if (board[i][j] == word.charAt(0)) {
            char temp = board[i][j];
            board[i][j] = '#';
            if (word.substring(1).length() == 0) {
                return true;
            }
            for (Neighbor neighbor : getNeighbors(i, j, row, col)) {
                if (dfs (neighbor.i, neighbor.j, word.substring(1), row, col, board)) {
                    return true;
                }
            }
            board[i][j] = temp;
        }
        return false;
    }

    private List<Neighbor> getNeighbors(int i, int j, int row, int col) {
        List<Neighbor> neighbors = new ArrayList<Neighbor>();
        if (i + 1 < row) {
            neighbors.add(new Neighbor(i + 1, j));
        }
        if (j + 1 < col) {
            neighbors.add(new Neighbor(i, j + 1));
        }
        if (i - 1 >= 0) {
            neighbors.add(new Neighbor(i - 1, j));
        }
        if (j - 1 >= 0) {
            neighbors.add(new Neighbor(i, j - 1));
        }
        return neighbors;
    }

    class Neighbor {
        int i;
        int j;

        Neighbor(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String args[]) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };
        wordSearch.exist(board, "bacd");
    }
}
