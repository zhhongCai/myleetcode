package com.theonecai.leetcode.array;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 697
 * @Author: theonecai
 * @Date: Create in 2021/2/20 20:11
 * @Description:
 */
public class FindShortestSubArray {
    public int findShortestSubArray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int maxCount = 0;
        Map<Integer, Num> map = new HashMap<>();
        Num num;
        for (int i = 0; i < nums.length; i++) {
            num = map.getOrDefault(nums[i], new Num(i));
            num.count++;
            num.lastIndex = i;

            maxCount = Math.max(num.count, maxCount);
            map.put(nums[i], num);
        }

        int len = nums.length;
        for (Num n : map.values()) {
            if (maxCount != n.count) {
                continue;
            }
            len = Math.min(len, n.length());
        }

        return len;
    }

    private static class Num {
        private int count;
        private int firstIndex;
        private int lastIndex;

        public Num(int index) {
            this.count = 0;
            this.firstIndex = index;
            this.lastIndex = index;
        }

        public int length() {
            return this.lastIndex - this.firstIndex + 1;
        }
    }

    public static void main(String[] args) {
        FindShortestSubArray findShortestSubArray = new FindShortestSubArray();
        Assert.assertEquals(1, findShortestSubArray.findShortestSubArray(new int[]{11}));
        Assert.assertEquals(3, findShortestSubArray.findShortestSubArray(new int[]{1, 1, 1}));
        Assert.assertEquals(2, findShortestSubArray.findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        Assert.assertEquals(6, findShortestSubArray.findShortestSubArray(new int[]{1,2,2,3,1,4,2}));
    }
}
