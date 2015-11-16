package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find union and intersection of 2 sorted lists
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class SetOpOnList {
    public List<Integer> unionize(List<Integer> listA, List<Integer> listB) {
        if (listA.isEmpty()) {
            return listB;
        }

        if (listB.isEmpty()) {
            return listA;
        }

        List<Integer> union = new ArrayList<Integer>(listA.size() + listB.size());
        int listACount = 0;
        int listBCount = 0;

        for (int i = 0; i < listA.size() + listB.size(); i++) {
            if (listACount >= listA.size()) {
                union.add(listB.get(listBCount));
                listBCount++;
            } else if (listBCount >= listB.size()) {
                union.add(listA.get(listACount));
                listACount++;
            } else if (listA.get(listACount) < listB.get(listBCount)) {
                union.add(listA.get(listACount));
                listACount++;
            } else if (listA.get(listACount) > listB.get(listBCount)) {
                union.add(listB.get(listBCount));
                listBCount++;
            } else if (listA.get(listACount).equals(listB.get(listBCount))) {
                union.add(listB.get(listBCount));
                listACount++;
                listBCount++;
                i++;
            }
        }
        return union;
    }

    public List<Integer> intersectionize(List<Integer> listA, List<Integer> listB) {
        List<Integer> intersection = new ArrayList<Integer>();
        if (listA.isEmpty()) {
            return intersection;
        }

        if (listB.isEmpty()) {
            return intersection;
        }

        int listACount = 0;
        int listBCount = 0;

        while(listACount < listA.size() && listBCount < listB.size()) {
            if (listA.get(listACount) < listB.get(listBCount)) {
                listACount++;
            } else if (listA.get(listACount).equals(listB.get(listBCount))) {
                intersection.add(listA.get(listACount));
                listACount++;
                listBCount++;
            } else {
                listBCount++;
            }
        }
        return intersection;
    }

    public static void main(String[] args) {
        List<Integer> listA = Arrays.asList(1, 5, 9, 10, 11);
        List<Integer> listB = Arrays.asList(2, 3, 5, 6, 7, 20, 21);

        SetOpOnList setOpOnList = new SetOpOnList();
        List<Integer> union = setOpOnList.unionize(listA, listB);
        System.out.println("Union");
        for (Integer value : union) {
            System.out.print(", " + value);
        }
        System.out.println();
        System.out.println("Intersection");
        listA = Arrays.asList(1, 5, 9, 12);
        listB = Arrays.asList(2, 3, 5, 6, 7, 12, 20, 22);
        List<Integer> intersection = setOpOnList.intersectionize(listA, listB);
        for (Integer value : intersection) {
            System.out.print(", " + value);
        }
    }
}
