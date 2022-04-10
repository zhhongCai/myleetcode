package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2022/04/10 10:24
 * @Description:
 */
public class Weekend288 {

    public int largestInteger(int num) {
        String n = String.valueOf(num);
        int[] count = new int[10];

        for (int i = 0; i < n.length(); i++) {
            count[n.charAt(i) - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            int ch = n.charAt(i) - '0';
            if (ch % 2 == 0) {
                int j = 8;
                while (j >= 0 && count[j] == 0) {
                    j -= 2;
                }
                sb.append((char)(j + '0'));
                count[j]--;
            } else {
                int j = 9;
                while (j >= 0 && count[j] == 0) {
                    j -= 2;
                }
                sb.append((char)(j + '0'));
                count[j]--;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public String minimizeResult(String expression) {
        long min = Long.MAX_VALUE;
        String str = "";
        int plus = expression.indexOf("+");
        for (int i = 0; i < plus; i++) {
            for (int j = plus + 1; j < expression.length(); j++) {
                long r = calc(expression, i, j, plus);
                if (r < min) {
                    min = r;
                    str = expression.substring(0, i) + "(" + expression.substring(i, plus) + "+"
                            + expression.substring(plus + 1, j + 1) + ")" + expression.substring(j + 1);
                }
            }
        }
        return str;
    }

    private long calc(String expression, int left, int right, int plus) {
        String a = expression.substring(0, left);
        if (a.equals("")) {
            a = "1";
        }
        String b = expression.substring(left, plus);
        String c = expression.substring(plus + 1, right + 1);
        String d = expression.substring(right + 1);
        if (d.equals("")) {
            d = "1";
        }
        return Long.parseLong(a) * (Long.parseLong(b) + Long.parseLong(c)) * Long.parseLong(d);
    }

    public int maximumProduct(int[] nums, int k) {
        long mod = 1000000007L;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);

        }
        while (k > 0) {
            int n = minHeap.poll();
            n++;
            minHeap.add(n);
            k--;
        }

        long s = 1L;
        while (!minHeap.isEmpty()) {
            s *= minHeap.poll();
            s %= mod;
        }

        return (int)(s % mod);
    }

    public static void main(String[] args) {
        Weekend288 weekend = new Weekend288();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(180820950, maximumProduct(new int[]{24,5,64,53,26,38}, 54));
        Assert.assertEquals(20, maximumProduct(new int[]{0,4}, 5));
        Assert.assertEquals(216, maximumProduct(new int[]{6,3,3,2}, 2));
    }

    private void test2() {
        Assert.assertEquals("2(47+38)", minimizeResult("247+38"));
        Assert.assertEquals("(999+999)", minimizeResult("999+999"));
        Assert.assertEquals("(9199+9919)", minimizeResult("9199+9919"));
    }

    private void test() {

        Assert.assertEquals(3412, largestInteger(1234));
        Assert.assertEquals(87655, largestInteger(65875));
        Assert.assertEquals(12000, largestInteger(12000));
    }
}
