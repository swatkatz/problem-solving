package org.swati.leetcode.algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author swkumar (swkumar@groupon.com)
 * @since 1.0.0
 */
public class PowerSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int sslen = (int) Math.pow(2, nums.length);
        for (int i = 0; i < sslen; i++) {
            ans.add(getList(i, nums));
        }
        return ans;
    }

    private List<Integer> getList(int perm, int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            int pos = (int) Math.pow(2, i);
            if ((pos & perm) == pos) {
                list.add(nums[i]);
            }
        }
        return list;
    }
}
