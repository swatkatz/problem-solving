package org.swati.euler.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible.
 If the number of spaces on a line do not divide evenly between words, the empty slots on
 the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.

 click to show corner cases.

 Corner Cases:
 A line other than the last line might contain only one word. What should you do in this case?
 In this case, that line should be left-justified.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedList = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        if (words.length == 1) {
            justifiedList.add(appendSpacesAtTheEnd(words[0], maxWidth));
            return justifiedList;
        }
        for (String word : words) {
            if (sb.length() == 0) {
                sb.append(word);
            } else if ((sb.toString() + " " + word).length() > maxWidth) {
                //the new word + space can't be added together
                justifiedList.add(adjustSpace(sb.toString(), maxWidth));
                sb = new StringBuffer(maxWidth);
                sb.append(word);
            } else {
                //the maxWidth hasn't been reached, the word can be appended safely
                sb.append(" ").append(word);
            }
        }
        justifiedList.add(appendSpacesAtTheEnd(sb.toString(), maxWidth));
        return justifiedList;
    }

    private String adjustSpace(String givenString, int maxWidth) {
        if (givenString != null && givenString.length() > 0) {
            if (givenString.contains(" ")) {
                String[] splitString = givenString.split(" ");
                StringBuilder sb = new StringBuilder(maxWidth);
                //add the remainder + the already existing one
                int divisorSpaces = getRemainingSpaces(givenString, maxWidth) / (splitString.length - 1) + 1;
                int remainderSpaces = getRemainingSpaces(givenString, maxWidth) % (splitString.length - 1);
                int spaceCount;
                for (int strCount = 0; strCount < splitString.length - 1; strCount++) {
                    sb.append(splitString[strCount]);
                    if (strCount < remainderSpaces) {
                        spaceCount = divisorSpaces + 1;
                    } else {
                        spaceCount = divisorSpaces;
                    }
                    for (int i = 0; i < spaceCount; i++) {
                        sb.append(" ");
                    }
                }
                //append last word and return
                return sb.append(splitString[splitString.length - 1]).toString();
            } else {
                //givenString is only a single word and so just append spaces at the end
                return appendSpacesAtTheEnd(givenString, maxWidth);
            }
        }
        return givenString;
    }

    private String appendSpacesAtTheEnd(String givenString, int maxWidth) {
        StringBuilder spaceBuffer = new StringBuilder(givenString);
        for (int i = 0; i < getRemainingSpaces(givenString, maxWidth); i++) {
            spaceBuffer.append(" ");
        }
        return spaceBuffer.toString();
    }

    private int getRemainingSpaces(String givenString, int maxWidth) {
        return maxWidth - givenString.length();
    }

    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        String[] words = {"Here","is","an","example","of","text","justification."};
        List<String> justifiedList = textJustification.fullJustify(words, 15);
        System.out.println("The length of the list is " + justifiedList.size());
        for (String aJustifiedList : justifiedList) {
            System.out.println(aJustifiedList);
        }
    }
}