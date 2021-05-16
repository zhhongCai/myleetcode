package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: theonecai
 * @Date: Create in 2021/05/16 10:24
 * @Description:
 */
public class Weekend241 {
    private int sum;
    public int subsetXORSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        sum = 0;
        subSet(nums, 0, new ArrayList<>());
        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        subSet(nums, 0, list);

        return sum;
    }

    private void subSet(int[] nums, int index, List<Integer> list) {
        if (index >= nums.length) {
            int xor = 0;
            for (Integer num : list) {
                xor ^= num;
            }
            sum += xor;
            return;
        }
        List<Integer> current = new ArrayList<>(list);
        subSet(nums, index + 1, list);
        if (index < nums.length - 1) {
            current.add(nums[index + 1]);
            subSet(nums, index + 1, current);
        }
    }

    public int minSwaps(String s) {
        int[] count = new int[2];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            count[ch - '0']++;
        }
        if (chars.length % 2 == 0) {
            if (count[0] != count[1]) {
                return -1;
            }
        } else {
            if ((count[0] + 1 != count[1]) && (count[0] != count[1] + 1)) {
                return -1;
            }
        }

        // 先0
        int diff = 0;
        // 先1
        int diff2 = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                if (chars[i] != '0') {
                    diff++;
                }
                if (chars[i] != '1') {
                    diff2++;
                }
            } else {
                if (chars[i] != '1') {
                    diff++;
                }
                if (chars[i] != '0') {
                    diff2++;
                }
            }
        }
        if (diff == 0 || diff2 == 0) {
            return 0;
        }
        if (count[0] > count[1]) {
            return diff / 2;
        } else if (count[0] == count[1]) {
            return Math.min(diff / 2, diff2 / 2);
        } else {
            return diff2 / 2;
        }
    }

    static class FindSumPairs {
        private int[] nums;
        private int[] nums2;
        private TreeMap<Integer, Integer> map;

        public FindSumPairs(int[] nums1, int[] nums2) {
            nums = nums1;
            this.nums2 = nums2;
            Arrays.sort(nums);
            map = new TreeMap<>();
            for (int n : nums2) {
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
        }

        public void add(int index, int val) {
            int count = map.get(nums2[index]);
            if (count == 1) {
                map.remove(nums2[index]);
            } else {
                map.put(nums2[index], count - 1);
            }
            nums2[index] += val;
            map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);

        }

        public int count(int tot) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (tot < nums[i]) {
                    break;
                }
                int remain = tot - nums[i];
                if (remain > map.lastKey() || remain < map.firstKey()) {
                    continue;
                }
                Integer c = map.get(remain);
                if (c != null) {
                    count += c;
                }

            }
            return count;
        }
    }


    public static void main(String[] args) {
        Weekend241 weekend = new Weekend241();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        FindSumPairs findSumPairs = new FindSumPairs(new int[]{1, 1, 2, 2, 2, 3}, new int[]{1, 4, 5, 2, 5, 4});
        Assert.assertEquals(8, findSumPairs.count(7));
        findSumPairs.add(3, 2);
        Assert.assertEquals(2, findSumPairs.count(8));
        Assert.assertEquals(1, findSumPairs.count(4));
    }

    private void test2() {
        Assert.assertEquals(65, this.minSwaps("00011110110110000000000110110101011101111011111101010010010000000000000001101101010010001011110000001101111111110000110101101101001011000011111011101101100110011111110001100110001110000000001100010111110100111001001111100001000110101111010011001"));
        Assert.assertEquals(1, this.minSwaps("101010110"));
        Assert.assertEquals(0, this.minSwaps("10101010"));
        Assert.assertEquals(0, this.minSwaps("101010101"));
        Assert.assertEquals(1, this.minSwaps("100"));
        Assert.assertEquals(1, this.minSwaps("001"));
        Assert.assertEquals(0, this.minSwaps("101"));
        Assert.assertEquals(0, this.minSwaps("010"));
        Assert.assertEquals(1, this.minSwaps("111000"));
        Assert.assertEquals(-1, this.minSwaps("1110"));
        Assert.assertEquals(-1, this.minSwaps("1000"));
    }

    private void test() {
        Assert.assertEquals(6, this.subsetXORSum(new int[]{1,3}));
        Assert.assertEquals(28, this.subsetXORSum(new int[]{5,1,6}));
        Assert.assertEquals(480, this.subsetXORSum(new int[]{3,4,5,6,7,8}));
    }
}
