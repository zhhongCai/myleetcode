package com.theonecai.leetcode.map;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 352
 */
class SummaryRanges {

    private TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        if(map.isEmpty()){
            map.put(val, val);
            return;
        }

        Integer[] leftRange = new Integer[2];
        Integer[] rightRange = new Integer[2];

        leftRange[0] = map.floorKey(val);
        if(leftRange[0] != null){
            leftRange[1] = map.get(leftRange[0]);
        }
        rightRange[0] = map.ceilingKey(val);
        if(rightRange[0] != null){
            rightRange[1] = map.get(rightRange[0]);
        }

        if (inLeftRange(val, leftRange)) {
            if (inRightRange(val, rightRange)) {
                merge(leftRange, rightRange);
            }
            return;
        }
        if (inRightRange(val, rightRange)) {
            return;
        }
        map.put(val, val);
    }

    private void merge(Integer[] leftRange, Integer[] rightRange) {
        map.remove(leftRange[0]);
        map.remove(rightRange[0]);
        leftRange[1] = Math.max(leftRange[1], rightRange[1]);
        map.put(leftRange[0], leftRange[1]);
    }

    private boolean inLeftRange(int val, Integer[] leftRange) {
        if (leftRange[0] == null) {
            return false;
        }
        if(leftRange[1] + 1 == val){
            map.remove(leftRange[0]);
            leftRange[1] = val;
            map.put(leftRange[0], leftRange[1]);
            return true;
        }
        return val <= leftRange[1];
    }

    private boolean inRightRange(int val, Integer[] rightRange) {
        if (rightRange[0] == null) {
            return false;
        }
        if (rightRange[0] == val) {
            return true;
        }
        if (rightRange[0] == val + 1) {
            map.remove(rightRange[0]);
            rightRange[0] = val;
            map.put(rightRange[0], rightRange[1]);
            return true;
        }
        return false;
    }

    public int[][] getIntervals() {
        int[][] data = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet()){
            data[i][0] = e.getKey();
            data[i][1] = e.getValue();
            i++;
        }
        return data;
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
