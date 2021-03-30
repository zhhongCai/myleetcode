package com.theonecai.leetcode.trie;

import org.junit.Assert;

/**
 * leetcode 1803
 * @Author: theonecai
 * @Date: Create in 2021/3/26 22:06
 * @Description:
 */
public class CountPairs {


    public int countPairs(int[] nums, int low, int high) {
        if (nums.length <= 1) {
            return 0;
        }
        int max = -1;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        max = Math.max(max, high + 1);

        DicTree dicTree = new DicTree(Integer.toBinaryString(max).length() + 1);
        int result = 0;
        for (int num : nums) {
            result += dicTree.countLessThan(num, high + 1) - dicTree.countLessThan(num, low);
            dicTree.insertNum(num);
        }
        return result;
    }

    private class DicTree {

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
                bit = (num >>> i) & 1;
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
                numBit = (num >>> i) & 1;
                limitBit = (limit >>> i) & 1;
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

    public static void main(String[] args) {
        CountPairs countPairs = new CountPairs();
        Assert.assertEquals(6, countPairs.countPairs(new int[]{3,3,4,3,4,4,3}, 1, 100000));
        Assert.assertEquals(6, countPairs.countPairs(new int[]{1,4,2,7}, 2, 6));
        Assert.assertEquals(8, countPairs.countPairs(new int[]{9,8,4,2,1}, 5, 14));
    }

}

