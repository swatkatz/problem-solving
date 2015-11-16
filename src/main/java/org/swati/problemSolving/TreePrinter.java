package org.swati.problemSolving;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
class TreePrinter {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /*
    Tree:
1
/ \
3 5
/ \ \
2 4 7
/ \ \
9 6 8
     */
    static class NodeLevel {
        Node node;
        int level;
        public NodeLevel(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public void printTree(Node root) {
        Queue<NodeLevel> queue = new LinkedList<NodeLevel>();
        int prevLevel = 0;
        queue.add(new NodeLevel(root, 0));
        while (!queue.isEmpty()) {
            NodeLevel nodeLevel = queue.poll();
            if (nodeLevel.node != null) {
                if (nodeLevel.level > prevLevel) {
                    System.out.println();
                    prevLevel = nodeLevel.level;
                }
                System.out.print(nodeLevel.node.value);
                queue.add(new NodeLevel(nodeLevel.node.left, nodeLevel.level + 1));
                queue.add(new NodeLevel(nodeLevel.node.right, nodeLevel.level + 1));
            }
        }
    }

    public static void main(String args[]) {
        TreePrinter treePrinter = new TreePrinter();
        Node nine = new Node(9, null, null);
        Node six = new Node(6, null, null);
        Node eight = new Node(8, null, null);
        Node two = new Node(2, nine, null);
        Node four = new Node(4, null, six);
        Node seven = new Node(7, null, eight);
        Node three = new Node(3, two, four);
        Node five = new Node(5, null, seven);
        Node root = new Node(1, three, five);
        treePrinter.printTree(root);
    }
}
