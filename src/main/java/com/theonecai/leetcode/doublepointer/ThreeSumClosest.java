package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leecode 16
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int diff = 0;
        int res = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                diff = sum - target;
                if (diff == 0) {
                    return sum;
                } else {
                    if (pre > Math.abs(diff)) {
                        res = sum;
                        pre = Math.abs(diff);
                    }
                    if (diff > 0) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSumClosest closest = new ThreeSumClosest();
        Assert.assertEquals(2, closest.threeSumClosest(new int[]{-1,0,1,1,55}, 3));
        Assert.assertEquals(2, closest.threeSumClosest(new int[]{1,1,1,0}, -100));
        Assert.assertEquals(2, closest.threeSumClosest(new int[]{-1,2,1,-4}, 1));
        Assert.assertEquals(6, closest.threeSumClosest(new int[]{1,2,3,4}, 6));
    }
}
