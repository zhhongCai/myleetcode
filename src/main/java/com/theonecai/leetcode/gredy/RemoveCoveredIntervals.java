package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1288
 * @Author: theonecai
 * @Date: Create in 2020/10/17 09:32
 * @Description:
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        // 按起点排序，起点相同按终点逆序
        Arrays.sort(intervals, ((o1, o2) -> {
            int res = o1[0] - o2[0];
            if (res == 0) {
                return o2[1] - o1[1];
            }
            return res;
        }));

        int count = 1;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (pre[1] < current[1]) {
                count++;
                pre = current;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        RemoveCoveredIntervals remove = new RemoveCoveredIntervals();
        Assert.assertEquals(2, remove.removeCoveredIntervals(new int[][]{
                {3,10},{4,10},{5,11}
        }));
        Assert.assertEquals(2, remove.removeCoveredIntervals(new int[][]{
                {1,4},{3,6},{2,8}
        }));
    }
}
