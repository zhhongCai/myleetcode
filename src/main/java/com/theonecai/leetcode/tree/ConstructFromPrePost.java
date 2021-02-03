package com.theonecai.leetcode.tree;

import org.junit.Assert;

/**
 * leetcode 889
 * @Author: theonecai
 * @Date: Create in 2021/2/3 20:21
 * @Description:
 */
public class ConstructFromPrePost {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePost(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    private TreeNode constructFromPrePost(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || preStart >= pre.length) {
            return null;
        }
        if (postStart > postEnd || postStart >= post.length) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preStart]);
        int postIndex = postStart;
        for (; postIndex < postEnd && preStart < pre.length - 1; postIndex++) {
            if (pre[preStart + 1] == post[postIndex]) {
                break;
            }
        }
        int len = postIndex - postStart + 1;
        if (preEnd > preStart) {
            node.left = constructFromPrePost(pre, post, preStart + 1, preStart + len, postStart, postIndex);
        }
        if (postEnd > postStart) {
            node.right = constructFromPrePost(pre, post, preStart + len + 1, preEnd, postIndex + 1, postEnd);
        }
        return node;
    }

    public static void main(String[] args) {
        ConstructFromPrePost prePost = new ConstructFromPrePost();
        Assert.assertEquals("[1,2,3,4,5,6,7]", BinaryTreeUtil.serialize(prePost.constructFromPrePost(new int[]{1,2,4,5,3,6,7},
                new int[]{4,5,2,6,7,3,1})));
        Assert.assertEquals("[1]", BinaryTreeUtil.serialize(prePost.constructFromPrePost(new int[]{1},
                new int[]{1})));
    }

}
