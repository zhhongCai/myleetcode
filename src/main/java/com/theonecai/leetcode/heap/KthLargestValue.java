package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * leetcode 5663
 * @Author: theonecai
 * @Date: Create in 2021/1/24 13:55
 * @Description:
 */
public class KthLargestValue {
    public int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        minHeap.offer(matrix[0][0]);
        for (int i = 1; i < cols; i++) {
            matrix[0][i] = matrix[0][i -1] ^ matrix[0][i];
            addToHeapIfSizeLessThanK(matrix[0][i], k, minHeap);
        }
        for (int i = 1; i < rows; i++) {
            matrix[i][0] = matrix[i - 1][0] ^ matrix[i][0];
            addToHeapIfSizeLessThanK(matrix[i][0], k, minHeap);
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] = matrix[i - 1][j - 1] ^ matrix[i - 1][j] ^ matrix[i][j - 1] ^ matrix[i][j];
                addToHeapIfSizeLessThanK(matrix[i][j], k, minHeap);
            }
        }

        return minHeap.poll();
    }

    private void addToHeapIfSizeLessThanK(int value, int k, PriorityQueue<Integer> minHeap) {
        if (k > minHeap.size()) {
            minHeap.offer(value);
        } else {
            if (value > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(value);
            }
        }
    }

    public static void main(String[] args) {
        KthLargestValue kthLargestValue = new KthLargestValue();
        Assert.assertEquals(7, kthLargestValue.kthLargestValue(new int[][]{
                {5,2},{1,6}
        }, 1));
        Assert.assertEquals(5, kthLargestValue.kthLargestValue(new int[][]{
                {5,2},{1,6}
        }, 2));
        Assert.assertEquals(4, kthLargestValue.kthLargestValue(new int[][]{
                {5,2},{1,6}
        }, 3));
        Assert.assertEquals(0, kthLargestValue.kthLargestValue(new int[][]{
                {5,2},{1,6}
        }, 4));
    }
}
