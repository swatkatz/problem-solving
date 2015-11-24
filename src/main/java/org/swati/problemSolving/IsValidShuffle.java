package org.swati.problemSolving;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class IsValidShuffle {
    /*
One more difficult String algorithm based coding question for senior developers. You are given 3 strings: first,  second, and  third.  third String is said to be a shuffle of first and second if it can be formed by interleaving the characters of first and second String in a way that maintains the left to right ordering of the characters from each string. For example, given first = "abc" and second = "def",  third = "dabecf"  is a valid shuffle since it preserves the character ordering of the two strings. So, given these 3 strings write a function that detects whether third String is a valid shuffle of first and second String

Read more: http://javarevisited.blogspot.com/2015/01/top-20-string-coding-interview-question-programming-interview.html#ixzz3sLeczWEO
*/

    public static boolean isValidShuffle(String str1, String str2, String shuffleStr) {
        if (shuffleStr == null ) {
            return str1 == null && str2 == null;
        }

        if (str2 == null || str2.length() == 0) {
            return str1.equals(shuffleStr);
        }

        if (str1 == null || str1.length() == 0) {
            return str2.equals(shuffleStr);
        }

        for (int i = 0; i < shuffleStr.length(); i++) {
            if (str1.length() == 0 && str2.length() == 0) {
                return false;
            }

            if (shuffleStr.charAt(i) == str1.charAt(0)) {
                if (str1.length() == 1) {
                    str1 = "";
                } else {
                    str1 = str1.substring(1);
                }
            } else if (shuffleStr.charAt(i) == str2.charAt(0)) {
                if (str2.length() == 1) {
                    str2 = "";
                } else {
                    str2 = str2.substring(1);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("isValidShuffle for abc def and daebfc is " + isValidShuffle("abc", "def", "daebfc"));
        System.out.println("isValidShuffle for abc def and dagebfc is " + isValidShuffle("abc", "def", "dagebfc"));
        System.out.println("isValidShuffle for abc def and daebfcg is " + isValidShuffle("abc", "def", "daebfcg"));
        System.out.println("isValidShuffle for null def and def is " + isValidShuffle(null, "def", "def"));
        System.out.println("isValidShuffle for null null and null is " + isValidShuffle(null, null, null));
        System.out.println("isValidShuffle for abc def and null is " + isValidShuffle("abc", "def", null));
    }
}
