package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 120
 */
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int r = triangle.size();
        // dp[i]表示到达i行i列时的最小路径和
        int[] dp = new int[r];
        Arrays.fill(dp, 1000000);
        dp[0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < r; i++) {
            int preCol = triangle.get(i - 1).size();
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                if (j < preCol) {
                    dp[j] = dp[j] + triangle.get(i).get(j);
                }
                if (j > 0) {
                    dp[j] = Math.min(dp[j], dp[j - 1] + triangle.get(i).get(j));
                }
                if (i == r - 1) {
                    res = Math.min(res, dp[j]);
                }
            }
        }

        return res == Integer.MAX_VALUE ? dp[0] : res;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size();
        // dp[i][j] 表示到达i行j列时的最小路径和
        int[][] dp = new int[r][c];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1000000);
        }
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < r; i++) {
            int preCol = triangle.get(i - 1).size();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j < preCol) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + triangle.get(i).get(j));
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + triangle.get(i).get(j));
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < c; i++) {
            res = Math.min(res, dp[r - 1][i]);
        }

        return res;
    }


    public static void main(String[] args) {
        MinimumTotal total = new MinimumTotal();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));
        Assert.assertEquals(11, total.minimumTotal(list));

        list = new ArrayList<>();
        list.add(Arrays.asList(-10));
        Assert.assertEquals(-10, total.minimumTotal(list));
    }
}
