package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 452
 * @Author: theonecai
 * @Date: Create in 2020/9/18 20:00
 * @Description:
 */
public class FindMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length < 1) {
            return 0;
        }
        Arrays.sort(points, ((o1, o2) -> {
           int res = o1[0] - o2[0];
           if (res == 0) {
               return o1[1] - o2[1];
           }
           return res;
        }));

        int count = 1;
        int[] pre = points[0];
        // 多个重叠区间的范围
        int[] overlap = points[0];
        int[] current;
        for (int i = 1; i < points.length; i++) {
            current = points[i];
            if (pre[1] < current[0]) {
                count++;
                overlap = current;
            } else {
                if ((current[0] >= overlap[0] && current[0] <= overlap[1]) ||
                        (current[1] >= overlap[0] && current[1] <= overlap[1])) {
                    overlap[0] = Math.max(current[0], overlap[0]);
                    overlap[1] = Math.min(current[1], overlap[1]);
                } else {
                    count++;
                    overlap = current;
                }
            }
            pre = current;
        }

        return count;
    }

    public static void main(String[] args) {
        FindMinArrowShots findMinArrowShots = new FindMinArrowShots();
        /**
         * 1 6
         *  2   8
         *     7   12
         *        10 16
         */
        Assert.assertEquals(2, findMinArrowShots.findMinArrowShots(new int[][]{{12,16},{2,8},{1,6},{6,12}}));
        Assert.assertEquals(1, findMinArrowShots.findMinArrowShots(new int[][]{{12,16}}));
    }
}
