package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Author: theonecai
 * @Date: Create in 2021/09/19 10:24
 * @Description:
 */
public class Weekend259 {
    public int finalValueAfterOperations(String[] operations) {
        int res = 0;
        for (String operation : operations) {
            if ("++X".equals(operation) || "X++".equals(operation)) {
                res++;
            } else if ("--X".equals(operation) || "X--".equals(operation)) {
                res--;
            }

        }
        return res;
    }

    public int sumOfBeauties2(int[] nums) {
        int res = 0;
        int n = nums.length;
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        left.put(nums[0], 1);
        for (int i = 1; i < n; i++) {
            right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 1; i < n - 1; i++) {
            int c = right.getOrDefault(nums[i], 0);
            if (c <= 1) {
                right.remove(nums[i]);
            } else {
                right.put(nums[i], c - 1);
            }
            int v = nums[i - 1] < nums[i] && nums[i] < nums[i + 1] ? 1 : 0;
            if (left.ceilingKey(nums[i]) == null && right.floorKey(nums[i]) == null) {
                v = 2;
            }
            res += v;
            left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);

        }

        return res;
    }

    public int sumOfBeauties(int[] nums) {
        int res = 0;
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = nums[0];
        right[n - 1] = nums[n - 1];
        for (int i = 1, j = n - 2; i < n; i++, j--) {
            left[i] = Math.max(left[i - 1], nums[i]);
            right[j] = Math.min(right[j + 1], nums[j]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (left[i - 1] < nums[i] && nums[i] < right[i + 1]) {
                res += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                res += 1;
            }
        }

        return res;
    }

    class DetectSquares {

        private int[][] points;

        private int[][] direct = new int[][]{
                {-1, 1},
                {1, 1},
                {1, -1},
                {-1, -1},
        };

        public DetectSquares() {
            points = new int[1001][1001];
        }

        public void add(int[] point) {
            points[point[0]][point[1]]++;
        }

        public int count(int[] point) {
            int res = 0;
            for (int[] d : direct) {
                res += countDirect(d, point);
            }
            return res;
        }
        private int countDirect(int[] d, int[] point) {
            int res = 0;
            int x = point[0];
            int y = point[1];
            int xAdd = d[0];
            int yAdd = d[1];
            int len = maxLen(d, point);
            int i = 1;
            while (i <= len) {
                int hx = x + xAdd * i;
                int hy = y;
                int vx = x;
                int vy = y + yAdd * i;
                int cx = hx;
                int cy = vy;
                res += points[hx][hy] * points[vx][vy] * points[cx][cy];
                i++;
            }

            return res;
        }

        private int maxLen(int[] d, int[] point) {
            if (d[0] == -1 && d[1] == 1) {
                return Math.min(point[0], points.length - point[1] - 1);
            }
            if (d[0] == 1 && d[1] == 1) {
                return  Math.min(points.length - point[0] - 1, points.length - point[1] - 1);
            }
            if (d[0] == 1 && d[1] == -1) {
                return Math.min(points.length - point[0] - 1, point[1]);
            }
            if (d[0] == -1 && d[1] == -1) {
                return Math.min(point[0], point[1]);
            }
            return 0;
        }
    }




    public static void main(String[] args) {
        Weekend259 weekend = new Weekend259();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3,10});
        detectSquares.add(new int[]{11,2});
        detectSquares.add(new int[]{3,2 });
        Assert.assertEquals(1, detectSquares.count(new int[]{11,10}));
        Assert.assertEquals(0, detectSquares.count(new int[]{14,8}));
        detectSquares.add(new int[]{11 ,2});

        Assert.assertEquals(2, detectSquares.count(new int[]{11,10}));
    }

    private void test2() {
        Assert.assertEquals(14, sumOfBeauties(new int[]{1,2,3,4,5,7,8,9,10}));
        Assert.assertEquals(2, sumOfBeauties(new int[]{1,2,3}));
        Assert.assertEquals(1, sumOfBeauties(new int[]{2,4,6,4}));
        Assert.assertEquals(0, sumOfBeauties(new int[]{3,2,1}));
    }

    private void test() {
    }
}
