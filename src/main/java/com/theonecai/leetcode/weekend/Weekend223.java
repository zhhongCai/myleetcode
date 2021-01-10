package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/01/10 10:24
 * @Description:
 */
public class Weekend223 {

    public int[] decode(int[] encoded, int first) {
        int[] arr = new int[encoded.length + 1];
        arr[0] = first;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = encoded[i - 1] ^ arr[i - 1];
        }

        return arr;
    }

    public ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int i = 1;
        ListNode front = null;
        ListNode node = head;
        while (node != null) {
            if (i == k) {
                front = node;
            }
            i++;
            node = node.next;
        }
        int end = i - k;
        i = 1;
        ListNode last = null;
        node = head;
        while (i <= end) {
            if (i == end) {
                last = node;
            }
            i++;
            node = node.next;
        }

        int tmp = front.val;
        front.val = last.val;
        last.val = tmp;

        return head;
    }


    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        if (source.length == 1) {
            return source[0] == target[0] ? 0 : 1;
        }
        int[] parent = new int[source.length];
        Map<Integer, List<Integer>> targetIndexMap = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            List<Integer> list = targetIndexMap.getOrDefault(target[i], new LinkedList<>());
            list.add(i);
            targetIndexMap.put(target[i], list);
        }
        for (int[] allowedSwap : allowedSwaps) {
            union(parent, allowedSwap[0], allowedSwap[1]);
        }
        int count = 0;
        for (int i = 0; i < source.length; i++) {
            if (!targetIndexMap.containsKey(source[i])) {
                count++;
                continue;
            }
            boolean found = false;
            List<Integer> list = targetIndexMap.get(source[i]);
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                Integer idx = it.next();
                if (findParent(parent, i) == findParent(parent, idx)) {
                    found = true;
                    it.remove();
                    break;
                }
            }

            if (!found) {
                count++;
            }
        }

        return count;
    }

    public int findParent(int[] parent, int x) {
        int i = x;
        while (parent[i] != i) {
            i = parent[i];
        }
        parent[x] = i;
        return i;
    }

    public void union(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);
        if (xParent != yParent) {
            parent[xParent] = yParent;
        }
    }

    public static void main(String[] args) {
        Weekend223 weekend223 = new Weekend223();
        weekend223.test();
        weekend223.test2();
        weekend223.test3();
        weekend223.test4();
    }

    private void test4() {
    }

    private void test3() {

        // []
        //[]
        //[[]]
        Assert.assertEquals(12, this.minimumHammingDistance(new int[]{41,37,51,100,25,33,90,49,65,87,11,18,15,18},
                new int[]{41,92,69,75,29,13,53,21,17,81,33,19,33,32}, new int[][]{
                {0,11},{5,9},{6,9},{5,7},{8,13},{4,8},{12,7},{8,2},{13,5},{0,7},{6,4},{8,9},{4,12},{6,1},{10,0},{10,2},{7,3},{11,10},{5,2},{11,1},{3,0},{8,5},{12,6},{2,1},{11,2},{4,9},{2,9},{10,6},{12,10},{4,13},{13,2},{11,9},{3,6},{0,4},{1,10},{5,11},{12,1},{10,4},{6,2},{10,7},{3,13},{4,5},{13,10},{4,7},{0,12},{9,10},{9,3},{0,5},{1,9},{5,10},{8,0},{12,11},{11,4},{7,9},{7,2},{13,9},{12,3},{8,6},{7,6},{8,12},{4,3},{7,13},{0,13},{2,0},{3,8},{8,1},{13,6},{1,4},{0,9},{2,3},{8,7},{4,2},{9,12}
        }));
        Assert.assertEquals(0, this.minimumHammingDistance(new int[]{5,1,2,4,3}, new int[]{1,5,4,2,3}, new int[][]{
                {0,4},{4,2},{1,3},{1,4}
        }));
        Assert.assertEquals(1, this.minimumHammingDistance(new int[]{1,2,3,4}, new int[]{2,1,4,5}, new int[][]{
                {0,1},{2,3}
        }));
        Assert.assertEquals(2, this.minimumHammingDistance(new int[]{1,2,3,4}, new int[]{1,3,2,4}, new int[][]{
        }));
    }

    private void test2() {
        ListNode a = this.swapNodes(new ListNode(1), 1);
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);
        ListNode head = this.swapNodes(h, 2);
        Assert.assertNotNull(h);
    }

    private void test() {

        System.out.println(Arrays.toString(this.decode(new int[]{1,2,3}, 1)));
        System.out.println(Arrays.toString(this.decode(new int[]{6,2,7,3}, 4)));
    }
}
