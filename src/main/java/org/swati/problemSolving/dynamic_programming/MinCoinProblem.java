package org.swati.problemSolving.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Given a set of coins, find the minimum # of coins
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MinCoinProblem {

    private int getMinCoinsRecursion(Integer[] denoms, int amount, int ways) {
        if (amount < 0) {
            return 0;
        }
        if (amount == 0) {
            return 1;
        }
        if (ways <= 0 && amount >= 1) {
            return 0;
        }
        return getMinCoinsRecursion(denoms, amount - denoms[ways], ways) + getMinCoinsRecursion(denoms, amount, denoms[ways - 1]);
    }

    private int getMinCoins(Integer[] denoms, int amount) {
        LinkedHashSet<Integer> denomination = new LinkedHashSet<Integer>(Arrays.asList(denoms));
        int[] cachedSum = new int[amount + 1];
        //Initialize the array with -1
        for (int i = 0; i < amount + 1; i++) {
            cachedSum[i] = -1;
        }

        cachedSum[0] = 0;
        for (int i = 1; i <= amount; i++) {
            if (denomination.contains(i)) {
                cachedSum[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= i/2; j++) {
                    if (cachedSum[j] >= 0 && cachedSum[i - j] >= 0) {
                        int totalAmount = cachedSum[j] + cachedSum[i - j];
                        if (min > totalAmount) {
                            min = totalAmount;
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    cachedSum[i] = min;
                }
            }
        }
        return cachedSum[amount];
    }

    //To return the list of denominations
    private List<Integer> getMinCoinsVal(Integer[] denoms, int amount) {
        LinkedHashSet<Integer> denomination = new LinkedHashSet<Integer>(Arrays.asList(denoms));
        int[] cachedSum = new int[amount + 1];
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();

        //Initialize the list to be returned
        for (int i = 0; i <= amount; i++) {
            combinations.add(new ArrayList<Integer>());
        }
        //Initialize the array with -1
        for (int i = 0; i < amount + 1; i++) {
            cachedSum[i] = -1;
        }

        cachedSum[0] = 0;
        for (int i = 1; i <= amount; i++) {
            if (denomination.contains(i)) {
                cachedSum[i] = 1;
                combinations.get(i).add(i);
            } else {
                int min = Integer.MAX_VALUE;
                Map<Integer, List<Integer>> minValMap = new HashMap<Integer, List<Integer>>();
                for (int j = 1; j <= i/2; j++) {
                    if (cachedSum[j] >= 0 && cachedSum[i - j] >= 0) {
                        int totalAmount = cachedSum[j] + cachedSum[i - j];
                        if (min > totalAmount) {
                            List<Integer> newList = new ArrayList<Integer>();
                            min = totalAmount;
                            newList.addAll(combinations.get(j));
                            newList.addAll(combinations.get(i - j));
                            minValMap.put(totalAmount, newList);
                        }
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    cachedSum[i] = min;
                    combinations.get(i).addAll(minValMap.get(min));
                }
            }
        }
        return combinations.get(combinations.size() - 1);
    }

    public static void main(String[] args) {
        MinCoinProblem minCoinProblem = new MinCoinProblem();
        Integer[] denominations = {1, 3, 5};
        int val = 27;
        int result = minCoinProblem.getMinCoins(denominations, val);
        if (result > 0) {
            System.out.println("Min # of coins to make " + val + " are " + result);
        } else {
            System.out.println("The combination could not be found based on given denominations");
        }


        result = minCoinProblem.getMinCoinsRecursion(denominations, val, denominations.length - 1);
        if (result > 0) {
            System.out.println("Min # of coins to make " + val + " are " + result);
        } else {
            System.out.println("The combination could not be found based on given denominations");
        }


        List<Integer> comboList = minCoinProblem.getMinCoinsVal(denominations, val);
        if (!comboList.isEmpty()) {
            for (Integer value : comboList) {
                System.out.print(value + " , ");
            }
        } else {
            System.out.println("The combination could not be found based on given denominations");
        }
    }
}
