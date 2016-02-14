package org.swati.problemSolving.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A simple iterator for a 2-D Array
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SimpleArrayListIterator {
    private Queue<Integer> internalQueue;

    public SimpleArrayListIterator(ArrayList<ArrayList<Integer>> matrix) {
        //Flatten the list
        this.internalQueue = new LinkedList<Integer>();
        if (matrix != null) {
            for (ArrayList<Integer> subList : matrix) {
                if (subList != null && !subList.isEmpty()) {
                    for (Integer value : subList) {
                        internalQueue.offer(value);
                    }
                }
            }
        }
    }

    public boolean hasNext() {
        return !internalQueue.isEmpty();
    }

    public int next() {
        return internalQueue.poll();
    }

    public static void main(String args[]) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                matrix.add(null);
            } else {
                matrix.add(new ArrayList<Integer>());
            }
        }

        for (int i = 0; i < 4; i++) {
            List<Integer> subList = matrix.get(i);
            if (i == 0) {
                //do nothing
            } else if (i == 1) {
                subList.add(4);
            } else if (i == 2) {
                subList.add(6);
                subList.add(7);
                subList.add(8);
                subList.add(9);
            } else {
                subList.add(10);
                subList.add(11);
            }
        }

        SimpleArrayListIterator iterator = new SimpleArrayListIterator(matrix);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
