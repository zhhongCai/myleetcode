package com.theonecai.leetcode.tree;

/**
 * leetcode 617
 * @Author: theonecai
 * @Date: Create in 2021/1/17 15:08
 * @Description:
 */
public class MergeTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val = t1.val + t2.val;

        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }

    public static void main(String[] args) {
        MergeTrees mergeTrees = new MergeTrees();
        TreeNode root = mergeTrees.mergeTrees(BinaryTreeUtil.deserialize("[1,3,2,5]"),
                BinaryTreeUtil.deserialize("[2,1,3,null,4,null,7]"));
        System.out.println(BinaryTreeUtil.serialize(root));
    }

}
