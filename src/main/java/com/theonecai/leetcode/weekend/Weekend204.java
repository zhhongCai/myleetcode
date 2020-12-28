package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.LinkedList;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/30 10:31
 * @Description:
 */
public class Weekend204 {

    public int minDays(int[][] grid) {
        if (!onlyOneIsland(grid)) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (!onlyOneIsland(grid)) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private boolean onlyOneIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        boolean allZero = true;
        boolean visitedOnce = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    allZero = false;
                    visit(grid, i, j, visited);
                    visitedOnce = true;
                    break;
                }
            }
            if (visitedOnce) {
                break;
            }
        }
        if( (allZero)) {
            return false;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private void visit(int[][] grid, int row, int col, boolean[][] visited) {
        LinkedList<int[]> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.add(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] first = queue.removeFirst();
            int i =  first[0];
            int j = first[1];

            // 上
            if (i > 0 && !visited[i - 1][j] && grid[i - 1][j] == 1) {
                queue.add(new int[]{i - 1, j});
                visited[i - 1][j] = true;
            }
            // 右
            if (j < grid[0].length - 1 && !visited[i][j + 1] && grid[i][j + 1] == 1) {
                queue.add(new int[]{i, j + 1});
                visited[i][j + 1] = true;
            }
            // 下
            if (i < grid.length - 1 && !visited[i + 1][j] && grid[i + 1][j] == 1) {
                queue.add(new int[]{i + 1, j});
                visited[i + 1][j] = true;
            }
            // 左
            if (j > 0 && !visited[i][j - 1] && grid[i][j - 1] == 1) {
                queue.add(new int[]{i, j - 1});
                visited[i][j - 1] = true;
            }
        }
    }

    public int getMaxLen(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int max = 0;
        int firstNegativeIndex = -1;
        int lastNegativeIndex = -1;
        int start = -1;
        int negativeCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (start == -1) {
                start = i;
            }
            if (nums[i] == 0) {
                max = Math.max(max, getMaxLen(firstNegativeIndex, lastNegativeIndex, start, negativeCount, i));

                negativeCount = 0;
                firstNegativeIndex = - 1;
                lastNegativeIndex = -1;
                start = -1;
            } else if (nums[i] < 0) {
                if (firstNegativeIndex == -1) {
                    firstNegativeIndex = i;
                }
                lastNegativeIndex = i;
                negativeCount++;
            }
            if (i == nums.length - 1) {
                max = Math.max(max, getMaxLen(firstNegativeIndex, lastNegativeIndex, start, negativeCount, i + 1));
            }
        }
        return max;
    }

    private int getMaxLen(int firstNegativeIndex, int lastNegativeIndex, int start, int negativeCount, int i) {
        if (start == -1) {
            return 0;
        }
        int count = 0;
        if (negativeCount % 2 == 0) {
            count = i - start;
        } else {
            count = Math.max(i - firstNegativeIndex - 1, lastNegativeIndex - start);
        }
        return count;
    }

    public boolean containsPattern(int[] arr, int m, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count = check(arr, i, i + m, m, k);
            if (count >= k) {
                return true;
            }
        }
        return false;
    }

    private int check(int[] arr, int i, int start, int m, int k) {

        int count = 1;
        int ii = start;
        while (count < k && ii < arr.length && ii + m - 1 < arr.length) {
            boolean ok = true;
            for (int j = 0; j < m; j++) {
                if (arr[i + j] != arr[ii + j]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                count++;
            } else {
                break;
            }
            ii = ii + m;
        }
        return count;
    }

    public static void main(String[] args) {
        Weekend204 weekend204 = new Weekend204();
        int[] arr = {1, 2, 4, 4, 4, 4};
        Assert.assertTrue(weekend204.containsPattern(arr, 1, 3));
        int[] arr2 = {1, 2, 1, 2, 1, 1, 1, 3};
        int[] arr22 = {2, 2, 2, 2};
        Assert.assertTrue(weekend204.containsPattern(arr2, 2, 2));
        Assert.assertFalse(weekend204.containsPattern(arr22, 2, 3));

        int[] arr3 = {1, 2, 1, 2, 1, 3};
        int[] arr33 = {1, 2, 3, 1, 2};
        Assert.assertFalse(weekend204.containsPattern(arr3, 2, 3));
        Assert.assertFalse(weekend204.containsPattern(arr33, 2, 2));
        int[] arr4 = {2, 2, 1, 2, 2, 1, 1, 1, 2, 1};
        Assert.assertTrue(weekend204.containsPattern(arr4, 3, 2));
        /**
         * []
         * 2
         * 2
         */
        int[] arr5 = {1, 2, 3, 1, 2};
        Assert.assertFalse(weekend204.containsPattern(arr5, 2, 2));


        int[] nums = {1, -2, -3, 4};
        Assert.assertEquals(4, weekend204.getMaxLen(nums));

        int[] nums2 = {0, 1, -2, -3, -4};
        Assert.assertEquals(3, weekend204.getMaxLen(nums2));


        int[] nums3 = {-1,-2,-3,0,1};
        Assert.assertEquals(2, weekend204.getMaxLen(nums3));

        int[] nums4 = {-1,2};
        Assert.assertEquals(1, weekend204.getMaxLen(nums4));

        int[] nums5 = {1,2,3,5,-6,4,0,10};
        Assert.assertEquals(4, weekend204.getMaxLen(nums5));

        int[] nums6 = {-1};
        Assert.assertEquals(0, weekend204.getMaxLen(nums6));
        int[] nums7 = {0,0,0,0,0};
        Assert.assertEquals(0, weekend204.getMaxLen(nums7));

        int[][] grid22 = {{1,1},{1,0}};
        Assert.assertEquals(1, weekend204.minDays(grid22));

        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        Assert.assertEquals(2, weekend204.minDays(grid));

        int[][] grid2 = {{1,1}};
        Assert.assertEquals(2, weekend204.minDays(grid2));

        int[][] grid3 = {{1,0,1,0}};
        Assert.assertEquals(0, weekend204.minDays(grid3));


        int[][] grid4 = {{1,1,0,1,1},
                {1,1,1,1,1},
                {1,1,0,1,1},
                {1,1,0,1,1}};
        Assert.assertEquals(1, weekend204.minDays(grid4));

        int[][] grid5 = {{1,1,0,1,1},
                {1,1,1,1,1},
                {1,1,0,1,1},
                {1,1,1,1,1}};
        Assert.assertEquals(2, weekend204.minDays(grid5));
    }
}
