package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 421
 * theonecai
 */
public class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        buildTree(nums);

        // 直接从二进制字典树中计算最大异或值
        return maximumXOR();
    }

    private int maximumXOR() {
        int a = -1;
        int b = 0;
        TreeNode aNode = root;
        TreeNode bNode = root;
        for (int i =  maxLevel - 1; i >= 0; i--) {
            boolean bZeroBitExists = bNode.next[0] != null;
            boolean bOneBitExists = bNode.next[1] != null;
            boolean aZeroBitExists = aNode.next[0] != null;
            boolean aOneBitExists = aNode.next[1] != null;
            if (a == -1 && aOneBitExists) {
                a = bZeroBitExists ? 1 << i : -1;
                bNode = bZeroBitExists ? bNode.next[0] : bNode.next[1];
                aNode = aNode.next[1];
            } else {
               if (aOneBitExists && bZeroBitExists) {
                   a += 1 << i;
                   aNode = aNode.next[1];
                   bNode = bNode.next[0];
               } else if (aZeroBitExists && bOneBitExists) {
                   b += 1 << i;
                   aNode = aNode.next[0];
                   bNode = bNode.next[1];
               } else {
                   aNode = aOneBitExists ? aNode.next[1] : aNode.next[0];
                   bNode = bOneBitExists ? bNode.next[1] : bNode.next[0];
               }
            }
        }
        if (a == -1) {
            return 0;
        }
        return a ^ b;
    }

    public int findMaximumXOR2(int[] nums) {
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
        Assert.assertEquals(Integer.parseInt("11", 2), xorValue.findMaximumXOR(new int[]{1,Integer.parseInt("10", 2),Integer.parseInt("11", 2)}));
        Assert.assertEquals(Integer.parseInt("111", 2), xorValue.findMaximumXOR(new int[]{
                Integer.parseInt("1", 2),
                Integer.parseInt("10", 2),
                Integer.parseInt("11", 2),
                Integer.parseInt("100", 2),
                Integer.parseInt("101", 2),
                Integer.parseInt("110", 2),
                Integer.parseInt("111", 2),
        }));
        Assert.assertEquals(Integer.parseInt("110000", 2), xorValue.findMaximumXOR(new int[]{
                Integer.parseInt("1111111", 2),
                Integer.parseInt("1101111", 2),
                Integer.parseInt("1111111", 2),
                Integer.parseInt("1101111", 2),
                Integer.parseInt("1011111", 2),
                Integer.parseInt("1001111", 2),
                Integer.parseInt("1011111", 2),
        }));
        Assert.assertEquals(Integer.parseInt("1111111110", 2), xorValue.findMaximumXOR(new int[]{1,Integer.parseInt("1111111111", 2)}));
        Assert.assertEquals(7, xorValue.findMaximumXOR(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, xorValue.findMaximumXOR(new int[]{1,1,1,1}));
    }
}
