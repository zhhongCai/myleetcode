package com.theonecai.leetcode.stack;

import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 85
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/30 21:43
 * @Description:
 */
public class MaxRectangleMatrix {

    public int maximalRectangle(char[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        // +1 保存每段间隔高度0
        int fixRow = m.length + 1;
        int[] heights = new int[fixRow * m[0].length];
        int index = 0;
        for (int col = 0; col < m[0].length; col++) {
            for (int row = 0; row < m.length; row++) {
                if (m[row][col] == '0') {
                    heights[index++] = 0;
                } else {
                    if (col > 0 && m[row][col - 1] == '1') {
                        // 前1列的高度-1
                        heights[index] = heights[index - fixRow] - 1;
                        index++;
                        continue;
                    }
                    int colIndex = col + 1;
                    while (colIndex < m[0].length && m[row][colIndex] == '1') {
                        colIndex++;
                    }
                    heights[index++] = colIndex - col;
                }
            }
            // 用高度0隔开每段
            heights[index++] = 0;
        }

        return largestRectangleArea(heights);
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        // 递增栈,存索引
        Stack<Integer> incStack = new Stack<>();
        int popIndex;
        int maxRange;
        int max = heights[0];
        incStack.push(0);

        int i = 1;
        while (!incStack.isEmpty()) {
            while ((i < heights.length && heights[incStack.peek()] > heights[i]) || i == heights.length) {
                popIndex = incStack.pop();
                if (heights[popIndex] > 0) {
                    maxRange = incStack.isEmpty() ? i : (i - incStack.peek() - 1);
                    max = Math.max(max, heights[popIndex] * maxRange);
                }
                if (incStack.isEmpty()) {
                    break;
                }
            }
            if (i < heights.length) {
                incStack.push(i++);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        MaxRectangleMatrix maxRectangleMatrix = new MaxRectangleMatrix();
        char[][] m = {
            {'1', '1', '1', '1', '0', '0'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1'},
            {'1', '0', '1', '1', '1', '1'}
        };
        Assert.assertEquals(20, maxRectangleMatrix.maximalRectangle(m));
    }
}
