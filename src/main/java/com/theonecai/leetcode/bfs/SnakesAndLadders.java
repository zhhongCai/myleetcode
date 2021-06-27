package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * leetcode 909
 * @Author: theonecai
 * @Date: Create in 6/27/21 17:26
 * @Description:
 */
public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        boolean[] visited = new boolean[board.length * board[0].length + 1];
        int[] line = toLine(board);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(1, 0);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int j = 1; j <= 6; j++) {
                int next = index + j;
                if (next >= line.length) {
                    break;
                }
                if (line[next] != -1) {
                    next = line[next];
                }
                if (next == line.length - 1) {
                    return countMap.getOrDefault(index, 0) + 1;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    countMap.put(next, Math.min(countMap.getOrDefault(next, Integer.MAX_VALUE),
                            countMap.getOrDefault(index, 0) + 1));
                }
            }

        }
        return -1;
    }

    private int[] toLine(int[][] board) {
        int[] line = new int[board.length * board[0].length + 1];
        int level = 0;
        int index = 1;
        for (int i = board.length - 1; i >= 0; i--,level++) {
            if (level % 2 == 0) {
                for (int j = 0; j < board[0].length; j++) {
                    line[index++] = board[i][j];
                }
            } else {
                for (int j = board[0].length - 1; j >= 0; j--) {
                    line[index++] = board[i][j];
                }
            }
        }
        return line;
    }

    public static void main(String[] args) {
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        Assert.assertEquals(2, snakesAndLadders.snakesAndLadders(new int[][]{
                {-1,1,2,-1},
                {2,13,15,-1},
                {-1,10,-1,-1},
                {-1,6,2,8}
        }));
        Assert.assertEquals(-1, snakesAndLadders.snakesAndLadders(new int[][]{
                {1,1,-1},
                {1,1,1},
                {-1,1,1}
        }));
        Assert.assertEquals(1, snakesAndLadders.snakesAndLadders(new int[][]{
                {-1,-1},
                {-1,1}
        }));
        Assert.assertEquals(4, snakesAndLadders.snakesAndLadders(new int[][]{
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        }));
    }
}
