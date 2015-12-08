package org.swati.datastructures;

/**
 * A Binary Search Tree DS
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class BinarySearchTree {
    private Node root;
    private Node prev;

    /**
     * Initialize the binary search tree with a root
     */
    public BinarySearchTree(int rootVal) {
        this.root = new Node(rootVal, null, null, null);
        this.prev = this.root;
    }

    /**
     * Searches for the value in the given binary search tree
     * @param val to be searched
     * @return true/false depending on if the value was found
     */
    public boolean search(int val) {
        return search(this.root, val) != null;
    }

    /**
     * If the value exits, don't change anything. If it doesn't exist, insert a new node with this value
     * @param val to be searched
     */
    public void insert(int val) {
        //re-initialize prev to root
        this.prev = this.root;
        Node n = search(this.root, val);
        if (n == null) {
            Node newNode = new Node(val, null, null, prev);
            if (prev.getValue() > val) {
                prev.setLeft(newNode);
            } else {
                prev.setRight(newNode);
            }
        }
    }

    /**
     * Prints the binary search tree values in sorted order
     */
    public void printSortedOrder() {
        System.out.println("Sort order: ");
        dfs(this.root);
        System.out.println();
    }

    /**
     * Gets the maximum value
     * @return max
     */
    public int max() {
        return max(this.root, this.root).getValue();
    }

    /**
     * Gets the minimum value
     * @return min
     */
    public int min() {
        return min(this.root, this.root).getValue();
    }

    /**
     * Gets the predecessor of the given int value corresponding to a node
     * @param value to be searched
     * @return Integer or null if smallest
     */
    public Integer predecessor(int value) {
        Node predecessorNode = predecessor(this.root, value);
        return predecessorNode != null ? predecessorNode.getValue() : null;
    }

    /**
     * Gets the successor of the given int value corresponding to a node
     * @param value to be searched
     * @return successor Integer or null if largest
     */
    public Integer successor(int value) {
        Node sucessorNode = successor(this.root, value);
        return sucessorNode != null ? sucessorNode.getValue() : null;
    }

    /**
     * Deletes the node corresponding to the given value, if no such node, does nothing
     * @param value to be searched
     */
    public void delete(int value) {
        delete(this.root, value);
    }

    //Internal methods
    private Node search(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == val) {
            return node;
        } else if (node.getValue() > val) {
            this.prev = node;
            return search(node.getLeft(), val);
        } else {
            this.prev = node;
            return search(node.getRight(), val);
        }
    }

    private void dfs(Node n) {
        if (n != null) {
            dfs(n.getLeft());
            System.out.print(n.getValue() + " , ");
            dfs(n.getRight());
        }
    }

    private Node max(Node node, Node prev) {
        if (node != null) {
            return max(node.getRight(), node);
        }
        return prev;
    }

    private Node min(Node node, Node prev) {
        if (node != null) {
            return min(node.getLeft(), node);
        }
        return prev;
    }

    private Node predecessor(Node startNode, int value) {
        Node searchNode = search(startNode, value);
        if (searchNode == null) {
            return null;
        }
        if (searchNode.getLeft() != null) {
            return max(searchNode.getLeft(), searchNode.getLeft());
        } else {
            return followParent(searchNode, false);
        }
    }

    private Node successor(Node startNode, int value) {
        Node searchNode = search(startNode, value);
        if (searchNode == null) {
            return null;
        }
        if (searchNode.getRight() != null) {
            return min(searchNode.getRight(), searchNode.getRight());
        } else {
            return followParent(searchNode, true);
        }
    }

    private Node followParent(Node givenNode, boolean greaterThanItself) {
        Node parent = givenNode.getParent();
        if (parent == null) {
            return null;
        }
        if (greaterThanItself && parent.getValue() > givenNode.getValue()) {
            return parent;
        } else if (!greaterThanItself && parent.getValue() < givenNode.getValue()) {
            return parent;
        }
        return followParent(parent, greaterThanItself);
    }

    //for future: to clean up the delete code a bit and make it modular
    private void delete(Node givenNode, int value) {
        Node searchNode = givenNode.getValue() != value ? search(givenNode, value) : givenNode;
        //no node found
        if (searchNode == null) {
            return;
        }
        //If leaf
        if (searchNode.getRight() == null && searchNode.getLeft() == null) {
            Node parent = searchNode.getParent();
            if (parent != null) {
                if (parent.getLeft() != null && parent.getLeft().equals(searchNode)) {
                    parent.setLeft(null);
                } else if (parent.getRight() != null && parent.getRight().equals(searchNode)) {
                    parent.setRight(null);
                }
            } else {
                this.root = null;
            }
            searchNode.setParent(null);
        } else if (searchNode.getRight() == null) { //If only left child exists
            Node parent = searchNode.getParent();
            Node child = searchNode.getLeft();
            if (parent == null) {
                this.root = child;
                child.setParent(null);
            } else if (parent.getValue() < child.getValue()) {
                parent.setRight(child);
                child.setParent(parent);
            } else {
                parent.setLeft(child);
                child.setParent(parent);
            }
            searchNode.setParent(null);
        } else if (searchNode.getLeft() == null) {
            Node parent = searchNode.getParent();
            Node child = searchNode.getRight();
            if (parent == null) {
                this.root = child;
            } else if (parent.getValue() < child.getValue()) { //If only right child exists
                parent.setRight(child);
                child.setParent(parent);
            } else {
                parent.setLeft(child);
                child.setParent(parent);
            }
            searchNode.setParent(null);
        } else {
            Node predecessor = predecessor(searchNode, searchNode.getValue());
            //Swap predecessory and node
            Node temp = new Node(searchNode);
            searchNode.setParent(predecessor.getParent());
            searchNode.setRight(predecessor.getRight());
            searchNode.setLeft(predecessor.getLeft());

            Node left = temp.getLeft();
            Node right = temp.getRight();
            left.setParent(predecessor);
            right.setParent(predecessor);

            //Make the parent point to searchNode now
            Node predParent = predecessor.getParent();
            if (predParent.getLeft() != null && predParent.getLeft().equals(predecessor)) {
                predParent.setLeft(searchNode);
            } else if (predParent.getRight() != null && predParent.getRight().equals(predecessor)) {
                predParent.setRight(searchNode);
            }

            //Set up predecessor to be what searchNode used to be
            predecessor.setParent(temp.getParent());
            predecessor.setRight(temp.getRight());
            predecessor.setLeft(temp.getLeft());

            //If searchNode was a root, then predecessor is now root
            if (this.root == searchNode) {
                this.root = predecessor;
            }
            delete(searchNode, value);
        }
    }

    //Test
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree(3);
        //insertion to create the tree
        binarySearchTree.insert(1);
        binarySearchTree.insert(5);
        binarySearchTree.insert(2);
        binarySearchTree.insert(4);
        //search for a node
        System.out.println("Is node 10 present? : " + binarySearchTree.search(10));
        System.out.println("Is node 5 present? : " + binarySearchTree.search(5));
        //print sorted order
        binarySearchTree.printSortedOrder();
        //max
        System.out.println("Max is " + binarySearchTree.max());
        //min
        System.out.println("Min is " + binarySearchTree.min());
        //predecessor
        System.out.println("Predecessor to 3 is " + binarySearchTree.predecessor(3));
        //successor
        System.out.println("Successor to 3 is " + binarySearchTree.successor(3));
        //delete 3 (root)
        binarySearchTree.delete(3);
        binarySearchTree.printSortedOrder();
        //delete 5
        binarySearchTree.delete(5);
        binarySearchTree.printSortedOrder();
        //delete 4
        binarySearchTree.delete(4);
        binarySearchTree.printSortedOrder();
        //delete 2
        binarySearchTree.delete(2);
        binarySearchTree.printSortedOrder();
        //delete 1
        binarySearchTree.delete(1);
        binarySearchTree.printSortedOrder();
    }
}
