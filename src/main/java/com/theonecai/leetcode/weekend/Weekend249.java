package com.theonecai.leetcode.weekend;


import com.theonecai.leetcode.tree.BinaryTreeUtil;
import com.theonecai.leetcode.tree.TreeNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/07/11 10:24
 * @Description:
 */
public class Weekend249 {

    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = nums[i % nums.length];
        }
        return ans;
    }

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] preSum = new int[26][n];
        char[] chars = s.toCharArray();
        preSum[chars[0] - 'a'][0]++;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (chars[i] - 'a' == j) {
                    preSum[j][i] = preSum[j][i - 1] + 1;
                } else {
                    preSum[j][i] = preSum[j][i - 1];
                }
            }
        }
        int count = 0;
        Set<String> exists = new HashSet<>();
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < 26; j++) {
                int left = preSum[j][i - 1];
                int right = preSum[j][n - 1] - preSum[j][i];
                if (left >0 && right > 0) {
                    char[] ch = new char[3];
                    ch[0] = (char)(j + 'a');
                    ch[1] = chars[i];
                    ch[2] = (char)(j + 'a');
                    String sub = String.valueOf(ch);
//                    System.out.println(sub);
                    if (!exists.contains(sub)) {
                        count++;
                        exists.add(sub);
                    }
                }
            }
        }

        return count;
    }


    private Map<Integer, TreeNode> map;
    public TreeNode canMerge(List<TreeNode> trees) {
        if (trees.size() == 1) {
            return trees.get(0);
        }
        trees.sort((o1, o2) -> o2.val - o1.val);
        map = new HashMap<>();
        for (TreeNode tree : trees) {
            map.put(tree.val, tree);
        }
        int i = 0;
        TreeNode root = trees.get(i++);
        List<TreeNode> treeNodes = new ArrayList<>();
        while (i < trees.size()) {
            TreeNode other = trees.get(i++);
            root = merge(root, other);
            if (root == null) {
                treeNodes.add(other);
            }
        }
        return root;
    }

    /**
     * root.val > node.val
     * @param root
     * @param node
     * @return
     */
    private TreeNode merge(TreeNode root, TreeNode node) {
        TreeNode p = findNode(root, node.val);
        if (p == null) {
            return doMerge(node, root);
        }
        if (node.right != null && node.right.val >= root.val) {
            return null;
        }
        p.left = node.left;
        p.right = node.right;
        return root;
    }

    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val && root.left == null && root.right == null) {
            return root;
        }
        if (val > root.val) {
            return findNode(root.right, val);
        }

        return findNode(root.left, val);
    }

    /**
     * node.val < other.val
     * @param node
     * @param other
     * @return
     */
    private TreeNode doMerge(TreeNode node, TreeNode other) {
        TreeNode p = findNode(node, other.val);
        if (p == null) {
            return null;
        }
        if (other.left != null && other.left.val <= node.val) {
            return null;
        }
        p.left= other.left;
        p.right = other.right;
        return node;
    }

    public static void main(String[] args) {
        Weekend249 weekend = new Weekend249();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        List<TreeNode> trees = new ArrayList<>();
        TreeNode root = null;

        trees.add(BinaryTreeUtil.deserialize("[10,9]"));
        trees.add(BinaryTreeUtil.deserialize("[9,8]"));
        trees.add(BinaryTreeUtil.deserialize("[8,7]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));

        trees = new ArrayList<>();
        trees.add(BinaryTreeUtil.deserialize("[2,1]"));
        trees.add(BinaryTreeUtil.deserialize("[3,2,5]"));
        trees.add(BinaryTreeUtil.deserialize("[5,4]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));

        trees = new ArrayList<>();
        trees.add(BinaryTreeUtil.deserialize("[5,3,8]"));
        trees.add(BinaryTreeUtil.deserialize("[3,2,6]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));

        trees = new ArrayList<>();
        trees.add(BinaryTreeUtil.deserialize("[5,4]"));
        trees.add(BinaryTreeUtil.deserialize("[3]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));

        trees = new ArrayList<>();
        trees.add(BinaryTreeUtil.deserialize("[5,4]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));

        trees = new ArrayList<>();
        trees.add(BinaryTreeUtil.deserialize("[2,1,3]"));
        trees.add(BinaryTreeUtil.deserialize("[3,2]"));
        root = canMerge(trees);
        System.out.println(BinaryTreeUtil.serialize(root));


    }

    private void test3() {
    }

    private void test2() {
        Assert.assertEquals(3, countPalindromicSubsequence("aabca"));
        Assert.assertEquals(0, countPalindromicSubsequence("abc"));
        Assert.assertEquals(4, countPalindromicSubsequence("bbcbaba"));
    }

    private void test() {
    }
}
