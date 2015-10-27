package org.swati.interview;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindMaxDays {
    /**
     * Provide a set of positive integers (an array of integers).
     * Each integer represent number of nights user request on Airbnb.com.
     * If you are a host, you need to design and implement an algorithm to
     * find out the maximum number a nights you can accommodate.
     * The constraint is that you have to reserve at least one day between each request,
     * so that you have time to clean the room. Example: 1) Input: [1, 2, 3] => output: 4,
     * because you will pick 1 and 3 2) input: [5, 1, 2, 6] => output: 11,
     * because you will pick 5 and 6 3) input: [5, 1, 2, 6, 20, 2] => output: 27,
     * because you will pick 5, 2, 20
     */

    private int maxNumberOfDays(int[] arr) {
        return findMaxDays(0, arr);
    }

    private int findMaxDays(int i, int[] arr) {
        if (i == arr.length - 1) {
            return arr[i];
        }
        if (i >= arr.length) {
            return 0;
        }
        return Math.max(arr[i] + findMaxDays(i + 2, arr), arr[i] + findMaxDays(i + 3, arr));
    }

    //Iterative method
    private int findMaxDays(int arr[]) {
        int [] maxDaysToPos = new int[arr.length + 1];
        maxDaysToPos[0] = 0;
        maxDaysToPos[1] = arr[0];
        for (int i = 2; i < maxDaysToPos.length; i++) {
            maxDaysToPos[i] = Math.max(maxDaysToPos[i - 1], maxDaysToPos[i - 2] + arr[i -1]);
        }
        return maxDaysToPos[maxDaysToPos.length - 1];
    }

    public static void main(String args[]) {
        FindMaxDays maxDays = new FindMaxDays();
        int[] three = {5, 1, 2, 6};
        System.out.println("Max days is " + maxDays.maxNumberOfDays(three));
        System.out.println("Max days is " + maxDays.findMaxDays(three));
    }
}
