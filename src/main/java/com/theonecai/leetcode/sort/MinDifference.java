package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1509
 * @Author: theonecai
 * @Date: Create in 2020/9/7 20:07
 * @Description:
 */
public class MinDifference {

    public int minDifference(int[] nums) {
        if (nums ==  null || nums.length < 5) {
            return 0;
        }

        Arrays.sort(nums);

        int len = nums.length;

        // 替换最小3个数 或 最小2个数和最大1个数
        int a = Math.min(nums[len - 1] - nums[3], nums[len - 2] - nums[2]);
        // 替换最大2个数和最小1个数 或 替换最大3个数
        a = Math.min(a, Math.min(nums[len - 3] - nums[1], nums[len - 4] - nums[0]));
        return a;
    }

    public static void main(String[] args) {
        MinDifference minDifference = new MinDifference();
        Assert.assertEquals(2, minDifference.minDifference(new int[]{0,1,3,6,8,2}));
        Assert.assertEquals(0, minDifference.minDifference(new int[]{6,6,6,0,2,4}));
        Assert.assertEquals(0, minDifference.minDifference(new int[]{1,1,1,0,2,4}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,0,1,0,2,4}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,1,1,0,0,2,4}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,1,1,0,2,4,0,2}));
        Assert.assertEquals(0, minDifference.minDifference(new int[]{1,1,1,1,2,4,1,2}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,1,1,1,2,4,1,2,3,4}));
        Assert.assertEquals(0, minDifference.minDifference(new int[]{5,3,2,4}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,5,0,10,14}));
        Assert.assertEquals(2, minDifference.minDifference(new int[]{6,6,0,1,1,4,6}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{1,5,6,14,15}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{81,82,92,20,87}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{81,82,82,92,20,87}));
        Assert.assertEquals(1, minDifference.minDifference(new int[]{81,82,82,81,92,20,87}));
        Assert.assertEquals(2, minDifference.minDifference(new int[]{81,82,82,81,92,20,87,80}));
        Assert.assertTrue(minDifference.minDifference(ArrayUtil.randIntArray(15)) > 0);
    }
}
