package org.swati.problemSolving;

/**
 * List subsets that add up to a given number k
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PrintSubstringSum {
    private int[] givenArr;

    public PrintSubstringSum(int[] givenArr) {
        this.givenArr = givenArr;
    }

    public void printSubsets(int k) {
        printSubsetHelper(k, 0, givenArr.length, false);
    }

    private void printSubsetHelper(int k, int start, int length, boolean isRight) {
        if (start >= length) {
            return;
        }
        if (sum(start, length) == k) {
            printArray(start, length);
            System.out.println();
        }
        if (isRight) {
            printSubsetHelper(k, start + 1, length, true);
        }
        printSubsetHelper(k, start, length - 1, false);
        printSubsetHelper(k, start + 1, length, false);
    }

    private int sum(int start, int length)  {
        int sum = 0;
        if (start < 0 || length > givenArr.length) {
            return sum;
        }
        for (int i = start; i < length; i++) {
            sum += givenArr[i];
        }
        return sum;
    }

    private void printArray(int start, int length) {
        for (int i = start; i < length; i++) {
            System.out.print(", " + givenArr[i]);
        }
    }

    public static void main(String[] args) {
        int[] givenArr = {6, 7, 2, 10, 5};
        PrintSubstringSum printSubstringSum = new PrintSubstringSum(givenArr);
        printSubstringSum.printSubsets(15);
    }
}
