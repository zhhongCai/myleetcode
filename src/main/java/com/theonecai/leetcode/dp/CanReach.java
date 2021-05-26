package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1871
 * @Author: theonecai
 * @Date: Create in 5/26/21 20:35
 * @Description:
 */
public class CanReach {
    public boolean canReach(String s, int minJump, int maxJump) {
        if (s.charAt(s.length() - 1) == '1') {
            return false;
        }
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;
        // preSum[i]表示s[0~i]中有效'0'的个数
        int[] preSum = new int[s.length()];
        for (int i = 0; i < minJump; i++) {
            preSum[i] = 1;
        }
        for (int i = minJump; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                int left = Math.max(0, i - maxJump);
                int right = i - minJump;
                int n = preSum[right] - (left == 0 ? 0 : preSum[left - 1]);
                dp[i] = n > 0;
            }
            preSum[i] = preSum[i - 1] + (dp[i] ? 1 : 0);
        }

        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        CanReach canReach = new CanReach();
        Assert.assertFalse(canReach.canReach("011011", 2, 3));
        Assert.assertTrue(canReach.canReach("011010", 2, 3));
        Assert.assertFalse(canReach.canReach("01101110", 2, 3));
        Assert.assertTrue(canReach.canReach("00000000", 2, 3));
    }
}
