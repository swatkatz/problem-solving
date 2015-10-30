package org.swati.problemSolving;

/**
 * Print the all the values for a 2-D array iterator
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
import java.util.*;

public class ArrayListIterator {
    ArrayList<ArrayList<Integer>> matrix;
    int currRow;
    int currCol;

    public ArrayListIterator(ArrayList<ArrayList<Integer>> matrix) {
        this.matrix = matrix;
        this.currRow = 0;
        this.currCol = 0;
    }

    public boolean hasNext() {
        if (currRow >= matrix.size()) {
            return false;
        }
        List<Integer> subList = matrix.get(currRow);
        if (subList.isEmpty() && matrix.size() == 1) {
            return false;
        }
        return true;
    }

    public int next() {
        int value;
        List<Integer> subList = matrix.get(currRow);
        while(subList.isEmpty()) {
            currRow++;
            currCol = 0;
            subList = matrix.get(currRow);
        }

        if (currCol < subList.size()) {
            value = subList.get(currCol);
            currCol++;
            if (currCol >= subList.size()) {
                currRow++;
                currCol = 0;
            }
        } else {
            subList = matrix.get(currRow);
            value = subList.get(currCol);
        }
        return value;
    }


    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 3; i++) {
            matrix.add(new ArrayList<Integer>());
        }
        //Simple case
        /*for (int i = 0; i < 3; i++) {
            List<Integer> subList = matrix.get(i);
            if (i == 0) {
                subList.add(1);
                subList.add(2);
                subList.add(3);
            } else if (i == 1) {
                subList.add(4);
                subList.add(5);
            } else {
                subList.add(6);
                subList.add(7);
                subList.add(8);
                subList.add(9);
            }
        }*/

        //More difficult case
        /*for (int i = 0; i < 3; i++) {
            List<Integer> subList = matrix.get(i);
            if (i == 0) {
                //do nothing
            } else if (i == 1) {
                subList.add(4);
                subList.add(5);
            } else {
                subList.add(6);
                subList.add(7);
                subList.add(8);
                subList.add(9);
            }
        }*/

        //Most difficult case
        for (int i = 0; i < 3; i++) {
            List<Integer> subList = matrix.get(i);
            if (i == 0) {
                //do nothing
            } else if (i == 1) {
                //do nothing
            } else {
                subList.add(6);
                subList.add(7);
                subList.add(8);
                subList.add(9);
            }
        }

        ArrayListIterator iterator = new ArrayListIterator(matrix);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

