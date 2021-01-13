package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * 538
 */
public class ConvertBST {

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        dfs(root, 0);

        return root;
    }

    /**
     * 返回node为根的(原树)所有节点的值的和
     * @param node
     * @param parentNodeNewVal
     * @return
     */
    private int dfs(TreeNode node, int parentNodeNewVal) {
        if (node == null) {
            return 0;
        }
        // 原树node为根的原树的所有节点值的和
        int sum = node.val;
        sum += dfs(node.right, parentNodeNewVal);

        node.val = sum + parentNodeNewVal;

        sum += dfs(node.left, node.val);

        return sum;
    }

    public static void main(String[] args) {
        /**
         * 4,1,6,0,2,5,7,null,null,null,3,null,null,null,8
                       4
                     /   \
                   1      6
                  /  \   /  \
                0    2   5   7
                      \       \
                       3       8



         */

        ConvertBST convertBST = new ConvertBST();
        TreeNode root = convertBST.convertBST(BinaryTreeUtil.deserialize("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]"));
        Assert.assertEquals("[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]", BinaryTreeUtil.serialize(root));

        root = convertBST.convertBST(BinaryTreeUtil.deserialize("[0,null,1]"));
        Assert.assertEquals("[1,null,1]", BinaryTreeUtil.serialize(root));

        root = convertBST.convertBST(BinaryTreeUtil.deserialize("[1,0,2]"));
        Assert.assertEquals("[3,3,2]", BinaryTreeUtil.serialize(root));

        root = convertBST.convertBST(BinaryTreeUtil.deserialize("[3,2,4,1]"));
        Assert.assertEquals("[7,9,4,10]", BinaryTreeUtil.serialize(root));

    }
}
