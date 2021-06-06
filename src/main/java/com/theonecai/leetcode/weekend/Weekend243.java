package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2021/05/30 10:24
 * @Description:
 */
public class Weekend243 {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        String sum = "";
        for (int i = 0; i < firstWord.length(); i++) {
            sum += "" + (firstWord.charAt(i) - 'a');
        }
        long s = Long.parseLong(sum);
        sum = "";
        for (int i = 0; i < secondWord.length(); i++) {
            sum += "" + (secondWord.charAt(i) - 'a');
        }
        s += Long.parseLong(sum);
        sum = "";
        for (int i = 0; i < targetWord.length(); i++) {
            sum += "" + (targetWord.charAt(i) - 'a');
        }
        long s2 = Long.parseLong(sum);
        return s == s2;
    }

    public String maxValue(String n, int x) {
        boolean negative = n.charAt(0) == '-';
        char[] num = new char[n.length() + 1];
        int i = 0;
        if (negative) {
            num[i++] = '-';
            boolean inserted = false;
            for (int j = 1; j < n.length(); j++) {
                int c = n.charAt(j) - '0';
                if (x < c && !inserted) {
                    num[i++] = (char)('0' + x);
                    inserted = true;
                }
                num[i++] = n.charAt(j);
            }
            if (!inserted) {
                num[i++] = (char)('0' + x);
            }
        } else {
            boolean inserted = false;
            for (int j = 0; j < n.length(); j++) {
                int c = n.charAt(j) - '0';
                if (x > c && !inserted) {
                    num[i++] = (char)('0' + x);
                    inserted = true;
                }
                num[i++] = n.charAt(j);
            }
            if (!inserted) {
                num[i++] = (char)('0' + x);
            }
        }

        return new String(num);
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int[] res = new int[tasks.length];
        if (servers.length == 1) {
            return  res;
        }
        //int[] a; a[0]=权重,a[1]=index
        PriorityQueue<int[]> idle = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));
        // int[] a; a[0]=空闲起始时间,a[1]=index
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));

        for (int i = 0; i < servers.length; i++) {
            idle.add(new int[]{servers[i], i});
        }
        int idx = 0;
        int now = 0;
        for (int i = 0; i < tasks.length; i++) {
            now = Math.max(now, i);
            busyToIdle(servers, busy, idle, now);
            if (idle.isEmpty()) {
                now = busy.peek()[0];
                busyToIdle(servers, busy, idle, now);
            }
            int[] top = idle.poll();
            res[idx++] = top[1];
            busy.add(new int[]{now + tasks[i], top[1]});

        }

//        System.out.println(Arrays.toString(res));
        return res;
    }

    private void busyToIdle(int[] servers, PriorityQueue<int[]> busy, PriorityQueue<int[]> idle, int now) {
        while (!busy.isEmpty() && busy.peek()[0] <= now)  {
            int[] top = busy.poll();
            idle.add(new int[]{servers[top[1]], top[1]});
        }
    }



    public static void main(String[] args) {
        Weekend243 weekend = new Weekend243();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertArrayEquals(new int[]{2,2,0,2,1,2}, this.assignTasks(new int[]{3,3,2}, new int[]{1,2,3,2,1,2}));
        Assert.assertArrayEquals(new int[]{1,4,1,4,1,3,2}, this.assignTasks(new int[]{5,1,4,3,2}, new int[]{2,1,2,4,5,2,1}));
    }

    private void test2() {
        Assert.assertEquals("4699757879438632651173569913153377", this.maxValue("469975787943862651173569913153377", 3));
        Assert.assertEquals("-132233", this.maxValue("-13223", 3));
        Assert.assertEquals("-1323", this.maxValue("-132", 3));
        Assert.assertEquals("5156", this.maxValue("156", 5));
        Assert.assertEquals("91", this.maxValue("1", 9));
        Assert.assertEquals("999", this.maxValue("99", 9));
        Assert.assertEquals("-123", this.maxValue("-13", 2));
        Assert.assertEquals("-2987", this.maxValue("-987", 2));
    }

    private void test() {
        Assert.assertTrue(this.isSumEqual("acb", "cba", "cdb"));
        Assert.assertFalse(this.isSumEqual("aaa", "a", "aab"));
        Assert.assertTrue(this.isSumEqual("aaa", "a", "aaaa"));
    }
}
