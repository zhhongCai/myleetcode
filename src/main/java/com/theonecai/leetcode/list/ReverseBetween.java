package com.theonecai.leetcode.list;

import java.util.Stack;

/**
 * leetcode 92
 * @Author: theonecai
 * @Date: Create in 2020/7/16 20:06
 * @Description:
 */
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next ==null) {
            return head;
        }

        Stack<ListNode> stack = new Stack<>();

        int i = 1;
        ListNode startPre = null;
        ListNode current = head;
        while (i < n) {
            if (i == m - 1) {
                startPre = current;
            }
            if (i >= m) {
                stack.push(current);
            }
            i++;
            current = current.next;
        }
        stack.push(current);

        ListNode endNext = current.next;

        if (startPre == null) {
            head = current;
        } else {
            current = startPre;
        }
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        current.next = endNext;

        return head;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null || head.next ==null) {
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
        }

        ListNode nNext = current.next;
        insertFirst(tmpHead, current);
        tmpTail.next = nNext;

        if (mPre == null) {
            return tmpHead.next;
        } else {
            mPre.next = tmpHead.next;
        }

        return head;
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
        ReverseBetween reverseBetween = new ReverseBetween();
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        root.next.next.next.next.next.next.next = new ListNode(8);
        root.next.next.next.next.next.next.next.next = new ListNode(9);
        root.next.next.next.next.next.next.next.next.next = new ListNode(10);

        ListNode node = reverseBetween.reverseBetween(root, 2, 9);
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
        root.next.next.next.next.next.next.next.next.next = new ListNode(10);
        node = reverseBetween.reverseBetween2(root, 2, 10);
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }
}
