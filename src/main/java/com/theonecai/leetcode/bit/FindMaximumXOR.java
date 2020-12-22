package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 421
 * theonecai
 */
public class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        buildTree(nums);

        int res = 0;
        for (int num : nums) {
            res = Math.max(res, calcMaxXorValueFor(num));
        }
        return res;
    }

    /**
     * 根据字典树求与num异或的最大值
     * @param num
     * @return
     */
    private int calcMaxXorValueFor(int num) {
        TreeNode node = root;
        int sum = 0;
        for (int j = maxLevel - 1; j >= 0; j--) {
            int bit = (num >> j) & 1;
            if (bit == 1) {
                sum += node.next[0] == null ? 0 : 1 << j;
                node = node.next[0] == null ? node.next[1] : node.next[0];
            } else {
                sum += node.next[1] == null ? 0 : 1 << j;
                node = node.next[1] == null ? node.next[0] : node.next[1];
            }
        }
        return sum;
    }

    /**
     * 数组构造二进制字典树
     * @param nums
     */
    private void buildTree(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        root = new TreeNode();
        maxLevel = toBinaryString(max).length();

        for (int num : nums) {
            insertNum(toBinaryString(num));
        }
    }

    private String toBinaryString(int num) {
        return Integer.toBinaryString(num);
        /*StringBuilder sb = new StringBuilder();
        int bit = 0;
        while (num != 0) {
            bit = num & 1;
            num = num >> 1;
            sb.append(bit == 1 ? "1" : "0");
        }
        return sb.reverse().toString();*/
    }

    private void insertNum(String binary) {
        int zeroPrefixCount = maxLevel - binary.length();
        TreeNode current = root;
        for (int i = 0; i < zeroPrefixCount; i++) {
            if (current.next[0] == null) {
                current.next[0] = new TreeNode();
            }
            current = current.next[0];
        }

        for (int j = 0; j < binary.length(); j++) {
            if (binary.charAt(j) == '0') {
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
    private int maxLevel;

    private static class TreeNode {
        private TreeNode[] next;

        public TreeNode() {
            this.next = new TreeNode[2];
        }
    }

    public static void main(String[] args) {
        FindMaximumXOR xorValue = new FindMaximumXOR();
        Assert.assertEquals(7, xorValue.findMaximumXOR(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, xorValue.findMaximumXOR(new int[]{1,1,1,1}));
        Assert.assertEquals(Integer.parseInt("1111111110", 2), xorValue.findMaximumXOR(new int[]{1,Integer.parseInt("1111111111", 2)}));
    }
}
