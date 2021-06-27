package com.theonecai.leetcode.weekend;


import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/06/27 10:24
 * @Description:
 */
public class Weekend247 {

    public int maxProductDifference(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] * nums[n - 2]) - (nums[0] * nums[1]);
    }

    private int m;
    private int n;
    public int[][] rotateGrid(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int[][] after = new int[m][n];
        int level = Math.min(m, n) / 2;
        while (level > 0) {
            process(grid, k, level, after);
            level--;
        }

        return after;
    }

    private void process(int[][] grid, int k, int level, int[][] after) {
        int c = Math.min(m, n) / 2 - level;
        int[] leftTop = new int[]{c, c};
        int[] rightBottom = new int[]{m - 1 - c, n - 1 -c};
        int rowCount = rightBottom[0] - leftTop[0] + 1;
        int colCount = rightBottom[1] - leftTop[1] - 1;
        int numCount = (rowCount + colCount) * 2;
        int move = k % numCount;
        int[][] index = new int[numCount][2];
        int idx = 0;
        for (int i = leftTop[0]; i <= rightBottom[0]; i++) {
            int col = leftTop[1];
            index[idx++] = new int[]{i, col};
        }
        for (int i = leftTop[1] + 1; i <= rightBottom[1]; i++) {
            int row = rightBottom[0];
            index[idx++] = new int[]{row, i};
        }
        for (int i = rightBottom[0] - 1; i >= leftTop[0] ; i--) {
            int col = rightBottom[1];
            index[idx++] = new int[]{i, col};
        }
        for (int i = rightBottom[1] - 1; i > leftTop[1] ; i--) {
            int row = leftTop[0];
            index[idx++] = new int[]{row, i};
        }

        idx = 0;
        for (int i = leftTop[0]; i <= rightBottom[0]; i++) {
            int col = leftTop[1];
            int[] p = index[(idx + move) % numCount];
            after[p[0]][p[1]] = grid[i][col];
            idx++;
        }
        for (int i = leftTop[1] + 1; i <= rightBottom[1]; i++) {
            int row = rightBottom[0];
            int[] p = index[(idx + move) % numCount];
            after[p[0]][p[1]] = grid[row][i];
            idx++;
        }
        for (int i = rightBottom[0] - 1; i >= leftTop[0] ; i--) {
            int col = rightBottom[1];
            int[] p = index[(idx + move) % numCount];
            after[p[0]][p[1]] = grid[i][col];
            idx++;
        }
        for (int i = rightBottom[1] - 1; i > leftTop[1] ; i--) {
            int row = leftTop[0];
            int[] p = index[(idx + move) % numCount];
            after[p[0]][p[1]] = grid[row][i];
            idx++;
        }
    }

    public long wonderfulSubstrings(String word) {
        int[] count = new int[10];
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }

        return 0;
    }

    public static void main(String[] args) {
        Weekend247 weekend = new Weekend247();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
    }

    private void test2() {
        int[][] arr = this.rotateGrid(new int[][]{
                {4,5,8,9,4,2,4,7,2,4},
                {7,1,9,6,6,1,4,5,7,7},
                {7,1,5,1,1,7,10,1,3,1},
                {7,2,2,5,2,6,6,4,7,7},
                {1,2,3,8,4,7,6,9,6,2},
                {5,10,3,4,7,2,7,5,3,10}}, 2);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }

        arr = this.rotateGrid(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}}, 2);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private void test() {
        /**
         *  [[4,5,8,9,4,2,4,7,2,4],[7,1,9,6,6,1,4,5,7,7],[7,1,5,1,1,7,10,1,3,1],[7,2,2,5,2,6,6,4,7,7],[1,2,3,8,4,7,6,9,6,2],[5,10,3,4,7,2,7,5,3,10]]
         *
         *  [[8,9,4,2,4,7,2,4,7,1],
         *  [5,6,6,1,4,5,7,3,7,7],
         *  [4,9,1,7,10,1,4,6,6,2],
         *  [7,1,1,5,2,5,2,6,9,10],
         *  [7,1,2,2,3,8,4,7,6,3],
         *  [7,1,5,10,3,4,7,2,7,5]]
         */
    }
}
