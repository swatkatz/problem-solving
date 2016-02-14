package org.swati.leetcode.algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * nums = [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class LongestIncreasingPath {
    //plain recursion (no memoization)
    public int longestIncreasingPathWithRecursion(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int count = dfs(i, j, matrix, matrix.length, matrix[0].length, 0, Integer.MIN_VALUE);
                if (max < count) {
                    max = count;
                }
            }
        }
        return max;
    }

    public int dfs(int i, int j, int[][] matrix, int row, int col, int count, int prev) {
        if (i < 0 || j < 0 || i >= row || j >= col || matrix[i][j] <= prev) {
            return count;
        } else {
            count += 1;
            int max = count;
            List<Neighbor> neighbors = getNeighbors(i, j);
            for (Neighbor neighbor : neighbors) {
                int val = dfs(neighbor.i, neighbor.j, matrix, row, col, count, matrix[i][j]);
                if (max < val) {
                    max = val;
                }
            }
            return max;
        }
    }

    /**
     * With memoization
     * @param matrix: given
     * @return longest increasing path
     */
    public int longestIncreasingPathWithMemoization(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int[] val : result) {
            Arrays.fill(val, -1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                findPath(i, j, matrix, result);
                if (max < result[i][j]) {
                    max = result[i][j];
                }
            }
        }
        return max;
    }

    private void findPath(int i, int j, int[][] matrix, int[][] result) {
        int row = matrix.length;
        int col = matrix[0].length;

        List<Neighbor> neighbors = getNeighbors(i, j);
        int max = 1;

        for (Neighbor neighbor : neighbors) {
            if (neighbor.i < 0 || neighbor.j < 0 || neighbor.i >= row || neighbor.j >= col) {
                continue;
            }
            if (matrix[neighbor.i][neighbor.j] > matrix[i][j]) {
                if (result[neighbor.i][neighbor.j] == -1) {
                    findPath(neighbor.i, neighbor.j, matrix, result);
                }
                max = Math.max(max, result[neighbor.i][neighbor.j] + 1);
            }
        }
        result[i][j] = max;
    }

    private List<Neighbor> getNeighbors(int i, int j) {
        List<Neighbor> neighbors = new ArrayList<Neighbor>();
        neighbors.add(new Neighbor(i + 1, j));
        neighbors.add(new Neighbor(i - 1, j));
        neighbors.add(new Neighbor(i, j + 1));
        neighbors.add(new Neighbor(i, j - 1));
        return neighbors;
    }

    class Neighbor {
        public int i;
        public int j;
        public Neighbor(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
