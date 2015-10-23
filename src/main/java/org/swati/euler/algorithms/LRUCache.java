package org.swati.euler.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation for the LRU Cache
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class LRUCache {
    private Map<Integer, Node> cache;
    private int capacity;
    //Pointers to head and end node for modification
    private Node head;
    private Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>(capacity);
    }

    public int get(int key) {
        //size of the cache doesn't change
        if (cache.get(key) != null) {
            Node foundNode = cache.get(key);
            //remove the node from the list
            removeNode(foundNode);
            //set the node as head
            setHead(foundNode);
            return cache.get(key).getValue();
        }
        return -1;
    }

    public void set(int key, int value) {
        Node node = new Node(key, value);
        Node oldNode = cache.get(key);
        //node already exists, remove it
        if (oldNode != null) {
            removeNode(oldNode);
            oldNode.next = null;
            oldNode.prev = null;
        }
        if (cache.size() == capacity) {
            //remove the end node
            removeNode(end);
        }
        setHead(node);
    }

    private void removeNode(Node currNode) {
        if (currNode != null ) {
            cache.remove(currNode.getKey());
            Node prev = currNode.getPrev();
            Node next = currNode.getNext();
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            //If the node being removed is end node, move the end pointer to the previous node
            if (end != null && end.equals(currNode)) {
                end = currNode.getPrev();
            }
            //If the node being removed is the head node, move the head pointer to the next node
            if (head != null && head.equals(currNode)) {
                head = currNode.getNext();
            }
        }
    }

    //Sets the given node as head
    private void setHead(Node currNode) {
        if (head != null) {
            currNode.next = head;
            head.prev = currNode;
        }
        currNode.prev = null;
        head = currNode;
        cache.put(head.getKey(), head);
        //set the node as end node if end is null
        if (end == null) {
            end = currNode;
        }
    }

    public class Node {
        private Node prev;
        private Node next;
        private int key;
        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String args[]) {
        /*LRUCache lruCache = new LRUCache(2);
        lruCache.set(2, 1);
        lruCache.set(1, 1);
        lruCache.get(2);
        lruCache.set(4,1);
        lruCache.get(1);
        lruCache.get(2);*/
        //[2,1,1,-1,3]
        LRUCache lruCache = new LRUCache(2);
        lruCache.set(2,1);
        lruCache.set(3,2);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
        lruCache.set(4, 3);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
