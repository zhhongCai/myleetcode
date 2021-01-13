package com.theonecai.leetcode.tree;

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
            if (root == node) {
                return null;
            }
            deleteChild(parent, node);
            return root;
        }

        // 左子树的最大值节点替换要删除节点并删除该最大值节点
        if (replaceNode(node, true)) {
            return root;
        }

        // 右子树的最小值节点替换要删除节点并删除该最小值节点
        replaceNode(node, false);

        return root;
    }

    private boolean replaceNode(TreeNode node, boolean maxValue) {
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
            deleteChild(p, replaceNode);
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
    private void deleteChild(TreeNode parent, TreeNode node) {
        if (node.left == null && node.right == null) {
            if (parent.val > node.val) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else {
            if (parent.val > node.val) {
                parent.left = node.left;
            } else {
                parent.right = node.right;
            }
        }
    }

    public static void main(String[] args) {
        //[8,0,31,null,6,28,45,1,7,25,30,32,49,null,4,null,null,9,26,29,null,null,42,47,null,2,5,null,12,null,27,null,null,41,43,46,48,null,3,null,null,10,19,null,null,33,null,null,44,null,null,null,null,null,null,null,11,18,20,null,37,null,null,null,null,14,null,null,22,36,38,13,15,21,24,34,null,null,39,null,null,null,16,null,null,23,null,null,35,null,40,null,17]
        //1
        BSTreeCodec codec = new BSTreeCodec();
        DeleteNode deleteNode = new DeleteNode();
        TreeNode root = BinaryTreeUtil.deserialize("[5,3,6,2,4,null,7]");
        root = deleteNode.deleteNode(root, 7);
        System.out.println(codec.serialize(root));

        root = BinaryTreeUtil.deserialize("[4,null,7,6,8,5,null,null,9]");
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
