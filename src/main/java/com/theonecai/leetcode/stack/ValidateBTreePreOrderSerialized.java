package com.theonecai.leetcode.stack;

import org.junit.Assert;

/**
 * leetcode 331
 *
 * @Author: thonecai
 * @Date: Create in 2020/8/6 19:50
 * @Description:
 */
public class ValidateBTreePreOrderSerialized {

    private static final String NULL_LEAF = "#";

    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.trim().equals("") ||
                (preorder.startsWith(NULL_LEAF) && preorder.length() > 1)) {
            return false;
        }

        String[] strs = preorder.split(",");
        String[] stack = new String[strs.length];
        int top = -1;
        stack[++top] = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (isNullLeaf(strs[i])) {
                int t = top;
                while (top > -1 && isNullLeaf(stack[top])) {
                    if (top > 0 && isNullLeaf(stack[top - 1])) {
                        return false;
                    } else {
                        top--;
                    }
                }
                if (t == top) {
                    top++;
                }
                while (top > 1 && isNullLeaf(stack[top - 1]) && !isNullLeaf(stack[top - 2])) {
                    top -= 2;
                }
                if (top > -1) {
                    stack[top] = NULL_LEAF;
                    if (i < strs.length - 1 && top == 0 && isNullLeaf(stack[0])) {
                        return false;
                    }
                }
            } else {
                stack[++top] = strs[i];
            }
        }

        return top == 0 && isNullLeaf(stack[0]);
    }

    private boolean isNullLeaf(String node) {
        return NULL_LEAF.equals(node);
    }

    public static void main(String[] args) {
        ValidateBTreePreOrderSerialized validate = new ValidateBTreePreOrderSerialized();
        Assert.assertTrue(validate.isValidSerialization("1,2,#,#,4,#,#"));
        Assert.assertTrue(validate.isValidSerialization("9,3,4,#,#,2,#,#,6,#,#"));
        Assert.assertTrue(validate.isValidSerialization("9,3,#,#,#"));
        Assert.assertFalse(validate.isValidSerialization("9,3,#,#"));
        Assert.assertTrue(validate.isValidSerialization("9,#,#"));
        Assert.assertFalse(validate.isValidSerialization("9,1,#"));
        Assert.assertFalse(validate.isValidSerialization("#,#,#"));
        Assert.assertTrue(validate.isValidSerialization("#"));
        Assert.assertFalse(validate.isValidSerialization(""));
        Assert.assertFalse(validate.isValidSerialization("1,#,#,#,#"));
    }
}
