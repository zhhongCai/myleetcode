package com.theonecai.leetcode.weekend;


import com.theonecai.leetcode.tree.TreeNode;
import org.junit.Assert;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/03/20 10:24
 * @Description:
 */
public class Weekend283 {
    public List<String> cellsInRange(String s) {
        char row = s.charAt(1);
        char row2 = s.charAt(4);
        char col = s.charAt(0);
        char col2 = s.charAt(3);
        List<String> ans = new ArrayList<>();
        for (char i = col; i <= col2; i++) {
            for (char j = row; j <= row2; j++) {
                ans.add(i + String.valueOf(j));
            }
        }

        return ans;
    }


    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0;
        long pre = 0;
        long s = k;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - pre > 1) {
                long kk = Math.min(s, nums[i] - pre - 1);
                s -= kk;
                ans += (pre + 1) * kk + (kk - 1) * kk / 2;
            }
            if (s == 0) {
                break;
            }
            pre = nums[i];
        }
        if (s > 0) {
            ans += (pre + 1) * s + (s - 1) * s / 2;
        }

        return ans;
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> parent = new HashMap<>();
        Map<Integer, List<Integer>> children = new HashMap<>();
        Map<Integer, Boolean> degree = new HashMap<>();
        Set<Integer> node = new HashSet<>();
        for (int[] d : descriptions) {
            parent.put(d[1], new int[]{d[0], d[2]});
            children.putIfAbsent(d[0], new ArrayList<>());
            children.get(d[0]).add(d[1]);
            node.add(d[0]);
            node.add(d[1]);
            degree.put(d[1], true);
        }
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        TreeNode root = null;
        for (Integer n : node) {
            if (!degree.containsKey(n)) {
                queue.add(n);
                root = new TreeNode(n);
                nodeMap.put(n, root);
            }
        }


        while (!queue.isEmpty()) {
            int v = queue.poll();
            TreeNode nd = new TreeNode(v);
            if (!nodeMap.containsKey(v)) {
                nodeMap.put(v, nd);
            }

            if (parent.containsKey(v)) {
                int[] p = parent.get(v);
                TreeNode pn = nodeMap.get(p[0]);
                if (p[1] == 1) {
                    pn.left = nd;
                } else {
                    pn.right = nd;
                }
            }

            List<Integer> ch = children.get(v);
            if (ch != null) {
                for (int next : ch) {
                    queue.add(next);
                }
            }
        }

        return root;
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {

        int n = nums.length;
        int[] stack = new int[n];
        int idx = -1;
        for (int num : nums) {
            if (idx == -1) {
                stack[++idx] = num;
            } else {
                long x = stack[idx];
                long y = num;
                long gcd = gcd(x, y);
                if (gcd == 1) {
                    stack[++idx] = num;
                } else {
                    long lcm = x * y / gcd;
                    while (idx > 0) {
                        y = lcm;
                        x = stack[idx - 1];
                        gcd = gcd(x, y);
                        if (gcd != 1) {
                            lcm = x * y / gcd;
                            idx--;
                        } else {
                            break;
                        }
                    }
                    stack[idx] = (int)lcm;
                }
            }
        }

        List<Integer> list = new ArrayList<>(idx + 1);
        for (int i = 0; i <= idx; i++) {
            list.add(stack[i]);
        }
        return list;
    }

    private long gcd(long x, long y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    public long lcm(long m, long n){
        long gcd = gcd(m,n);
        return m*n / gcd;
    }

    public static void main(String[] args) {
        Weekend283 weekend = new Weekend283();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        System.out.println(lcm(899, 20677));
        System.out.println(replaceNonCoprimes(new int[]{287,41,49,287,899,23,23,20677,5,825}));
        System.out.println(replaceNonCoprimes(new int[]{31,97561,97561,97561,97561,97561,97561,97561,97561}));
        System.out.println(replaceNonCoprimes(new int[]{6,4,3,2,7,6,2}));
        System.out.println(replaceNonCoprimes(new int[]{2,2,1,1,3,3,3}));
    }

    private void test3() {
        TreeNode r = createBinaryTree(new int[][]{
                {20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}
        });
        System.out.println(r);
    }

    private void test2() {

        Assert.assertEquals(3444, minimalKSum(new int[]{53,41,90,33,84,26,50,32,63,47,66,43,29,88,71,28,83}, 76));
        Assert.assertEquals(5, minimalKSum(new int[]{1,4,25,10,25}, 2));
        Assert.assertEquals(25, minimalKSum(new int[]{5,6}, 6));
    }

    private void test() {
    }
}
