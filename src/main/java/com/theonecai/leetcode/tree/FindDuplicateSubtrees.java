package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  652
 */
public class FindDuplicateSubtrees {

    private int id;

    private Map<String, Integer> map;

    private Map<Integer, Integer> count;

    private List<TreeNode> list;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        this.id = 1;
        this.map = new HashMap<>();
        this.count = new HashMap<>();
        this.list = new ArrayList<>();

        dfs(root);

        return list;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right);
        int uid = this.map.computeIfAbsent(serial, k -> id++);
        int c = this.count.getOrDefault(uid, 0);
        this.count.put(uid, c + 1);
        if (c == 1) {
            this.list.add(node);
        }
        return uid;
    }

    public static void main(String[] args) {
        FindDuplicateSubtrees findDuplicateSubtrees = new FindDuplicateSubtrees();
        List<TreeNode> list = findDuplicateSubtrees.findDuplicateSubtrees(BinaryTreeUtil.deserialize("[1,2,3,4,null,2,4,null,null,4]"));
        list.forEach(t -> System.out.println(BinaryTreeUtil.serialize(t)));
        list = findDuplicateSubtrees.findDuplicateSubtrees(BinaryTreeUtil.deserialize("[1,1,1,1,1,1,1]"));
        list.forEach(t -> System.out.println(BinaryTreeUtil.serialize(t)));
    }

}
