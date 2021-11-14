package com.theonecai.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 5927
 */
public class ReverseEvenLengthGroups {

    public ListNode reverseEvenLengthGroups(ListNode head) {
        // 当前分组最大节点数量
        int count = 1;
        // 当前分组节点数量
        int c = 0;
        ListNode node = head;
        ListNode left = null;
        ListNode right = null;
        // 当前分组的前驱节点
        ListNode pre = null;
        while (node != null) {
            c++;
            if (left ==  null) {
                left = node;
            }
            right = node;
            if (c == count || node.next == null) {
                if (c % 2 == 0) {
                    reverse(pre, left, right);
                    // 翻转后left就是最后一个节点了
                    node = left;
                    pre = left;
                } else {
                    pre = right;
                }

                left = null;
                c = 0;
                count++;
            }
            node = node.next;
        }

        return head;
    }

    /**
     * 翻转left到right的节点，并接到pre后面
     * @param pre
     * @param left
     * @param right
     */
    private void reverse(ListNode pre, ListNode left, ListNode right) {
        ListNode d = new ListNode();
        d.next = left;
        ListNode n = left.next;
        left.next = right.next;
        while (d.next != right) {
            ListNode nn = n.next;
            n.next = d.next;
            d.next = n;
            n = nn;
        }
        pre.next = d.next;
    }

    private ListNode tail;
    public ListNode reverseEvenLengthGroups2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            if (pre != null) {
                pre.next = null;
            }
            list.add(node);
            pre = node;
            node = node.next;
        }
        int c = 2;
        head = list.get(0);
        int i = 1;
        tail = head;
        while (i < list.size()) {
            i = doReverse(list, i, c);
            c++;
        }

        return head;
    }

    private int doReverse(List<ListNode> list, int start, int count) {
        int e = Math.min(list.size() - 1, start + count - 1);
        if (count % 2 == 0 || e == list.size() - 1) {
            if ((e - start + 1) % 2 != 0) {
                ListNode d = tail;
                for (int i = start; i <= e; i++) {
                    d.next = list.get(i);
                    d = d.next;
                }
                tail = list.get(e);
                return list.size();
            }
            ListNode d = tail;
            for (int i = e;  i >= start; i--) {
                d.next = list.get(i);
                d = d.next;
            }
            tail = list.get(start);
        } else {
            ListNode d = tail;
            for (int i = start; i <= e; i++) {
                d.next = list.get(i);
                d = d.next;
            }
            tail = list.get(e);
        }

        return start + count;
    }

    public static void main(String[] args) {
        ReverseEvenLengthGroups groups = new ReverseEvenLengthGroups();
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6,7,8,9,10,11}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6,7,8,9,10}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6,7,8,9}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6,7,8}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6,7}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5,6}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4, 5}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3, 4}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2, 3}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1, 2}))));
        System.out.println(ListNode.from(groups.reverseEvenLengthGroups(ListNode.from(new int[]{1}))));
    }
}
