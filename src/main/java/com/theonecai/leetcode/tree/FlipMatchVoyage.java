package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 971
 * @Author: theonecai
 * @Date: Create in 2021/2/20 21:19
 * @Description:
 */
public class FlipMatchVoyage {

    private List<Integer> result;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        this.result = new ArrayList<>();
        if (root.val != voyage[0]) {
            this.result.add(-1);
            return this.result;
        }

        int index = preOrder(root, voyage, 0);

        if (index < voyage.length) {
            this.result.clear();
            this.result.add(-1);
            return this.result;
        }

        return this.result;
    }

    private int preOrder(TreeNode node, int[] voyage, int index) {
        if (node == null) {
            return index;
        }
        if (index < 0 || index >= voyage.length) {
            return -1;
        }
        if (node.val != voyage[index]) {
            return -1;
        }
        if (node.left != null) {
            if (node.left.val == voyage[index + 1]) {
                int i = preOrder(node.left, voyage, index + 1);
                i = preOrder(node.right, voyage, i);
                return i;
            } else {
                this.result.add(node.val);
                int i = preOrder(node.right, voyage, index + 1);
                i = preOrder(node.left, voyage, i);
                return i;
            }
        } else {
            return preOrder(node.right, voyage, index + 1);
        }
    }

    public static void main(String[] args) {
        FlipMatchVoyage flipMatchVoyage = new FlipMatchVoyage();
        Assert.assertEquals("[-1]", flipMatchVoyage.flipMatchVoyage(
                BinaryTreeUtil.deserialize("[1,2,null,3]"),
                new int[]{1,3,2}
        ).toString());
        Assert.assertEquals("[]", flipMatchVoyage.flipMatchVoyage(
                BinaryTreeUtil.deserialize("[1,2]"),
                new int[]{1,2}
        ).toString());
        Assert.assertEquals("[-1]", flipMatchVoyage.flipMatchVoyage(
                BinaryTreeUtil.deserialize("[2,1]"),
                new int[]{1,2}
        ).toString());
        Assert.assertEquals("[1]", flipMatchVoyage.flipMatchVoyage(
                BinaryTreeUtil.deserialize("[1,2,3]"),
                new int[]{1,3,2}
        ).toString());
        Assert.assertEquals("[]", flipMatchVoyage.flipMatchVoyage(
                BinaryTreeUtil.deserialize("[1,2,3]"),
                new int[]{1,2,3}
        ).toString());
    }
}
