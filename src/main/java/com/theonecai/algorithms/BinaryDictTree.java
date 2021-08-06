package com.theonecai.algorithms;

/**
 * 0/1字典数
 * @Author: theonecai
 * @Date: Create in 7/18 17:13
 * @Description:
 */
public class BinaryDictTree {
    /**
     * 最长二进制位数
     */
    private int maxLevel;

    private TreeNode root;

    public BinaryDictTree(int maxLevel) {
        this.maxLevel = maxLevel;
        this.root = new TreeNode();
    }

    /**
     * 根据字典树求与num异或的最大值
     * @param num
     * @return
     */
    public int calcMaxXorValueFor(int num) {
        TreeNode node = root;
        int sum = 0;
        for (int j = maxLevel - 1; j >= 0; j--) {
            int bit = (num >> j) & 1;
            if (bit == 1) {
                if (node.next[0] != null && node.next[0].count > 0) {
                    sum += 1 << j;
                    node = node.next[0];
                } else {
                    node = node.next[1];
                }
            } else {
                if (node.next[1] != null && node.next[1].count > 0) {
                    sum += 1 << j;
                    node = node.next[1];
                } else {
                    node = node.next[0];
                }
            }
        }
        return sum;
    }

    public void insert(int num) {
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
            current.count++;
        }
    }

    public void delete(int num) {
        TreeNode current = root;
        for (int i = maxLevel - 1; i >= 0; i--) {
            if (((num >> i) & 1) == 0) {
                current = current.next[0];
            } else {
                current = current.next[1];
            }
            current.count--;
        }
    }

    private static class TreeNode {
        private TreeNode[] next;
        private int count = 0;

        public TreeNode() {
            this.next = new TreeNode[2];
        }
    }
}
