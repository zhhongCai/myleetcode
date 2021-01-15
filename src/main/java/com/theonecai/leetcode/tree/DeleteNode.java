package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 450
 */
public class DeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        TreeNode parent = root;
        TreeNode node = root;
        while (node != null && node.val != key) {
            parent = node;
            if (key > node.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (node == null) {
            return root;
        }

        // key值所在节点为叶子节点
        if (node.left == null && node.right == null) {
            if (parent == node) {
                return null;
            }
            if (parent.val > node.val) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return root;
        }

        // 取左子树的最大值替换待删除节点的值，并删除该替换值节点
        if (node.left != null) {
            TreeNode replaceNode = node.left;
            TreeNode p = node;
            while (replaceNode.right != null) {
                p = replaceNode;
                replaceNode = replaceNode.right;
            }
            node.val = deleteReplaceNode(replaceNode, p);

            return root;
        }

        // 取右子树的最小值替换待删除节点的值，并删除该替换值节点
        TreeNode replaceNode = node.right;
        TreeNode p = node;
        while (replaceNode.left != null) {
            p = replaceNode;
            replaceNode = replaceNode.left;
        }
        node.val = deleteReplaceNode(replaceNode, p);

        return root;
    }

    private int deleteReplaceNode(TreeNode replaceNode, TreeNode replaceNodeParent) {
        int val = replaceNode.val;
        TreeNode subRoot = deleteNode(replaceNode, replaceNode.val);
        if (subRoot == null) {
            if (replaceNodeParent.val > replaceNode.val) {
                replaceNodeParent.left = null;
            } else {
                replaceNodeParent.right = null;
            }
            return val;
        }
        if (replaceNodeParent.val > subRoot.val) {
            replaceNodeParent.left = subRoot;
        } else {
            replaceNodeParent.right = subRoot;
        }
        return val;
    }

    public static void main(String[] args) {
        /*
                   8
                  0                                     31
                   6                                  28                   45
                 1    7                              25  30                32         49
                  4                                      9
                 2  5
                  3
         */

        DeleteNode deleteNode = new DeleteNode();

        TreeNode root = BinaryTreeUtil.deserialize("[8,0,31,null,6,28,45,1,7,25,30,32,49,null,4,null,null,9,26,29," +
                "null,null,42,47,null,2,5,null,12,null,27,null,null,41,43,46,48,null,3,null,null,10,19,null,null,33,null," +
                "null,44,null,null,null,null,null,null,null,11,18,20,null,37,null,null,null,null,14,null,null,22,36,38,13," +
                "15,21,24,34,null,null,39,null,null,null,16,null,null,23,null,null,35,null,40,null,17]");
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 1);
        Assert.assertEquals("[8,0,31,null,6,28,45,2,7,25,30,32,49,null,4,null,null,9,26,29,null,null,42,47," +
                "null,3,5,null,12,null,27,null,null,41,43,46,48,null,null,null,null,10,19,null,null,33,null,null,44," +
                "null,null,null,null,null,11,18,20,null,37,null,null,null,null,14,null,null,22,36,38,13,15,21,24,34," +
                "null,null,39,null,null,null,16,null,null,23,null,null,35,null,40,null,17]", BinaryTreeUtil.serialize(root));

        root = BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]");
        root = deleteNode.deleteNode(root, 3);
        System.out.println(BinaryTreeUtil.serialize(root));

        root = BinaryTreeUtil.deserialize("[20,10,40,5,15,null,null,4,6,14]");
        root = deleteNode.deleteNode(root, 10);
        System.out.println(BinaryTreeUtil.serialize(root));

        root = BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]");
        root = deleteNode.deleteNode(root, 7);
        Assert.assertEquals("[5,3,6,2,4]", BinaryTreeUtil.serialize(root));

        root = BinaryTreeUtil.deserialize("[4,null,7,6,8,5,null,null,9]");
        root = deleteNode.deleteNode(root, 7);
        Assert.assertEquals("[4,null,6,5,8,null,null,null,9]", BinaryTreeUtil.serialize(root));

        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(20);
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 5);
        System.out.println(BinaryTreeUtil.serialize(root));

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 5);
        System.out.println(BinaryTreeUtil.serialize(root));

        String data = "[5,3,6,2,4,null,7]";
        root = BinaryTreeUtil.deserialize(data);
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 3);
        System.out.println(BinaryTreeUtil.serialize(root));

        data = "[5]";
        root = BinaryTreeUtil.deserialize(data);
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 5);
        System.out.println(BinaryTreeUtil.serialize(root));

    }
}
