package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 1008
 * @Author: theonecai
 * @Date: Create in 2021/3/6 20:20
 * @Description:
 */
public class BstFromPreorder {
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, 0, preorder.length - 1);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(preorder[start]);
        }
        TreeNode node = new TreeNode(preorder[start]);
        int firstGreaterIndex = end + 1;
        for (int i = start + 1; i <= end; i++) {
            if (preorder[start] < preorder[i]) {
                firstGreaterIndex = i;
                break;
            }
        }
        node.left = bstFromPreorder(preorder, start + 1, firstGreaterIndex - 1);
        node.right = bstFromPreorder(preorder, firstGreaterIndex, end);

        return node;
    }

    public static void main(String[] args) {
        BstFromPreorder bstFromPreorder = new BstFromPreorder();
        TreeNode tree = bstFromPreorder.bstFromPreorder(new int[]{8,5,1,7,10,12});
        Assert.assertEquals("[8,5,10,1,7,null,12]", BinaryTreeUtil.serialize(tree));
        tree = bstFromPreorder.bstFromPreorder(new int[]{8,5,1,7});
        Assert.assertEquals("[8,5,null,1,7]", BinaryTreeUtil.serialize(tree));
    }
}
