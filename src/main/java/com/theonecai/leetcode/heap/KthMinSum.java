package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * leetcode 1439
 * @Author: theonecai
 * @Date: Create in 2020/8/14 21:16
 * @Description:
 */
public class KthMinSum {

    /**
     * 最小堆
     */
    private PriorityQueue<Node> minHeap = new PriorityQueue<>();
    /**
     * 每个和的所有行的列索引
     */
    private Set<String> sumIndexSet = new HashSet<>();

    public int kthSmallest(int[][] mat, int k) {
        int row = mat.length;
        // 去重
        sumIndexSet = new HashSet<>();
        minHeap = new PriorityQueue<>();

        int[] colIndex = new int[row];
        minHeap.add(new Node(sum(mat, colIndex), colIndex));
        sumIndexSet.add(arrayToString(colIndex));

        Node top = null;
        int count = 0;
        while (count < k) {
            top = minHeap.poll();
            count++;

            if (count >= k) {
                break;
            }

            nextSumAddToHeap(mat, top);
        }

        return top.val;
    }

    private void nextSumAddToHeap(int[][] nums, Node top) {
        int[] nextColIndex;
        String key;
        for (int i = 0; i < nums.length; i++) {
            nextColIndex = Arrays.copyOf(top.getColIndex(), top.getColIndex().length);
            if (nextColIndex[i] < nums[0].length - 1) {
                // 第i行取下个数
                nextColIndex[i]++;
                key = arrayToString(nextColIndex);
                if (sumIndexSet.contains(key)) {
                    continue;
                }
                sumIndexSet.add(key);
                minHeap.add(new Node(top.val - nums[i][nextColIndex[i] - 1] + nums[i][nextColIndex[i]], nextColIndex));
            }
        }
    }

    private int sum(int[][] nums, int[] colIndex) {
        int sum = 0;
        for (int i = 0; i < colIndex.length; i++) {
            sum += nums[i][colIndex[i]];
        }
        return sum;
    }

    private String arrayToString(int[] index) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(index).forEach(i -> sb.append(i).append("_"));
        return sb.toString();
    }

    static class Node implements Comparable<Node> {
        int val;
        int[] colIndex;

        public Node(int val, int[] colIndex) {
            this.val = val;
            this.colIndex = colIndex;
        }

        public int[] getColIndex() {
            return colIndex;
        }

        public int getVal() {
            return val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) {
        KthMinSum kthMinSum = new KthMinSum();
        /**
         * 3,3,4,4,4,4,5,5,5,5,6,6
         */
        int[][] nums = {
                {1, 2, 3, 3, 6},
                {1, 1, 3, 4, 7},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8},
                {1, 2, 3, 4, 8}
        };
        long a = System.currentTimeMillis();
        Assert.assertEquals(14, kthMinSum.kthSmallest(nums, 200));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] nums2 = {{1,2,3,4,5,6,7,8,9,10,11}};
        Assert.assertEquals(10, kthMinSum.kthSmallest(nums2, 10));

        int[][] nums3 = {
                {1,2,3,4,5,6,7,8,9,10,11},
                {1,2,13,14,15,16,17,18,19,110,111}
        };
        // 2,3,3,4,4,5,4,5,5,6,6,7,5,6,7,8
        Assert.assertEquals(14, kthMinSum.kthSmallest(nums3, 23));

        int[][] nums4 = {
                {1,2,3,4,5,6,7,8,9,10,11},
                {1,2,3,4,5,6,7,8,9,10,11},
                {1,2,3,4,5,6,7,8,9,10,11},
                {1,2,3,4,5,6,7,8,9,10,11},
                {1,2,3,4,5,6,7,8,9,10,11}
        };
        // 2,3,3,4,4,5,4,5,5,6,6,7,5,6,7,8
        a = System.currentTimeMillis();
        Assert.assertEquals(10, kthMinSum.kthSmallest(nums4, 200));
        System.out.println("cost=" + (System.currentTimeMillis() - a));
    }
}
