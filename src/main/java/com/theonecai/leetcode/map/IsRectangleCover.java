package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * leetcode 391
 */
public class IsRectangleCover {
    public boolean isRectangleCover(int[][] rectangles) {

        int[] leftBottom = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] rightTop = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE};
        long expectArea = 0;
        long area = 0;
        Set<Integer> count = new HashSet<>();
        for (int[] r : rectangles) {
            if (r[0] < leftBottom[0]) {
                leftBottom[0] = r[0];
            }
            if (r[1] < leftBottom[1]) {
                leftBottom[1] = r[1];
            }
            if (r[2] > rightTop[0]) {
                rightTop[0] = r[2];
            }
            if (r[3] > rightTop[1]) {
                rightTop[1] = r[3];
            }
            area += (long)(r[2] - r[0]) * (long)(r[3] - r[1]);
            add(count, r[0], r[1]);
            add(count, r[2], r[3]);
            add(count, r[0], r[3]);
            add(count, r[2], r[1]);

        }
        expectArea = (long)(rightTop[0] - leftBottom[0]) * (long)(rightTop[1] - leftBottom[1]);
        if (area != expectArea) {
            return false;
        }
        return (count.size() == 4 && count.contains(key(leftBottom[0], leftBottom[1]))
            && count.contains(key(rightTop[0], rightTop[1]))
            && count.contains(key(leftBottom[0], rightTop[1]))
            && count.contains(key(rightTop[0], leftBottom[1])));
    }

    private int key(int x, int y) {
        return x * 100000 + y;
    }

    private void add(Set<Integer> set, int x, int y) {
        int key = key(x, y);
        if (set.contains(key)) {
            set.remove(key);
            return;
        }
        set.add(key);
    }

    public static void main(String[] args) {
        IsRectangleCover isRectangleCover = new IsRectangleCover();
        Assert.assertFalse(isRectangleCover.isRectangleCover(new int[][]{
                {0,0,1,1},{0,0,1,1},{1,1,2,2},{1,1,2,2}
        }));
        Assert.assertTrue(isRectangleCover.isRectangleCover(new int[][]{
                {1,1,3,3},{3,1,4,2},{3,2,4,4},{1,3,2,4},{2,3,3,4}
        }));
        Assert.assertFalse(isRectangleCover.isRectangleCover(new int[][]{
                {1,1,2,3},{1,3,2,4},{3,1,4,2},{3,2,4,4}
        }));
        Assert.assertFalse(isRectangleCover.isRectangleCover(new int[][]{
                {1,1,3,3},{3,1,4,2},{1,3,2,4},{3,2,4,4}
        }));
        Assert.assertFalse(isRectangleCover.isRectangleCover(new int[][]{
                {1,1,3,3},{3,1,4,2},{1,3,2,4}
        }));
        Assert.assertFalse(isRectangleCover.isRectangleCover(new int[][]{
                {0,0,1,1},{0,1,3,2},{1,0,2,2}
        }));
    }
}
