package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.List;

/**
 * leetcode 988
 * @Author: theonecai
 * @Date: Create in 2021/3/5 21:08
 * @Description:
 */
public class SmallestFromLeaf {

    private String min;

    public String smallestFromLeaf(TreeNode root) {

        dfs(root, new StringBuilder());
//        dfs(root, new ArrayList<>());

        return min;
    }

    private void dfs(TreeNode node, StringBuilder chars) {
        if (node == null) {
            return;
        }

        chars.append((char) (node.val + 'a'));

        if (node.left == null && node.right == null) {
            chars.reverse();
            String str = chars.toString();
            chars.reverse();
            if (min == null) {
                min = str;
            } else {
                min = min.compareTo(str) > 0 ? str : min;
            }
        }

        dfs(node.left, chars);
        dfs(node.right, chars);

        chars.deleteCharAt(chars.length() - 1);
    }

    private void dfs(TreeNode node, List<Integer> chars) {
        if (node == null) {
            return;
        }

        chars.add(node.val);

        if (node.left == null && node.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = chars.size() - 1; i >= 0; i--) {
                sb.append((char)(chars.get(i) + 'a'));
            }
            if (min == null) {
                min = sb.toString();
            } else {
                String str = sb.toString();
                min = min.compareTo(str) > 0 ? str : min;
            }
        }

        dfs(node.left, chars);
        dfs(node.right, chars);

        chars.remove(chars.size() - 1);
    }

    public static void main(String[] args) {
        SmallestFromLeaf smallestFromLeaf = new SmallestFromLeaf();
        Assert.assertEquals("dba", smallestFromLeaf.smallestFromLeaf(BinaryTreeUtil.deserialize("[0,1,2,3,4,3,4]")));
        Assert.assertEquals("adz", smallestFromLeaf.smallestFromLeaf(BinaryTreeUtil.deserialize("[25,1,3,1,3,0,2]")));
        Assert.assertEquals("abc", smallestFromLeaf.smallestFromLeaf(BinaryTreeUtil.deserialize("[2,2,1,null,1,0,null,0]")));
    }
}
