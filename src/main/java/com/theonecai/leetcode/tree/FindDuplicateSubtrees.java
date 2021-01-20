package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 652
 */
public class FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Set<TreeNode> exist = new HashSet<>();
        List<TreeNode> list = new ArrayList<>();

        dfs(root, root.left, exist, list);
        dfs(root, root.right, exist, list);

        return list;
    }

    private void dfs(TreeNode root, TreeNode node, Set<TreeNode> exist, List<TreeNode> list) {
        if (node == null || root == null) {
            return;
        }

        if (!exist.contains(node)) {
            TreeNode n = containSubTree(root, node);
            exist.add(node);
            if (n != null && !exist.contains(n)) {
                list.add(node);
                exist.add(n);
            }
        }
        dfs(root, node.left, exist, list);
        dfs(root, node.right, exist, list);
    }

    /**
     * root中跟node结构一样的子树
     * @param root
     * @param node
     * @return
     */
    private TreeNode containSubTree(TreeNode root, TreeNode node) {
        if (root == null && node == null) {
            return null;
        }
        if (root == null || node == null) {
            return null;
        }
        if (isSameTree(root, node)) {
            return root;
        }
        TreeNode result = containSubTree(root.left, node);
        if (result == null) {
            return containSubTree(root.right, node);
        }
        return result;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        if (p == q) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        FindDuplicateSubtrees findDuplicateSubtrees = new FindDuplicateSubtrees();
        List<TreeNode> list = findDuplicateSubtrees.findDuplicateSubtrees(BinaryTreeUtil.deserialize("[1,2,3,4,null,2,4,null,null,4]"));
        list.forEach(t -> System.out.println(BinaryTreeUtil.serialize(t)));
        list = findDuplicateSubtrees.findDuplicateSubtrees(BinaryTreeUtil.deserialize("[1,1,1,1,1,1,1]"));
        list.forEach(t -> System.out.println(BinaryTreeUtil.serialize(t)));
    }

}
