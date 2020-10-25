package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode offer 63
 * @Author: theonecai
 * @Date: Create in 2020/10/25 16:48
 * @Description:
 */
public class MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int pre = 0;
        int current = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            current = Math.max(pre, prices[i] - min);
            min = Math.min(min, prices[i]);
            pre = current;
        }
        return current;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        Stack<Integer> incStack = new Stack<>();
        incStack.push(prices[0]);
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (incStack.isEmpty()) {
                incStack.push(prices[i]);
            } else {
                while (!incStack.isEmpty() && incStack.peek() >= prices[i]) {
                    int top = incStack.pop();
                    if (!incStack.isEmpty()) {
                        profit = Math.max(profit, top - incStack.get(0));
                    }
                }
                incStack.push(prices[i]);
            }
        }
        if (!incStack.isEmpty() && incStack.size() > 1) {
            profit = Math.max(profit, incStack.get(incStack.size() - 1) - incStack.get(0));
        }

        return profit;
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        Assert.assertEquals(1, maxProfit.maxProfit(new int[]{1,2}));
        Assert.assertEquals(5, maxProfit.maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(0, maxProfit.maxProfit(new int[]{7,6,4,3,1}));
    }
}
