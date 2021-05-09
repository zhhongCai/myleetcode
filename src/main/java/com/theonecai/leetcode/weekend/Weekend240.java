package com.theonecai.leetcode.weekend;


import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2021/05/09 10:24
 * @Description:
 */
public class Weekend240 {
    public int maximumPopulation(int[][] logs) {
        int[] yearCount = new int[101];
        for (int[] log : logs) {
            for (int i = log[0]; i < log[1]; i++) {
                yearCount[i - 1950]++;
            }
        }
        int max = 0;
        int i = 0;
        for (int j = 0; j < yearCount.length; j++) {
            if (max < yearCount[j]) {
                max = yearCount[j];
                i = j;
            }
        }
        return i + 1950;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int n = nums1.length;
        if (nums1[n - 1] > nums2[0]) {
            return 0;
        }
        int maxDist = 0;
        for (int i = 0; i < n; i++) {
            int j = find(nums2, nums1[i], i);
            if (j != -1) {
                maxDist = Math.max(maxDist, j - i);
            }
        }
        return maxDist;
    }

    private int find(int[] nums2, int num, int idx) {
        int left = idx;
        int right = nums2.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums2[mid] >= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left < nums2.length) {
            if (nums2[left] >= num) {
                return left;
            }
            if (left > 0 && nums2[left - 1] >= num) {
                return left - 1;
            }
        } else {
            if (left > idx) {
                if (nums2[left - 1] >= num) {
                    return left - 1;
                }
            }
        }

        return -1;
    }


    private int mod = 1000000007;
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        long result = 0;


        return (int)(result % mod);
    }

    public static void main(String[] args) {
        Weekend240 weekend = new Weekend240();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(12, this.maxSumMinProduct(new int[]{1,2,3,2}));
        Assert.assertEquals(18, this.maxSumMinProduct(new int[]{2,3,3,1,2}));
        Assert.assertEquals(60, this.maxSumMinProduct(new int[]{3,1,5,6,4,2}));
    }

    private void test2() {
        Assert.assertEquals(1, this.maxDistance(new int[]{2,2,2}, new int[]{10,10,1}));
        Assert.assertEquals(2, this.maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5}));
        Assert.assertEquals(2, this.maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,25,25}));
        Assert.assertEquals(0, this.maxDistance(new int[]{5,4}, new int[]{3,2}));
    }

    private void test() {
        Assert.assertEquals(1993, this.maximumPopulation(new int[][]{
                {1993,1999},{2000,2010}
        }));
        Assert.assertEquals(1960, this.maximumPopulation(new int[][]{
                {1950,1961},{1960,1971},{1970,1981}
        }));
    }
}
