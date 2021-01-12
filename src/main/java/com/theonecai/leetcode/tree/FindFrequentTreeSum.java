package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 508
 */
public class FindFrequentTreeSum {

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Map<Integer, Integer> countMap = new HashMap<>();

        dfs(root, countMap);

        List<Map.Entry<Integer, Integer>> list = countMap.entrySet().stream().sorted((e, e2) ->
                e2.getValue().compareTo(e.getValue())).collect(Collectors.toList());
        int preCount = -1;
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            if (preCount == -1) {
                result.add(entry.getKey());
            } else {
                if (entry.getValue() == preCount) {
                    result.add(entry.getKey());
                } else {
                    break;
                }
            }
            preCount = entry.getValue();
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 计算以node为根的树的所有节点的和
     * @param node
     * @param countMap
     * @return
     */
    private Integer dfs(TreeNode node, Map<Integer, Integer> countMap) {
        if (node == null) {
            return null;
        }

        Integer leftSum = dfs(node.left, countMap);
        Integer rightSum = dfs(node.right, countMap);
        int sum = (leftSum == null ? 0 : leftSum) + (rightSum == null ? 0 : rightSum) + node.val;

        countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);

        return sum;
    }

    public static void main(String[] args) {
        FindFrequentTreeSum findFrequentTreeSum = new FindFrequentTreeSum();
        TreeNode root = BinaryTreeUtil.deserialize("[5,2,-3]");
        System.out.println(Arrays.toString(findFrequentTreeSum.findFrequentTreeSum(root)));

        root = BinaryTreeUtil.deserialize("[5,2,-5]");
        System.out.println(Arrays.toString(findFrequentTreeSum.findFrequentTreeSum(root)));

        root = BinaryTreeUtil.deserialize("[2,1,-1,1,1,1,1]");
        System.out.println(Arrays.toString(findFrequentTreeSum.findFrequentTreeSum(root)));
    }
}
