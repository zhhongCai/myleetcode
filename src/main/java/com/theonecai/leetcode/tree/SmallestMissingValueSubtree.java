package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * leetcode 5870
 */
public class SmallestMissingValueSubtree {
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        Map<Integer, List<Integer>> childrenMap = new HashMap<>();
        int n = parents.length;
        for (int i = 1; i < n; i++) {
            List<Integer> list = childrenMap.getOrDefault(parents[i], new ArrayList<>());
            list.add(i);
            childrenMap.put(parents[i], list);
        }

        int[] res = new int[parents.length];
        postOrder(0, childrenMap, nums, res);

        return res;
    }

    private Set<Integer> postOrder(int root, Map<Integer, List<Integer>> childrenMap, int[] nums, int[] res) {
        Set<Integer> set = new HashSet<>();
        int max = 1;

        List<Integer> children = childrenMap.get(root);
        if (children != null && !children.isEmpty()) {
            for (Integer child : children) {
                Set<Integer> s = postOrder(child, childrenMap, nums, res);
                max = Math.max(res[child], max);
                if (s.size() > set.size()) {
                    s.addAll(set);
                    set = s;
                } else {
                    set.addAll(s);
                }
            }
        }

        set.add(nums[root]);
        res[root] = findFirst(set, max);
        return set;
}

    private int findFirst(Set<Integer> set, int max) {
        int i = max;
        while (set.contains(i)) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        SmallestMissingValueSubtree subtree = new SmallestMissingValueSubtree();
        Assert.assertArrayEquals(new int[]{7,1,1,4,2,1}, subtree.smallestMissingValueSubtree(new int[]{
                -1,0,1,0,3,3
        }, new int[]{
                5,4,6,2,1,3
        }));

        Assert.assertArrayEquals(new int[]{5,1,1,1}, subtree.smallestMissingValueSubtree(new int[]{
                -1,0,0,2
        }, new int[]{
                1,2,3,4
        }));
    }
}
