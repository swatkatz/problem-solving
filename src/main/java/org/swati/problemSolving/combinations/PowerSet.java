package org.swati.problemSolving.combinations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate PowerSet of a set
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class PowerSet {

    private Set<Set<Integer>> getPowerSet(List<Integer> givenSet) {
        Set<Set<Integer>> powerSet = new HashSet<Set<Integer>>();
        for (int i = 0; i < Math.pow(2, givenSet.size()); i++) {
            Set<Integer> newSet = new HashSet<Integer>();
            for (int j = 0; j < givenSet.size(); j++) {
                int val = (int) Math.pow(2, j);
                if ((val & i) == val) {
                    newSet.add(givenSet.get(j));
                }
            }
            powerSet.add(newSet);
        }
        return powerSet;
    }

    public static void main(String[] args) {
        PowerSet powerSet = new PowerSet();
        Set<Set<Integer>> result = powerSet.getPowerSet(Arrays.asList(1, 2, 4, 10));
        System.out.println("size of result is " + result.size());
        for (Set<Integer> aSet : result) {
            for (Integer anElement : aSet) {
                System.out.print(anElement + ",");
            }
            System.out.println();
        }
    }
}
