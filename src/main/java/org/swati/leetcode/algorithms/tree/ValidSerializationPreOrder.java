package org.swati.leetcode.algorithms.tree;

import java.util.Stack;

/**
 * Check if valid preorder serialization for a binary tree
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class ValidSerializationPreOrder {
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<String>();
        String[] strArr = preorder.split(",");
        for (String str : strArr) {
            if (str.equalsIgnoreCase("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return false;
                    }
                    String val = stack.pop();
                    if (val.equalsIgnoreCase("#")) {
                        return false;
                    }
                }
            }
            stack.push(str);
        }
        return stack.pop().equalsIgnoreCase("#") && stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidSerializationPreOrder v = new ValidSerializationPreOrder();
        boolean isValid = v.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        System.out.println("isValid " + isValid);
    }
}
