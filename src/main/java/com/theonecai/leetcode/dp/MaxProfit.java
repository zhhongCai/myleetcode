package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 714
 * @Author: theonecai
 * @Date: Create in 2020/9/22 18:46
 * @Description:
 */
public class MaxProfit {

    /**
     * profit[i][j]: prices[0~i]的j种状态收入: j=0,不持股，j=1持股,卖出时扣fee
     * 不持股, profit[i][0] = max(profit[i - 1][0], profit[i - 1][1] + (prices[i] - 2))
     * 持股, profit[i][1] = Math.max(profit[i - 1][0] - prices[i], profit[i - 1][1])
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] profit = new int[prices.length][2];
        profit[0][0] = 0;
        profit[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i] - fee);
            profit[i][1] = Math.max(profit[i - 1][0] - prices[i], profit[i - 1][1]);
        }
        return Math.max(profit[prices.length - 1][0], profit[prices.length - 1][1]);
    }

    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
           return 0;
        }
        int hold = -prices[0];
        int noHold = 0;
        for (int i = 1; i < prices.length; i++) {
           noHold = Math.max(noHold, hold + prices[i] - fee);
           hold = Math.max(noHold - prices[i], hold);
        }
        return Math.max(hold, noHold);
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        Assert.assertEquals(8, maxProfit.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        Assert.assertEquals(8, maxProfit.maxProfit2(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}
