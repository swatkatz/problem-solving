package org.swati.problemSolving.permutations;

/* Write a function to compute the maximum length palindromic sub-sequence of an array.
A palindrome is a sequence which is equal to its reverse.
A sub-sequence of an array is a sequence which can be constructed by removing elements of the array.
Ex: Given [4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4] should return 10 (all 4's) */

public class PalindromSubSeq {
    public static int maxLengthPalindrome(int[] values) {
        int startSeq = 0;
        int maxCount = 0;
        while(startSeq < values.length) {
            int tempStart = startSeq;
            for (int endSeq = values.length - 1; endSeq > startSeq; endSeq--) {
                if (isPalindrome(startSeq, endSeq, values)) {
                    if (maxCount < (endSeq - startSeq + 1)) {
                        maxCount = endSeq - startSeq + 1;
                    }
                    startSeq = endSeq + 1;
                    break;
                }
            }
            if (startSeq == tempStart) {
                startSeq++;
            }
        }
        return maxCount;
    }

    private static boolean isPalindrome(int start, int end, int[] values) {
        int backCount = end;
        int size = end - start + 1;
        for (int i = start; i < start + size/2; i++) {
            if (values[i] != values[backCount]) {
                return false;
            }
            backCount--;
        }
        return true;
    }

    public static void main(String args[]) {
        int[] values = {4, 1, 2, 3, 4, 5, 6, 5, 4, 3, 4, 4, 2, 2, 2, 2, 2, 4, 4};
        System.out.println("max length palindrome sub seq is " + maxLengthPalindrome(values));
    }
}
