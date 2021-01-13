package com.theonecai.leetcode.tree;

/**
 *  450
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
            if (root == node) {
                return null;
            }
            deleteChild(parent, parent, node);
            return root;
        }

        // 左子树的最大值节点替换要删除节点并删除该最大值节点
        if (replaceNode(parent, node, true)) {
            return root;
        }

        // 右子树的最小值节点替换要删除节点并删除该最小值节点
        replaceNode(parent, node, false);

        return root;
    }

    private boolean replaceNode(TreeNode parent, TreeNode node, boolean maxValue) {
        TreeNode replaceNode = maxValue ? node.left : node.right;
        TreeNode p = node;
        while (replaceNode != null) {
            if ((maxValue && replaceNode.right == null) || (!maxValue && replaceNode.left == null)) {
                break;
            }
            p = replaceNode;
            replaceNode = maxValue ? replaceNode.right : replaceNode.left;
        }
        if (replaceNode != null) {
            deleteChild(parent, p, replaceNode);
            node.val = replaceNode.val;
            return true;
        }
        return false;
    }

    /**
     * 删除节点node
     * @param parent
     * @param node
     */
    private void deleteChild(TreeNode pparent, TreeNode parent, TreeNode node) {
        if (node.left == null && node.right == null) {
            if (parent.val > node.val) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else {
            if (pparent.val > node.val) {
                pparent.left = node.left;
            } else {
                pparent.right = node.right;
            }
        }
    }

    public static void main(String[] args) {
        //[4,null,7,6,8,5,null,null,9]
        //7
        BSTreeCodec codec = new BSTreeCodec();
        DeleteNode deleteNode = new DeleteNode();
        TreeNode root = BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]");
        root = deleteNode.deleteNode(root, 7);
        System.out.println(codec.serialize(root));

        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(20);
        System.out.println(codec.serialize(root));
        root = deleteNode.deleteNode(root, 5);
        System.out.println(codec.serialize(root));

        root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        System.out.println(codec.serialize(root));
        root = deleteNode.deleteNode(root, 5);
        System.out.println(codec.serialize(root));

        String data = "[5,3,6,2,4,null,7]";
        root = BinaryTreeUtil.deserialize(data);
        System.out.println(BinaryTreeUtil.serialize(root));
        root = deleteNode.deleteNode(root, 3);
        System.out.println(BinaryTreeUtil.serialize(root));

    }
}
