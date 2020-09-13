package com.theonecai.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/13 10:11
 * @Description:
 */
public class Weekend206 {
    public int numSpecial(int[][] mat) {
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1 && onlyOne(mat, i, j)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private boolean onlyOne(int[][] mat, int row, int col) {
        for (int i = 0; i < mat[0].length; i++) {
            if (i != col && mat[row][i] == 1) {
                return false;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            if (i != row && mat[i][col] == 1) {
                return false;
            }
        }
        return true;
    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        if (pairs.length == 1) {
            return 0;
        }

        Set<Integer> unhappy = new HashSet<>();
        for (int i = 0; i < pairs.length; i++) {
            int[] current = pairs[i];
            int x = current[0];
            int y = current[1];

            check(x, y, i, preferences, pairs, unhappy);
            check(y, x, i, preferences, pairs, unhappy);
        }

        return unhappy.size();
    }

    private void check(int x, int y, int excludePair, int[][] preferences, int[][] pairs, Set<Integer> unhappy) {
        if (preferences[x][0] == y) {
            return;
        }
        Set<Integer> prefersBefore = new HashSet<>();
        for (int i = 0; i < preferences[x].length; i++) {
            if (preferences[x][i] == y) {
                break;
            }
            prefersBefore.add(preferences[x][i]);
        }

        for (int i = 0; i < pairs.length; i++) {
            if (i == excludePair) {
                continue;
            }
            if (prefersBefore.contains(pairs[i][0]) || prefersBefore.contains(pairs[i][1])) {
                checkCondition(x, y, pairs[i][0], pairs[i][1], prefersBefore, preferences, unhappy);
                checkCondition(x, y, pairs[i][1], pairs[i][0], prefersBefore, preferences, unhappy);
            }
        }
    }

    private void checkCondition(int x, int y, int u, int v, Set<Integer> prefersBefore, int[][] preferences, Set<Integer> unhappy) {
        if (unhappy.contains(x)) {
            return;
        }
        if (!prefersBefore.contains(u)) {
            return;
        }
        if (checkXU(x, y, u, preferences[x]) && checkXU(u, v, x, preferences[u])) {
            unhappy.add(x);
        }
    }

    private boolean checkXU(int x, int y, int u, int[] preference) {
        int[] xpre = preference;
        boolean foundU = false;
        for (int xi : xpre) {
            if (xi == u) {
                foundU = true;
            }
            if (xi == y && foundU) {
                return true;
            }
        }
        return false;
    }

    public int minCostConnectPoints(int[][] points) {
        Arrays.sort(points, (o1, o2) -> {
            int res = o1[0] - o2[0];
            if (res == 0) {
                return o1[1]- o2[1];
            }
            return res;
        });

        int cost = 0;
        int[] pre = points[0];
        for (int i = 0; i < points.length; i++) {
            int[] current = points[i];
            cost += Math.abs(pre[0] - current[0]) + Math.abs(pre[1] - current[1]);
        }

        return cost;
    }

    public static void main(String[] args) {
        Weekend206 weekend206 = new Weekend206();
        weekend206.test();
        weekend206.test2();
        weekend206.test3();
    }

    private void test3() {
        Assert.assertEquals(20, minCostConnectPoints(new int[][]{
                {0,0},{2,2},{3,10},{5,2},{7,0}
        }));

    }


    private void test2() {

        // [], pairs = [[0, 1], [2, 3]]
        Assert.assertEquals(2, unhappyFriends(4, new int[][]{
                {1, 2, 3},{3, 2, 0},{3, 1, 0},{1, 2, 0}
        }, new int[][]{
                {0,1},
                {2,3},
        }));

    }

    private void test() {
        int[][] mat = {{0,0,0,0,0},
        {1,0,0,0,0},
        {0,1,0,0,0},
        {0,0,1,0,0},
        {0,0,0,1,1}};
        Assert.assertEquals(3, numSpecial(mat));
    }
}
