package com.theonecai.leetcode.others;

import org.junit.Assert;

/**
 * leetcode 223
 */
public class ComputeArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area = Math.abs((ax1 - ax2) * Math.abs(ay1 - ay2));
        area += Math.abs(bx1 - bx2) * Math.abs(by1 - by2);
        int w = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int h = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        return area - w * h;
    }

    public static void main(String[] args) {
        ComputeArea computeArea = new ComputeArea();
        Assert.assertEquals(45, computeArea.computeArea(-3, 0,3,  4, 0, -1, 9, 2));
        Assert.assertEquals(16, computeArea.computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
    }
}
