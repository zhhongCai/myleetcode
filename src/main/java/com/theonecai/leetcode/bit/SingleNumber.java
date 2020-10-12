package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode 137
 * @Author: theonecai
 * @Date: Create in 2020/10/12 21:31
 * @Description:
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        long s = 0;
        long sum = 0;
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sum += num;
            }
            s += num;
        }
        return (int)(3 * sum - s) / 2;
    }

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        Assert.assertEquals(3, singleNumber.singleNumber(new int[]{1,1,1,3}));
    }
}
