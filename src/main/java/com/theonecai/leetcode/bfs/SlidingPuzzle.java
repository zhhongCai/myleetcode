package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


/**
 * leetcode 773
 * @Author: theonecai
 * @Date: Create in 6/26/21 20:02
 * @Description:
 */
public class SlidingPuzzle {

    private String target = "123450";
    private int row = 2;
    private int col = 3;
    private int[][] direction = new int[][]{
            {0,-1},
            {-1,0},
            {0,1},
            {1,0}
    };

    private int[][] neighbor = {
            { 1, 3 },
            { 0, 4, 2 },
            { 1, 5 },
            { 0, 4 },
            { 3, 1, 5 },
            { 4, 2 }
    };

    private static class StepInfo {
        private String board;
        private int zeroIndex;

        public StepInfo(String board, int zeroIndex) {
            this.board = board;
            this.zeroIndex = zeroIndex;
        }
    }

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        int zeroIndex = 0;
        for (int i = 0; i < 6; i++) {
            int cur = board[i / col][i % col];
            if (cur == 0) {
                zeroIndex = i;
            }
            sb.append(cur);
        }
        String start = sb.toString();
        if (target.equals(start)) {
            return 0;
        }

        Queue<StepInfo> queue = new LinkedList<>();
        queue.add(new StepInfo(start, zeroIndex));
        Map<String, Integer> stepMap = new HashMap<>();
        stepMap.put(start, 0);

        while (!queue.isEmpty()) {
            StepInfo step = queue.poll();
            int r = step.zeroIndex / col;
            int c = step.zeroIndex % col;
            char[] next = step.board.toCharArray();
            /*for (int[] d : direction) {
                int nextR = r + d[0];
                int nextC = c + d[1];
                if (checkRange(nextR, nextC)) {
                    int nextZeroIndex = nextR * col + nextC;
                    swap(next, nextZeroIndex, step.zeroIndex);
                    String nextStr = new String(next);
                    if (target.equals(nextStr)) {
                        return stepMap.get(step.board) + 1;
                    }
                    if (!stepMap.containsKey(nextStr)) {
                        queue.add(new StepInfo(nextStr, nextZeroIndex));
                        stepMap.put(nextStr, stepMap.get(step.board) + 1);
                    }
                    swap(next, step.zeroIndex, nextZeroIndex);
                }
            }*/
            for (int nextZeroIndex : neighbor[step.zeroIndex]) {
                swap(next, nextZeroIndex, step.zeroIndex);
                String nextStr = new String(next);
                if (target.equals(nextStr)) {
                    return stepMap.get(step.board) + 1;
                }
                if (!stepMap.containsKey(nextStr)) {
                    queue.add(new StepInfo(nextStr, nextZeroIndex));
                    stepMap.put(nextStr, stepMap.get(step.board) + 1);
                }
                swap(next, step.zeroIndex, nextZeroIndex);
            }
        }

        return -1;
    }

    private void swap(char[] next, int nextZeroIndex, int zeroIndex) {
        char tmp = next[nextZeroIndex];
        next[nextZeroIndex] = next[zeroIndex];
        next[zeroIndex] = tmp;
    }

    private boolean checkRange(int r, int c) {
        return 0 <= r && r < row && 0 <= c && c < col;
    }

    public static void main(String[] args) {
        SlidingPuzzle puzzle = new SlidingPuzzle();
        Assert.assertEquals(0, puzzle.slidingPuzzle(new int[][]{
                {1,2,3},
                {4,5,0},
        }));
        Assert.assertEquals(1, puzzle.slidingPuzzle(new int[][]{
                {1,2,3},
                {4,0,5},
        }));
        Assert.assertEquals(5, puzzle.slidingPuzzle(new int[][]{
                {4,1,2},
                {5,0,3},
        }));
        Assert.assertEquals(-1, puzzle.slidingPuzzle(new int[][]{
                {1,2,3},
                {5,4,0},
        }));
        Assert.assertEquals(14, puzzle.slidingPuzzle(new int[][]{
                {3,2,4},
                {1,5,0},
        }));
    }
}
