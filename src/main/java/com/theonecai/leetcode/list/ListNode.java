package com.theonecai.leetcode.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2021/3/26 20:52
 * @Description:
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode from(int[] nums) {
        ListNode h = new ListNode(-1);
        ListNode n = h;
        for (int num : nums) {
            n.next = new ListNode(num);
            n = n.next;
        }
        return h.next;
    }
    public static List<Integer> from(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }
}
