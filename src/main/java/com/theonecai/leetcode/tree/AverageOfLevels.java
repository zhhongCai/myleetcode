package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 637
 * @Author: theonecai
 * @Date: Create in 2021/1/17 21:05
 * @Description:
 */
public class AverageOfLevels {

    public List<Double> averageOfLevels(TreeNode root) {
        Map<Integer, long[]> map = new TreeMap<>();

        dfs(root, 1, map);

        List<Double> result = new ArrayList<>(map.size());
        for (Map.Entry<Integer, long[]> entry : map.entrySet()) {
            result.add((double)entry.getValue()[0] / entry.getValue()[1]);
        }
        return result;
    }

    private void dfs(TreeNode node, int depth, Map<Integer, long[]> map) {
        if (node == null) {
            return;
        }
        long[] count = map.getOrDefault(depth, new long[2]);
        count[0] += node.val;
        count[1]++;
        map.put(depth, count);

        dfs(node.left, depth + 1, map);
        dfs(node.right, depth + 1, map);
    }

    public static void main(String[] args) {
        AverageOfLevels averageOfLevels = new AverageOfLevels();
        System.out.println(averageOfLevels.averageOfLevels(BinaryTreeUtil.deserialize("[3,9,20,null,null,15,7]")));
    }
}
