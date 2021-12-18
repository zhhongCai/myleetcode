package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  1610
 */
public class VisiblePoints {
    private double eps = 1e-9;

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int ans = 0;
        int tx = location.get(0);
        int ty = location.get(1);
        List<Double> degree = new ArrayList<>(points.size());
        for (List<Integer> point : points) {
            int x = point.get(0);
            int y = point.get(1);
            if (x == tx && y == ty) {
                ans++;
                continue;
            }
            x -= tx;
            y -= ty;
            degree.add(Math.atan2(y, x) + Math.PI);
        }
        Collections.sort(degree);

        int n = degree.size();
        for (int i = 0; i < n; i++) {
            degree.add(degree.get(i) + 2 * Math.PI);
        }
        n *= 2;
        int max = 0;
        double t = angle * Math.PI / 180;
        for (int i = 0, j = 0; j < n; j++) {
            while (i < j && degree.get(j) - degree.get(i) > t + eps) {
                i++;
            }
            max = Math.max(max, j - i + 1);
        }

        return ans + max;
    }

    public static void main(String[] args) {
        VisiblePoints visiblePoints = new VisiblePoints();
        List<List<Integer>> points = new ArrayList<>();
        points.add(Arrays.asList(2,1));
        points.add(Arrays.asList(2,2));
        points.add(Arrays.asList(3,3));
        Assert.assertEquals(3, visiblePoints.visiblePoints(points, 90, Arrays.asList(1,1)));
    }
}
