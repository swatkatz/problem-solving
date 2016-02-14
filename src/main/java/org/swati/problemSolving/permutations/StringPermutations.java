package org.swati.problemSolving.permutations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, check if one of the combinations match a hiddenString
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class StringPermutations {
    //find all the combinations of a string in lowercase and uppercase.
    // For example, string "ab" => "ab", "Ab", "aB", "AB".
    // So, you will have 2^n (n = number of chars in the string) output strings.
    // The goal is for you to test each of these string and see if it match a hidden string.
    private boolean findCapitalHiddenString(String givenString, String hiddenString) {
        //total number of combinations
        int combinations = (int) Math.pow(2, givenString.length());
        String[] allCombinations = new String[combinations];
        char[] givenArr = givenString.toCharArray();
        for (int i = 0; i < combinations; i++) {
            allCombinations[i] = getCombination(givenArr, i);
            System.out.println(allCombinations[i]);
        }
        Set<String> allComboSet = new HashSet<String>(Arrays.asList(allCombinations));
        return allComboSet.contains(hiddenString);
    }

    private String getCombination(char[] givenArr, int level) {
        char[] modifiedArr = new char[givenArr.length];
        for (int i = 0; i < givenArr.length; i++) {
            int bitMap = (int) Math.pow(2, i);
            if ((bitMap & level) == bitMap) {
                modifiedArr[i] = Character.toUpperCase(givenArr[i]);
            } else {
                modifiedArr[i] = Character.toLowerCase(givenArr[i]);
            }
        }
        return new String(modifiedArr);
    }

    //Find all permutations of a given string and find the hiddenstring among them
    private boolean findHiddenString(String givenString, String hiddenString) {
        return givenString.equals(hiddenString) ||
                givenString.length() == hiddenString.length() &&
                        permutation(givenString, hiddenString);
    }

    private boolean permutation(String str, String hiddenString) {
        return permutation("", str, hiddenString);
    }

    private boolean permutation(String prefix, String str, String hiddenString) {
        int n = str.length();
        boolean retVal = false;
        if (n == 0) {
            System.out.println("prefix " + prefix);
            return hiddenString.equalsIgnoreCase(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                if (retVal) {
                    return true;
                } else {
                    retVal = permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), hiddenString);
                }
            }
        }
        return retVal;
    }

    public static void main(String args[]) {
        StringPermutations sc = new StringPermutations();
        if (sc.findHiddenString("abcd", "dcBA")) {
            System.out.println("Hidden string found");
        } else {
            System.out.println("Hidden string not found");
        }
        if (sc.findCapitalHiddenString("abcd", "ABCD")) {
            System.out.println("Cap Hidden string found");
        } else {
            System.out.println("Cap Hidden string not found");
        }
    }
}
