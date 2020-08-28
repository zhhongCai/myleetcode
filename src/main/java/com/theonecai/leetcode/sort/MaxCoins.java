package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/24 18:56
 * @Description:
 */
public class MaxCoins {

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int sum = 0;
        int count = piles.length / 3;
        int i = piles.length - 2;
        while (count > 0) {
            sum += piles[i];
            i -= 2;
            count--;
        }

        return sum;
    }

    public static void main(String[] args) {
        MaxCoins maxCoins = new MaxCoins();

        int[] piles = {2,4,1,2,7,8};
        Assert.assertEquals(9, maxCoins.maxCoins(piles));
        int[] piles2 = {2,4,5};
        Assert.assertEquals(4, maxCoins.maxCoins(piles2));
        int[] piles3 = {9,8,7,6,5,1,2,3,4};
        Assert.assertEquals(18, maxCoins.maxCoins(piles3));
    }
}
