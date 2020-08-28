package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 55
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/26 17:36
 * @Description:
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }


    public static void main(String[] args) {
        CanJump canJump = new CanJump();
        int[] a = {2,3,1,1,4};
        Assert.assertTrue(canJump.canJump(a));
    }
}
