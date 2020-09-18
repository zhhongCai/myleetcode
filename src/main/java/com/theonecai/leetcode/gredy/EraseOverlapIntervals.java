package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 435
 * @Author: theonecai
 * @Date: Create in 2020/9/18 19:58
 * @Description:
 */
public class EraseOverlapIntervals {
    /**
     * 按起点排序，两个区间有重合，删除终点比较大的那个区间
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int count = 0;
        int[] pre = intervals[0];
        int[] current;
        for (int i = 1; i < intervals.length; i++) {
            current = intervals[i];
            if (current[0] >= pre[1]) {
                pre = current;
            } else {
                if (pre[1] > current[1]) {
                    pre = current;
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        EraseOverlapIntervals eraseOverlapIntervals = new EraseOverlapIntervals();
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        Assert.assertEquals(1, eraseOverlapIntervals.eraseOverlapIntervals(intervals));
        Assert.assertEquals(2, eraseOverlapIntervals.eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));
        Assert.assertEquals(0, eraseOverlapIntervals.eraseOverlapIntervals(new int[][]{{1,2},{2,3}}));
    }
}
