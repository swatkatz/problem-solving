package org.swati.euler.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Find a 10 substring friendly number
 * A 10-substring of a number is a substring of its digits that sum to 10. For example, the 10-substrings of the number 3523014 are:
 * 352
 * 523
 * 5230
 * 23014
 A number is called 10-substring-friendly if every one of its digits belongs to a 10-substring.
 For example, 3523014 is 10-substring-friendly, but 28546 is not.
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TenSubstrings {

    public static void main(String args[]) {
        TenSubstrings tenSubstrings = new TenSubstrings();
        Integer number = 55001;
        if (tenSubstrings.isTenSubstringFriendly(number)) {
            System.out.println("The number " + number + " is ten substring friendly");
        } else {
            System.out.println("The number " + number + " is not ten substring friendly");
        }

    }

    /**
     * A method that returns true if the given number is 10-substring friendly
     * @param number to be checked for 10-substring friendliness
     * @return If the given number is ten substring friendly
     */

    public boolean isTenSubstringFriendly(Integer number) {
        List<Integer> integerList = convertLongToArr(number);
        //If it's a single number then the sum can't be 10
        if (integerList.size() == 1) {
            return false;
        }
        //stores the point in the array until which we have traversed
        int state = 0;
        for (int i = 0; i < integerList.size(); i++) {
            //stores the sum of each substring sequence
            int sum = 0;
            //a flag to denote if the substring sequence was reached in the traversal
            boolean isSumTen = false;
            for (int j = i; j < integerList.size(); j++) {
                sum += integerList.get(j);
                if (sum == 10) {
                    state = j;
                    isSumTen = true;
                }
            }
            //End the processing if all numbers have been consumed and found to belong to a 10-substring
            if (state == integerList.size() -1) {
                return true;
            }
            //If the sequence did not result in a sum of 10 and the start of this sequence was not part of a previous sequence
            if (!isSumTen && state < i) {
                return false;
            }
        }
        return false;
    }

    private List<Integer> convertLongToArr(Integer number) {
        String temp = number.toString();
        List<Integer> integerList = new ArrayList<Integer>(temp.length());
        for (int i = 0; i < temp.length(); i++) {
            integerList.add(temp.charAt(i) - '0');
        }
        return integerList;
    }
}
