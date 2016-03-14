package org.swati.interviewcake;

/**
 * You have an array of integers, and for each index you want to find the product of every integer except the integer at that index.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindProduct {
    public static int[] myFunction(int[] arr) {
        int[] storeAsc = new int[arr.length - 1];
        int[] storeDesc = new int[arr.length - 1];

        storeAsc[0] = 1; storeDesc[0] = 1;
        int count = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            storeAsc[i + 1] = storeAsc[i] * arr[i];
            storeDesc[i + 1] = storeDesc[i] * arr[count];
            count --;
        }

        int[] ans = new int[arr.length];
        count = storeDesc.length - 1;
        for (int i = 0; i < arr.length; i++) {
            ans[i] = storeAsc[i] * storeDesc[count];
            count --;
        }
        return ans;
    }
    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        int[] testInput = {1, 7, 3, 4};
        int[] ans = myFunction(testInput);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }
}
