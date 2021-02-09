package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

/**
 * leetcode 992
 * @Author: theonecai
 * @Date: Create in 2021/2/9 21:19
 * @Description:
 */
public class SubarraysWithKDistinct {
    public int subarraysWithKDistinct(int[] A, int K) {
        return countWithKDistinct(A, K) - countWithKDistinct(A, K - 1);
    }

    /**
     * 求nums中最多出现K个不同数的子数组的个数
     * @param nums
     * @param k
     * @return
     */
    private int countWithKDistinct(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int count = 0;
        int result = 0;
        int[] countMap = new int[nums.length + 1];
        while (right < nums.length) {
            if (countMap[nums[right]] == 0) {
                count++;
            }
            countMap[nums[right]]++;
            right++;

            while (count > k) {
                countMap[nums[left]]--;
                if (countMap[nums[left]] == 0) {
                    count--;
                }
                left++;
            }
            result += right - left;
        }
        return result;
    }

    public static void main(String[] args) {
        SubarraysWithKDistinct distinct = new SubarraysWithKDistinct();
        Assert.assertEquals(7, distinct.subarraysWithKDistinct(new int[] {1,2,1,2,3}, 2));
        Assert.assertEquals(3, distinct.subarraysWithKDistinct(new int[] {1,2,1,3,4}, 3));
    }
}
