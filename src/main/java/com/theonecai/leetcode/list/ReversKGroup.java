package com.theonecai.leetcode.list;

/**
 * leetcode 25
 * @Author: theonecai
 * @Date: Create in 2020/7/22 21:18
 * @Description:
 */
public class ReversKGroup {
    private ListNode reversedTail;

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        int i = 1;
        reversedTail = head;
        ListNode node = null;
        ListNode preTail = null;
        while (reversedTail != null) {
            preTail = reversedTail;
            if (i == 1) {
                node = reverse(head, 1, k);
                head = node;
            } else {
                node = reverse(reversedTail.next, 1, k);
                preTail.next = node;
            }
            i++;
        }

        return head;
    }

    public ListNode reverse(ListNode head, int m, int n) {
        if (head == null || head.next ==null) {
            reversedTail = null;
            return head;
        }

        int i = 1;
        ListNode tmpHead = new ListNode(-1);
        ListNode tmpTail = null;
        ListNode mPre = null;
        ListNode current = head;
        ListNode next = null;
        while (i < n) {
            if (i == m - 1) {
                mPre = current;
            }
            if (i >= m) {
                if (tmpTail == null) {
                    tmpTail = current;
                }
                next = current.next;
                insertFirst(tmpHead, current);
            } else {
                next = current.next;
            }
            i++;
            current = next;
            if (next == null) {
                reversedTail = null;
                head = reverseList(tmpHead, tmpTail);
                return head;
            }
        }

        ListNode nNext = current.next;
        reversedTail = tmpTail;
        insertFirst(tmpHead, current);
        tmpTail.next = nNext;

        if (mPre == null) {
            return tmpHead.next;
        } else {
            mPre.next = tmpHead.next;
        }

        return head;
    }

    private ListNode reverseList(ListNode tmpHead, ListNode tmpTail) {
        ListNode current;
        ListNode next;
        ListNode tHead = new ListNode(-1);
        current = tmpHead.next;
        ListNode tTail = current;
        while (current != tmpTail) {
            next = current.next;
            insertFirst(tHead, current);
            current = next;
        }
        insertFirst(tHead, current);
        tTail.next = null;
        return tHead.next;
    }

    private void insertFirst(ListNode tmpHead, ListNode node) {
        if (tmpHead.next == null) {
            tmpHead.next = node;
            return;
        }

        node.next = tmpHead.next;
        tmpHead.next = node;
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ReversKGroup reversKGroup = new ReversKGroup();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        root.next.next.next.next.next.next.next = new ListNode(8);
        root.next.next.next.next.next.next.next.next = new ListNode(9);
//        root.next.next.next.next.next.next.next.next.next = new ListNode(10);

        ListNode node = reversKGroup.reverseKGroup(root, 9);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");

        root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        root.next.next.next.next.next.next.next = new ListNode(8);
        root.next.next.next.next.next.next.next.next = new ListNode(9);
//        root.next.next.next.next.next.next.next.next.next = new ListNode(10);
        node = reversKGroup.reverseKGroup(root, 10);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }

}
