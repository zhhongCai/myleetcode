package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 655
 */
public class PrintTree {

    private static final String EMP_STR = "";

    private int m;
    private int n;

    private List<String> empList;

    List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        this.m = 0;
        visit(root, 1);
        this.n = (int)Math.pow(2, this.m) - 1;
        this.empList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            empList.add(EMP_STR);
        }

        List<List<String>> levelList = new ArrayList<>(this.m);

        String[][] treeStr = new String[m][n];
        for (String[] strings : treeStr) {
            Arrays.fill(strings, EMP_STR);
        }

        dfs(root, 1, 0, n -1, treeStr);

        for (int i = 0; i < m; i++) {
            levelList.add(Arrays.asList(treeStr[i]).subList(0, n));
        }

        return levelList;
    }

    private void visit(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        this.m = Math.max(this.m, depth);
        visit(node.left, depth + 1);
        visit(node.right, depth + 1);
    }

    private void dfs(TreeNode node, int level, int low, int high, String[][] treeStr) {
        if (level > this.m) {
            return;
        }
        if (node == null) {
            return;
        }

        dfs(node.left, level + 1, low, (low + high) / 2, treeStr);


        treeStr[level - 1][(low + high) / 2] = String.valueOf(node.val);

        dfs(node.right, level + 1, (low + high) / 2 + 1, high, treeStr);
    }

    public static void main(String[] args) {
        PrintTree printTree = new PrintTree();

        for (List<String> strings : printTree.printTree(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7,8]"))) {
            System.out.println(strings);
        }

        for (List<String> strings : printTree.printTree(BinaryTreeUtil.deserialize("[1,2,3,4]"))) {
            System.out.println(strings);
        }

        for (List<String> strings : printTree.printTree(BinaryTreeUtil.deserialize("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18]"))) {
            System.out.println(strings);
        }
    }
}
