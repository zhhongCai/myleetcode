package com.theonecai.leetcode.list;

/**
 * leetcode 725
 */
public class SplitListToParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode[] res = new ListNode[k];
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        int c = n / k;
        int r = n % k;
        int i = 0;
        node = head;
        while (node != null) {
            res[i++] = node;
            int j = c;
            while (j > 1 && node != null) {
                node = node.next;
                j--;
            }
            if (c != 0 && r > 0 && node != null) {
                node = node.next;
                r--;
            }
            if (node != null) {
                ListNode tmp = node;
                node = tmp.next;
                tmp.next = null;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SplitListToParts splitListToParts = new SplitListToParts();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode[] list = splitListToParts.splitListToParts(node, 5);
        for (ListNode n : list) {
            while(n != null) {
                System.out.print(n.val + "->");
                n = n.next;
            }
            System.out.println("-");
        }
    }
}
