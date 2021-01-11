package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * leetcode 501
 */
public class FindMode {
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        dfs(root, countMap);
        List<Integer> result = new ArrayList<>(countMap.size());
        List<Map.Entry<Integer, Integer>> countList = countMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        Integer pre = null;
        for (int i = countList.size() - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> entry = countList.get(i);
            if (pre == null) {
                result.add(entry.getKey());
            } else {
                if (pre.equals(entry.getValue())) {
                    result.add(entry.getKey());
                } else {
                    break;
                }
            }
            pre = entry.getValue();
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(TreeNode node, Map<Integer, Integer> countMap) {
        if (node == null) {
            return;
        }
        countMap.put(node.val, countMap.getOrDefault(node.val, 0) + 1);
        dfs(node.left, countMap);
        dfs(node.right, countMap);
    }

    public static void main(String[] args) {
        FindMode findMode = new FindMode();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        System.out.println(Arrays.toString(findMode.findMode(root)));
    }
}
