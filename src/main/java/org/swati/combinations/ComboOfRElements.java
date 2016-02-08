package org.swati.combinations;

/**
 * Print all possible combinations of r elements in a given array of size n
 * Given an array of size n, generate and print all possible combinations of r elements in array.
 * For example, if input array is {1, 2, 3, 4} and r is 2,
 * then output should be {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4} and {3, 4}.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ComboOfRElements {

    public void printElements(int[] array, int r) {
        System.out.println("With StringBuilder code");
        recursivePrinting(array, new StringBuilder(), 0, r);
        System.out.println("With Array Code");
        int[] data = new int[r];
        recursivePrintingWithArr(array, data, 0, 0, r);
    }

    private void recursivePrinting(int[] arr, StringBuilder sb, int start, int r) {
        if (sb.length() == r * 3) {
            printStringBuffer(sb);
        }
        for (int i = start; i < arr.length && arr.length - i >= r - sb.length(); i++) {
            sb.append(arr[i]).append(", ");
            recursivePrinting(arr, sb, i + 1, r);
        }
        if (sb.length() - 3 >= 0) {
            sb.delete(sb.length() - 3, sb.length());
        }
    }

    private void recursivePrintingWithArr(int[] arr, int[] data, int start, int index, int r) {
        if (index == r) {
            printArray(data);
            return;
        }

        for (int i = start; i < arr.length && arr.length - i >= r - index; i++) {
            data[index] = arr[i];
            recursivePrintingWithArr(arr, data, i + 1, index + 1, r);
        }
    }

    private void printStringBuffer(StringBuilder sb) {
        System.out.println(sb.toString());
    }

    private void printArray(int[] data) {
        for (int val : data) {
            System.out.print(val + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ComboOfRElements comboOfRElements = new ComboOfRElements();
        int[] arr = {1, 2, 3, 4, 5};
        comboOfRElements.printElements(arr, 3);
    }

}
