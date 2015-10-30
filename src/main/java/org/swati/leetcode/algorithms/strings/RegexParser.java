package org.swati.leetcode.algorithms.strings;

/**
 * Solve for
 *  * => matches 0 or many
 *  . => matches any
 *  char => matches that char
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class RegexParser {

    private static final char DOT = '.';
    private static final char STAR = '*';

    private boolean matches(String regex, String str, int regexPos, int strPos) {
        while (regexPos < regex.length() && strPos < str.length()) {
            if (regex.charAt(regexPos) == str.charAt(strPos) || regex.charAt(regexPos) == DOT) {
                strPos++;
                regexPos++;
            } else if (regex.charAt(regexPos) == STAR) {
                regexPos++;
                while (strPos < str.length()) {
                    if (matches(regex, str, regexPos, strPos)) {
                        return true;
                    }
                    strPos++;
                }
            } else {
                return false;
            }
        }

        if (regexPos != regex.length()) {
            for (int x = regexPos; x < regex.length() ; x++) {
                if (regex.charAt(x) != STAR) {
                    return false;
                }
            }
        }

        if (strPos != str.length()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        RegexParser regexParser = new RegexParser();
        String str = "abbxcddee";
        String regex = "a**b.c*d*e";
        System.out.println("Matches is " + regexParser.matches(regex, str, 0, 0));
    }
}
