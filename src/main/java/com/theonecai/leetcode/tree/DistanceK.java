package com.theonecai.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 863
 * @Author: theonecai
 * @Date: Create in 2021/1/30 20:12
 * @Description:
 */
public class DistanceK {

    private Map<TreeNode, TreeNode> fatherMap;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        fatherMap = new HashMap<>();

        dfs(root, null);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);
        visited.add(null);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                visited.add(node);
                if (step == K) {
                    result.add(node.val);
                } else {
                    if (!visited.contains(node.left)) {
                        queue.add(node.left);
                    }
                    if (!visited.contains(node.right)) {
                        queue.add(node.right);
                    }
                    TreeNode father = this.fatherMap.get(node);
                    if (!visited.contains(father)) {
                        queue.add(father);
                    }
                }
                size--;
            }
            step++;
        }
        return result;
    }

    private void dfs(TreeNode node, TreeNode father) {
        if (node == null) {
            return;
        }
        this.fatherMap.put(node, father);
        dfs(node.left, node);
        dfs(node.right, node);
    }

    public static void main(String[] args) {
        DistanceK distanceK = new DistanceK();
        TreeNode root = TreeNode.buildTree();
        TreeNode target = root.left;
        System.out.println(distanceK.distanceK(root, target, 2));
        System.out.println(distanceK.distanceK(root, target, 0));
        System.out.println(distanceK.distanceK(null, null, 0));
    }
}
