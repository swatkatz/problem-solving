package org.swati.interview;

import java.util.PriorityQueue;

/**
 * A simple priority queue implementation of min-heap
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PriorityQueueExample {
    PriorityQueue<Integer> priorityQueue;

    public PriorityQueueExample() {
        priorityQueue = new PriorityQueue<Integer>();
    }

    public void minHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            priorityQueue.offer(array[i]);
        }
        System.out.println("Smallest int is " + priorityQueue.peek());
        int first = priorityQueue.poll();
        System.out.println("Smallest int after polling " + priorityQueue.peek());
        int[] sortedArr = new int[array.length];
        sortedArr[0] = first;
        int i = 1;
        while (priorityQueue.size() > 0) {
            sortedArr[i] = priorityQueue.poll();
            i++;
        }

        for (i = 0; i < sortedArr.length; i++) {
            System.out.print("," + sortedArr[i]);
        }
    }

    public static void main(String[] args) {
        PriorityQueueExample pre = new PriorityQueueExample();
        int[] array = {5, 3, 1, 2, 8, 6};
        pre.minHeap(array);
    }
}
