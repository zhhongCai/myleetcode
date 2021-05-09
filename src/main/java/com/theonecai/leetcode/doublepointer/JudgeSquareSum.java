package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 633
 * @Author: theonecai
 * @Date: Create in 4/28/21 20:41
 * @Description:
 */
public class JudgeSquareSum {
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JudgeSquareSum judgeSquareSum = new JudgeSquareSum();
        Assert.assertTrue(judgeSquareSum.judgeSquareSum(0));
        Assert.assertTrue(judgeSquareSum.judgeSquareSum(1));
        Assert.assertTrue(judgeSquareSum.judgeSquareSum(2));
        Assert.assertFalse(judgeSquareSum.judgeSquareSum(3));
        Assert.assertTrue(judgeSquareSum.judgeSquareSum(4));
        Assert.assertTrue(judgeSquareSum.judgeSquareSum(45));
    }
}
