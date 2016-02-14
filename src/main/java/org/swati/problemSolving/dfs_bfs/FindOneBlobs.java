package org.swati.problemSolving.dfs_bfs;

/**
 * Given a 2-D array print the number of 1 blobs that we find in the array.
 * a 1-blob is defined as a group of ones that are adjacent to each other
 * adjacency is defined as top, bottom, left, right and the 4 diagonal neighbors
 *
 *[1, 0, 0, 1]
 *[1, 1, 0, 0]
 *[1, 0, 0, 1]
 *
 * The above matrix has 3 1-blobs
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindOneBlobs {

    private int printNumberOf1Blobs(int[][] givenArr, int rowMax, int colMax) {
        boolean[][] visited = new boolean[rowMax][colMax];
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                visited[i][j] = false;
            }
        }

        int numberOfOneBlobs = 0;
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                if (givenArr[i][j] == 1 && !visited[i][j]) {
                    neighborDFS(i, j, givenArr, visited, rowMax, colMax);
                    numberOfOneBlobs++;
                }
            }
        }
        return numberOfOneBlobs;
    }

    private void neighborDFS(int i, int j, int[][] givenArr, boolean[][] visited, int rowMax, int colMax) {
        if (visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (givenArr[i][j] == 1) {
            if (i+1 < rowMax) {
                neighborDFS(i+1, j, givenArr, visited, rowMax, colMax);
                if (j+1 < colMax) {
                    neighborDFS(i+1, j+1, givenArr, visited, rowMax, colMax);
                }
                if (j-1 >= 0) {
                    neighborDFS(i+1, j-1, givenArr, visited, rowMax, colMax);
                }
            }
            if (i-1 >= 0) {
                neighborDFS(i-1, j, givenArr, visited, rowMax, colMax);
                if (j-1 >= 0) {
                    neighborDFS(i-1, j-1, givenArr, visited, rowMax, colMax);
                }
                if (j+1 < colMax) {
                    neighborDFS(i-1, j+1, givenArr, visited, rowMax, colMax);
                }
            }
            if (j-1 >= 0) {
                neighborDFS(i, j-1, givenArr, visited, rowMax, colMax);
            }
            if (j+1 < colMax) {
                neighborDFS(i, j+1, givenArr, visited, rowMax, colMax);
            }
        }
    }

    public static void main(String[] args) {
        FindOneBlobs fb = new FindOneBlobs();
        int[][] givenArr = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("The number of one blobs is " + fb.printNumberOf1Blobs(givenArr, 5, 5));
    }
}
