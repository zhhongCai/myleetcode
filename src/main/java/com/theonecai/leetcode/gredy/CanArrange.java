package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1479
 * @Author: theonecai
 * @Date: Create in 2020/10/17 14:21
 * @Description:
 */
public class CanArrange {

    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int key = 0;
        for (int num : arr) {
            key = (num % k + k) % k;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 0) {
                if (entry.getValue() % 2 != 0) {
                    return false;
                }
            } else {
                key = k - entry.getKey();
                if (!map.containsKey(key)) {
                    return false;
                }
                if (map.get(key).compareTo(entry.getValue()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanArrange canArrange = new CanArrange();
        Assert.assertTrue(canArrange.canArrange(new int[]{1,2,3,4,5,10,6,7,8,9}, 5));
        Assert.assertTrue(canArrange.canArrange(new int[]{1,2,3,4,5,6}, 7));
        Assert.assertFalse(canArrange.canArrange(new int[]{1,2,3,4,5,6}, 10));
        Assert.assertTrue(canArrange.canArrange(new int[]{-10,10}, 2));
        Assert.assertTrue(canArrange.canArrange(new int[]{-1,1,-2,2,-3,3,-4,4}, 3));
    }
}
