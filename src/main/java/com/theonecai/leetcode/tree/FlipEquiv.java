package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 951
 */
public class FlipEquiv {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root2 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) ||
                    (flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right)) ;
        }

        return false;
    }

    public static void main(String[] args) {
        FlipEquiv flipEquiv = new FlipEquiv();
        Assert.assertTrue(flipEquiv.flipEquiv(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,null,null,null,7,8]"),
                BinaryTreeUtil.deserialize("[1,3,2,null,6,4,5,null,null,null,null,8,7]")));
        Assert.assertTrue(flipEquiv.flipEquiv(BinaryTreeUtil.deserialize("[1,2,3]"),
                BinaryTreeUtil.deserialize("[1,3,2]")));
        Assert.assertTrue(flipEquiv.flipEquiv(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[1]")));
        Assert.assertFalse(flipEquiv.flipEquiv(BinaryTreeUtil.deserialize("[1]"),
                BinaryTreeUtil.deserialize("[1,2]")));
    }
}
