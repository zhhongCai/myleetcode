package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 515
 */
public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        Map<Integer, Integer> depthMaxMap = new TreeMap<>();

        dfs(root, 0, depthMaxMap);

        return new ArrayList<>(depthMaxMap.values());
    }
    public void dfs(TreeNode node, int depth, Map<Integer, Integer> depthMaxMap) {
        if (node == null) {
            return;
        }
        if (!depthMaxMap.containsKey(depth)) {
            depthMaxMap.put(depth, node.val);
        } else {
            depthMaxMap.put(depth, Math.max(depthMaxMap.get(depth), node.val));
        }
        dfs(node.left, depth + 1, depthMaxMap);
        dfs(node.right, depth + 1, depthMaxMap);
    }

    public static void main(String[] args) {
        LargestValues largestValues = new LargestValues();
        TreeNode root = BinaryTreeUtil.deserialize("[1,3,2,5,3,9]");
        System.out.println(largestValues.largestValues(root));
    }
}
