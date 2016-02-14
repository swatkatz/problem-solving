package org.swati.problemSolving.permutations;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a dictionary as a hashtable and a word.
 * Find the minimum # of deletions needed on the word in order to make it a valid word in the dictionary.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MakeValidWord {
    private static final Set<String> DICTIONARY = new HashSet<String>();;

    public MakeValidWord() {
        DICTIONARY.add("abc");
        DICTIONARY.add("abd");
        DICTIONARY.add("a");
        DICTIONARY.add("ab");
        DICTIONARY.add("acd");
        DICTIONARY.add("abcd");
    }

    private int getMinDeletions(String givenWord) {
        int min = Integer.MAX_VALUE;
        for (int level = (int) Math.pow(2, givenWord.length()) - 1; level > 0; level--) {
            String reducedStr = getReducedStr(givenWord, level);
            if (DICTIONARY.contains(reducedStr)) {
                int diffLength = givenWord.length() - reducedStr.length();
                if (min > diffLength) {
                    System.out.println("chosen string is " + reducedStr);
                    min = diffLength;
                }
            }
        }
        return min;
    }

    private String getReducedStr(String givenWord, int level) {
        char[] givenArr = givenWord.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < givenArr.length; i++) {
            int bitMap = (int) Math.pow(2, i);
            if ((bitMap & level) == bitMap) {
                sb.append(givenArr[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MakeValidWord makeValidWord = new MakeValidWord();
        System.out.println("Min deletions reqd is " + makeValidWord.getMinDeletions("aqwtbscde"));
    }

}
