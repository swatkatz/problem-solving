package org.swati.datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Directed graph data structure using Adjacency Lists
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Set<Vertex> getNeighbors(Vertex vertex) {
        Set<Vertex> neighbors = new HashSet<Vertex>();
        if (vertex != null && vertex.getTails() != null) {
            for (Edge edge : vertex.getTails()) {
                if (edge != null) {
                    neighbors.add(edge.getHead());
                }
            }
        }
        return neighbors;
    }

    public List<Edge> getDirectPaths(Vertex source, Vertex destination) {
        //no direct path from source to destination exists
        if (!getNeighbors(source).contains(destination) || source.getTails() == null) {
            return new ArrayList<Edge>();
        }
        List<Edge> chosenEdges = new ArrayList<Edge>();
        for (Edge edge : source.getTails()) {
            if (edge.getHead().equals(destination)) {
                chosenEdges.add(edge);
            }
        }
        return chosenEdges;
    }
}

class Vertex {
    private char name;
    private List<Edge> tails;
    private Integer score = Integer.MAX_VALUE;
    private Vertex predecessor = null;
    private boolean visited = false;

    public char getName() {
        return name;
    }

    public List<Edge> getTails() {
        return tails;
    }

    public Integer getScore() {
        return score;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void setTails(List<Edge> tails) {
        this.tails = tails;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex vertex = (Vertex) o;

        return getName() == vertex.getName();

    }

    @Override
    public int hashCode() {
        return (int) getName();
    }
}

class Edge {
    private Vertex head;
    private Vertex tail;
    private int weight;

    public Vertex getHead() {
        return head;
    }

    public Vertex getTail() {
        return tail;
    }

    public int getWeight() {
        return weight;
    }

    public void setHead(Vertex head) {
        this.head = head;
    }

    public void setTail(Vertex tail) {
        this.tail = tail;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "" + tail + "->" + head;
    }
}
