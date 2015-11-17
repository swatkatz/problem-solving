package org.swati.problemSolving;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class DepthSum {
    /**
     * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
     * For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1's at depth 2, one 2 at depth 1)
     * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)
     */
    int sum = 0;
    public int depthSum (List<NestedInteger> input)
    {
        sum = 0;
        dfs(input, 1);
        return sum;
    }

    private void dfs(List<NestedInteger> ns, int depth) {
        for (NestedInteger nsVal : ns) {
            if (nsVal.isInteger()) {
                sum += nsVal.getInteger() * depth;
            } else {
                dfs(nsVal.getList(), depth + 1);
            }
        }
    }

    public static class NestedInteger
    {
        List<NestedInteger> nsList = new ArrayList<NestedInteger>();
        Integer val = null;

        public NestedInteger(Integer val) {
            this.val = val;
        }

        public NestedInteger(List<NestedInteger> nsList) {
            this.nsList = nsList;
        }

        /** @return true if this NestedInteger holds a single integer, rather than a nested list */
        public boolean isInteger() {
            return nsList.size() == 0;
        }

        /** @return the single integer that this NestedInteger holds, if it holds a single integer
         * Return null if this NestedInteger holds a nested list */
        public Integer getInteger() {
            return val;
        }

        /** @return the nested list that this NestedInteger holds, if it holds a nested list
         * Return null if this NestedInteger holds a single integer */
        public List<NestedInteger> getList() {
            return this.isInteger() ? null : nsList;
        }
    }

    public static void main(String[] args) {
        DepthSum ds = new DepthSum();
        List<NestedInteger> input = new ArrayList<NestedInteger>();
        List<NestedInteger> list1 = new ArrayList<NestedInteger>();
        list1.add(new NestedInteger(1));
        list1.add(new NestedInteger(1));
        input.add(new NestedInteger(list1));
        input.add(new NestedInteger(2));
        input.add(new NestedInteger(list1));
        System.out.println("Depth sum is " + ds.depthSum(input));
        list1.clear();
        input.clear();

        list1 = new ArrayList<NestedInteger>();
        list1.add(new NestedInteger(4));
        List<NestedInteger> list2 = new ArrayList<NestedInteger>();
        list2.add(new NestedInteger(6));
        list1.add(new NestedInteger(list2));
        input.add(new NestedInteger(1));
        input.add(new NestedInteger(list1));
        System.out.println("Depth sum is " + ds.depthSum(input));
    }
}
