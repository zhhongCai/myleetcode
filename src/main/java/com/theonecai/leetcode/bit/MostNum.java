package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class MostNum {

    public int mostNum2(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int c = 0;
        int n = nums[0];
        for (int i = 0; i < nums.length; i++) {
            c = countMap.getOrDefault(nums[i], 0);
            if (c >= nums.length / 2) {
                n = nums[i];
                break;
            }
            countMap.put(nums[i], c + 1);
        }
        return n;
    }

    /**
     * Boyer Moore 投票算法
     * @param nums
     * @return
     */
    public int mostNum(int[] nums) {
        int count = 0;
        int candidate = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        MostNum mostNum = new MostNum();
        Assert.assertEquals(1, mostNum.mostNum(new int[]{1}));
        Assert.assertEquals(1, mostNum.mostNum(new int[]{1,1,1,2,2}));
        Assert.assertEquals(2, mostNum.mostNum(new int[]{1,1,2,2,2,2}));
    }
}
