package org.swati.leetcode.algorithms.strings;

import java.util.*;

public class PrintSpiral {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int row = Integer.parseInt(line.split(",")[0]);
        int col = Integer.parseInt(line.split(",")[1]);
        int [][] matrix = new int[row][col];
        boolean [][] visited = new boolean[row][col];

        //initialize the matrix
        int i = 0;
        while(sc.hasNextLine() && i < row) {
            String processLine = sc.nextLine();
            String[] rowArr = processLine.split(",");
            if (rowArr.length > 0) {
                for (int j = 0; j < rowArr.length; j++) {
                    matrix[i][j] = Integer.parseInt(rowArr[j]);
                    visited[i][j] = false;
                }
                i++;
            }
        }

        printSpiral(visited, matrix, 0, 0, col, row);
    }

    private static void printSpiral(boolean[][] visited, int[][] matrix, int iStart, int jStart, int maxCol, int maxRow) {
        if (maxCol >= 1 && maxRow >= 1) {
            for (int j = jStart; j < maxCol; j++) {
                if (!visited[iStart][j]) {
                    System.out.print(matrix[iStart][j] + ",");
                    visited[iStart][j] = true;
                }
            }

            for (int i = iStart; i < maxRow; i++) {
                if (!visited[i][maxCol - 1]) {
                    System.out.print(matrix[i][maxCol - 1] + ",");
                    visited[i][maxCol - 1] = true;
                }
            }

            for (int j = maxCol - 1; j >= jStart; j--) {
                if (!visited[maxRow - 1][j]) {
                    System.out.print(matrix[maxRow - 1][j] + ",");
                    visited[maxRow - 1][j] = true;
                }
            }

            for (int i = maxRow - 1; i >= iStart; i--) {
                if (i != jStart && !visited[i][jStart]) {
                    System.out.print(matrix[i][jStart] + ",");
                    visited[i][jStart] = true;
                }
            }
            printSpiral(visited, matrix, iStart+1, jStart+1, maxCol-1, maxRow-1);
        }
    }
}