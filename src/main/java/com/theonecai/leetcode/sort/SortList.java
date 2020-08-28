package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 147
 * leetcode
 * @Author: zhenghong.cai
 * @Date: Create in 2020/8/25 19:38
 * @Description:
 */
public class SortList {

    public ListNode insertionSortList2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode tmp;
        while (head != null) {
            tmp = head;
            list.add(tmp);
            head = head.next;
            tmp.next = null;
        }
        if (list.size() == 0) {
            return head;
        }
        list.sort(Comparator.comparingInt(o -> o.val));
        head = list.get(0);
        tmp = head;
        ListNode next;
        for (int i = 1; i < list.size(); i++) {
            next = list.get(i);
            tmp.next = next;
            tmp = next;
        }

        return head;
    }


    public ListNode insertionSortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fastVisited = head;
        ListNode slowVisited = head;
        while (slowVisited.next != null && fastVisited.next != null && fastVisited.next.next != null) {
            fastVisited = fastVisited.next.next;
            slowVisited = slowVisited.next;
        }

        ListNode mid = slowVisited.next;
        slowVisited.next = null;

        ListNode left = mergeSort(head);
        ListNode right = mergeSort(mid);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while (left != null || right != null) {
            if (left != null && (right == null || left.val <= right.val)) {
                node.next = left;
                node = node.next;
                left = left.next;
                continue;
            }
            node.next = right;
            node = node.next;
            right = right.next;
        }
        return head.next;
    }


    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        /***
         * 1-2-3-4-5-6-9
         */
        SortList sortList = new SortList();
        ListNode head = null;
        ListNode node = null;
        int[] nums = ArrayUtil.randIntArray(1000000);
        for (int i = 0; i < nums.length; i++) {
            ListNode tmp = new ListNode(nums[i]);
            if (head == null) {
                head = tmp;
                node = tmp;
                continue;
            }
            node.next = tmp;
            node = node.next;
        }
        long a = System.currentTimeMillis();
        ListNode sorted = sortList.insertionSortList(head);
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        Arrays.sort(nums);
        int i = 0;
        while (sorted != null) {
            Assert.assertEquals(nums[i++], sorted.val);
            sorted = sorted.next;
        }
    }
}
