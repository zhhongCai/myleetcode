package com.theonecai.leetcode.weekend;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/09/12 10:24
 * @Description:
 */
public class Weekend258 {

    public String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);
        if (idx == -1) {
            return word;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(word, 0, idx + 1);
        sb.reverse();
        if (idx + 1 < word.length()) {
            sb.append(word.substring(idx + 1));
        }
        return sb.toString();
    }

    public long interchangeableRectangles(int[][] rectangles) {
        int n = rectangles.length;
        Map<String, Long> map = new HashMap<>();
        long count = 0;
        for (int i = 0; i < n; i++) {
            int gcd = gcd(rectangles[i][0], rectangles[i][1]);
            int w =  rectangles[i][0] / gcd;
            int h =  rectangles[i][1] / gcd;
            String key = w + "-" + h;
            if (!map.containsKey(key)) {
                map.put(key, map.getOrDefault(key, 0L) + 1);
            } else {
                long c = map.get(key);
                count += c;
                map.put(key, c + 1);
            }
        }

        return count;
    }

    public int gcd(int a, int b) {
        int res = 0;
        while (b != 0) {
            res = a % b;
            a = b;
            b = res;
        }

        return a;
    }


    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        TreeNode root = buildTree(parents, nums);
        int[] res = new int[parents.length];
        postOrder(root, res);
        return res;
    }

    private Res postOrder(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        int max = 1;
        Set<Integer> ss = new HashSet<>();
        if (!root.children.isEmpty()) {
            for (TreeNode child : root.children) {
                Res result = postOrder(child, res);
                max = Math.max(result.max, max);
                if (ss.size() > result.set.size()) {
                    ss.addAll(result.set);
                } else {
                    result.set.addAll(ss);
                    ss = result.set;
                }
            }
        }
        ss.add(root.val);
        res[root.idx] = findFirst(ss, max);
        return new Res(res[root.idx], ss);
    }

    private int findFirst(Set<Integer> set, int max) {
        for (int i = max; i < 100002; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode buildTree(int[] parents, int[] nums) {
        TreeNode root = new TreeNode(nums[0], 0);
        TreeNode[] map = new TreeNode[parents.length];
        map[0] = root;
        for (int i = 1; i < parents.length; i++) {
           TreeNode node = new TreeNode(nums[i], i);
            map[i] = node;
        }
        for (int i = 1; i < parents.length; i++) {
            map[parents[i]].children.add(map[i]);
        }
        return root;
    }

    private static class Res {
        public int max;
        public Set<Integer> set;

        public Res(int max, Set<Integer> set) {
            this.max = max;
            this.set = set;
        }
    }

    private static class TreeNode {
        public int val;
        public int idx;
        public List<TreeNode> children;
        TreeNode(int val, int idx) {
            this.val = val;
            this.idx = idx;
            children = new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        Weekend258 weekend = new Weekend258();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        Assert.assertArrayEquals(new int[]{7,1,1,4,2,1}, this.smallestMissingValueSubtree(new int[]{
                -1,0,1,0,3,3
        }, new int[]{
                5,4,6,2,1,3
        }));

        Assert.assertArrayEquals(new int[]{5,1,1,1}, this.smallestMissingValueSubtree(new int[]{
                -1,0,0,2
        }, new int[]{
                1,2,3,4
        }));


    }

    private void test3() {
    }

    private void test2() {
        Assert.assertEquals(6, this.interchangeableRectangles(new int[][]{
                {4,8},{3,6},{10,20},{15,30}
        }));

        Assert.assertEquals(0, this.interchangeableRectangles(new int[][]{
                {4,5},{7,8}
        }));
        Assert.assertEquals(6, this.interchangeableRectangles(new int[][]{
                {4,4},{2,2},{3,3},{2,8},{1,1}
        }));
    }

    private void test() {
        Assert.assertEquals("dcbaef", this.reversePrefix("abcdef", 'd'));

        //"rzwuktxcjfpamlonbgyieqdvhs"
        //"h"
        Assert.assertEquals("abcdef", this.reversePrefix("abcdef", 'k'));
        Assert.assertEquals("hvdqeiygbnolmapfjcxtkuwzrs", this.reversePrefix("rzwuktxcjfpamlonbgyieqdvhs", 'h'));
    }
}
