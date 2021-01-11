package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 449
 */
public class BSTreeCodec {

    private final static String EMP_TREE = "[]";
    private final static String VALUE_SPLIT = ",";

    public String serialize(TreeNode root) {
        if (root == null) {
            return EMP_TREE;
        }
        List<Integer> list = new ArrayList<>();

        bfs(root, list);

        StringBuilder tree = new StringBuilder("[");
        for (Integer val : list) {
            tree.append(val).append(VALUE_SPLIT);
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
            int i = 0;
            while (i < levelSize) {
                TreeNode n = queue.poll();
                list.add(n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
                i++;
            }
        }
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.trim().length() == 0 || EMP_TREE.equals(data.trim())) {
            return null;
        }
        String[] dataArr = data.substring(1, data.length() - 1).split(VALUE_SPLIT);
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0].trim()));

        int i = 1;
        while (i < dataArr.length) {
            String nodeStr = dataArr[i].trim();
            TreeNode node = new TreeNode(Integer.parseInt(nodeStr));
            insert(root, node);
            i++;
        }

        return root;
    }

    private void insert(TreeNode root, TreeNode node) {
        if (root.val > node.val) {
            if (root.left != null) {
                insert(root.left, node);
            } else {
                root.left = node;
            }
        } else {
            if (root.right != null) {
                insert(root.right, node);
            } else {
                root.right = node;
            }
        }
    }

    public static void main(String[] args) {
        BSTreeCodec bsTreeCodec = new BSTreeCodec();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
//        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.right = new TreeNode(15);
        root.right.right = new TreeNode(20);
        String data = bsTreeCodec.serialize(root);
        System.out.println(data);
        root = bsTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = bsTreeCodec.serialize(root);
        System.out.println(data);

        data = "[10,9,8,7,6,5,4]";
        System.out.println(data);
        root = bsTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = bsTreeCodec.serialize(root);
        System.out.println(data);

        data = "[1,2,3,4,5]";
        System.out.println(data);
        root = bsTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = bsTreeCodec.serialize(root);
        System.out.println(data);

        data = "[]";
        System.out.println(data);
        root = bsTreeCodec.deserialize(data);
        Assert.assertNull(root);
        data = bsTreeCodec.serialize(root);
        System.out.println(data);

        data = "[1]";
        System.out.println(data);
        root = bsTreeCodec.deserialize(data);
        Assert.assertNotNull(root);
        data = bsTreeCodec.serialize(root);
        System.out.println(data);
    }
}
