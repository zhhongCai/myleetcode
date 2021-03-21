package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 70
 * @Author: theonecai
 * @Date: Create in 2021/3/21 15:51
 * @Description:
 */
public class ClimbStairs {
    public int climbStairs2(int n) {
        // dp[i]表示到达台阶i的方法数,dp[i] = dp[i - 1] + dp[i - 2];
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs(int n) {
        int pre = 1;
        int prePre = 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = pre + prePre;
            prePre = pre;
            pre = result;
        }
        return result;
    }

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        Assert.assertEquals(1, climbStairs.climbStairs(1));
        Assert.assertEquals(2, climbStairs.climbStairs(2));
        Assert.assertEquals(3, climbStairs.climbStairs(3));
    }
}
