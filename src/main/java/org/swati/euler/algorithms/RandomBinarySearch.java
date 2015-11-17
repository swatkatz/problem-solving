package org.swati.euler.algorithms;

import java.util.Random;

/**
 * Random Binary number search for various things
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class RandomBinarySearch {
    private Integer number;
    private Integer minLimit;
    private Integer maxLimit;

    public RandomBinarySearch(Integer number, Integer minLimit, Integer maxLimit) {
        this.number = number;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }

    public Integer randomBinarySearch(Integer lb, Integer ub, Integer tries) {
        //The comparison of number with lower and upper bound is also counted as a try
        if (number.equals(lb) || number.equals(ub) || lb >= ub || lb < minLimit || ub > maxLimit) {
            return tries;
        }
        Integer tryVal = new Random().nextInt(ub - lb) + lb;
        if (number.equals(tryVal)) {
            return tries + 1;
        } else if (tryVal < number) {
            lb = tryVal + 1;
        } else {
            ub = tryVal - 1;
        }
        return randomBinarySearch(lb, ub, tries + 1);
    }

    public Integer actualBinarySearch(Integer lb, Integer ub, Integer tries) {
        if (number.equals(lb) || number.equals(ub) || lb >= ub || lb < minLimit || ub > maxLimit) {
            return tries;
        }
        Integer tryVal = (ub + lb) / 2;
        if (number.equals(tryVal)) {
            return tries + 1;
        } else if (tryVal < number) {
            lb = tryVal + 1;
        } else {
            ub = tryVal - 1;
        }
        return actualBinarySearch(lb, ub, tries + 1);
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getMinLimit() {
        return minLimit;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public static void main(String args[]) {
        RandomBinarySearch rb = new RandomBinarySearch(3, 1, 100);
        System.out.println("The random binary search took " + rb.randomBinarySearch(rb.minLimit, rb.maxLimit, 1) + " tries to complete");
        System.out.println("The actual binary search took " + rb.actualBinarySearch(rb.minLimit, rb.maxLimit, 1) + " tries to complete");
    }

}
