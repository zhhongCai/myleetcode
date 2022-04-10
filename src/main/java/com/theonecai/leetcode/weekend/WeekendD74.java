package com.theonecai.leetcode.weekend;

import com.google.common.collect.Comparators;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class WeekendD74 {
    public long maximumSubsequenceCount(String text, String pattern) {
        long count = 0;
        char[] chars = text.toCharArray();
        int n = chars.length;
        long[] aCount = new long[n];
        long[] bCount = new long[n];
        aCount[0] = chars[0] == pattern.charAt(0) ? 1 : 0;
        bCount[n - 1] = chars[n - 1] == pattern.charAt(1) ? 1 : 0;
        for (int i = 1, j = n - 2; i < n; i++, j--) {
           aCount[i] = aCount[i - 1] + (chars[i] == pattern.charAt(0) ? 1 : 0);
           bCount[j] = bCount[j + 1] + (chars[j] == pattern.charAt(1) ? 1 : 0);
        }
        count += bCount[0];
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == pattern.charAt(0)) {
                count += bCount[i + 1];
            }
        }
        long c = aCount[n - 1];
        for (int i = n - 1; i > 0; i--) {
            if (chars[i] == pattern.charAt(1)) {
                c += aCount[i - 1];
            }
        }

        return Math.max(c, count);
    }

    public int halveArray(int[] nums) {
        double sum = 0.0;
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            sum += num;
            maxHeap.add((double)num);
        }
        int count = 0;
        double diff = 0.0;
        while (!maxHeap.isEmpty() && diff < sum / 2.0) {
            double d = maxHeap.poll();
            d = d / 2.0;
            diff += d;
            maxHeap.add(d);
            count++;
        }

        return count;
    }

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        if (numCarpets * carpetLen >= floor.length()) {
            return 0;
        }
        char[] chars = floor.toCharArray();
        int n = chars.length;
        int[] count = new int[n];

        while (numCarpets-- > 0) {
            count[0] = chars[0] == '1' ? 1 : 0;
            for (int i = 1; i < n; i++) {
                count[i] = count[i - 1] + (chars[i] - '0');
            }
            int max = 0;
            int left = -1;
            for (int i = 0; i < n - carpetLen + 1; i++) {
                int r = i + carpetLen - 1;
                int b = count[r] - (i > 0 ? count[i - 1] : 0);
                if (max < b) {
                    max = b;
                    left = i;
                }
            }
            if (left == -1) {
                return 0;
            }
            for (int i = left; i < left + carpetLen; i++) {
                chars[i] = '0';
            }
        }
        int c = 0;
        for (char ch : chars) {
            if (ch == '1') {
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        WeekendD74 weekend = new WeekendD74();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        System.out.println(448-14*3-16*5);
        //14*3 + 16*5
        Assert.assertEquals(199, minimumWhiteTiles("0001100111110001111111110111010110100111000111111001011011010000011011101100001011111111111111111011110101111011010101001011111111111111111011110101000101010010101111111011011111111101100111111101101111000011101101001110011011100010100111111111111111101011001111101110101110111001111111111110110111111101011110111000111011011010111011111111111111111011111011011111111110001110001100111001101101011111111111111111101011011111101101100111111111111111"
                , 8, 16));
        Assert.assertEquals(2, minimumWhiteTiles("10110101", 2, 2));
        Assert.assertEquals(0, minimumWhiteTiles("11111", 2, 3));
    }

    private void test3() {
        Assert.assertEquals(3, halveArray(new int[]{5,19,8,1}));
        Assert.assertEquals(3, halveArray(new int[]{3,8,20}));
    }

    private void test2() {
        Assert.assertEquals(4L, maximumSubsequenceCount("abdcdbc", "ac"));
        Assert.assertEquals(6L, maximumSubsequenceCount("aabb", "ab"));
        Assert.assertEquals(5L, maximumSubsequenceCount("abab", "ab"));

    }
    private void test() {

    }
}
