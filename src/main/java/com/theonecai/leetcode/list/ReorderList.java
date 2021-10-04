package com.theonecai.leetcode.list;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 143
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        Deque<ListNode> queue = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            queue.add(node);
            node = node.next;
        }
        ListNode first = queue.removeFirst();
        ListNode last = queue.isEmpty() ? null : queue.removeLast();
        while (!queue.isEmpty()) {
            last.next = first.next;
            first.next = last;
            node = queue.getLast();
            if (node != null) {
                node.next = null;
            }
            first = queue.removeFirst();
            if (queue.isEmpty()) {
                break;
            }
            last = queue.removeLast();
        }
    }

    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode head = ListNode.from(new int[]{1,2,3,4,5,6,7,8});
        reorderList.reorderList(head);
        Assert.assertArrayEquals(ListNode.from(head).stream().mapToInt(Integer::intValue).toArray(), new int[]{1,8,2,7,3,6,4,5});

        head = ListNode.from(new int[]{1,2,3,4,5,6,7});
        reorderList.reorderList(head);
        Assert.assertArrayEquals(ListNode.from(head).stream().mapToInt(Integer::intValue).toArray(), new int[]{1,7,2,6,3,5,4});
        head = ListNode.from(new int[]{1,2});
        reorderList.reorderList(head);
        Assert.assertArrayEquals(ListNode.from(head).stream().mapToInt(Integer::intValue).toArray(), new int[]{1,2});
        head = ListNode.from(new int[]{1});
        reorderList.reorderList(head);
        Assert.assertArrayEquals(ListNode.from(head).stream().mapToInt(Integer::intValue).toArray(), new int[]{1});
    }
}
