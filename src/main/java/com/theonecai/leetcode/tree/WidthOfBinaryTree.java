package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 622
 */
public class WidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int width = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = -1;
            int ii = -1;
            int j = 0;
            boolean allNull = true;
            while (j < size) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                    allNull = false;
                    if (ii == -1) {
                        ii = j;
                    } else {
                        i = j;
                    }
                }  else {
                    queue.add(null);
                    queue.add(null);
                }
                j++;
            }
            if (i != -1) {
                width = Math.max(width, (i - ii) + 1);
            }
            if (allNull) {
                break;
            }
        }

        return width;
    }

    public static void main(String[] args) {
        WidthOfBinaryTree treeWidth = new WidthOfBinaryTree();
        /**
         * 1
         * 1 1
         * 1 1 1 1
         * null,null,null,1,null,null,null,null
         * 2,2,2,2,2,2,2,null,2,null,null,2,null,2
         *
         */
        Assert.assertEquals(3,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,null,5,null,7,8]")));
        Assert.assertEquals(8,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,1,1,1,1,1,1,null,null,null,1,null,null,null,null,2,2,2,2,2,2,2,null,2,null,null,2,null,2]")));
        Assert.assertEquals(8,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,1,1,1,null,null,1,1,null,null,1]")));
        Assert.assertEquals(2,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,4]")));
        Assert.assertEquals(4,  treeWidth.widthOfBinaryTree(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7,8]")));
    }
}
