package com.theonecai.leetcode.list;

/**
 * ms 02 0s5
 * @Author: theonecai
 * @Date: Create in 2021/3/26 20:54
 * @Description:
 */
public class AddTwoNumbers2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        ListNode first = l1;
        ListNode second = l2;
        int add = 0;
        int val = 0;
        while (first != null || second != null || add > 0) {
            val = 0;
            if (first != null) {
                val += first.val;
                first = first.next;
            }
            if (second != null) {
                val += second.val;
                second = second.next;
            }
            val += add;
            add = val / 10;
            val %= 10;
            tail.next = new ListNode(val);
            tail = tail.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers2 addTwoNumbers2 = new AddTwoNumbers2();
        ListNode two = new ListNode(8);
        ListNode one = new ListNode(8);
        ListNode list = addTwoNumbers2.addTwoNumbers(one, two);
        print(list);
    }

    private static void print(ListNode h) {
        while (h != null) {
            System.out.println(h.val);
            h = h.next;
        }
    }

}
