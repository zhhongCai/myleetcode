package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1301
 */
public class PathsWithMaxScore {

    private int mod = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size();
        int n = board.get(0).length();
        char[][] boards = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boards[i][j] = board.get(i).charAt(j);
            }
        }

        int[][] direction = new int[][]{
                {0,1},
                {1,1},
                {1,0},
        };

        int[][][] dp = new int[m][n][2];
        dp[m - 1][n - 1] = new int[]{0, 1};
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (boards[i][j] == 'X') {
                    continue;
                }
                for (int[] d : direction) {
                    int row = i + d[0];
                    int col = j + d[1];
                    if (0 <= row && row < m && 0 <= col && col < n && boards[row][col] != 'X') {
                        int score = getScore(boards, i, j) + dp[row][col][0];
                        if (score > dp[i][j][0]) {
                            dp[i][j][0] = score;
                            dp[i][j][1] = dp[row][col][1];
                        } else if (score == dp[i][j][0]) {
                            dp[i][j][1] += dp[row][col][1];
                        }
                        dp[i][j][0] %= mod;
                        dp[i][j][1] %= mod;
                    }
                }
            }
        }
        if (dp[0][0][1] == 0) {
            return new int[]{0,0};
        }
        return dp[0][0];
    }

    private int getScore(char[][] boards, int i, int j) {
        if (boards[i][j] == 'S' || boards[i][j] == 'E') {
            return 0;
        }
        return boards[i][j] - '0';
    }

    public static void main(String[] args) {
        PathsWithMaxScore pathsWithMaxScore = new PathsWithMaxScore();
        List<String> list = new ArrayList<>();
        list.add("E23");
        list.add("2X2");
        list.add("12S");
        Assert.assertArrayEquals(new int[]{7, 1}, pathsWithMaxScore.pathsWithMaxScore(list));
        list = new ArrayList<>();
        list.add("E12");
        list.add("1X1");
        list.add("21S");
        Assert.assertArrayEquals(new int[]{4, 2}, pathsWithMaxScore.pathsWithMaxScore(list));
        list = new ArrayList<>();
        list.add("E12");
        list.add("XXX");
        list.add("21S");
        Assert.assertArrayEquals(new int[]{0, 0}, pathsWithMaxScore.pathsWithMaxScore(list));
        list = new ArrayList<>();
        list.add("E5555555");
        list.add("55555555");
        list.add("55555555");
        list.add("55555555");
        list.add("55555555");
        list.add("55555555");
        list.add("55555555");
        list.add("5555555S");
        Assert.assertArrayEquals(new int[]{65, 3432}, pathsWithMaxScore.pathsWithMaxScore(list));
    }
}
