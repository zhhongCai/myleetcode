package com.theonecai.leetcode.list;

/**
 * 83
 * @Author: theonecai
 * @Date: Create in 2021/3/26 20:33
 * @Description:
 */
public class DeleteDuplicatesSimple {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = null;
        ListNode tail = null;
        ListNode current = head;
        ListNode pre = null;
        while (current != null) {
            pre = current;
            // 找到第一个唯一数
            while (current != null && current.next != null && current.val == current.next.val) {
                current = current.next;
            }
            if (pre == current) {
                if (h == null) {
                    h = current;
                } else {
                    tail.next = current;
                }
                tail = current;
                current = current.next;
                tail.next = null;
            }
        }

        return h;
    }

    public static void main(String[] args) {
        DeleteDuplicatesSimple deleteDuplicates = new DeleteDuplicatesSimple();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(3);
        node.next.next.next.next.next = new ListNode(3);
        ListNode h = deleteDuplicates.deleteDuplicates(node);
        print(h);
    }

    private static void print(ListNode h) {
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
