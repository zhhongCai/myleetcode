package com.theonecai.leetcode.sort;

import org.junit.Assert;

/**
 * leetcode 493
 * @Author: theonecai
 * @Date: Create in 2020/8/26 21:58
 * @Description:
 */
public class ReversedNumPairs {

    public int reversePairs(int[] nums) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        Node[] tmpArray = new Node[nums.length];

        return mergeSort(nodes, 0, nums.length - 1, tmpArray);
    }

    private int mergeSort(Node[] nodes, int start, int end, Node[] tmpArray) {
        if (start >= end) {
            return 0;
        }

        int mid = (end + start) / 2;
        int count = mergeSort(nodes, start, mid, tmpArray);
        count += mergeSort(nodes, mid + 1, end, tmpArray);

        count += merge(nodes, start, mid, end, tmpArray);
        return count;
    }

    private int merge(Node[] nodes, int start, int mid, int end, Node[] tmpArray) {
        int count = 0;
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tmpIndex = start;
        int j = rightIndex;
        for (int i = start; i <= mid; i++) {
            while (j <= end && nodes[i].value > nodes[j].value * 2L) {
                j++;
            }
            count += j - rightIndex;
        }
        while (leftIndex <= mid || rightIndex <= end) {
            if (rightIndex > end || (leftIndex <= mid && nodes[leftIndex].value < nodes[rightIndex].value)) {
                tmpArray[tmpIndex++] = nodes[leftIndex++];
            } else {
                tmpArray[tmpIndex++] = nodes[rightIndex++];
            }
        }

        System.arraycopy(tmpArray, start, nodes, start, end - start + 1);

        return count;
    }


    static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        ReversedNumPairs reversedNumPairs = new ReversedNumPairs();
        int[] nums = {1,3,3,3,1};
        Assert.assertEquals(3, reversedNumPairs.reversePairs(nums));

        int[] nums2 = {-5, -5};
        Assert.assertEquals(1, reversedNumPairs.reversePairs(nums2));
        /**
         * 2 3 4
         *
         * 1 5
         */
        int[] nums3 = {2,4,3,5,1};
        Assert.assertEquals(3, reversedNumPairs.reversePairs(nums3));
    }
}
