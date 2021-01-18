package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 5243
 * @Author: theonecai
 * @Date: Create in 2021/1/17 13:57
 * @Description:
 */
public class TupleSameProduct {


    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int s = nums[i] * nums[j];
                counts.put(s, counts.getOrDefault(s, 0) + 1);
            }
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            count += entry.getValue() * (entry.getValue() - 1) * 4;
        }

        return count;
    }

    public int tupleSameProduct2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int a;
        int b;
        int c;
        int d;
        for (int i = 0; i < nums.length - 3; i++) {
            a = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                c = nums[j];
                if (c > a * nums[nums.length - 1]) {
                    break;

                }
                for (int k = j + 1; k < nums.length - 1; k++) {
                    d = nums[k];
                    if (c*d > a * nums[nums.length - 1]) {
                        break;
                    }
                    if ((c * d) %  a == 0) {
                        b = c * d / a;
                        if (b == a || b == c || b == d) {
                            continue;
                        }

                        int index = Arrays.binarySearch(nums, k, nums.length, b);
                        if (index >= 0) {
                            count += 8;

                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TupleSameProduct product = new TupleSameProduct();
        Assert.assertEquals(16, product.tupleSameProduct(new int[]{1,2,4,5,10}));
        Assert.assertEquals(8, product.tupleSameProduct(new int[]{2,3,4,6}));
        Assert.assertEquals(40, product.tupleSameProduct(new int[]{2,3,4,6,8,12}));
        Assert.assertEquals(0, product.tupleSameProduct(new int[]{2,3,5,7}));
    }
}
