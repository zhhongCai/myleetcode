package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 5687
 * @Author: theonecai
 * @Date: Create in 2021/2/21 17:14
 * @Description:
 */
public class MaximumScore {

    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int n = nums.length;
        //dp[left][right]
        int[][] dp = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            dp[0][i] = dp[0][i - 1] + multipliers[i - 1] * nums[n - i];
            dp[i][0] = dp[i - 1][0] + multipliers[i - 1] * nums[i - 1];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; i + j <= m; j++) {
                int multiplier =  multipliers[i + j - 1];
                dp[i][j] = Math.max(dp[i - 1][j] + multiplier * nums[i - 1], dp[i][j - 1] + multiplier * nums[n - j]);
                if (i + j == m) {
                    max = Math.max(max, dp[i][m - i]);
                }
            }
        }
        return max;
    }

    private Map<String, Integer> memo;
    public int maximumScore2(int[] nums, int[] multipliers) {
        memo = new HashMap<>();
        return dfs(nums, multipliers, 0, nums.length - 1, 0);
    }


    private int dfs(int[] nums, int[] multipliers, int left, int right, int step) {
        String key = left + "-" + (step);
        if (memo.containsKey(key)) {
//            System.out.println(key);
            return memo.get(key);
        }
        if (step >= multipliers.length) {
            return 0;
        }

        int score = dfs(nums, multipliers, left + 1, right, step + 1) + nums[left] * multipliers[step];
        int score2 = dfs(nums, multipliers, left, right - 1, step + 1) + nums[right] * multipliers[step];

        memo.put(key, Math.max(score, score2));
        return memo.get(key);
    }


    public static void main(String[] args) {
        MaximumScore maximumScore = new MaximumScore();
        Assert.assertEquals(6861161, maximumScore.maximumScore2(
                new int[]{555,526,732,182,43,-537,-434,-233,-947,968,-250,-10,470,-867,-809,-987,120,607,-700,25,-349,-657,349,-75,-936,-473,615,691,-261,-517,-867,527,782,939,-465,12,988,-78,-990,504,-358,491,805,756,-218,513,-928,579,678,10},
                new int[]{783,911,820,37,466,-251,286,-74,-899,586,792,-643,-969,-267,121,-656,381,871,762,-355,721,753,-521}));
        Assert.assertEquals(102, maximumScore.maximumScore2(new int[]{-5,-3,-3,-2,7,1}, new int[]{-10,-5,3,4,6}));
        Assert.assertEquals(14, maximumScore.maximumScore(new int[]{1,2,3}, new int[]{3,2,1}));
    }
}
