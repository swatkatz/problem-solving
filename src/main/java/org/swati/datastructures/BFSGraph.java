package org.swati.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * BFS Search for a node in the graph
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class BFSGraph {

    public static Graph constructGraph() {
        Map<Character, Vertex> vertexMap = new HashMap<Character, Vertex>();
        for (char c = 'a'; c <= 'h'; c++) {
            Vertex vertex = new Vertex();
            vertex.setName(c);
            vertexMap.put(c, vertex);
        }

        Edge ab = constructEdge(vertexMap.get('b'), vertexMap.get('a'));
        Edge ac = constructEdge(vertexMap.get('c'), vertexMap.get('a'));
        vertexMap.get('a').setTails(Arrays.asList(ab, ac));

        Edge bc = constructEdge(vertexMap.get('c'), vertexMap.get('b'));
        Edge bd = constructEdge(vertexMap.get('d'), vertexMap.get('b'));
        vertexMap.get('b').setTails(Arrays.asList(bc, bd));

        Edge ce = constructEdge(vertexMap.get('e'), vertexMap.get('c'));
        vertexMap.get('c').setTails(Collections.singletonList(ce));

        Edge de = constructEdge(vertexMap.get('e'), vertexMap.get('d'));
        Edge dg = constructEdge(vertexMap.get('g'), vertexMap.get('d'));
        vertexMap.get('d').setTails(Arrays.asList(de, dg));

        Edge eg = constructEdge(vertexMap.get('g'), vertexMap.get('e'));
        vertexMap.get('e').setTails(Collections.singletonList(eg));

        Edge gh = constructEdge(vertexMap.get('h'), vertexMap.get('g'));
        vertexMap.get('g').setTails(Collections.singletonList(gh));

        Graph graph = new Graph();
        graph.setVertices(new ArrayList<Vertex>(vertexMap.values()));
        return graph;
    }

    private static Edge constructEdge(Vertex head, Vertex tail) {
        Edge edge = new Edge();
        edge.setHead(head);
        edge.setTail(tail);
        return edge;
    }

    private boolean vertexFound(Graph graph, Vertex sourceVertex, Vertex searchVertex) {
        if (sourceVertex == null || searchVertex == null) {
            return false;
        }

        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.offer(sourceVertex);
        sourceVertex.setScore(1);
        while(!queue.isEmpty()) {
            Vertex currVertex = queue.poll();
            if (currVertex.equals(searchVertex)) {
                System.out.println("Level is " + currVertex.getScore());
                return true;
            }
            if (!currVertex.isVisited()) {
                currVertex.setVisited(true);
                for (Vertex vertex : graph.getNeighbors(currVertex)) {
                    vertex.setScore(currVertex.getScore() + 1);
                    queue.offer(vertex);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BFSGraph bfsGraph = new BFSGraph();
        Graph graph = constructGraph();
        int last = graph.getVertices().size() - 1;
        System.out.println("Vertex h found is "
                + bfsGraph.vertexFound(graph, graph.getVertices().get(0), graph.getVertices().get(last)));
    }

}
