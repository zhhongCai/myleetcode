package com.theonecai.leetcode.weekend;


import com.theonecai.leetcode.list.ListNode;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2021/11/14 10:24
 * @Description:
 */
public class Weekend267 {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int res = 0;
        int i = 0;
        while (true) {
            if (tickets[k] == 0) {
                break;
            }
            if (tickets[i%n] > 0) {
                tickets[i%n]--;
                res++;
            }
            i++;
        }
        return res;
    }

    private ListNode tail;
    public ListNode reverseEvenLengthGroups(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        ListNode pre = null;
        while (node != null) {
            if (pre != null) {
                pre.next = null;
            }
            list.add(node);
            pre = node;
            node = node.next;
        }
        int c = 2;
        head = list.get(0);
        int i = 1;
        tail = head;
        while (i < list.size()) {
            i = doReverse(list, i, c);
            c++;
        }

        return head;
    }

    private int doReverse(List<ListNode> list, int start, int count) {
        int e = Math.min(list.size() - 1, start + count - 1);
        if (count % 2 == 0 || e == list.size() - 1) {
            if ((e - start + 1) % 2 != 0) {
                ListNode d = tail;
                for (int i = start; i <= e; i++) {
                    d.next = list.get(i);
                    d = d.next;
                }
                tail = list.get(e);
                return list.size();
            }
            ListNode d = tail;
            for (int i = e;  i >= start; i--) {
                d.next = list.get(i);
                d = d.next;
            }
            tail = list.get(start);
        } else {
            ListNode d = tail;
            for (int i = start; i <= e; i++) {
                d.next = list.get(i);
                d = d.next;
            }
            tail = list.get(e);
        }

        return start + count;
    }

    public static void main(String[] args) {
        Weekend267 weekend = new Weekend267();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
    }

    private void test2() {
        Assert.assertArrayEquals(new int[]{1,3,2,5,4},
                ListNode.from(reverseEvenLengthGroups(ListNode.from(new int[]{1,2,3,4,5}))).stream().mapToInt(Integer::intValue).toArray());
    }

    private void test() {
        Assert.assertEquals(8, timeRequiredToBuy(new int[]{5,1,1,1}, 0));
    }
}
