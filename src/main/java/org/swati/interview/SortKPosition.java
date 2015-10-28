package org.swati.interview;

import java.util.PriorityQueue;

/**
 * Given an array of n elements, where each element is at most k away from its target position,
 * devise an algorithm that sorts in O(n log k) time.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SortKPosition {
    private int[] heapSort(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int[] sortedArr = new int[arr.length];
        //Add first k elements
        //offer takes log(k) time. Since, we perform this operation n times, it will be O(n log k)
        for (int i = 0; i < k; i++) {
            minHeap.offer(arr[i]);
        }
        int count = k;
        while (minHeap.size() > 0) {
            sortedArr[count - k] = minHeap.poll();
            if (count < arr.length) {
                minHeap.offer(arr[count]);
            }
            count++;
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        SortKPosition sp = new SortKPosition();
        int[] input = {5, 3, 1, 2, 8, 6};
        int[] output = sp.heapSort(input, 3);
        for (int anOutput : output) {
            System.out.print(", " + anOutput);
        }
    }
}
