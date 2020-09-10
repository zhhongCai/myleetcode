package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * leetcode 1481
 * @Author: theonecai
 * @Date: Create in 2020/9/10 21:04
 * @Description:
 */
public class FindLeastNumOfUniqueInts {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == arr.length) {
            return 0;
        }

        // 数字及出现次数
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        int t = k;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry :
                map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList())) {
            t -= entry.getValue();
            if (t <= 0) {
                if (t == 0) {
                    count++;
                }
                break;
            } else {
                count++;
            }
        }


        return map.size() - count;
    }

    public static void main(String[] args) {
        FindLeastNumOfUniqueInts find = new FindLeastNumOfUniqueInts();
        Assert.assertEquals(2, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 1));
        Assert.assertEquals(2, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 2));
        Assert.assertEquals(1, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 3));
        Assert.assertEquals(1, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 4));
        Assert.assertEquals(1, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 5));
        Assert.assertEquals(0, find.findLeastNumOfUniqueInts(new int[]{1,2,3,1,2,1}, 6));
    }
}
