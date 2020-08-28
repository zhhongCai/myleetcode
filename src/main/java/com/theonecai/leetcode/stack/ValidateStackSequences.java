package com.theonecai.leetcode.stack;

import org.junit.Assert;

/**
 * leetcode 946
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/5 19:48
 * @Description:
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length < popped.length) {
            return false;
        }
        int pushedIndex = 0;
        int poppedIndex = 0;
        int[] stack = new int[pushed.length];
        int top = -1;
        while (poppedIndex < popped.length && pushedIndex < pushed.length) {
            if (top > -1 && stack[top] == popped[poppedIndex]) {
                poppedIndex++;
                top--;
                continue;
            }

            while (pushedIndex < pushed.length && pushed[pushedIndex] != popped[poppedIndex]) {
                stack[++top] = pushed[pushedIndex];
                pushedIndex++;
            }
            if (pushedIndex < pushed.length && pushed[pushedIndex] == popped[poppedIndex]) {
                pushedIndex++;
                poppedIndex++;
            }
        }
        while (top > -1 && stack[top] == popped[poppedIndex]) {
            top--;
            poppedIndex++;
        }
        return poppedIndex == popped.length;
    }

    public static void main(String[] args) {
        ValidateStackSequences validateStackSequences = new ValidateStackSequences();
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4,5,3,2,1};
        Assert.assertTrue(validateStackSequences.validateStackSequences(pushed, popped));
    }
}
