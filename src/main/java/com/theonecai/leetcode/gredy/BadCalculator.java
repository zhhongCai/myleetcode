package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 *
 * @Author: theonecai
 * @Date: Create in 2020/9/29 20:22
 * @Description:
 */
public class BadCalculator {

    public int calculate(int X, int Y) {
        
        if (X >= Y) {
            return X - Y;
        }
        int k = (Y + 1) / 2;
        int times = 0;
        while (X != Y) {
            if (X < k) {
                X *= 2;
                times++;
            } else {
                times += X - k + 1;
                break;
            }
        }
        if (Y % 2 == 1) {
            times++;
        }

        return times;
    }

    public static void main(String[] args) {
        BadCalculator tokenGame = new BadCalculator();
        Assert.assertEquals(0, tokenGame.calculate(3, 3));
        Assert.assertEquals(7, tokenGame.calculate(10, 3));
        Assert.assertEquals(2, tokenGame.calculate(3, 12));
        Assert.assertEquals(8, tokenGame.calculate(3, 14));
        /**
         * 3*2=6, 6*2=12, 12-4=8, 8*2=16, 16-1=15
         */
        Assert.assertEquals(8, tokenGame.calculate(3, 15));
    }
}
