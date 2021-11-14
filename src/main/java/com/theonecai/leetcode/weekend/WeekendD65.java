package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/11/13 22:24
 * @Description:
 */
public class WeekendD65 {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        int[] count = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            count[word1.charAt(i) - 'a']++;
            count2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (Math.abs(count[i] - count2[i]) > 3) {
                return false;
            }
        }
        return true;
    }

    private static class Robot {

        private String[] dir = new String[]{"East","North","West","South"};
        private int[][] direct = new int[][]{
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
        };

        private int width;
        private int height;
        private int curX;
        private int curY;
        private int d;
        private int r;

        public Robot(int width, int height) {
            this.width = width;
            this.height = height;
            this.curX = 0;
            this.curY = 0;
            d = 0;
            r = 2 * (this.width +  this.height) - 4;
        }

        public void move(int num) {
            moveOneByOne(num);
            if (num == 0) {
                return;

            }
            num %= r;
            if (num == 0 ) {
                if ((curX == 0 && curY == 0) || (curX == this.width - 1 && curY == 0) ||
                        (curX == 0 && curY == this.height - 1) ||(curX == this.width && curY == this.height)) {
                    d += 3;
                    d %= 4;
                }
                return;
            }
            while (num > 0) {
                int[] dt = direct[d];
                int x = curX + dt[0];
                int y = curY + dt[1];
                if (inRange(x, y)) {
                    curX = x;
                    curY = y;
                    num--;
                } else {
                    d++;
                    d %= 4;
                }
            }
        }

        private int moveOneByOne(int num) {
            while (num > 0) {
                if (curX == this.width - 1 || curX == 0 || curY == this.height - 1 || curY == 0) {
                   break;
                }
                int[] dt = direct[d];
                int x = curX + dt[0];
                int y = curY + dt[1];
                if (inRange(x, y)) {
                    curX = x;
                    curY = y;
                    num--;
                } else {
                    d++;
                    d %= 4;
                }
            }
            return num;
        }

        public int[] getPos() {
            return new int[]{curX, curY};
        }

        public String getDir() {
            return dir[d];
        }

        private boolean inRange(int x, int y) {
            return 0 <= x && x < this.width && 0 <= y && y < this.height;
        }
    }

    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> {
            int res = a[0] - b[0];
            if (res == 0) {
                return a[1] - b[1];
            }
            return res;
        });
        int[] max = new int[items.length];
        max[0] = items[0][1];
        for (int i = 1; i < items.length; i++) {
            max[i] = Math.max(max[i - 1], items[i][1]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = find(items, queries[i]);
            if (idx != -1) {
                ans[i] = max[idx];
            }
        }

        return ans;
    }

    private int find(int[][] items, int price) {
        int left = 0;
        int right = items.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (items[mid][0] <= price) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (items[left][0] > price) {
            if (left > 0) {
                return left - 1;
            } else {
                return -1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        WeekendD65 weekend = new WeekendD65();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {
        Assert.assertArrayEquals(new int[]{0}, maximumBeauty(new int[][]{
                {10,1000},
        }, new int[]{
               5
        }));
        Assert.assertArrayEquals(new int[]{4}, maximumBeauty(new int[][]{
                {1,2},{1,2},{1,3},{1,4},
        }, new int[]{
                1
        }));
        Assert.assertArrayEquals(new int[]{2,4,5,5,6,6}, maximumBeauty(new int[][]{
                {1,2},{3,2},{2,4},{5,6},{3,5},
        }, new int[]{
                1,2,3,4,5,6
        }));
    }

    private void test2() {
        /**
         * ["Robot","getPos","getDir","move","move","move","move","move","move","move","move","getDir","getPos",
         * [[97,98],[],[],[],[],[],[],[],[],[],[],[],[],
         *
         * [null,[0,0],"East",null,null,null,null,null,null,null,null,"South",[0,0]
         */
        Robot robot2 = new Robot(97,98);
        Assert.assertArrayEquals(new int[]{0,0}, robot2.getPos());
        Assert.assertEquals("East", robot2.getDir());
        robot2.move(66392);
        robot2.move(83376);
        robot2.move(71796);
        robot2.move(57514);
        robot2.move(36284);
        robot2.move(69866);
        robot2.move(31652);
        robot2.move(32038);
        Assert.assertEquals("South", robot2.getDir());
        Assert.assertArrayEquals(new int[]{0,0}, robot2.getPos());


        Robot robot = new Robot(6,3);
        robot.move(2);
        robot.move(2);
        Assert.assertArrayEquals(new int[]{4,0}, robot.getPos());
        Assert.assertEquals("East", robot.getDir());
        robot.move(2);
        robot.move(1);
        robot.move(4);
        Assert.assertArrayEquals(new int[]{1,2}, robot.getPos());
        Assert.assertEquals("West", robot.getDir());
        robot.move(14);
        Assert.assertArrayEquals(new int[]{1,2}, robot.getPos());
        Assert.assertEquals("West", robot.getDir());
        robot.move(16);
        Assert.assertArrayEquals(new int[]{0,1}, robot.getPos());
        Assert.assertEquals("South", robot.getDir());
    }

    private void test() {
    }
}
