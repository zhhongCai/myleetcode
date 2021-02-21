package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.TreeMap;

/**
 * leetcode 1438
 * @Author: thonecai
 * @Date: Create in 2021/2/21 09:51
 * @Description:
 */
public class LongestSubarray {
    public int longestSubarray(int[] nums, int limit) {
        int result = 1;
        int left = 0;
        int right = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        while (right < nums.length) {
            treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            while (!treeMap.isEmpty() && (treeMap.lastKey() - treeMap.firstKey() > limit)) {
                int c = treeMap.get(nums[left]);
                if (c == 1) {
                    treeMap.remove(nums[left]);
                } else {
                    treeMap.put(nums[left], c - 1);
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        LongestSubarray longestSubarray = new LongestSubarray();
        Assert.assertEquals(3, longestSubarray.longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
        Assert.assertEquals(2, longestSubarray.longestSubarray(new int[]{8,2,4,7}, 4));
        Assert.assertEquals(4, longestSubarray.longestSubarray(new int[]{10,1,2,4,7,2}, 5));
    }
}
