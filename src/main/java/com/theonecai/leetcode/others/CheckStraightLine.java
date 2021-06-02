package com.theonecai.leetcode.others;

import org.junit.Assert;

/**
 * leetcode 1232
 *
 * @Author: theonecai
 * @Date: Create in 2021/1/17 10:04
 * @Description:
 */
public class CheckStraightLine {
    /**
     * y = ax+b
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        if (x2 == x1) {
            for (int i = 2; i < coordinates.length; i++) {
                if (x1 != coordinates[i][0]) {
                    return false;
                }
            }
        } else {
            double a = (double)(y2 - y1) / (x2 - x1);
            double b = y1 - (double)(y2 - y1) *x1 / (x2 - x1);

            for (int i = 2; i < coordinates.length; i++) {
                double y = coordinates[i][1];
                if (y != coordinates[i][0] * a + b) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckStraightLine checkStraightLine = new CheckStraightLine();
        Assert.assertTrue(checkStraightLine.checkStraightLine(new int[][]{
                {1,2},{2,3},{3,4},{4,5},{5,6},{6,7}
        }));
        Assert.assertFalse(checkStraightLine.checkStraightLine(new int[][]{
                {1,1},{2,2},{3,4},{4,5},{5,6},{7,7}
        }));
    }
}
