package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 5862
 */
public class MinOperations {

    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        Map<Integer, Integer> count = new HashMap<>();
        int res = n;
        for (int left = 0, right = 0; right < n; right++) {
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
            while (nums[left] + n - 1 < nums[right] && left < right) {
                int c = count.get(nums[left]);
                if (c == 1) {
                    count.remove(nums[left]);
                } else {
                    count.put(nums[left], c - 1);
                }
                left++;
            }
            res = Math.min(res, n - count.size());
        }
        return res;
    }

    public static void main(String[] args) {
        MinOperations minOperations = new MinOperations();
        Assert.assertEquals(1, minOperations.minOperations(new int[]{1,2,3,5,6}));
        Assert.assertEquals(0, minOperations.minOperations(new int[]{4,2,5,3}));
        Assert.assertEquals(0, minOperations.minOperations(new int[]{1,2,3,5,4}));
        Assert.assertEquals(3, minOperations.minOperations(new int[]{1,10,100,1000}));
        Assert.assertEquals(2, minOperations.minOperations(new int[]{1,2,3,10,20}));
    }
}
