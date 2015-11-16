package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TreeBuilder {
    /**
     * Represents a pair relation between one parent node and one child node inside a binary tree
     * If the _parent is null, it represents the ROOT node
     */
    public static class Relation {
        public Integer parent;
        public Integer child;
        public boolean isLeft;

        public Relation(Integer child, Integer parent, boolean isLeft) {
            this.parent = parent;
            this.child = child;
            this.isLeft = isLeft;
        }
    }


    /**
     * Represents a single Node inside a binary tree
     */
    public static class Node {
        public Integer id;
        public Node left;
        public Node right;

        public Node(Integer id, Node left, Node right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "id " + id;
        }
    }

    /**
     * Implement a method to build a tree from a list of parent-child relationships
     * And return the root Node of the tree
     */
    private Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();

    public Node buildTree (List<Relation> data) {
        Node root = null;
        for (Relation relation : data) {
            //parent exists
            Node parent;
            Node child;
            if (relation != null && relation.child != null) {
                if (nodeMap.get(relation.child) != null) {
                    child = nodeMap.get(relation.child);
                } else {
                    child = new Node(relation.child, null, null);
                    nodeMap.put(relation.child, child);
                }
                if (relation.parent == null) {
                    root = child;
                } else {
                    if (nodeMap.get(relation.parent) != null) {
                        parent = nodeMap.get(relation.parent);
                    } else {
                        parent = new Node(relation.parent, null, null);
                        nodeMap.put(relation.parent, parent);
                    }
                    if (relation.isLeft) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }
            }
        }
        return root;
    }

    public void printTree(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node != null) {
                System.out.print(" " + node);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        /**
         Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside the tree are unique.

         Example:

         Given the following relationships:

         Child Parent IsLeft
         15 20 true
         19 80 true
         17 20 false
         16 80 false
         80 50 false
         50 null false
         20 50 true


         You should return the following tree:
         50
         / \
         20 80
         / \ / \
         15 17 19 16
         */

        TreeBuilder treeBuilder = new TreeBuilder();
        List<Relation> relations = new ArrayList<Relation>();
        relations.add(new Relation(15, 20, true));
        relations.add(new Relation(19, 80, true));
        relations.add(new Relation(17, 20, false));
        relations.add(new Relation(16, 80, false));
        relations.add(new Relation(80, 50, false));
        relations.add(new Relation(50, null, false));
        relations.add(new Relation(20, 50, true));

        Node root = treeBuilder.buildTree(relations);
        treeBuilder.printTree(root);
    }
}
