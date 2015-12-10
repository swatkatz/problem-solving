package org.swati.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * An implementation of Dijkstra's algorithm
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ShortestPath {
    //construct the graph
    public Graph constructGraph() {
        /**
         *  a to b = 1
         *  a to c = 3
         *  b to c = 1
         *  c to d = 3
         *  b to d = 10
         */
        Graph graph = new Graph();
        List<Vertex> vertexList = new ArrayList<Vertex>();
        for (char c = 'a'; c < 'e'; c++) {
            Vertex vertex = new Vertex();
            vertex.setName(c);
            vertexList.add(vertex);
        }
        Edge ab = new Edge();
        Edge ac = new Edge();
        ab.setWeight(1);
        ac.setWeight(3);
        vertexList.get(0).setTails(Arrays.asList(ab, ac));
        ab.setTail(vertexList.get(0));
        ab.setHead(vertexList.get(1));

        ac.setTail(vertexList.get(0));
        ac.setHead(vertexList.get(2));

        Edge bc = new Edge();
        bc.setWeight(1);
        Edge bd = new Edge();
        bd.setWeight(10);
        vertexList.get(1).setTails(Arrays.asList(bc, bd));

        bc.setTail(vertexList.get(1));
        bc.setHead(vertexList.get(2));
        bd.setTail(vertexList.get(1));
        bd.setHead(vertexList.get(3));

        Edge cd = new Edge();
        cd.setWeight(3);
        vertexList.get(2).setTails(Collections.singletonList(cd));
        cd.setTail(vertexList.get(2));
        cd.setHead(vertexList.get(3));

        graph.setVertices(vertexList);
        graph.setEdges(Arrays.asList(ab, ac, bc, bd, cd));

        return graph;
    }

    private int getShortestPath(Graph graph, Vertex source, Vertex destination) {
        Queue<Vertex> frontier = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            public int compare(Vertex o1, Vertex o2) {
                if (o1 != null && o2 != null) {
                    return o1.getScore().compareTo(o2.getScore());
                }
                return 0;
            }
        });
        source.setScore(0);
        frontier.offer(source);
        int shortestPathVal = -1;
        while (!frontier.isEmpty()) {
            Vertex curr = frontier.poll();
            shortestPathVal = curr.getScore();
            if (curr == destination) {
                printShortestPath(destination);
                return shortestPathVal;
            }
            Set<Vertex> neighbors = graph.getNeighbors(curr);
            for (Vertex vertex : neighbors) {
                int minScore = vertex.getScore();
                Vertex minVertex = null;
                //Get the min score for all the edges from curr to vertex
                for (Edge edge : graph.getDirectPaths(curr, vertex)) {
                    int newScore = shortestPathVal + edge.getWeight();
                    if (newScore < minScore) {
                        minScore = newScore;
                    }
                }
                //If the minScore is less than vertex's score then
                if (minScore < vertex.getScore()) {
                    //if frontier contains vertex, remove it
                    if (frontier.contains(vertex)) {
                        frontier.remove(vertex);
                    }
                    //set the vertex with the new minScore
                    vertex.setScore(minScore);
                    vertex.setPredecessor(curr);
                    //add the vertex back to the frontier
                    frontier.offer(vertex);
                }
            }
        }
        return shortestPathVal;
    }

    private void printShortestPath(Vertex destination) {
        Stack<Vertex> vertexStack = new Stack<Vertex>();
        Vertex current = destination;
        while (current != null) {
            vertexStack.push(current);
            current = current.getPredecessor();
        }
        while (!vertexStack.isEmpty()) {
            System.out.print(vertexStack.pop().toString() + " -> ");
        }
    }

    public static void main(String args[]) {
        ShortestPath shortestPath = new ShortestPath();
        Graph graph = shortestPath.constructGraph();
        System.out.println("The shortest path from a->d in the graph is " +
                shortestPath.getShortestPath(graph, graph.getVertices().get(0), graph.getVertices().get(3)));
    }
}
