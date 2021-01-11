package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BFS
 * 297
 */
public class BinaryTreeCodec {

    private final static String EMP_TREE = "[]";
    private final static String NULL_VAL = "null";
    private final static String VALUE_SPLIT = ",";

    /**
     * Encodes a tree to a single string.
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return EMP_TREE;
        }
        List<Integer> list = new ArrayList<>();

        bfs(root, list);

        StringBuilder tree = new StringBuilder("[");
        for (Integer val : list) {
            tree.append(val == null ? NULL_VAL : val).append(VALUE_SPLIT);
        }
        tree.deleteCharAt(tree.length() - 1);
        tree.append(']');

        return tree.toString();
    }

    private void bfs(TreeNode node, List<Integer> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int lastHasChildrenIndex = -1;
            int i = 0;
            List<TreeNode> levelList = new ArrayList<>(i);
            while (i < levelSize) {
                TreeNode n = queue.poll();
                levelList.add(n);
                if (n != null && (n.left != null || n.right != null)) {
                    lastHasChildrenIndex = i;
                }
                i++;
            }

            for (int j = 0; j < levelList.size(); j++) {
                TreeNode n = levelList.get(j);
                list.add(n == null ? null : n.val);
                if (j <= lastHasChildrenIndex) {
                    queue.add(n == null ? null : n.left);
                    queue.add(n == null ? null : n.right);
                }
            }

        }
    }

    /**
     * Decodes your encoded data to tree.
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() < 2 || EMP_TREE.equals(data)) {
            return null;
        }
        String[] dataArr = data.substring(1, data.length() - 1).split(VALUE_SPLIT);
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0].trim()));

        int i = 1;
        List<TreeNode> parentList = new ArrayList<>();
        parentList.add(root);
        while (i < dataArr.length) {
            int levelSize = Math.min(parentList.size() * 2, dataArr.length - i);
            List<TreeNode> nextLevel = new ArrayList<>(levelSize);
            int j = 0;
            while (j < levelSize && i < dataArr.length) {
                String nodeStr = dataArr[i].trim();
                if (!NULL_VAL.equals(nodeStr)) {
                    TreeNode node = new TreeNode(Integer.parseInt(nodeStr));
                    nextLevel.add(node);
                    TreeNode parent = parentList.get(j / 2);
                    if (parent != null) {
                        if (j % 2== 0) {
                            parent.left = node;
                        } else {
                            parent.right = node;
                        }
                    }
                } else {
                    if (j < levelSize - 1) {
                        nextLevel.add(null);
                    }
                }
                j++;
                i++;
            }
            parentList = nextLevel;
        }

        return root;
    }

    public static void main(String[] args) {
        BinaryTreeCodec binaryTreeCodec = new BinaryTreeCodec();
        String data = binaryTreeCodec.serialize(TreeNode.buildTree());
        System.out.println(data);
        TreeNode root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        //[4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]
        data = "[1,2,3,null,null,4,5]";
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(8);
        root.left.left.left = new TreeNode(7);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        root = new TreeNode(10);
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(8);
        root.right.right.right = new TreeNode(7);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        data = binaryTreeCodec.serialize(null);
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);

        root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.left.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.right = new TreeNode(11);
        root.right.right = new TreeNode(12);
        root.right.right.right = new TreeNode(13);
        root.right.right.right.right = new TreeNode(14);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);
        root = binaryTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = binaryTreeCodec.serialize(root);
        System.out.println(data);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));