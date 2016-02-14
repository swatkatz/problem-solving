package org.swati.problemSolving.datastructures;

import java.util.PriorityQueue;

/**
 * From a given integer array find the kth largest element
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindKthLargestElement {

    private int findKthLargestElement(int[] arr, int k) {
        if (k <= 0 || k > arr.length) {
            return -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
        //Add the first k elements to the heap
        for (int i = 0; i < k; i++) {
            minHeap.offer(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            int minElement = minHeap.peek();
            if (minElement < arr[i]) {
                minHeap.poll();
                minHeap.offer(arr[i]);
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 1, 5, 21, 6};
        int k = 6;
        FindKthLargestElement fk = new FindKthLargestElement();
        System.out.println("The " + k + " largest element in the array is " + fk.findKthLargestElement(arr, k));
    }
}
