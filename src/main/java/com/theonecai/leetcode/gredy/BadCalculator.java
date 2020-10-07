package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 991
 * @Author: theonecai
 * @Date: Create in 2020/9/29 20:22
 * @Description:
 */
public class BadCalculator {

    public int brokenCalc(int X, int Y) {
        int times = 0;
        while (Y > X) {
            times++;
            if (Y % 2 == 0) {
                Y = Y / 2;
            } else {
                Y += 1;
            }
        }

        return times + X - Y;
    }

    public static void main(String[] args) {
        BadCalculator tokenGame = new BadCalculator();
        Assert.assertEquals(0, tokenGame.brokenCalc(3, 3));
        Assert.assertEquals(7, tokenGame.brokenCalc(10, 3));
        Assert.assertEquals(2, tokenGame.brokenCalc(3, 12));
        Assert.assertEquals(5, tokenGame.brokenCalc(3, 14));
        /**
         * 3*2=6, 6*2=12, 12-4=8, 8*2=16, 16-1=15
         */
        Assert.assertEquals(5, tokenGame.brokenCalc(3, 15));
    }
}
