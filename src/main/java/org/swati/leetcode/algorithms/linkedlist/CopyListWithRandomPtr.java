package org.swati.leetcode.algorithms.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Copy List with Random Pointer
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class CopyListWithRandomPtr {

    public class RandomListNode {
        RandomListNode random;
        RandomListNode next;
        Integer label;

        public RandomListNode(Integer label) {
            this.label = label;
            this.random = null;
            this.next = null;
        }
    }

    public RandomListNode createNewRandomList(int[] values) {
        RandomListNode head = new RandomListNode(values[0]);
        RandomListNode temp = head;
        for (int i = 1; i < values.length; i++) {
            RandomListNode randomListNode = new RandomListNode(values[i]);
            temp.next = randomListNode;
            temp = randomListNode;
        }
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.next.random = head.next;

        return head;
    }

    public RandomListNode deepCopy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> originalToCopyNodeMap = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode node = head;
        RandomListNode prev = null;

        while (node != null) {
            RandomListNode newNode = new RandomListNode(node.label);
            if (prev != null) {
                prev.next = newNode;
            }
            originalToCopyNodeMap.put(node, newNode);
            prev = newNode;
            node = node.next;
        }

        //Apply the random nodes
        node = head;
        while (node != null) {
            RandomListNode random = node.random;
            if (random != null) {
                originalToCopyNodeMap.get(node).random = originalToCopyNodeMap.get(random);
            }
            node = node.next;
        }
        return originalToCopyNodeMap.get(head);
    }
}
