package org.swati.problemSolving.permutations;

import java.util.HashSet;
import java.util.Set;

/**
 * Get the maximum product of the words length
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MaxWProductWordLength {

    public int getMaxProduct(String[] words) {
        int product = 0;
        int[] wordRep = new int[words.length];
        for (int i = 0; i < wordRep.length; i++) {
            wordRep[i] = getRepresentation(words[i]);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (i != j && (wordRep[i] & wordRep[j]) == 0) { //words are distinct
                    int prodLength = words[i].length() * words[j].length();
                    if (product < prodLength) {
                        product = prodLength;
                    }
                }
            }
        }
        return product;
    }

    private int getRepresentation(String word) {
        char[] wordChar = word.toCharArray();
        Set<Character> charSet = new HashSet<Character>();
        int rep = 0;
        for (int i = 0; i < wordChar.length; i++) {
            if (!charSet.contains(wordChar[i])) {
                charSet.add(wordChar[i]);
                int power = (int) Math.pow(2, wordChar[i] - 'a');
                rep += power;
            }
        }
        return rep;
    }

    public void printMaxProduct(String[] words) {
        if (getMaxProduct(words) == 0) {
            System.out.println("No such pair of words");
        } else {
            System.out.println("Max product is " + getMaxProduct(words));
        }
    }

    public static void main(String[] args) {
        MaxWProductWordLength maxWProductWordLength = new MaxWProductWordLength();
        String[] words = {"a","aa","aaa","aaaa"};
        maxWProductWordLength.printMaxProduct(words);
    }
}
