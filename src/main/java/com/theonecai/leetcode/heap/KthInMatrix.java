package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.PriorityQueue;

/**
 * leetcode 378
 * @Author: theonecai
 * @Date: Create in 2020/8/11 21:56
 * @Description:
 */
public class KthInMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (count < k) {
                    maxHeap.offer(matrix[i][j]);
                    count++;
                } else {
                    if (matrix[i][j] < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(matrix[i][j]);
                    } else {
                        break;
                    }
                }
            }
            if (count == k && i < matrix.length - 1 && matrix[i + 1][0] > maxHeap.peek()) {
                break;
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        KthInMatrix kthInMatrix = new KthInMatrix();
        int[][] matrix = {
                {1, 2, 3, 4, 5, 6, 7},
                {2, 3, 4, 5, 6, 7, 8},
                {3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 10},
                {5, 6, 7, 8, 9, 10, 11},
                {6, 7, 8, 9, 10, 11, 12},
                {7, 8, 9, 10, 11, 12, 13}
        };
        Assert.assertEquals(13, kthInMatrix.kthSmallest(matrix, 49));
    }

}
