package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 51
 * @Author: theonecai
 * @Date: Create in 2020/7/4 21:06
 * @Description:
 */
public class SolveNQueens {
    private List<List<String>> results;

    public List<List<String>> solveNQueens(int n) {
        int[] result = new int[n];
        results = new LinkedList<>();
        if (n == 0) {
            return results;
        }

        calcEightQueen(0, n, result);

        return results;
    }

    public void calcEightQueen(int row, int n, int[] result) {
        if (row == n) {
            printQueens(result);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isOk(row, col, result)) {
                result[row] = col;
                calcEightQueen(row + 1, n, result);
            }
        }

    }

    private void printQueens(int[] result) {
        List<String> list = new ArrayList<>(result.length);
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < result.length; row++) {
            sb.delete(0, sb.length());
            for (int col = 0; col < result.length; col++) {
                if (result[row] == col) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            list.add(sb.toString());
        }
        results.add(list);
    }

    private boolean isOk(int row, int col, int[] result) {
        int leftUp = col - 1;
        int rightUp = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            // 上一行col列已放,返回false
            if (result[i] == col) {
                return false;
            }
            // 左上角已放，返回false
            if (result[i] == leftUp) {
                return false;
            }
            // 右上角已放，返回false
            if (result[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    public static void main(String[] args) {
        SolveNQueens nQueens = new SolveNQueens();
        List<List<String>> lists = nQueens.solveNQueens(9);
        for (List<String> list : lists) {
            for (String row : list) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

}
