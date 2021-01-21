package com.theonecai.geekforgeek.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bitmasking and DP
 * Count ways to assign unique cap to every person;
 */
public class AssignUniqueCap {

    public static final int MOD = 1000000007;

    private int[][] dp;

    /**
     * capPersonList[i][j]表示帽子i可以戴在人j上
     *
     */
    private List<List<Integer>> capPersonList;

    private int allMask;

    private int n;

    private int p;

    /**
     * personCapList[i]表示第i个人所有的帽子
     * n 帽子总数
     *  1 <= n <= 100
     *  最多10个人
     * @param personCapList
     * @param n
     * @return  不重复的戴帽子方式
     */
    public int countWays(int[][] personCapList, int n) {
        this.dp = new int[1025][n];
        this.capPersonList = new ArrayList<>(n);
        this.n = n;

        for (int i = 0; i < n; i++) {
            this.capPersonList.add(new ArrayList<>());
        }
        for (int i = 0; i < personCapList.length; i++) {
            for (int j = 0; j < personCapList[i].length; j++) {
                this.capPersonList.get(personCapList[i][j]).add(i);
            }
        }


        this.allMask = (1 << personCapList.length) - 1;
        this.p = personCapList.length;

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return (int)ways(0, 0);
    }

    /**
     *
     * @param mask : 当前戴帽子情况
     * @param i : 第i顶帽子
     * @return
     */
    private long ways(int mask, int i) {
        if (mask == this.allMask) {
            return 1;
        }
        if (i >= this.n) {
            return 0;
        }

        if (this.dp[mask][i] != -1) {
            return this.dp[mask][i];
        }

        // 不戴第i顶帽子
        long ways = ways(mask, i + 1);

        int size = this.capPersonList.get(i).size();
        // 对有第i顶帽子的人
        for (int j = 0; j < size; j++) {
            // 已经戴了帽子
            if ((mask & (1 << this.capPersonList.get(i).get(j))) != 0) {
                continue;
            }
            ways += ways(mask | 1 << this.capPersonList.get(i).get(j), i + 1);
            ways %= MOD;
        }
        dp[mask][i] = (int)ways;
        return ways;
    }

    public int countWaysDp(int[][] personCapList, int n) {
        this.dp = new int[1025][n];
        this.capPersonList = new ArrayList<>(n);
        this.n = n;

        for (int i = 0; i < n; i++) {
            this.capPersonList.add(new ArrayList<>());
        }
        for (int i = 0; i < personCapList.length; i++) {
            for (int j = 0; j < personCapList[i].length; j++) {
                this.capPersonList.get(personCapList[i][j]).add(i);
            }
        }


        this.allMask = (1 << personCapList.length) - 1;

        return waysDp();
    }

    private int waysDp() {
        int[] dp = new int[1 << this.p];
        dp[0] = 1;

        for (List<Integer> personList : this.capPersonList) {
            int size = personList.size();
            if (size == 0) {
                continue;
            }
            for (int mask = dp.length - 1; mask >= 0; mask--) {
                for (Integer person : personList) {
                    int pMask = 1 << person;
                    if ((mask & pMask) == 0) {
                        continue;
                    }
                    dp[mask] += dp[mask - pMask];
                    dp[mask] %= MOD;
                }
            }
        }

        return dp[allMask];
    }

    public static void main(String[] args) {
        AssignUniqueCap uniqueCap = new AssignUniqueCap();
        Assert.assertEquals(uniqueCap.countWays(new int[][]{
                {5,9,1,3,4},
                {2,6,5,4},
                {5,9,2,6,7,8},
        }, 10),
                uniqueCap.countWaysDp(new int[][]{
                {5,9,1,3,4},
                {2,6,5,4},
                {5,9,2,6,7,8},
        }, 10));
    }
}
