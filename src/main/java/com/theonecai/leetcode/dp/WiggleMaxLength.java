package com.theonecai.leetcode.dp;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/14 19:50
 * @Description:
 */
public class WiggleMaxLength {
    /**
     * list[i]表示nums[0...i]中包括nums[i]的最长串个数：
     * list[i] = max(list[j]) + 1,其中1 <= j < i且 nums[j] > nums[j - 1] && nums[i] < nums[j] 或
     *  nums[j] < nums[j - 1] && nums[i] > nums[j]
     * 若j不存在,list[i] = nums[i] == nums[i-1] ? 1 : 2;
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int[] list = new int[nums.length];
        list[0] = 1;
        list[1] = nums[1] == nums[0] ? 1 : 2;

        for (int i = 2; i < nums.length; i++) {
            boolean exist = false;
            for (int j = 1; j < i; j++) {
                if ((nums[j] > nums[j - 1] && nums[i] < nums[j]) ||
                        (nums[j] < nums[j - 1] && nums[i] > nums[j])) {
                    list[i] = Math.max(list[i], list[j] + 1);
                    exist = true;
                }
            }
            if (!exist) {
                list[i] = nums[i] == nums[i - 1] ? 1 : 2;
            }
        }

        return list[nums.length - 1];
    }

    /**
     * up[i]表示nums[0...i]中起始为升序的包括nums[i]的最长串长度:
     * 如果nums[i] > nums[i - 1],up[i] = down[i - 1] + 1;
     * 如果nums[i] <= nums[i - 1],up[i] = up[i - 1];
     *
     * down[i]表示nums[0...i]中起始为降序的包括nums[i]的最长串长度:
     * 如果nums[i] < nums[i - 1],down[i] = up[i - 1] + 1;
     * 如果nums[i] >= nums[i - 1],down[i] = down[i - 1];
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = 1;
        down[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                 down[i] = up[i - 1] + 1;
                 up[i] = up[i - 1];
            } else {
                 up[i] = up[i - 1];
                 down[i] = down[i - 1];
            }
        }

        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    /**
     * 从wiggleMaxLength2优化而来
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int up = 1;
        int down = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }

    public static void main(String[] args) {
        WiggleMaxLength wiggleMaxLength = new WiggleMaxLength();
        Assert.assertEquals(3, wiggleMaxLength.wiggleMaxLength(new int[]{1,2,1}));
        Assert.assertEquals(1, wiggleMaxLength.wiggleMaxLength(new int[]{1,1,1}));
        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength(new int[]{1,2,3}));
        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength(new int[]{3,2,1}));
        Assert.assertEquals(8, wiggleMaxLength.wiggleMaxLength(new int[]{3,4,2,5,7,6,8,9,2,10}));
        Assert.assertEquals(7, wiggleMaxLength.wiggleMaxLength(new int[]{0,5,9,0,4,3,5,9,9,6}));

        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength2(new int[]{3,2,1}));
        Assert.assertEquals(1, wiggleMaxLength.wiggleMaxLength2(new int[]{1,1,1}));
        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength2(new int[]{1,2,3}));
        Assert.assertEquals(3, wiggleMaxLength.wiggleMaxLength2(new int[]{1,2,1}));
        Assert.assertEquals(7, wiggleMaxLength.wiggleMaxLength2(new int[]{0,5,9,0,4,3,5,9,9,6}));
        Assert.assertEquals(8, wiggleMaxLength.wiggleMaxLength2(new int[]{3,4,2,5,7,6,8,9,2,10}));


        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength3(new int[]{3,2,1}));
        Assert.assertEquals(1, wiggleMaxLength.wiggleMaxLength3(new int[]{1,1,1}));
        Assert.assertEquals(2, wiggleMaxLength.wiggleMaxLength3(new int[]{1,2,3}));
        Assert.assertEquals(3, wiggleMaxLength.wiggleMaxLength3(new int[]{1,2,1}));
        Assert.assertEquals(7, wiggleMaxLength.wiggleMaxLength3(new int[]{0,5,9,0,4,3,5,9,9,6}));
        Assert.assertEquals(8, wiggleMaxLength.wiggleMaxLength3(new int[]{3,4,2,5,7,6,8,9,2,10}));
        int[] nums = ArrayUtil.randIntArray(10);
        ArrayUtil.print(nums);
        Assert.assertEquals(wiggleMaxLength.wiggleMaxLength2(nums), wiggleMaxLength.wiggleMaxLength(nums));
        Assert.assertEquals(wiggleMaxLength.wiggleMaxLength3(nums), wiggleMaxLength.wiggleMaxLength2(nums));
    }
}
