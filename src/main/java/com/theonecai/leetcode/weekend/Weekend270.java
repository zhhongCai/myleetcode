package com.theonecai.leetcode.weekend;


import com.theonecai.leetcode.list.ListNode;
import com.theonecai.leetcode.tree.TreeNode;
import jdk.nashorn.internal.parser.JSONParser;
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
 * @Date: Create in 2021/12/05 10:24
 * @Description:
 */
public class Weekend270 {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> list = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            dfs(digits, i, list);
        }

        int[] ans = list.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(ans);
        return ans;
    }

    private void dfs(int[] digits, int i, Set<Integer> list) {
        if (i >= digits.length) {
            return;
        }
        if (digits[i] == 0) {
            return;
        }
        for (int j = 0; j < digits.length; j++) {
            if (j == i) {
                continue;
            }
            for (int k = 0; k < digits.length; k++) {
                if (k == i || j == k) {
                    continue;
                }
                int a = digits[i] * 100 + digits[j] * 10 + digits[k];
                if (a %2 == 0) {
                    list.add(a);
                }
            }
        }
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        int n = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            n++;
        }
        int mid = n / 2;
        int i = 0;
        node = head;
        while (i < mid - 1) {
            node = node.next;
            i++;
        }
        if (node != null) {
            ListNode d = node.next;
            if (d != null) {
                node.next = d.next;
            } else {
                return null;
            }
        }

        return head;
    }

    private Map<Integer, Integer> pathMap;
    private Map<Integer, TreeNode> nodeMap;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        pathMap = new HashMap<>();
        nodeMap = new HashMap<>();
        dfsParent(root, -1);
        List<TreeNode> startList = new ArrayList<>();
        List<TreeNode> destList = new ArrayList<>();
        int cur = startValue;
        while (cur != -1) {
            startList.add(nodeMap.get(cur));
            cur = pathMap.get(cur);
        }
        cur = destValue;
        while (cur != -1) {
            destList.add(nodeMap.get(cur));
            cur = pathMap.get(cur);
        }
        int i = startList.size() - 1;
        int j = destList.size() - 1;
        for (; i >= 0 && j >= 0; i--,j--) {
            if (startList.get(i) != destList.get(j)) {
                break;
            }
        }
        i = i + 1;
        j = j + 1;
        StringBuilder path = new StringBuilder();
        for (int k = 0; k < i; k++) {
            path.append("U");
        }
        for (int k = j - 1; k >= 0; k--) {
            TreeNode p = destList.get(k + 1);
            TreeNode n = destList.get(k);
            if (p.left == n) {
                path.append("L");
            } else {
                path.append("R");
            }
        }
        return path.toString();
    }

    private void dfsParent(TreeNode root, int parent) {
        if (root == null) {
            return;
        }
        nodeMap.put(root.val, root);
        pathMap.put(root.val, parent);
        dfsParent(root.left, root.val);
        dfsParent(root.right, root.val);
    }


    public static void main(String[] args) {
        Weekend270 weekend = new Weekend270();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(4);
        Assert.assertEquals("L", getDirections(root, 1, 3));
        Assert.assertEquals("U", getDirections(root, 3, 1));
        Assert.assertEquals("UURL", getDirections(root, 3, 6));
        Assert.assertEquals("UULL", getDirections(root, 6, 3));
        Assert.assertEquals("LL", getDirections(root, 5, 3));
        Assert.assertEquals("RL", getDirections(root, 5, 6));

    }

    private void test2() {
        ListNode node = ListNode.from(new int[]{1,3,4,7,1,2,6});
        Assert.assertArrayEquals(new int[]{1,3,4,1,2,6}, ListNode.from(deleteMiddle(node)).stream().mapToInt(Integer::intValue).toArray());
        node = ListNode.from(new int[]{1,2,4,2,6});
        Assert.assertArrayEquals(new int[]{1,2,2,6}, ListNode.from(deleteMiddle(node)).stream().mapToInt(Integer::intValue).toArray());
        node = ListNode.from(new int[]{1,2});
        Assert.assertArrayEquals(new int[]{1}, ListNode.from(deleteMiddle(node)).stream().mapToInt(Integer::intValue).toArray());
        node = ListNode.from(new int[]{1});
        Assert.assertArrayEquals(new int[]{}, ListNode.from(deleteMiddle(node)).stream().mapToInt(Integer::intValue).toArray());
    }

    private void test() {
        Assert.assertArrayEquals(new int[]{102,120,130,132,210,230,302,310,312,320}, findEvenNumbers(new int[]{2,1,3,0}));
        Assert.assertArrayEquals(new int[]{222,228,282,288,822,828,882}, findEvenNumbers(new int[]{2,2,8,8,2}));
        Assert.assertArrayEquals(new int[]{}, findEvenNumbers(new int[]{3,7,5}));
        Assert.assertArrayEquals(new int[]{200}, findEvenNumbers(new int[]{0,2,0,0}));
        Assert.assertArrayEquals(new int[]{}, findEvenNumbers(new int[]{0,0,0}));

    }
}
