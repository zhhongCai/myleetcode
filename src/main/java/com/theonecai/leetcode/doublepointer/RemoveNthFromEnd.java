package com.theonecai.leetcode.doublepointer;

import com.theonecai.leetcode.list.ListNode;

/**
 * 19
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tmp = new ListNode(-1);
        tmp.next = head;

        ListNode first = tmp;
        ListNode second = tmp;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        ListNode pre = second;
        while (first != null) {
            first = first.next;
            pre = second;
            second = second.next;
        }
        ListNode nn = second.next;
        pre.next = nn;

        return tmp.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        ListNode h = removeNthFromEnd.removeNthFromEnd(node, 4);
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }
}
