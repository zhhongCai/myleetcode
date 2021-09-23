package com.theonecai.leetcode.doublepointer;

import com.theonecai.leetcode.list.ListNode;

/**
 * leetcode 86
 */
public class Partition {
    public ListNode partition(ListNode head, int x) {
        ListNode tmp = new ListNode(-1);
        tmp.next = head;

        ListNode firstPre = tmp;
        ListNode first = head;
        ListNode secondPre = tmp;
        ListNode second = head;
        while (first != null && first.val < x) {
            firstPre = first;
            first = first.next;
        }
        secondPre = firstPre;
        second = first;
        while (second != null) {
            while (second != null && second.val >= x) {
                secondPre = second;
                second = second.next;
            }
            if (second != null) {
                secondPre.next = second.next;
                firstPre.next = second;
                second.next = first;
                firstPre = second;
                second = secondPre.next;
            }
        }

        return tmp.next;
    }

    public static void main(String[] args) {
        Partition partition = new Partition();

        System.out.println(ListNode.from(partition.partition(ListNode.from(new int[]{}), 3)));
        System.out.println(ListNode.from(partition.partition(ListNode.from(new int[]{1,4,3,2,5,2}), 3)));
        System.out.println(ListNode.from(partition.partition(ListNode.from(new int[]{1, 2, 3, 4, 5, 6}), 6)));
        System.out.println(ListNode.from(partition.partition(ListNode.from(new int[]{6,5,4,3,2,1}), 1)));
    }
}
