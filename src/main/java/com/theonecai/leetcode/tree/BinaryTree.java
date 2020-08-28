package com.theonecai.leetcode.tree;

import org.mortbay.util.ArrayQueue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * leetcode 144, 94
 * @Author: theonecai
 * @Date: Create in 2020/6/20 19:41
 * @Description:
 */
public class BinaryTree {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();

        preorderTraversal2(root, list);

        return list;
    }

    /**
     * 前序
     * @param node
     * @param list
     */
    private void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        list.add(node.val);
        if (node.left != null) {
            preorderTraversal(node.left, list);
        }
        if (node.right != null) {
            preorderTraversal(node.right, list);
        }
    }

    private void preorderTraversal2(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode x;
        stack.push(node);
        while (!stack.isEmpty()) {
            x = stack.pop();
            list.add(x.val);
            if (x.right != null) {
                stack.push(x.right);
            }
            if (x.left != null) {
                stack.push(x.left);
            }
        }
    }

    /**
     * 中序
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();

        inorderTraversal2(root, list);

        return list;
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

    private void inorderTraversal2(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode x = node;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
                continue;
            }

            x = stack.pop();
            list.add(x.val);
            x = x.right;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new LinkedList<>();

        postorderTraversal2(root, list);

        return list;
    }

    public void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postorderTraversal(node.left, list);
        }
        if (node.right != null) {
            postorderTraversal(node.right, list);
        }
        list.add(node.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root,  List<Integer> list) {
        if (root == null) {
            return Collections.emptyList();
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode x;
        stack.push(root);
        while (!stack.isEmpty()) {
            x = stack.peek();
            // 没有左右节点或已访问过
            if ((x.left == null && x.right == null) ||
                    (pre != null && (x.left == pre || x.right == pre))) {
                list.add(x.val);
                stack.pop();
                pre = x;
            } else {
                if (x.right != null) {
                    stack.push(x.right);
                }
                if (x.left != null) {
                    stack.push(x.left);
                }
            }
        }

        return list;
    }


    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * BFS 遍历树
     * @param root
     * @return
     */
    public List<Integer> bfsTraversal(TreeNode root) {
        Queue<TreeNode> queue = new ArrayQueue<>();
        List<Integer> result = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
    }

    /**
     * DFS 遍历树 == 前序遍历
     * @param root
     * @return
     */
    public List<Integer> dfsTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BinaryTree tree = new BinaryTree();
        List<Integer> list = tree.preorderTraversal(root);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();
        list = tree.inorderTraversal(root);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();
        list = tree.postorderTraversal(root);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();

        list = tree.bfsTraversal(root);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();

        list = tree.dfsTraversal(root);
        list.forEach(e -> System.out.print(e + " "));
        System.out.println();
    }
}
