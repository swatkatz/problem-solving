package org.swati.leetcode.algorithms.linkedlist;

/**
 * Write a function AlternatingSplit() that takes one list and divides up its nodes to make two smaller lists ‘a’ and ‘b’.
 * The sublists should be made from alternating elements in the original list.
 * So if the original list is 0->1->0->1->0->1 then one sublist should be 0->0->0 and the other should be 1->1->1.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class AlternateSplit {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public Node[] alternateSplit(Node head) {
        if (head == null) {
            return new Node[] {null, null};
        }
        if (head.next == null) {
            return new Node[] {head, null};
        }

        Node currNode = head;
        Node head1 = currNode;
        Node head2 = currNode.next;

        while (currNode != null && currNode.next != null) {
            Node t = getNext(currNode);
            if (t != null) {
                currNode.next = t.next;
                t.next = getNext(t.next);
            }
            currNode = currNode.next;
        }
        return new Node[] {head1, head2};
    }

    private Node getNext(Node node) {
        if (node != null) {
            return node.next;
        }
        return null;
    }

    private void printLinkedList(Node head) {
        Node currNode = head;
        while (currNode != null) {
            System.out.print(currNode.value + " ,");
            currNode = currNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AlternateSplit as = new AlternateSplit();
        Node head = new Node(0);
        Node node = head;
        for (int i = 1; i <= 10; i++) {
            Node newNode = new Node(i);
            node.next = newNode;
            node = newNode;
        }
        System.out.println("original ll");
        as.printLinkedList(head);
        System.out.println("new lls");
        Node[] heads = as.alternateSplit(head);
        for (Node llHead : heads) {
            as.printLinkedList(llHead);
        }
    }
}
