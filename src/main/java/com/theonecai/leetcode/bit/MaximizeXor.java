package com.theonecai.leetcode.bit;


import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1707
 */
public class MaximizeXor {

    public int[] maximizeXor(int[] nums, int[][] queries) {
        buildTree(nums);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            result[i] = calcMaxXorValueFor(query[0], query[1]);
        }

        return result;
    }

    /**
     * 根据字典树求与num异或的最大值
     * @param num
     * @param m
     * @return
     */
    private int calcMaxXorValueFor(int num, int m) {
        if (m < 0) {
            return -1;
        }
        TreeNode node = root;
        int sum = 0;
        int n = 0;
        for (int j = maxLevel - 1; j >= 0; j--) {
            int bit = (num >> j) & 1;
            if (bit == 1) {
                if (node.next[0] == null) {
                    int t = 1 << j;
                    if (n + t > m) {
                        return calcMaxXorValueFor(num, n - 1);
                    }
                    node = node.next[1];
                    n += t;
                } else {
                    sum += 1 << j;
                    node = node.next[0];
                }
            } else {
                if (node.next[1] == null) {
                    node = node.next[0];
                } else {
                    int t = 1 << j;
                    if (n + t > m) {
                        if (node.next[0] == null) {
                            return calcMaxXorValueFor(num, n - 1);
                        }
                        node = node.next[0];
                        continue;
                    }
                    sum += 1 << j;
                    node = node.next[1];
                    n += t;
                }
            }
        }
        return sum;
    }

    /**
     * 数组构造二进制字典树
     * @param nums
     */
    private void buildTree(int[] nums) {
        root = new TreeNode();
        for (int num : nums) {
            insertNum(num);
        }
    }

    private void insertNum(int num) {
        TreeNode current = root;
        for (int i = maxLevel - 1; i >= 0; i--) {
            if (((num >> i) & 1) == 0) {
                if (current.next[0] == null) {
                    current.next[0] = new TreeNode();
                }
                current = current.next[0];
            } else {
                if (current.next[1] == null) {
                    current.next[1] = new TreeNode();
                }
                current = current.next[1];
            }
        }
    }

    private TreeNode root;
    /**
     * 最长二进制位数
     */
    private int maxLevel = 32;

    private static class TreeNode {
        private TreeNode[] next;

        public TreeNode() {
            this.next = new TreeNode[2];
        }
    }

    public static void main(String[] args) {
        MaximizeXor maximizeXor = new MaximizeXor();
        Assert.assertEquals(Arrays.toString(maximizeXor.maximizeXor(new int[]{33554432,765021065,42883,293499083,1000000000}, new int[][]{
                {568658171,1000000000},{17921962,1000000000},{162675788,1000000000},{12122115,534140862},{3731913,847670022},
        })), Arrays.toString(new int[]{815495216,982236586,841545292,298223816,765573696}));
        Assert.assertEquals(Arrays.toString(maximizeXor.maximizeXor(new int[]{0,1,2,3,4,5}, new int[][]{
                {1,3},
                {3,1},
                {5,6},
                {5,0},
        })), Arrays.toString(new int[]{3, 3, 7, 5}));
        Assert.assertEquals(Arrays.toString(maximizeXor.maximizeXor(new int[]{536870912,0,534710168,330218644,142254206}, new int[][]{
                {558240772,1000000000},{307628050,1000000000},{3319300,1000000000},{2751604,683297522},{214004,404207941},
        })), Arrays.toString(new int[]{1050219420,844498962,540190212,539622516,330170208}));
        Assert.assertEquals(Arrays.toString(maximizeXor.maximizeXor(new int[]{5,2,4,6,6,3}, new int[][]{
                {12,4},
                {8,1},
                {6,3},
        })), Arrays.toString(new int[]{15, -1, 5}));
    }
}
