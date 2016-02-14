package org.swati.leetcode.algorithms.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

 Examples:
 pattern = "abab", str = "redblueredblue" should return true.
 pattern = "aaaa", str = "asdasdasdasd" should return true.
 pattern = "aabb", str = "xyzabcxzyabc" should return false.
 Notes:
 You may assume both pattern and str contains only lowercase letters.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordPatternII {
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        Set<String> seen = new HashSet<String>();
        return isMatch(str, 0, pattern, 0, map, seen);
    }

    private boolean isMatch(String str, int start, String pattern, int patStart, HashMap<Character, String> map, Set<String> seen) {
        //Both the strings have reached the end simultaneously
        if (start == str.length() && patStart == pattern.length()) {
            return true;
        }

        //One of them have finished processing, the other is still remaining
        if (start == str.length() || patStart == pattern.length()) {
            return false;
        }

        //Get the character
        Character c = pattern.charAt(patStart);

        //Check if map already contains this character
        if (map.containsKey(c)) {
            String s = map.get(c);
            //Check if where we are right now in str, that position starts with the string that was saved earlier
            if (!str.startsWith(s, start)) {
                return false;
            }
            //since it does, we can skip over the length of the just now matched string and move to the next pattern processing
            return isMatch(str, start + s.length(), pattern, patStart + 1, map, seen);
        } else {
            for (int i = start; i < str.length(); i++) {
                String temp = str.substring(start, i+1);
                if (seen.contains(temp)) {
                    continue;
                }
                map.put(c, temp);
                seen.add(temp);
                if (isMatch(str, i + 1, pattern, patStart + 1, map, seen)) {
                    return true;
                }
                //This relation did not yeild results, remove it from the map and greedy match again
                map.remove(c);
                seen.remove(temp);
            }
        }
        return false;
    }
}
