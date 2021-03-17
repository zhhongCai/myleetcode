package com.theonecai.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 54
 * @Author: theonecai
 * @Date: Create in 2021/3/15 22:24
 * @Description:
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        return spiralOrder(matrix, 0, 0, matrix.length, matrix[0].length);
    }

    private List<Integer> spiralOrder(int[][] matrix, int startRow, int startCol, int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            result.add(matrix[startRow][startCol + i]);
        }
        if (rows > 1) {
            for (int i = 1; i < rows; i++) {
                result.add(matrix[startRow + i][startCol + cols - 1]);
            }
            for (int i = 1; i < cols; i++) {
                result.add(matrix[startRow + rows - 1][startCol + cols - 1 - i]);
            }
            if (cols > 1) {
                for (int i = 1; i < rows - 1; i++) {
                    result.add(matrix[startRow + rows -1 - i][startCol]);
                }
            }
        }

        List<Integer> list = spiralOrder(matrix, startRow + 1, startCol + 1, rows - 2, cols - 2);
        if (list.size() > 0) {
            result.addAll(list);
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralOrder spiralOrder = new SpiralOrder();
        System.out.println(spiralOrder.spiralOrder(new int[][]{
                {1,2,3},
        }));
        System.out.println(spiralOrder.spiralOrder(new int[][]{
                {1,2,3},
                {6,5,4},
        }));
        System.out.println(spiralOrder.spiralOrder(new int[][]{
                {1,2,3},
                {6,5,4},
                {7,8,9},
        }));
        System.out.println(spiralOrder.spiralOrder(new int[][]{
                {3},
                {4},
                {9},
        }));
        System.out.println(spiralOrder.spiralOrder(new int[][]{
                {1,3},
                {6,4},
                {7,9},
        }));
    }

}
