package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 997
 */
public class FindJudge {
    public int findJudge(int n, int[][] trust) {
        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];
        for (int[] t : trust) {
            inDegree[t[1]]++;
            outDegree[t[0]]++;
        }

        for (int i = 1; i <= n; i++) {
            if (outDegree[i] == 0 && inDegree[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }

    public int findJudge2(int n, int[][] trust) {
        boolean[][] map = new boolean[n + 1][n + 1];
        int[] trustCount = new int[n + 1];
        for (int[] t : trust) {
            map[t[0]][t[1]] = true;
            trustCount[t[0]]++;
        }

        List<Integer> noTrust = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (trustCount[i] == 0) {
                noTrust.add(i);
            }
        }
        if (noTrust.isEmpty() || noTrust.size() != 1) {
            return -1;
        }
        int judge = noTrust.get(0);
        for (int i = 1; i <= n; i++) {
            if (i == judge) {
                continue;
            }
            if (!map[i][judge]) {
                return -1;
            }
        }

        return judge;
    }

    public static void main(String[] args) {
        FindJudge findJudge = new FindJudge();
        Assert.assertEquals(2, findJudge.findJudge(2, new int[][]{
                {1,2}
        }));
        Assert.assertEquals(3, findJudge.findJudge(3, new int[][]{
                {1,3},
                {2,3},
        }));
        Assert.assertEquals(-1, findJudge.findJudge(3, new int[][]{
                {1,3},
                {2,3},
                {3,1},
        }));
        Assert.assertEquals(3, findJudge.findJudge(4, new int[][]{
                {1,3},{1,4},{2,3},{2,4},{4,3},
        }));
    }
}
