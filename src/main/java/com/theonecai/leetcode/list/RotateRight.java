package com.theonecai.leetcode.list;

/**
 * leetcode 61
 * @Author: theonecai
 * @Date: Create in 2020/7/16 22:12
 * @Description:
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null|| head.next == null) {
            return head;
        }
        ListNode root = head;
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }

        if (k % size == 0) {
            return root;
        }

        tail.next = root;
        int shiftK = size - k % size;
        while (shiftK > 0) {
            tail = tail.next;
            shiftK--;
        }
        root = tail.next;
        tail.next = null;

        return root;
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        RotateRight rotateRight = new RotateRight();
        ListNode root = null;

        for (int i = 0; i < 20; i++) {
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

            ListNode node = rotateRight.rotateRight(root, i);
            while (node != null) {
                System.out.print(node.val + "->");
                node = node.next;
            }
            System.out.println("null");
        }
    }
}
