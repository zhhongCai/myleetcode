package com.theonecai.leetcode.array;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 1995
 */
public class CountQuadruplets {
    public int countQuadruplets2(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        int n = nums.length;
        int ans = 0;
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                int df = nums[d] - nums[b + 1];
                count.put(df, count.getOrDefault(df, 0) + 1);
            }
            for (int a = 0; a < b; a++) {
                ans += count.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }

    public int countQuadruplets(int[] nums) {
        int[] count = new int[202];
        int n = nums.length;
        int ans = 0;
        for (int b = n - 3; b >= 1; b--) {
            for (int d = b + 2; d < n; d++) {
                int df = nums[d] - nums[b + 1];
                if (df >= 0) {
                    count[df]++;
                }
            }
            for (int a = 0; a < b; a++) {
                ans += count[nums[a] + nums[b]];
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        CountQuadruplets quadruplets = new CountQuadruplets();
        Assert.assertEquals(2, quadruplets.countQuadruplets(new int[]{9,6,8,23,39,23}));
        Assert.assertEquals(0, quadruplets.countQuadruplets(new int[]{3,3,6,4,5}));
        Assert.assertEquals(4, quadruplets.countQuadruplets(new int[]{1,1,1,3,5}));
        Assert.assertEquals(1, quadruplets.countQuadruplets(new int[]{1,2,3,6}));
    }
}
