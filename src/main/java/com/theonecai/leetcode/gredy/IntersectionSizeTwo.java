package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/18 20:41
 * @Description:
 */
public class IntersectionSizeTwo {

    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            int res = o2[0] - o1[0];
            if (res == 0) {
                return o1[1] - o2[1];
            }
            return res;
        });

        int min = -1;
        int min2 = -1;
        int[] current;
        int count = 0;
//        for (int[] interval : intervals) {
//            System.out.println(Arrays.toString(interval));
//        }
        for (int i = 0; i < intervals.length; i++) {
            current = intervals[i];
            if (!inRange(current, min) && !inRange(current, min2)) {
                count += 2;
                min = current[0];
                min2 = current[0] + 1;
            } else if (inRange(current, min) && !inRange(current, min2)) {
                count++;
                if (min == current[0]) {
                    min2 = min + 1;
                } else {
                    min2 = min;
                    min = current[0];
                }
            } else if (!inRange(current, min) && inRange(current, min2)) {
                count++;
                if (min2 == current[0]) {
                    min = current[0];
                    min2 = current[0] + 1;
                } else {
                    min = current[0];
                }
            }
//            System.out.println("min=" + min + ",min2=" + min2);
        }
        return count;
    }

    private boolean inRange(int[] range, int num) {
        return range[0] <= num && num <= range[1];
    }

    public static void main(String[] args) {
        IntersectionSizeTwo two = new IntersectionSizeTwo();
        Assert.assertEquals(4, two.intersectionSizeTwo(new int[][]{{6,21},{1,15},{15,20},{10,21},{0,7}}));
        Assert.assertEquals(7, two.intersectionSizeTwo(new int[][]{{1,3},{4,9},{0,10},{6,7},{1,2},{0,6},{7,9},{0,1},{2,5},{6,8}}));
        Assert.assertEquals(4, two.intersectionSizeTwo(new int[][]{{4,14},{6,17},{7,14},{14,21},{4,7}}));
        Assert.assertEquals(4, two.intersectionSizeTwo(new int[][]{{8,9},{4,21},{3,19},{5,9},{1,5}}));
        Assert.assertEquals(5, two.intersectionSizeTwo(new int[][]{{1, 2},{2, 3},{2, 4},{4, 5}}));
        Assert.assertEquals(3, two.intersectionSizeTwo(new int[][]{{1, 3},{1, 4},{2, 5},{3, 5}}));
    }
}
