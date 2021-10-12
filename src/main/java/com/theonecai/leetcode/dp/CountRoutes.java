package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 1575
 */
public class CountRoutes {
    int mod = 1000000007;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        //dp[i][j] = dp[k][j - need];
        int[][] dp = new int[locations.length][fuel + 1];
        for (int i = 0; i <= fuel; i++) {
            dp[finish][i] = 1;
        }
        for (int i = 1; i <= fuel; i++) {
            for (int j = 0; j < locations.length; j++) {
                for (int k = 0; k < locations.length; k++) {
                    if (j == k) {
                        continue;
                    }
                    int need = Math.abs(locations[j] - locations[k]);
                    if (i >= need) {
                        dp[j][i] += dp[k][i - need];
                        dp[j][i] %= mod;
                    }
                }
            }
        }

        return dp[start][fuel];
    }

    public static void main(String[] args) {
        CountRoutes countRoutes = new CountRoutes();
        Assert.assertEquals(4, countRoutes.countRoutes(new int[]{2,3,6,8,4}, 1, 3, 5));
        Assert.assertEquals(5, countRoutes.countRoutes(new int[]{4,3,1}, 1, 0, 6));
        Assert.assertEquals(0, countRoutes.countRoutes(new int[]{5,2,1}, 0, 2, 3));
        Assert.assertEquals(2, countRoutes.countRoutes(new int[]{2,1,5}, 0, 0, 3));
        Assert.assertEquals(615088286, countRoutes.countRoutes(new int[]{1,2,3}, 0, 2, 40));
        Assert.assertEquals(615088286, countRoutes.countRoutes(new int[]{1,2,3,4,5}, 1, 2, 40));
    }
}
