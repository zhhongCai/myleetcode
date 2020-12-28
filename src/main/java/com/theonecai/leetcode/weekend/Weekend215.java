package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/11/15 10:24
 * @Description:
 */
public class Weekend215 {
    public static class OrderedStream {
        private int ptr;
        private String[] data;

        public OrderedStream(int n) {
            this.ptr = 1;
            this.data = new String[n + 1];
        }

        public List<String> insert(int id, String value) {
            data[id] = value;
            List<String> list = new LinkedList<>();
            while (ptr < data.length && data[ptr] != null) {
                list.add(data[ptr++]);
            }
            return list;
        }
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] count = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            count[word1.charAt(i) - 'a']++;
            count2[word2.charAt(i) - 'a']++;
        }
        boolean result = true;
        boolean charTheSame = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != count2[i]) {
                result = false;
            }
            if ((count[i] == 0 && count2[i] > 0) || (count2[i] == 0 && count[i] > 0)) {
                charTheSame = false;
                break;
            }
        }
        if (!charTheSame) {
            return false;
        }
        if (result) {
            return true;
        }
        Arrays.sort(count);
        Arrays.sort(count2);
        result = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] != count2[i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    public int minOperations(int[] nums, int x) {
        int len = nums.length;
        int[] prefixSum = new int[len];
        int[] suffixSum = new int[len];
        prefixSum[0] = nums[0];
        suffixSum[len - 1] = nums[len - 1];
        int count = nums.length + 1;
        if (prefixSum[0] == x || suffixSum[len - 1] == x) {
            return 1;
        }
        for (int i = 1, j = len - 2; i < len; i++, j--) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            if (prefixSum[i] == x) {
                count = Math.min(count, i + 1);
            }
            suffixSum[j] = suffixSum[j + 1] + nums[j];
            if (suffixSum[j] == x) {
                count = Math.min(count, len - j);
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            int idx = Arrays.binarySearch(prefixSum, 0, i, x - suffixSum[i]);
            if (idx >= 0) {
                count = Math.min(count, idx + 1 + len - i);
            }
        }

        return count == len + 1 ? -1 : count;
    }

    public static void main(String[] args) {
        Weekend215 weekend215 = new Weekend215();
        weekend215.test();
        weekend215.test2();
        weekend215.test3();
    }

    private void test3() {
        Assert.assertEquals(1, this.minOperations(new int[]{1,1,3,2,5}, 5));
        Assert.assertEquals(5, this.minOperations(new int[]{3,2,20,1,1,3}, 10));
        Assert.assertEquals(2, this.minOperations(new int[]{1,1,4,2,3}, 5));
        Assert.assertEquals(-1, this.minOperations(new int[]{5,6,7,8,9}, 4));
    }

    private void test2() {
        Assert.assertFalse(this.closeStrings("uau", "ssx"));
        Assert.assertTrue(this.closeStrings("abc", "bca"));
        Assert.assertFalse(this.closeStrings("a", "aa"));
        Assert.assertTrue(this.closeStrings("cabbba", "abbccc"));
        Assert.assertFalse(this.closeStrings("cabbba", "aabbss"));
    }

    private void test() {
        OrderedStream os = new OrderedStream(5);
        System.out.println(os.insert(3, "cccc"));
        System.out.println(os.insert(1, "aaaaa"));
        System.out.println(os.insert(2, "bbbb"));
        System.out.println(os.insert(5, "eeee"));
        System.out.println(os.insert(4, "dddd"));
    }
}
