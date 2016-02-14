package org.swati.problemSolving.dfs_bfs;

import java.util.ArrayList;
import java.util.List;

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
    private int[][] grid;
    private int rowSize, colSize;

    private int printNumberOf1Blobs(int[][] givenArr, int rowMax, int colMax) {
        grid = givenArr;
        rowSize = rowMax;
        colSize = colMax;

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
                    neighborDFS(i, j, visited);
                    numberOfOneBlobs++;
                }
            }
        }
        return numberOfOneBlobs;
    }

    private void neighborDFS(int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        List<Node> neighbors = getValidNeighbors(i, j, visited);
        for (Node node : neighbors) {
            neighborDFS(node.row, node.col, visited);
        }
    }

    private List<Node> getValidNeighbors(int startRow, int startCol, boolean[][] visited) {
        List<Node> validNodes = new ArrayList<Node>();
        addValidNode(startRow, startCol - 1, visited, validNodes);
        addValidNode(startRow, startCol + 1, visited, validNodes);
        addValidNode(startRow + 1, startCol + 1, visited, validNodes);
        addValidNode(startRow - 1, startCol + 1, visited, validNodes);
        addValidNode(startRow + 1, startCol, visited, validNodes);
        addValidNode(startRow - 1, startCol, visited, validNodes);
        addValidNode(startRow + 1, startCol - 1, visited, validNodes);
        addValidNode(startRow - 1, startCol - 1, visited, validNodes);
        return validNodes;
    }

    private void addValidNode(int row, int col, boolean[][] visited, List<Node> validNodes) {
        if (row < 0 || col < 0 || row >= rowSize || col >= colSize) {
            return;
        }
        if (grid[row][col] == 1 && !visited[row][col]) {
            validNodes.add(new Node(row, col));
        }
    }

    class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
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
