package com.theonecai.leetcode.array;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 523
 * @Author: theonecai
 * @Date: Create in 6/2/21 22:21
 * @Description:
 */
public class CheckSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        int remain = 0;
        Map<Integer, Integer> remainMap = new HashMap<>();
        remainMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            remain = (nums[i] + remain) % k;
            if (remainMap.containsKey(remain)) {
                if (i - remainMap.get(remain) >= 2) {
                    return true;
                }
            } else {
                remainMap.put(remain, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckSubarraySum checkSubarraySum = new CheckSubarraySum();
        Assert.assertTrue(checkSubarraySum.checkSubarraySum(new int[]{23,2,4,6,6}, 7));
        Assert.assertFalse(checkSubarraySum.checkSubarraySum(new int[]{27}, 6));
        Assert.assertTrue(checkSubarraySum.checkSubarraySum(new int[]{23,2,4,6,7}, 6));
        Assert.assertTrue(checkSubarraySum.checkSubarraySum(new int[]{23,2,6,4,7}, 6));
        Assert.assertFalse(checkSubarraySum.checkSubarraySum(new int[]{23,2,6,4,7}, 13));
    }
}
