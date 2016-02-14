package org.swati.leetcode.algorithms.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Find all strobogrammatic numbers that are of length = n.
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class StrobogrammaticNumbers {

    public int strobogrammaticInRange(String low, String high) {
        int count = 0;
        for (int i = low.length(); i <= high.length(); i++) {
            List<String> answer = findStrobogrammatic(i);
            for (String val : answer) {
                if (shouldAdd(low, high, val)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean shouldAdd(String low, String high, String value) {
        boolean lowSatisfied = false;
        boolean highSatisfied = false;

        if (low.length() < value.length()) {
            lowSatisfied = true;
        } else if (low.length() == value.length()) {
            lowSatisfied = low.compareTo(value) <= 0;
        }

        if (value.length() < high.length()) {
            highSatisfied = true;
        } else if (value.length() == high.length()) {
            highSatisfied = value.compareTo(high) <= 0;
        }

        return lowSatisfied && highSatisfied;
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> newList = new ArrayList<String>();
        if (n == 0) {
            newList.add("");
            return newList;
        } else if (n == 1) {
            newList.add("0");
            newList.add("1");
            newList.add("8");
        } else if (n == 2) {
            newList.add("11");
            newList.add("69");
            newList.add("88");
            newList.add("96");
        } else if (n % 2 == 0) {
            List<String> twoStr = findStrobogrammatic(2);
            twoStr.add(0, "00");
            newList = getCombination(findStrobogrammatic(n - 2), twoStr);
        } else {
            newList = getCombination(findStrobogrammatic(n - 1), findStrobogrammatic(1));
        }
        return newList;
    }

    private List<String> getCombination(List<String> bigList, List<String> smallList) {
        List<String> newList = new ArrayList<String>();
        for (String val : bigList) {
            for (String insert : smallList) {
                if (!val.startsWith("0")) {
                    String newString = val.substring(0, val.length()/2) + insert + val.substring(val.length()/2, val.length());
                    newList.add(newString);
                }
            }
        }
        return newList;
    }

    Set<String> stroboNums = new HashSet<String>();
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return false;
        }

        stroboNums.add("0");
        stroboNums.add("1");
        stroboNums.add("6");
        stroboNums.add("8");
        stroboNums.add("9");

        int i = 0;
        for (i = 0; i < num.length() / 2; i++) {
            int k = num.length() - i - 1;
            if (!isCorrect(num.substring(i, i+1), num.substring(k, k+1))) {
                return false;
            }
        }
        if (num.length() % 2 != 0) {
            return isCorrect(num.substring(i, i+1), null);
        }
        return true;
    }

    private boolean isCorrect(String a, String b) {
        if (b == null) {
            if (!stroboNums.contains(a) || a.equals("6") || a.equals("9")) {
                return false;
            }
            return true;
        }

        if (!stroboNums.contains(a) || !stroboNums.contains(b)) {
            return false;
        }
        if (a.equals("6") && b.equals("9")) {
            return true;
        }
        if (a.equals("9") && b.equals("6")) {
            return true;
        }
        if (a.equals("6") || b.equals("6") || a.equals("9") || b.equals("9")) {
            return false;
        }
        return a.equals(b);
    }

    public static void main(String args[]) {
        StrobogrammaticNumbers sb = new StrobogrammaticNumbers();
        /*List<String> answer = sb.findStrobogrammatic(6);
        for (String val : answer) {
            System.out.print(val + " ,");
        }*/
        System.out.println("value is " +sb.strobogrammaticInRange("0", "0"));
    }
}
