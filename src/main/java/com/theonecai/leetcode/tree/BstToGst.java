package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 1038
 * @Author: theonecai
 * @Date: Create in 2021/3/7 21:54
 * @Description:
 */
public class BstToGst {

    public TreeNode bstToGst(TreeNode root) {
        postOrder(root, 0);

        return root;
    }

    private int postOrder(TreeNode node, int parentNewVal) {
        if (node == null) {
            return 0;
        }

        int sum = node.val;
        sum += postOrder(node.right, parentNewVal);

        node.val = sum + parentNewVal;

        sum += postOrder(node.left, node.val);

        return sum;
    }


    public static void main(String[] args) {
        BstToGst bstToGst = new BstToGst();
        Assert.assertEquals("[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]", BinaryTreeUtil.serialize(bstToGst.bstToGst(
                BinaryTreeUtil.deserialize("[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]"))));
    }
}
