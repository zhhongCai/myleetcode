package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 45
 * @Author: theonecai
 * @Date: Create in 2020/7/26 17:36
 * @Description:
 */
public class CanJump2 {
    public int canJump(int[] nums) {
        // count[i]表示到达i位置的最小步数
        int[] count = new int[nums.length];
        for (int i = 1; i < count.length; i++) {
            count[i] = 0;
            if (nums[0] >= i) {
                count[i] = 1;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j < count.length; j++) {
                if (count[j] == 0) {
                    if (i + nums[i] >= j) {
                        count[j] = count[i] + 1;
                    }
                }
                if (count[count.length - 1] > 0) {
                    return count[count.length - 1];
                }
            }
        }
        return count[count.length - 1];
    }

    public int jump(int[] nums) {
        int step = 0;
        int maxIndex = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxIndex = Math.max(maxIndex, i + nums[i]);
            if (i == end) {
                end = maxIndex;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        CanJump2 canJump = new CanJump2();
        /**
         * 0 1 1 0 0
         * 0 1 1 0 0
         * 0 0 1 2 2
         * 0 0 0 2 2
         * 0 0 0 0 2
         */
        int[] a = {2,1,3,1,4};
        Assert.assertEquals(2, canJump.canJump(a));
        Assert.assertEquals(2, canJump.jump(a));

        int[] b = {2,0,2,1,1,1};
        Assert.assertEquals(3, canJump.canJump(b));
        Assert.assertEquals(3, canJump.jump(b));

        int[] c = {2,3,1,1,4};
        Assert.assertEquals(2, canJump.canJump(c));
        Assert.assertEquals(2, canJump.jump(c));
    }
}
