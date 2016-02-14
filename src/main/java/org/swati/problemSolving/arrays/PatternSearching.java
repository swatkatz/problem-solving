package org.swati.problemSolving.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a text txt[0..n-1] and a pattern pat[0..m-1],
 * write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[].
 * You may assume that n > m.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */

/**
 * Examples:
 1) Input:

 txt[] =  "THIS IS A TEST TEXT"
 pat[] = "TEST"
 Output:

 Pattern found at index 10
 2) Input:

 txt[] =  "AABAACAADAABAAABAA"
 pat[] = "AABA"
 Output:

 Pattern found at index 0
 Pattern found at index 9
 Pattern found at index 13
 */
public class PatternSearching {

    private List<Integer> getStartingOffset(String givenStr, String pattern) {
        List<Integer> offsets = new ArrayList<Integer>();
        char[] givenStrArr = givenStr.toCharArray();
        char[] patternArr = pattern.toCharArray();
        int i = 0;
        while (i < givenStrArr.length) {
            int count = i;
            boolean found = true;
            for (char aPatternArr : patternArr) {
                if (count == givenStrArr.length || givenStrArr[count] != aPatternArr) {
                    found = false;
                    break;
                }
                count++;
            }
            count++;
            if (found) {
                count--;
                offsets.add(i);
            }
            i = count;
        }
        return offsets;
    }

    public static void main(String args[]) {
        PatternSearching ps = new PatternSearching();
        List<Integer> lisOffsets = ps.getStartingOffset("THIS IS A TEST TEXT", "TEST");
        for (Integer val : lisOffsets) {
            System.out.print(", " + val);
        }
    }
}
