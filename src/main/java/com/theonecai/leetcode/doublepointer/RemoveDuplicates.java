package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 80
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        return precess(nums, 2);
    }

    private int precess(int[] nums, int k) {
        int i = 0;
        for (int num : nums) {
            if (i < k || nums[i - k] != num) {
                nums[i++] = num;
            }
        }
        return i;
    }


    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n;
        }
        int first = 0;
        int second = first + 1;
        while (second < n) {
            if (nums[first] != nums[second]) {
                if (second - first > 2) {
                    int rm = shift(nums, first, second, n);
                    n -= rm;
                    first += 2;
                    second = first + 1;
                    continue;
                }
                first++;
            }
            second++;
        }
        if (second - first >= 2) {
            n -= shift(nums, first, second, n);
        }

        return n;
    }

    private int shift(int[] nums, int first, int second, int n) {
        if (second == n) {
            return n - first - 2;
        }
        int start = first + 2;
        if (start >= n) {
            return 0;
        }
        int delCount = second - first - 2;
        while (second < n) {
            nums[start++] = nums[second++];
        }
        return delCount;
    }

    public static void main(String[] args) {
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int[] nums2 = new int[]{1,1,1,1,2,2,2,2,3,3,3};
        Assert.assertEquals(6, removeDuplicates.removeDuplicates(nums2));
        int[] nums = new int[]{1,1,1};
        Assert.assertEquals(2, removeDuplicates.removeDuplicates(nums));
        int[] nums3 = new int[]{1,1,1,2,2,3};
        Assert.assertEquals(5, removeDuplicates.removeDuplicates(nums3));
        int[] nums4 = new int[]{0,0,1,1,1,1,2,3,3};
        Assert.assertEquals(7, removeDuplicates.removeDuplicates(nums4));
        int[] nums5 = new int[]{0,0,1,1,2,3,3};
        Assert.assertEquals(7, removeDuplicates.removeDuplicates(nums5));

    }
}
