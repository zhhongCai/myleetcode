package com.theonecai.leetcode.dp;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 416
 * @Author: theonecai
 * @Date: Create in 2020/10/11 13:09
 * @Description:
 */
public class CanPartition {

    public boolean canPartition2(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int expectSubSum = sum / 2;
        Arrays.sort(nums);
        return dfs(nums, expectSubSum, 0);
    }

    private boolean dfs(int[] nums, int expectSubSum, int startIndex) {
        if (expectSubSum < 0) {
            return false;
        }
        if (expectSubSum == 0) {
            return true;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (nums[i] > expectSubSum) {
                return false;
            }
            if (dfs(nums, expectSubSum - nums[i], i + 1)) {
                return true;
            }
        }
        return false;
    }


    public boolean canPartition(int[] nums) {
        if (nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int expectSubSum = sum / 2;
        // 转化为0-1背包问题: 从nums,中取k个数，使其和为expectSubSum
        return resolveZeroOnePack(nums, expectSubSum) > 0;
    }

    public int resolveZeroOnePack(int[] costs, int w) {
        int[] f = new int[w + 1];

        // 如果要求恰好装满时
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;

        for (int i = 0; i < costs.length; i++) {
            zeroOnePack(costs[i], 1, w, f);
        }
        return f[w];
    }

    private void zeroOnePack(int cost, int worth, int w, int[] f) {
        for (int v = w; v >= cost; v--) {
            f[v] = Math.max(f[v], f[v - cost] + worth);
        }
    }

    public static void main(String[] args) {
        CanPartition canPartition = new CanPartition();
        Assert.assertTrue(canPartition.canPartition(new int[]{23,13,11,7,6,5,5}));
        Assert.assertFalse(canPartition.canPartition(new int[]{2, 2, 3, 5}));
        Assert.assertFalse(canPartition.canPartition(new int[]{1, 2, 5}));
        Assert.assertTrue(canPartition.canPartition(new int[]{1, 5, 11, 5}));
        Assert.assertFalse(canPartition.canPartition(new int[]{1, 2, 3, 5}));
        Assert.assertTrue(canPartition.canPartition(new int[]{1, 2, 3, 5, 7, 8}));
        Assert.assertTrue(canPartition.canPartition(new int[]{1, 2, 5, 5, 7, 8}));
        int[] nums = ArrayUtil.randIntArray(10);
        ArrayUtil.print(nums);
        Assert.assertTrue(canPartition.canPartition(nums));
    }
}
