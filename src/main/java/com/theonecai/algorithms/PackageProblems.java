package com.theonecai.algorithms;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 背包9讲
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/18 19:59
 * @Description:
 */
public class PackageProblems {

    /**
     * 0-1背包：
     * 有N件物品和一个容量为v的背包。放入第i件物品耗费的费用是Ci，得到的
     * 价值是Wi。求解将哪些物品装入背包可使价值总和最大。
     *
     * 状态转移方程：f[i, v] = max(f[i - 1, v], f[i - 1, v - Ci] + Wi)
     * 优化空间后，状态转移方程：f[v] = max(f[v], f[v - Ci] + Wi)
     *
     * @param costs : 物品重量
     * @param worth : 物品价值
     * @param w : 背包容量
     * @return
     */
    public int resolveZeroOnePack(int[] costs, int[] worth, int w) {
        int[] f = new int[w + 1];

        // 如果要求恰好装满时
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;

        // 没有要求恰好装满时
//        Arrays.fill(f, 0);

        for (int i = 0; i < costs.length; i++) {
            zeroOnePack(costs[i], worth[i], w, f);
        }
//        ArrayUtil.print(f);
        return f[w];
    }

    private void zeroOnePack(int cost, int worth, int w, int[] f) {
        for (int v = w; v >= cost; v--) {
            f[v] = Math.max(f[v], f[v - cost] + worth);
        }
    }

    public int resolveZeroOnePack2(int[] costs, int[] worth, int w) {
        int[][] f = new int[costs.length][w + 1];
        for (int v = costs[0]; v <= w; v++) {
            f[0][v] = worth[0];
        }
        for (int i = 1; i < costs.length; i++) {
            for (int v = 0; v <= w; v++) {
                f[i][v] = f[i - 1][v];
                if (costs[i] <= v) {
                    f[i][v] = Math.max(f[i - 1][v], f[i - 1][v - costs[i]] + worth[i]);
                }
            }
        }
//        for (int[] ints : f) {
//            ArrayUtil.print(ints);
//        }

        return f[costs.length - 1][w];
    }

    /**
     * 完全背包
     * 有 N 种物品和一个容量为 V 的背包，每种物品都有无限件可用。放入第 i 种物品
     * 的费用是 Ci，价值是 Wi。求解：将哪些物品装入背包，可使这些物品的耗费的费用总
     * 和不超过背包容量，且价值总和最大。
     *
     * @param costs
     * @param worth
     * @param w
     * @return
     */
    public int resolveCompletePack(int[] costs, int[] worth, int w) {
        return -1;
    }

    public static void main(String[] args) {
        PackageProblems packageProblems = new PackageProblems();
        // 每件物品价值
        int[] worth = {2,4,1,5,2,6,2,8};
        // 每件物品重量
        int[] costs = {2,5,4,6,3,7,3,2};
        // 背包最大承重量
        int w = 13;
        // 求装入背包物品的最大总价值
        System.out.println(packageProblems.resolveZeroOnePack(costs, worth, w));
        Assert.assertEquals(packageProblems.resolveZeroOnePack(costs, worth, w),
                packageProblems.resolveZeroOnePack2(costs, worth, w));
    }
}
