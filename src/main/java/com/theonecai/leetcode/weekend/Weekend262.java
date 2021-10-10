package com.theonecai.leetcode.weekend;


import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Author: theonecai
 * @Date: Create in 2021/10/10 10:24
 * @Description:
 */
public class Weekend262 {


    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    set.add(nums1[i]);
                    break;
                }
            }
            if (!set.contains(nums1[i])) {
                for (int j = 0; j < nums3.length; j++) {
                    if (nums1[i] == nums3[j]) {
                        set.add(nums1[i]);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            for (int j = 0; j < nums3.length; j++) {
                if (nums2[i] == nums3[j]) {
                    set.add(nums2[i]);
                    break;
                }
            }
        }
        return new ArrayList<>(set);

    }

    class StockPrice {

        private TreeMap<Integer, Integer> priceMap;
        private TreeMap<Integer, Integer> map;
        public StockPrice() {
            priceMap = new TreeMap<>();
            map = new TreeMap<>();
        }

        public void update(int timestamp, int price) {
            Integer p = priceMap.get(timestamp);
            if (p != null) {
                int c = map.get(p);
                if (c == 1) {
                    map.remove(p);
                } else {
                    map.put(p, c - 1);
                }
            }

            map.put(price, map.getOrDefault(price, 0) + 1);
            priceMap.put(timestamp, price);

        }

        public int current() {
            return priceMap.lastEntry().getValue();
        }

        public int maximum() {
            return map.floorKey(Integer.MAX_VALUE);
        }

        public int minimum() {
            return map.ceilingKey(0);
        }
    }

    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        int[] data = new int[n * m];
        int idx = 0;
        int mod = grid[0][0] % x;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] % x != mod) {
                    return -1;
                }
                data[idx++] = grid[i][j];
            }
        }

        Arrays.sort(data);
        int val = data[data.length / 2];
        int count = 0;
        for (int num : data) {
            int r = Math.abs(num - val);
            if (r == 0) {
                continue;
            }
            count += r / x;
        }
        return count;
    }


    public static void main(String[] args) {
        Weekend262 weekend = new Weekend262();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(-1, minOperations(new int[][]{
                {80,437,889,386},{45,655,891,659},{751,705,333,436},{452,155,603,775}}, 741));
        Assert.assertEquals(4, minOperations(new int[][]{
                {2,4},
                {6,8},
        }, 2));
        Assert.assertEquals(5, minOperations(new int[][]{
                {1,5},
                {3,2},
        }, 1));
        Assert.assertEquals(-1, minOperations(new int[][]{
                {1,2},
                {3,4},
        }, 2));

    }

    private void test2() {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        Assert.assertEquals(5, stockPrice.current());
        Assert.assertEquals(10, stockPrice.maximum());

        stockPrice.update(1, 3);
        Assert.assertEquals(5, stockPrice.maximum());

        stockPrice.update(4, 2);

        Assert.assertEquals(2, stockPrice.minimum());

    }

    private void test() {
    }
}
