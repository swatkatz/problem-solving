package org.swati.problemSolving;

/**
 * Merge 2 sorted string arrays
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MergeSortedArrStrings {
    public static String[] mergeSortedStrings(String[] arr1, String[] arr2) {
        int count1 = 0;
        int count2 = 0;
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }

        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        String[] mergedArr = new String[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length + arr2.length; i++) {
            if (count1 == arr1.length) {
                mergedArr[i] = arr2[count2];
                count2++;
            } else if (count2 == arr2.length) {
                mergedArr[i] = arr1[count1];
                count1++;
            } else if (arr1[count1].compareTo(arr2[count2]) < 0) {
                mergedArr[i] = arr1[count1];
                count1++;
            } else if (arr1[count1].compareTo(arr2[count2]) == 0) {
                mergedArr[i] = arr1[count1];
                mergedArr[i + 1] = arr2[count2];
                i++;
                count1++;
                count2++;
            } else {
                mergedArr[i] = arr2[count2];
                count2++;
            }
        }
        return mergedArr;
    }

    public static void main(String[] args) {
        String[] arr = {"good", "person", "works"};
        String[] brr = {"John", "he", "is", "person", "plays", "sweet"};
        String[] merged = mergeSortedStrings(arr, brr);
        for (String val : merged) {
            System.out.print(", " + val);
        }
    }
}
