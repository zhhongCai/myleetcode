package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 560
 * @Author: theonecai
 * @Date: Create in 5/29/21 22:02
 * @Description:
 */
public class SubarraySum {

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int pre = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);
        for (int num : nums) {
            pre += num;
            Integer c = countMap.get(pre - k);
            if (c != null) {
                res += c;
            }
            countMap.put(pre, countMap.getOrDefault(pre, 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        SubarraySum sum = new SubarraySum();
        Assert.assertEquals(2, sum.subarraySum(new int[]{1,1,1}, 2));
    }
}
