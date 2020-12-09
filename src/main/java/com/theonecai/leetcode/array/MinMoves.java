package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 1674
 * 差分数组
 * @Author: theonecai
 * @Date: Create in 2020/12/6 10:13
 * @Description:
 */
public class MinMoves {

    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] diff = new int[limit * 2 + 2];
        int low = 0;
        int high = 0;
        int sum = 0;
        for (int i = 0; i < n / 2; i++) {
            low = Math.min(nums[i], nums[n - i - 1]) + 1;
            high = Math.max(nums[i], nums[n - i - 1]) + limit;
            sum = nums[i] + nums[n - i - 1];
            diff[low]--;
            diff[sum]--;
            diff[sum + 1]++;
            diff[high + 1]++;
        }

        int result = n;
        int res = n;
        for (int i = 2; i <= limit * 2; i++) {
            res += diff[i];
            result = Math.min(res, result);
        }

        return result;
    }

    public static void main(String[] args) {
        MinMoves minMoves = new MinMoves();
        Assert.assertEquals(1, minMoves.minMoves(new int[]{1,2,4,3}, 4));
    }
}
