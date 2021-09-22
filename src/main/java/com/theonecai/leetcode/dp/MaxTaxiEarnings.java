package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2008
 */
public class MaxTaxiEarnings {
    public long maxTaxiEarnings2(int n, int[][] rides) {
        long[] dp = new long[n + 1];
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));

        int index = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            while (index < rides.length && rides[index][1] <= i) {
                dp[i] = Math.max(dp[i], dp[rides[index][0]] + rides[index][1] - rides[index][0] + rides[index][2]);
                index++;
            }
        }
        return dp[n];
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));

        long[] dp = new long[rides.length];

        int[] pre = null;
        for (int i = 0; i < rides.length; i++) {
            int profit = rides[i][1] - rides[i][0] + rides[i][2];
            dp[i] = Math.max(i == 0 ? 0 : dp[i - 1], profit);
            int left = 0;
            int right = i - 1;
            while (left < right) {
                int mid = (left + right + 1) / 2;
                if (rides[mid][1] > rides[i][0]) {
                    right = mid - 1;
                } else {
                    left = mid;
                }
            }
            pre = rides[left];
            if (pre[1] <= rides[i][0]) {
                dp[i] = Math.max(dp[i], dp[left] + profit);
            }
        }
        return dp[rides.length - 1];
    }

    public static void main(String[] args) {
        MaxTaxiEarnings maxTaxiEarnings = new MaxTaxiEarnings();

        Assert.assertEquals(20, maxTaxiEarnings.maxTaxiEarnings(20, new int[][]{
                {1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}
        }));
        Assert.assertEquals(7, maxTaxiEarnings.maxTaxiEarnings(5, new int[][]{
                {2,5,4},{1,5,1}
        }));
    }
}
