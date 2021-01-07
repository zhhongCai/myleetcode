package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 235
 */
public class LowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (q.val < p.val) {
            return lowestCommonAncestor(root, q, p);
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > q.val) {
                node = node.left;
                continue;
            }
            if (node.val < p.val) {
                node = node.right;
                continue;
            }
            return node;
        }
        return node;
    }

    private TreeNode commonParent;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> pathMap = new HashMap<>();

        search(root, p, pathMap);
        search(root, q, pathMap);

        return this.commonParent;
    }

    private void search(TreeNode root, TreeNode node, Map<Integer, TreeNode> pathMap) {
        if (root == null) {
            return;
        }
        if (pathMap.containsKey(root.val)) {
            commonParent = root;
        }
        pathMap.put(root.val, root);
        if (root.val == node.val) {
            return;
        }

        if (node.val > root.val) {
            search(root.right, node, pathMap);
        } else {
            search(root.left, node, pathMap);
        }

    }

    public static void main(String[] args) {
        LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
        TreeNode root = TreeNode.buildTree();
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(5);
        Assert.assertEquals(5, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
        q = new TreeNode(20);
        Assert.assertEquals(7, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
        q = new TreeNode(3);
        Assert.assertEquals(3, lowestCommonAncestor.lowestCommonAncestor(root, p, q).val);
    }
}
