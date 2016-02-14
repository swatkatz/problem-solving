package org.swati.problemSolving.datastructures;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note:
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int size = nums.length;
        if (size < k || size == 0) {
            return new int[0];
        }
        int[] solution = new int[size - k + 1];
        int solCount = 0;
        int max = Integer.MIN_VALUE;
        Deque<Integer> currWindow = new LinkedList<Integer>();

        for (int i = 0; i <= size; i++) {
            if (currWindow.size() == k) {
                solution[solCount] = max;
                solCount++;
                currWindow.pollFirst();
                if (currWindow.isEmpty()) {
                    max = Integer.MIN_VALUE;
                } else {
                    int tempMax = Integer.MIN_VALUE;
                    for (Integer aCurrWindow : currWindow) {
                        tempMax = Math.max(tempMax, aCurrWindow);
                    }
                    max = tempMax;
                }
            }
            if (i < size) {
                currWindow.offerLast(nums[i]);
                max = Math.max(max, currWindow.peekLast());
            }
        }
        return solution;
    }
}
