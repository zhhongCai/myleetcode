package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 *  leetcode 419
 */
public class CountBattleships {
    public int countBattleships(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                if (board[i][j] == 'X') {
                    ans++;
                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        CountBattleships countBattleships = new CountBattleships();
        Assert.assertEquals(3, countBattleships.countBattleships(new char[][]{
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'X','.','.','X'},
        }));
    }
}
