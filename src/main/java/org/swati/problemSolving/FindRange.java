package org.swati.problemSolving;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class FindRange {
    /**
     Given a sorted array with duplicates and a number, find the range in the
     form of (startIndex, endIndex) of that number. For example,

     find_range({0 2 3 3 3 10 10}, 3) should return (2,4).
     find_range({0 2 3 3 3 10 10}, 6) should return (-1,-1).
     The array and the number of duplicates can be large.
     **/

    public static class Range {
        int start;
        int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Range findRange(int[] arr, int val) {
        int rangeIndex = binSearch(0, arr.length -1, arr, val);
        int startIndex = -1;
        int endIndex = -1;
        if (rangeIndex == -1) {
            return new Range(-1, -1);
        } else {
            for (int i = rangeIndex; i >= 0; i--) {
                if (arr[i] == val) {
                    startIndex = i;
                } else if (arr[i] < val) {
                    break;
                }
            }

            for (int i = rangeIndex; i < arr.length; i++) {
                if (arr[i] == val) {
                    endIndex = i;
                } else if (arr[i] > val) {
                    break;
                }
            }
            return new Range(startIndex, endIndex);
        }
    }

    private static int binSearch(int low, int high, int[] arr, int val) {
        if (low >= high || low < 0 || high > arr.length) {
            return -1;
        }
        if (arr[low] == val) {
            return low;
        } else if (arr[high] == val) {
            return high;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == val) {
            return mid;
        } else if (arr[mid] > val) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
        return binSearch(low, high, arr, val);
    }
}
