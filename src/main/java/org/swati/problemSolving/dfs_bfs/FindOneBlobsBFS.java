package org.swati.problemSolving.dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Write two functions:
 * Given a n by n grid, (A) add a land at location (x,y) and
 * (B) find the number of islands where an island is a group of adjacent lands
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindOneBlobsBFS {
    private int[][] grid;
    private int size;

    public FindOneBlobsBFS(int n) {
        this.size = n;
        this.grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
    }

    private void addLandAtLoc(int x, int y) throws Exception {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            throw new Exception("Invalid coordinates");
        } else {
            grid[x][y] = 1;
        }
    }

    private int countNumberOfIslands() {
        boolean[][] visited = new boolean[size][size];
        int numberOfIslands = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    bfs(i, j, visited);
                    ++numberOfIslands;
                }
            }
        }
        return numberOfIslands;
    }

    private void bfs(int startRow, int startCol, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(startRow, startCol));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.row][node.col] = true;
            List<Node> neighbors = getValidNeighbors(node.row, node.col, visited);
            queue.addAll(neighbors);
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
        if (row < 0 || col < 0 || row >= size || col >= size) {
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

    public static void main(String[] args) throws Exception {
        /*int[][] givenArr = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        */
        FindOneBlobsBFS findOneBlobsBFS = new FindOneBlobsBFS(5);
        findOneBlobsBFS.addLandAtLoc(0, 0);
        findOneBlobsBFS.addLandAtLoc(0, 1);
        findOneBlobsBFS.addLandAtLoc(1, 1);
        findOneBlobsBFS.addLandAtLoc(1, 4);
        findOneBlobsBFS.addLandAtLoc(2, 0);
        findOneBlobsBFS.addLandAtLoc(2, 3);
        findOneBlobsBFS.addLandAtLoc(2, 4);
        findOneBlobsBFS.addLandAtLoc(4, 0);
        findOneBlobsBFS.addLandAtLoc(4, 2);
        findOneBlobsBFS.addLandAtLoc(4, 4);

        System.out.println("The number of islands are " + findOneBlobsBFS.countNumberOfIslands());
    }
}
