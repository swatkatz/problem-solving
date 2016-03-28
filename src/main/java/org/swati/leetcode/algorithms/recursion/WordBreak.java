package org.swati.leetcode.algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Get the given word broken into words from the dictionary
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class WordBreak {
    private List<String> ans;
    public List<String> wordBreak(String s, Set<String> wordDict) {
        ans = new ArrayList<String>();
        recurse(s, wordDict, "");
        return ans;
    }

    private boolean recurse(String s, Set<String> wordDict, String collected) {
        int len = s.length();
        if (len == 0) {
            return true;
        }

        String soFar = "";
        for(int i = 0; i < s.length(); i++) {
            soFar += s.charAt(i);
            if (wordDict.contains(soFar)) {
                String temp = s.substring(soFar.length(), s.length());
                collected = collected.equals("") ? collected + soFar : collected + " " + soFar;
                if (recurse(temp, wordDict, collected)) {
                    ans.add(collected);
                }
                collected = collected.replace(soFar, "");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        Set<String> stringSet = new HashSet<String>();
        stringSet.addAll(Arrays.asList("cat","cats","and","sand","dog"));
        List<String> answers = wordBreak.wordBreak("catsanddog",stringSet);
        for (String ans : answers) {
            System.out.println(ans);
        }
    }
}
