package com.theonecai.leetcode.trie;

/**
 * @Author: theonecai
 * @Date: Create in 2021/3/26 22:40
 * @Description:
 */
public class DicTree {

    private TreeNode root;

    private int maxLevel;

    private  class TreeNode {
        private TreeNode[] next;
        private int count;

        public TreeNode() {
            this.next = new TreeNode[2];
            this.count = 0;
        }
    }

    public DicTree(int maxLevel) {
        this.maxLevel = maxLevel;
        this.root = new TreeNode();
    }

    public void insertNum(int num) {
        TreeNode current = root;
        int bit;
        for (int i = maxLevel; i >= 0; i--) {
            bit = (num >> i) & 1;
            if (current.next[bit] == null) {
                current.next[bit] = new TreeNode();
            }
            current = current.next[bit];
            current.count++;
        }
    }

    /**
     * 统计Trie中与num异或值小于limit的个数
     * @param num
     * @return
     */
    public int countLessThan(int num, int limit) {
        TreeNode current = root;
        int count = 0;
        int numBit = 0;
        int limitBit = 0;
        for (int i = maxLevel; i >= 0; i--) {
            if (current == null) {
                return count;
            }
            numBit = (num >> i) & 1;
            limitBit = (limit >> i) & 1;
            if (limitBit == 1) {
                if (current.next[numBit] != null) {
                    count += current.next[numBit].count;
                }
                current = current.next[1 - numBit];
            } else {
                current = current.next[numBit];
            }
        }
        return count;
    }
}
