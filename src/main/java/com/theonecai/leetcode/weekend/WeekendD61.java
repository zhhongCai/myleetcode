package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: theonecai
 * @Date: Create in 2021/09/18 10:24
 * @Description:
 */
public class WeekendD61 {

    public int countKDifference(int[] nums, int k) {
        int c = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    c++;
                }
            }
        }

        return c;
    }

    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 == 1) {
            return new int[]{};
        }

        int[] nums = new int[200002];
        for (int i = 0; i < n; i++) {
            nums[changed[i]]++;
        }
        int[] res = new int[changed.length / 2];
        int k = 0;
        for (int i = 0; i < 100001; i++) {
            int j = i * 2;
            while (nums[i] > 0) {
                if (nums[i] > 0 && nums[j] > 0) {
                    nums[i]--;
                    nums[j]--;
                    res[k++] = i;
                } else {
                    return new int[]{};
                }
            }
        }
        if (k != n / 2) {
            return new int[]{};
        }
        return res;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));

        long[] dp = new long[rides.length];

        int[] pre = null;
        for (int i = 0; i < rides.length; i++) {
            int profit = rides[i][1] - rides[i][0] + rides[i][2];
            dp[i] = Math.max(i == 0 ? 0 : dp[i - 1], profit);
            for (int j = i - 1; j >= 0; j--) {
                pre = rides[j];
                if (pre[1] <= rides[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + profit);
                    break;
                }
            }
        }
        return dp[rides.length - 1];
    }

    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = n;

        for (int i = 0, j = 0; i < n; i++, j++) {

        }


        return 0;
    }

    public static void main(String[] args) {
        WeekendD61 weekend = new WeekendD61();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        Assert.assertEquals(0, minOperations(new int[]{1}));
        Assert.assertEquals(0, minOperations(new int[]{1,2,3,4,5}));
        Assert.assertEquals(1, minOperations(new int[]{1,2,3,5,6}));
        Assert.assertEquals(3, minOperations(new int[]{1,10,100,1000}));
        Assert.assertEquals(1, minOperations(new int[]{1,20,23,24,22}));
    }

    private void test3() {
    }

    private void test2() {
    }

    private void test() {
    }
}
