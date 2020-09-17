package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.TreeSet;

/**
 * leetcode 1414
 * @Author: theonecai
 * @Date: Create in 2020/9/17 19:49
 * @Description:
 */
public class FindMinFibonacciNumbers {

    private TreeSet<Integer> fabonacciNums;

    public FindMinFibonacciNumbers() {
        this.fabonacciNums = new TreeSet<>();
        initFabonacciNums();
    }

    public void initFabonacciNums() {
        int a = 1;
        int b = 2;
        fabonacciNums.add(a);
        fabonacciNums.add(b);
        int c = 0;
        for (int i = 3; i < 45; i++) {
            c = a + b;
            a = b;
            b = c;
            fabonacciNums.add(c);
        }
    }

    public int findMinFibonacciNumbers(int k) {
        int count = 0;
        int remain = k;
        Integer floor;
        while (remain > 0) {
            floor = fabonacciNums.floor(remain);
            if (floor == null) {
                break;
            }
            count++;
            if (floor == remain) {
                break;
            }
            remain = remain - floor;
        }
        return count;
    }

    public static void main(String[] args) {
        FindMinFibonacciNumbers findMinFibonacciNumbers = new FindMinFibonacciNumbers();
        Assert.assertEquals(2, findMinFibonacciNumbers.findMinFibonacciNumbers(7));
        Assert.assertEquals(2, findMinFibonacciNumbers.findMinFibonacciNumbers(10));
        Assert.assertEquals(3, findMinFibonacciNumbers.findMinFibonacciNumbers(19));
        Assert.assertEquals(2, findMinFibonacciNumbers.findMinFibonacciNumbers(29));
    }
}
