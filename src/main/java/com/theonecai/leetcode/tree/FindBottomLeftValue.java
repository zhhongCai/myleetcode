package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 513
 */
public class FindBottomLeftValue {

    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        int size;
        int index;
        TreeNode node;
        while (!queue.isEmpty()) {
            size = queue.size();
            index = 0;
            while (index < size) {
                node = queue.poll();
                if (index == 0) {
                    result = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                index++;
            }
        }

        return result;
    }

    public int findBottomLeftValue(TreeNode root) {
        List<Integer> depthFirstList = new ArrayList<>();

        dfs(root, 1, depthFirstList);

        return depthFirstList.get(depthFirstList.size() - 1);
    }
    public void dfs(TreeNode node, int depth, List<Integer> depthFirstList) {
        if (node == null) {
            return;
        }
        if (depthFirstList.size() < depth) {
            depthFirstList.add(node.val);
        }
        dfs(node.left, depth + 1, depthFirstList);
        dfs(node.right, depth + 1, depthFirstList);
    }

    public static void main(String[] args) {
        FindBottomLeftValue findBottomLeftValue = new FindBottomLeftValue();
        TreeNode root = BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7,8]");
        Assert.assertEquals(8, findBottomLeftValue.findBottomLeftValue(root));

        root = BinaryTreeUtil.deserialize("[2,1,3]");
        Assert.assertEquals(1, findBottomLeftValue.findBottomLeftValue(root));

        root = BinaryTreeUtil.deserialize("[1,2,3,4,null,5,6,null,null,7,null]");
        Assert.assertEquals(7, findBottomLeftValue.findBottomLeftValue(root));
    }
}
