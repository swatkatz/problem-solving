package org.swati.problemSolving;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TwoSum {
    /**
     * Stores @param input in an internal data structure.
     */
    Map<Integer, Integer> storeMap = new HashMap<Integer, Integer>();
    public void store(int input) {
        //if the input value exists in the map
        if (storeMap.containsKey(input)) {
            storeMap.put(input, storeMap.get(input) + 1);
        } else {
            storeMap.put(input, 1);
        }
    }

    /**
     * Returns true if there is any pair of numbers in the internal data structure which
     * have sum @param val, and false otherwise.
     * For example, if the numbers 1, -2, 3, and 6 had been stored,
     * the method should return true for 4, -1, and 9, but false for 10, 5, and 0
     */
    public boolean test(int val) {
        for (Integer currVal : storeMap.keySet()) {
            Integer otherNumber = val - currVal;
            //if the map contains the other number
            if (storeMap.containsKey(otherNumber)) {
                if (otherNumber.equals(currVal)) {
                    //If the number is the same as current, then check if another number exists
                    Integer count = storeMap.get(currVal);
                    //another same number exists
                    if (count > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.store(1);
        twoSum.store(-2);
        twoSum.store(3);
        twoSum.store(6);
        twoSum.store(6);

        System.out.println("Test " + twoSum.test(4));
        System.out.println("Test " + twoSum.test(-1));
        System.out.println("Test " + twoSum.test(9));
        System.out.println("Test " + twoSum.test(12));
        System.out.println("Test " + twoSum.test(10));
        System.out.println("Test " + twoSum.test(5));
        System.out.println("Test " + twoSum.test(0));

        try {
            Double.parseDouble("s");
        } catch (NumberFormatException e) {
            System.out.println("comes here");
        }
        Stack<Double> stack = new Stack<Double>();
        stack.push(43.53);
        stack.push(23.34);
        if (stack.size() >= 2) {
            Double val = stack.pop();
            Double val2 = stack.pop();
            System.out.println("val2 " + val2);
        }
    }
}
