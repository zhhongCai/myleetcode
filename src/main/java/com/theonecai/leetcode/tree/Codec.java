package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 297
 */
public class Codec {

    private final static String EMP_TREE = "[]";
    private final static String NULL_VAL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return EMP_TREE;
        }
        List<Integer> list = new ArrayList<>();

        dfs(root, list);

        return list.toString();
    }

    private void dfs(TreeNode node, List<Integer> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int i = queue.size();
            while (i > 0) {
                TreeNode n = queue.poll();
                list.add(n == null ? null : n.val);
                if (n != null) {
                    if (n.left != null || n.right != null) {
                        queue.add(n.left);
                        queue.add(n.right);
                    }
                } else {
                    if (i > 1) {

                    }
                }
                i--;
            }
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() < 2 || EMP_TREE.equals(data)) {
            return null;
        }
        String[] dataArr = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0].trim()));
        int level = 1;
        int i = 1;
        int levelCount = 0;
        List<TreeNode> parent = new ArrayList<>();
        parent.add(root);
        while (i < dataArr.length) {
            levelCount = 0;
            List<TreeNode> tmpList = new ArrayList<>(level * 2);
            while (levelCount < level * 2 && i < dataArr.length) {
                if (!NULL_VAL.equals(dataArr[i].trim())) {
                    TreeNode node = new TreeNode(Integer.parseInt(dataArr[i].trim()));
                    TreeNode p = parent.get(levelCount / 2);
                    if (levelCount % 2 == 0) {
                        p.left = node;
                    } else {
                        p.right = node;
                    }
                    tmpList.add(node);
                }
                levelCount++;
                i++;
            }
            level++;
            parent = tmpList;

        }

        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.serialize(TreeNode.buildTree()));
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        String data = codec.serialize(root);
        System.out.println(data);

        root = codec.deserialize(data);
        Assert.assertNotNull(root);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));