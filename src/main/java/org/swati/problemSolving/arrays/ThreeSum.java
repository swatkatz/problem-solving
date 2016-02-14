package org.swati.problemSolving.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Get the three-pairs of numbers whose sum is zero
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ThreeSum {

    public static List<List<Integer>> getThreeSums(int[] arr, int value) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (arr == null || arr.length < 3) {
            return result;
        }
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int curr = arr[i];
            int start = i + 1;
            int end = arr.length - 1;

            while (start < end) {
                int res = curr + arr[start] + arr[end];
                if (res == value) {
                    List<Integer> threes = new ArrayList<Integer>();
                    threes.add(curr);
                    threes.add(arr[start]);
                    threes.add(arr[end]);
                    result.add(threes);
                    start ++;
                    end --;
                } else if (res < value) {
                    start ++;
                } else {
                    end --;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-3, -4, 1, 2, 0, 3, -2};
        List<List<Integer>> res = getThreeSums(arr, 0);
        for (List<Integer> val : res) {
            for (Integer value : val) {
                System.out.print(", " + value);
            }
            System.out.println();
        }
    }
}
