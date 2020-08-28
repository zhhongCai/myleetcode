package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/26 21:58
 * @Description:
 */
public class ReversedNumPairs {

    public int reversePairs(int[] nums) {
        int count = 0;
        if (nums == null || nums.length < 2) {
             return count;
        }

        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }
        Arrays.sort(nodes);

        for (int i = nodes.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nodes[i].val <= 2 * nodes[j].val) {
                    break;
                }
                if (nodes[i].index < nodes[j].index) {
                    count++;
                }
            }
        }

        return count;
    }


    static class Node implements Comparable<Node> {
        int val;
        int index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) {
        ReversedNumPairs reversedNumPairs = new ReversedNumPairs();
        int[] nums = {1,3,3,3,1};
        Assert.assertEquals(3, reversedNumPairs.reversePairs(nums));
    }
}
