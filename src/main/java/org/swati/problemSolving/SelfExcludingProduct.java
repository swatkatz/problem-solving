package org.swati.problemSolving;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SelfExcludingProduct {
    /**
     * Implement a method which takes an integer array and returns an integer array (of equal size) in
     * which each element is the product of every number in the input array with the exception of the
     * number at that index.
     *
     * Example:
     *   [3, 1, 4, 2] => [8, 24, 6, 12]
     */
    public int[] selfExcludingProduct(int[] input) {
        int totalProduct = -1;
        boolean isZero = false;
        for (int i = 0; i < input.length; i++) {
            if (input[i] != 0) {
                if (totalProduct == -1) {
                    totalProduct = 1;
                }
                totalProduct *= input[i];
            } else {
                isZero = true;
            }
        }
        int[] ans = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                if (totalProduct == -1) {
                    ans[i] = 0;
                } else {
                    ans[i] = totalProduct;
                }
            } else if (isZero) {
                ans[i] = 0;
            } else {
                ans[i] = totalProduct / input[i];
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        SelfExcludingProduct sp = new SelfExcludingProduct();
        int[] given = {0, 1, 2, 4};
        int[] ans = sp.selfExcludingProduct(given);
        for (int val : ans) {
            System.out.print(", " + val);
        }
    }
}
