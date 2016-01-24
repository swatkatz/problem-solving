package org.swati.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutations using the merge method
 * Basically, permutations(str of length n) = merge{last char in n with permutations(str of length n -1)}
 * permutations(a) = {a}
 * permutations(ab) = {a'b', 'b'a}
 * permutations(abc) = {'c'ab, a'c'b, ab'c', 'c'ba, b'c'a, ba'c'}
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class StringPermutationII {

    List<String> permute(String str) {
        List<String> perm = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return perm;
        }
        int n = str.length();
        if (n == 1) {
            perm.add(str);
            return perm;
        }
        List<String> prev = permute(str.substring(0, n - 1));
        String lastChar = str.substring(n - 1);

        for (String p : prev) {
            for (int i = 0; i <= p.length(); i++) {
                if (i == 0) {
                    perm.add(lastChar + p);
                } else if (i == p.length()) {
                    perm.add(p + lastChar);
                } else {
                    perm.add(p.substring(0, i) + lastChar + p.substring(i, p.length()));
                }
            }
        }
        return perm;
    }

    public static void main(String[] args) {
        StringPermutationII sp2 = new StringPermutationII();
        List<String> permutations = sp2.permute("abcd");
        for (String permute : permutations) {
            System.out.println(permute);
        }
    }
}
