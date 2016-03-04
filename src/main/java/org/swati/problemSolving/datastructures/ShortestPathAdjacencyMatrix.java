package org.swati.problemSolving.datastructures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Shortest path with adjacency matrix
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ShortestPathAdjacencyMatrix {
    private static final int V = 4;

    public int[] getShortestPathFromSource(int[][] adjMatrix, int source) {
        int[] score = new int[V];
        Map<Integer, VS> vsMap = new HashMap<Integer, VS>();
        PriorityQueue<VS> frontier = new PriorityQueue<VS>(new Comparator<VS>() {
            public int compare(VS o1, VS o2) {
                return o1.score.compareTo(o2.score);
            }
        });
        for (int i = 0; i < V; i++) {
            vsMap.put(i, new VS(i, Integer.MAX_VALUE));
        }

        VS root = vsMap.get(source);
        root.score = 0;

        frontier.offer(root);
        while (!frontier.isEmpty()) {
            VS node = frontier.poll();
            int currShortest = node.score;

            for (int i = 0; i < V; i++) {
                if (adjMatrix[node.vertex][i] != 0) {
                    VS neighbor = vsMap.get(i);
                    int potentialShortestPath = adjMatrix[node.vertex][i] + currShortest;
                    if (neighbor.score > potentialShortestPath) {
                        if (frontier.contains(neighbor)) {
                            frontier.remove(neighbor);
                        }
                        neighbor.score = potentialShortestPath;
                        frontier.offer(neighbor);
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            score[i] = vsMap.get(i).score;
        }
        return score;
    }

    class VS {
        Integer vertex;
        Integer score;

        VS(Integer vertex, Integer score) {
            this.vertex = vertex;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        ShortestPathAdjacencyMatrix sp = new ShortestPathAdjacencyMatrix();
        int[][] adj = {{0, 4, 4, 8}, {4, 0, 8, 1}, {1, 1, 0, 1}, {3, 4, 2, 0}};
        sp.getShortestPathFromSource(adj, 0);
    }
}
