package org.swati.interviewcake;

/**
 * Given an arrayOfInts, find the highestProduct you can get from three of the integers.
 * The input arrayOfInts will always have at least three integers.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class HighestProduct {
    public static int getHighestProduct(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Array needs to be of length 3");
        }

        int highest = Math.max(arr[0], arr[1]);
        int lowest = Math.min(arr[0], arr[1]);
        int highestProductOf2 = arr[0] * arr[1];
        int lowestProductOf2 = arr[0] * arr[1];
        int highestProductOf3 = arr[0] * arr[1] * arr[2];

        for (int i = 2; i < arr.length; i++) {
            int current = arr[i];
            highestProductOf3 = Math.max(Math.max(highestProductOf3,
                            current * highestProductOf2),
                    current * lowestProductOf2);

            highestProductOf2 = Math.max(Math.max(highestProductOf2,
                            current * highest),
                    current * lowest);

            lowestProductOf2 = Math.min(Math.min(lowestProductOf2,
                            current * lowest),
                    current * highest);
            highest = Math.max(current, highest);
            lowest = Math.min(current, lowest);
        }
        return highestProductOf3;
    }
    public static void main(String[] args) {
        // run your function through some test cases here
        // remember: debugging is half the battle!
        int[] arr = {3, 1, -1, 7, 0, -5};
        System.out.println(getHighestProduct(arr));
        String ab = "catsanddog";
        String sub = "cat";
        ab = ab.replace(sub, "");
        System.out.println(ab);
    }
}
