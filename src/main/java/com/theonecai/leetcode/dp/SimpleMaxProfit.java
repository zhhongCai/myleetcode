package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 121
 * @Author: theonecai
 * @Date: Create in 2021/3/22 22:36
 * @Description:
 */
public class SimpleMaxProfit {

    public int maxProfit(int[] prices) {
        int pre = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            pre = Math.max(pre, prices[i] - min);
            if (min > prices[i]) {
                min = prices[i];
            }
        }
        return pre;
    }

    public static void main(String[] args) {
        SimpleMaxProfit simpleMaxProfit = new SimpleMaxProfit();
        Assert.assertEquals(5, simpleMaxProfit.maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, simpleMaxProfit.maxProfit(new int[]{7,6,4,3,1}));
    }
}
