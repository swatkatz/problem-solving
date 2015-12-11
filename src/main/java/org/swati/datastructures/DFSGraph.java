package org.swati.datastructures;

import java.util.Set;

/**
 * DFS Graph search for a directed graph
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class DFSGraph {
    private boolean vertexFound(Graph graph, Vertex sourceVertex, Vertex searchVertex) {
        if (searchVertex == null || sourceVertex == null) {
            return false;
        }

        return dfs(graph, sourceVertex, searchVertex);
    }

    private boolean dfs(Graph graph, Vertex currVertex, Vertex searchVertex) {
        boolean isFound;
        if (currVertex.equals(searchVertex)) {
            return true;
        }

        if (!currVertex.isVisited()) {
            currVertex.setVisited(true);
            Set<Vertex> neighbors = graph.getNeighbors(currVertex);
            for (Vertex vertex : neighbors) {
                isFound = dfs(graph, vertex, searchVertex);
                if (isFound) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DFSGraph dfsGraph = new DFSGraph();
        Graph graph = BFSGraph.constructGraph();
        int last = graph.getVertices().size() - 1;
        System.out.println("Vertex h found is "
                + dfsGraph.vertexFound(graph, graph.getVertices().get(0), graph.getVertices().get(last)));

        Vertex random = new Vertex();
        random.setName('r');
        System.out.println("Vertex random found is "
                + dfsGraph.vertexFound(graph, graph.getVertices().get(0), random));
    }
}
