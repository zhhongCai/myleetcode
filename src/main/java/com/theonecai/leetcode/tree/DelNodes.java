package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1110
 * @Author: theonecai
 * @Date: Create in 2021/3/14 17:02
 * @Description:
 */
public class DelNodes {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> result = new ArrayList<>();
        boolean[] toDeleteNodes = new boolean[1001];
        for (int i : to_delete) {
            toDeleteNodes[i] = true;
        }
        if (!toDeleteNodes[root.val]) {
            result.add(root);
        }
        dfs(root, null, false, toDeleteNodes, result);

        return result;
    }

    private void dfs(TreeNode node, TreeNode parent, boolean isLeft, boolean[] toDeleteNodes, List<TreeNode> result) {
        if (node == null) {
            return;
        }
        if (toDeleteNodes[node.val]) {
            if (parent != null) {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            if (node.left != null && !toDeleteNodes[node.left.val]) {
                result.add(node.left);
            }
            if (node.right != null &&  !toDeleteNodes[node.right.val]) {
                result.add(node.right);
            }
        }
        dfs(node.left, node, true, toDeleteNodes, result);
        dfs(node.right, node, false, toDeleteNodes, result);
    }

    public static void main(String[] args) {
        DelNodes delNodes = new DelNodes();
        List<TreeNode> list = delNodes.delNodes(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7]"), new int[]{3, 5});
        for (TreeNode treeNode : list) {
            System.out.println(BinaryTreeUtil.serialize(treeNode));
        }

        list = delNodes.delNodes(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7]"), new int[]{3,1,2,5});
        for (TreeNode treeNode : list) {
            System.out.println(BinaryTreeUtil.serialize(treeNode));
        }
        list = delNodes.delNodes(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7]"), new int[]{2,3});
        for (TreeNode treeNode : list) {
            System.out.println(BinaryTreeUtil.serialize(treeNode));
        }
        list = delNodes.delNodes(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7]"), new int[]{1,2,3,4,5,6,7});
        for (TreeNode treeNode : list) {
            System.out.println(BinaryTreeUtil.serialize(treeNode));
        }
    }
}
