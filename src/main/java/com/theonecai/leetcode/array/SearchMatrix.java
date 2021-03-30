package com.theonecai.leetcode.array;

import org.junit.Assert;

/**
 * leetcode 74
 * @Author: theonecai
 * @Date: Create in 2021/3/30 22:39
 * @Description:
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length * matrix[0].length - 1;
        int mid;
        int row = 0;
        int col = 0;
        while (low <= high) {
            mid = (high + low) / 2;
            row = mid / matrix[0].length;
            col = mid % matrix[0].length;
            if (matrix[row][col] > target) {
                high = mid - 1;
            } else if (matrix[row][col] < target) {
                low = mid + 1;
            } else {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        Assert.assertFalse(searchMatrix.searchMatrix(new int[][]{
                {10}
        }, 13));
        Assert.assertFalse(searchMatrix.searchMatrix(new int[][]{
                {1,3,5,7},{10,11,16,20},{23,30,34,60}
        }, 60));
        Assert.assertTrue(searchMatrix.searchMatrix(new int[][]{
                {1,3,5,7},{10,11,16,20},{23,30,34,60}
        }, 3));

    }
}
