package org.swati.leetcode.algorithms.array;

import java.util.HashMap;

/**
 * Match pattern with spaces
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> mapPattern = new HashMap<Character, String>();
        HashMap<String, Character> patternMap = new HashMap<String, Character>();

        String[] strSplit = str.split(" ");
        if (pattern.length() != strSplit.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (mapPattern.containsKey(pattern.charAt(i))) {
                if (!strSplit[i].equals(mapPattern.get(pattern.charAt(i)))) {
                    return false;
                }
            } else if (patternMap.containsKey(strSplit[i])) {
                if (patternMap.get(strSplit[i]) != pattern.charAt(i)) {
                    return false;
                }
            }
            else {
                mapPattern.put(pattern.charAt(i), strSplit[i]);
                patternMap.put(strSplit[i], pattern.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wordPattern = new WordPattern();
        System.out.println(wordPattern.wordPattern("abba", "dog dog dog dog"));
        System.out.println(wordPattern.wordPattern("aaaa", "dog dog dog dog"));
    }
}
