package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge multiple sorted arrays into a single array
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MergeSortedArrays {
    //Brute force method O(mn * n)
    public List<Integer> getSingleArrFromCollection(int[][] collectionArr) {
        int m = collectionArr.length;
        int n = collectionArr[0].length;
        int mn = m * n;
        List<Integer> newList = new ArrayList<Integer>(mn);
        int[] pointerArr = new int[collectionArr.length];
        for (int i = 0; i < collectionArr.length; i++) {
            pointerArr[i] = 0;
        }
        int count = 0;
        while (count < mn) {
            int min = Integer.MAX_VALUE;
            int pointer = -1;
            for (int pointerArrCount = 0; pointerArrCount < pointerArr.length; pointerArrCount++) {
                if (pointerArr[pointerArrCount] < n
                        && min > collectionArr[pointerArrCount][pointerArr[pointerArrCount]]) {
                    min = collectionArr[pointerArrCount][pointerArr[pointerArrCount]];
                    pointer = pointerArrCount;
                }
            }
            newList.add(count, min);
            pointerArr[pointer] = pointerArr[pointer] + 1;
            count++;
        }
        return newList;
    }

    //Using min heap O(mnlogm)
    public List<Integer> getSingleArrFromCollectionSmart(int[][] collectionArr) {
        int m = collectionArr.length;
        int n = collectionArr[0].length;
        int mn = m * n;
        List<Integer> newList = new ArrayList<Integer>(mn);
        PriorityQueue<Store> minHeap = new PriorityQueue<Store>(new Comparator<Store>() {
            public int compare(Store o1, Store o2) {
                Integer o1val = o1.value;
                Integer o2Val = o2.value;
                return o1val.compareTo(o2Val);
            }
        });
        //Initialize min heap
        for (int i = 0; i < collectionArr.length; i++) {
            minHeap.offer(new Store(collectionArr[i][0], i, 0));
        }

        int count = 0;
        while (count < mn) {
            Store minStored = minHeap.poll();
            int arrPointer = minStored.arrPointer;
            int valPointer = minStored.valPointer;
            int min = minStored.value;
            if (valPointer + 1 < n) {
                minHeap.offer(new Store(collectionArr[arrPointer][valPointer + 1], arrPointer, valPointer + 1));
            }
            newList.add(count, min);
            count++;
        }
        return newList;
    }

    class Store {
        public int value;
        public int arrPointer;
        public int valPointer;

        public Store(int value, int arrPointer, int valPointer) {
            this.value = value;
            this.arrPointer = arrPointer;
            this.valPointer = valPointer;
        }
    }

    public static void main(String[] args) {
        MergeSortedArrays sa = new MergeSortedArrays();
        int[][] collectionArr = {
                {1, 3, 5, 7},
                {2, 4, 6, 8},
                {0, 9, 10, 11}
        } ;
        System.out.println("Brute force method output : ");
        List<Integer> sortedList = sa.getSingleArrFromCollection(collectionArr);
        for (Integer aSortedList : sortedList) {
            System.out.print(" ," + aSortedList);
        }
        System.out.println();
        System.out.println("Heapify method output : ");
        sortedList = sa.getSingleArrFromCollectionSmart(collectionArr);
        for (Integer aSortedList : sortedList) {
            System.out.print(" ," + aSortedList);
        }
    }
}
