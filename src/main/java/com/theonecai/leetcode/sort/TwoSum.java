package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1
 * @Author: theonecai
 * @Date: Create in 2020/12/30 22:17
 * @Description:
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];
        Node[] data = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] =  new Node(i, nums[i]);
        }
        Arrays.sort(data);

        for (int i = 0; i < data.length - 1; i++) {
            int idx = Arrays.binarySearch(data, i + 1, data.length, new Node(-1, target - data[i].val));
            if (idx >= 0) {
                index[0] = data[i].index;
                index[1] = data[idx].index;
                break;
            }
        }

        return index;
    }

    private static class Node implements Comparable<Node> {
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        Assert.assertEquals("[1, 2]", Arrays.toString(twoSum.twoSum(new int[]{3, 2, 4}, 6)));
        Assert.assertEquals("[0, 1]", Arrays.toString(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
