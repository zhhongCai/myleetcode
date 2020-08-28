package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * leetcode 220
 * @Author: theonecai
 * @Date: Create in 2020/8/26 20:46
 * @Description:
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0 || nums == null || nums.length < 1) {
            return false;
        }
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        Arrays.sort(nodes);

        long tmp;
        for (int i = 0; i < nodes.length; i++ ) {
            for (int j = i - 1; j >= 0; j--) {
                tmp = Math.abs(nodes[i].val - nodes[j].val);
                if (tmp > (long)t) {
                    break;
                }
                if (Math.abs(nodes[j].index - nodes[i].index) <= k) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (k == 0 || nums == null || nums.length < 1) {
            return false;
        }

        Node tmp;
        Node node;
        TreeSet<Node> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            tmp = new Node(nums[i], i);
            if (i > 0) {
                // treeSet中大于等于nums[i]的最小值
                node = treeSet.ceiling(new Node(nums[i], -1));
                long border = nums[i] + t;
                while (node != null && node.val <= border) {
                    if (Math.abs(tmp.index - node.indexs.first()) <= k ||
                            Math.abs(tmp.index - node.indexs.last()) <= k) {
                        return true;
                    }
                    node = treeSet.ceiling(new Node(node.val + 1L, -1));
                }
                // treeSet中小于等于nums[i]的最大值
                node = treeSet.floor(new Node(nums[i], -1));
                border = nums[i] - t;
                while (node != null && node.val >= border) {
                    if (Math.abs(tmp.index - node.indexs.first()) <= k ||
                            Math.abs(tmp.index - node.indexs.last()) <= k) {
                        return true;
                    }
                    node = treeSet.floor(new Node(node.val - 1L, -1));
                }
            }
            if (treeSet.contains(new Node(nums[i], -1))) {
                node = treeSet.floor(new Node(nums[i], -1));
                if (node != null) {
                    node.indexs.add(i);
                }
            } else {
                treeSet.add(tmp);
            }
        }

        return false;
    }

    static class Node implements Comparable<Node> {
        long val;
        int index;
        TreeSet<Integer> indexs;

        public Node(long val, int index) {
            this.val = val;
            this.index = index;
            indexs = new TreeSet<>();
            indexs.add(index);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Long.hashCode(this.val);
        }

        @Override
        public int compareTo(Node o) {
            long res = this.val - o.val;
            if (res > 0) {
                return 1;
            } else if (res < 0) {
                return -1;
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate containsNearbyAlmostDuplicate = new ContainsNearbyAlmostDuplicate();
        int[] nums = ArrayUtil.randIntArray(1000000);
        long a = System.currentTimeMillis();
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums, 2, 2));
        System.out.println("cost" + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate2(nums, 2, 2));
        System.out.println("cost" + (System.currentTimeMillis() - a));
        int[] nums2 = {1,5,9,1,5,9};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums2, 3, 2));
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate2(nums2, 4, 2));
        int[] nums3 = {1,3,6,2};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums3, 1, 2));
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate2(nums3, 1, 2));
        int[] nums4 = {-1,-1};
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums4, 1, 0));
        Assert.assertTrue(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate2(nums4, 1, 0));

        int[] nums5 = {-1,2147483647};
        Assert.assertFalse(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate(nums5, 1, 2147483647));
        Assert.assertFalse(containsNearbyAlmostDuplicate.containsNearbyAlmostDuplicate2(nums5, 1, 2147483647));

    }
}
