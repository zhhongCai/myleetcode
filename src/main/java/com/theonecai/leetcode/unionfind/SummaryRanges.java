package com.theonecai.leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * leetcode 352
 */
class SummaryRanges {

    private Set<Integer> set;
    private int[] parent;

    public SummaryRanges() {
        set = new TreeSet<>();
        parent = new int[10002];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public void addNum(int val) {
        if (parent[val] != val) {
            return;
        }
        set.add(val);
        parent[val] = val + 1;
        findParent(val);
    }

    private int findParent(int val) {
        int x = val;
        while (parent[x] != x) {
            x = parent[x];
        }
        parent[val] = x;
        return  x;
    }

    public int[][] getIntervals() {
        List<int[]> list = new ArrayList<>();
        int[] pre = null;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            int num = it.next();
            if (pre != null && pre[0] <= num && num <= pre[1]) {
                it.remove();
                continue;
            }
            int[] range = new int[]{num, findParent(num) - 1};
            list.add(range);
            pre = range;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        int[][] data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();

        summaryRanges.addNum(3);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();

        summaryRanges.addNum(2);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();

        summaryRanges.addNum(7);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();

        summaryRanges.addNum(5);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();

        summaryRanges.addNum(4);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();


        summaryRanges.addNum(6);
        data = summaryRanges.getIntervals();
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
        System.out.println();
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
