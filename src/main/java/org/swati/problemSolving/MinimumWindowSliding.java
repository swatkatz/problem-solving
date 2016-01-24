package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Minimum window sliding - not complete -- need to implement duplicate characters!
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class MinimumWindowSliding {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        //preprocess the string
        Set<Character> letters = new HashSet<Character>();
        char[] tArr = t.toCharArray();
        for (int i = 0; i < tArr.length; i++) {
            letters.add(tArr[i]);
        }

        List<CharPos> currPositions = new ArrayList<CharPos>();
        CharPosComp comp = new CharPosComp();
        String subStr = "";

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            //If all the characters have not been found
            if (currPositions.size() != letters.size()) {
                //if one of the letters has been found
                if (letters.contains(curr)) {
                    boolean toReSort = false;
                    int j = 0;
                    for (j = 0; j < currPositions.size(); j++) {
                        CharPos element = currPositions.get(j);
                        if (element.currChar == curr) {
                            if (element.arrPos < i) {
                                element.arrPos = i;
                                toReSort = true;
                            }
                            break;
                        }
                    }
                    if (j >= currPositions.size() && currPositions.size() != letters.size()) {
                        currPositions.add(new CharPos(curr, i));
                        toReSort = true;
                    }
                    if (toReSort) {
                        Collections.sort(currPositions, comp);
                    }
                }
            } else {
                //all the chars have been found
                int last = currPositions.get(currPositions.size() - 1).arrPos;
                int first = currPositions.get(0).arrPos;
                int substringSize = last - first + 1;
                int prevMin = min;
                min = Math.min(min, substringSize);
                if (min < prevMin || subStr == "") {
                    subStr = s.substring(first, last + 1);
                }
                if (min < prevMin) {
                    currPositions.remove(0);
                }
            }
        }

        if (currPositions.size() == letters.size()) {
            //all the chars have been found
            int last = currPositions.get(currPositions.size() - 1).arrPos;
            int first = currPositions.get(0).arrPos;
            int substringSize = last - first + 1;
            int prevMin = min;
            min = Math.min(min, substringSize);
            if (min < prevMin || subStr == "") {
                subStr = s.substring(first, last + 1);
            }
        }

        return subStr;
    }

    class CharPos {
        public char currChar;
        public int arrPos;
        CharPos(char currChar, int arrPos) {
            this.currChar = currChar;
            this.arrPos = arrPos;
        }
    }

    class CharPosComp implements Comparator<CharPos> {
        public int compare(CharPos o1, CharPos o2) {
            return o1.arrPos - o2.arrPos;
        }
    }
}
