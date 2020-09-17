package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * Fibonacci:
 *
 * https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/16 15:18
 * @Description:
 */
public class Fibonacci {
    public int fabonacciRecursive(int n) {
        if (n == 1) {
            return n;
        }
        if (n == 2) {
            return n;
        }

        return fabonacciRecursive(n - 1) + fabonacciRecursive(n - 2);
    }

    public int fabonacciDp(int n) {
        int[] lookup = new int[n + 1];
        lookup[1] = 1;
        lookup[2] = 2;
        for (int i = 3; i <= n; i++) {
            lookup[i] = lookup[i - 1] + lookup[i - 2];
        }
        return lookup[n];
    }

    public int fabonacciDp2(int n) {
        if (n < 3) {
            return n;
        }
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(fibonacci.fabonacciRecursive(20), fibonacci.fabonacciDp(20));
        Assert.assertEquals(fibonacci.fabonacciDp2(100), fibonacci.fabonacciDp(100));
    }
}
