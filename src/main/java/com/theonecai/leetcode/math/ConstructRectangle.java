package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * 492
 */
public class ConstructRectangle {
    public int[] constructRectangle(int area) {
        int n = (int)Math.sqrt((double) area);
        if (n * n == area) {
            return new int[] {n, n};
        }
        while (n > 0) {
            if (area % n == 0) {
                break;
            }
            n--;
        }
        return new int[] {area / n, n};
    }

    public static void main(String[] args) {
        ConstructRectangle rectangle = new ConstructRectangle();
        Assert.assertArrayEquals(new int[] {2,2}, rectangle.constructRectangle(4));
        Assert.assertArrayEquals(new int[] {1,1}, rectangle.constructRectangle(1));
        Assert.assertArrayEquals(new int[] {3,2}, rectangle.constructRectangle(6));
        Assert.assertArrayEquals(new int[] {7,1}, rectangle.constructRectangle(7));
        Assert.assertArrayEquals(new int[] {3200, 3125}, rectangle.constructRectangle(10000000));
    }
}
