package com.theonecai.leetcode.list;

/**
 * leetcode 2
 * @Author: theonecai
 * @Date: Create in 2020/7/6 20:30
 * @Description:
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode left = l1;
        ListNode right = l2;
        ListNode node = null;
        ListNode result = null;
        ListNode current = null;
        int overflow = 0;
        int sum = 0;
        while (left != null && right != null) {
            sum = left.val + right.val + overflow;
            if (sum > 9) {
                overflow = 1;
                sum -= 10;
            } else {
                overflow = 0;
            }
            node =  new ListNode(sum);
            if (result == null) {
                result =  node;
                current = node;
            } else {
                current.next = node;
                current = current.next;
            }
            left = left.next;
            right = right.next;
        }
        if (left == null && right ==null) {
            if (overflow > 0) {
                current.next = new ListNode(overflow);
            }
        }  else {
            add(left, current, overflow);
            add(right, current, overflow);
        }


        return result;
    }

    private void add(ListNode node, ListNode currentResult, int overflow) {
        int sum = 0;
        while (node != null) {
            sum = node.val + overflow;
            if (sum > 9) {
                overflow = 1;
                sum -= 10;
            } else {
                overflow = 0;
            }
            currentResult.next =  new ListNode(sum);
            currentResult = currentResult.next;
            node = node.next;
            if (node == null && overflow > 0) {
                currentResult.next =  new ListNode(overflow);
            }
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // 991+99 = 1090
        AddTwoNumbers add = new AddTwoNumbers();
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(9);
        list2.next.next = new ListNode(9);
        ListNode list = new ListNode(9);
        list.next = new ListNode(9);
        ListNode result = add.addTwoNumbers(list, list2);
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }

        System.out.println();
        list2 = new ListNode(5);
        list = new ListNode(5);
        result = add.addTwoNumbers(list, list2);
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }

    }
}
