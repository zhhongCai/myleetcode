package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2021/06/20 10:24                                                      O
 * @Description:
 */
public class Weekend246 {

    public String largestOddNumber(String num) {
        int end = - 1;
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                end = i;
                break;
            }
        }
        if (end == -1) {
            return "";
        }
        return num.substring(0, end + 1);
    }

    public int numberOfRounds(String startTime, String finishTime) {

        if (startTime.compareTo(finishTime) > 0) {
            return calc(startTime, "23:60") + calc("00:00", finishTime);
        }
        return calc(startTime, finishTime);
    }

    private int calc(String startTime, String finishTime) {
        int startHour = Integer.parseInt(startTime.split(":")[0]);
        int startMin = Integer.parseInt(startTime.split(":")[1]);
        int finishHour = Integer.parseInt(finishTime.split(":")[0]);
        int finishtMin = Integer.parseInt(finishTime.split(":")[1]);

        if (startHour == finishHour) {
            return calcMin(startMin, finishtMin);
        } else {
            int res = (finishHour - 1 - startHour) * 4;
            res += calcMin(0, finishtMin) + calcMin(startMin, 60);
            return res;
        }
    }

    private int calcMin(int startMin, int endMin) {
        int c = 0;
        if (startMin > 0) {
            c++;
        }
        if (startMin > 15) {
            c++;
        }
        if (startMin > 30) {
            c++;
        }
        if (startMin > 45) {
            c++;
        }
        if (endMin < 60) {
            c++;
        }
        if (endMin < 45) {
            c++;
        }
        if (endMin < 30) {
            c++;
        }
        if (endMin < 15) {
            c++;
        }

        return 4 - Math.min(c, 4);
    }

    int m;
    int n;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        m = grid1.length;
        n = grid1[0].length;
        Map<Integer, Set<Integer>> land = new HashMap<>();
        Unionfind unionfind = new Unionfind(m * n, true);
        int[][] direction = new int[][]{
                {0,-1},
                {-1,0},
                {0,1},
                {1,0},
        };
        boolean allZero = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid2[i][j] == 0) {
                    continue;
                }
                allZero = false;
                int idx = i * n + j;
                for (int[] d : direction) {
                    int r = i + d[0];
                    int c = j + d[1];
                    if (inRange(r, c) && grid2[r][c] == 1) {
                        unionfind.union(idx, r * n + c);
                    }
                }
            }
        }
        if (allZero) {
            return 0;
        }
        for (int i = 0; i < unionfind.parent.length; i++) {

            int r = i / n;
            int c = i % n;
            if (grid2[r][c] == 0) {
                continue;
            }
            int p = unionfind.findParent(unionfind.parent[i]);
            if (!land.containsKey(p)) {
                land.put(p, new HashSet<>());
            }
            land.get(p).add(i);
        }

        int count = 0;
        for (Set<Integer> value : land.values()) {
            if (check(value, grid1)) {
                count++;
            }
        }
        return count;
    }

    private boolean check(Set<Integer> value, int[][] grid1) {
        for (Integer idx : value) {
            int r = idx / n;
            int c = idx % n;
            if (grid1[r][c] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean inRange(int r, int c) {
        return r >= 0 && r < m && c >=0 && c < n;
    }


    private static class Unionfind {

        private int[] parent;

        public Unionfind(int size, boolean defaultInit) {
            this.parent = new int[size];
            if (defaultInit) {
                initParent();
            }
        }

        public int[] getParent() {
            return this.parent;
        }

        public void initParent() {
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
        }

        public int findParent(int x) {
            int i = x;
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public void union(int x, int y) {
            int xParent = findParent(x);
            int yParent = findParent(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
            }
        }

        /**
         * x,y是否连通
         * @param x
         * @param y
         * @return
         */
        public boolean isConnected(int x, int y) {
            return findParent(x) == findParent(y);
        }
    }




    public static void main(String[] args) {
        Weekend246 weekend = new Weekend246();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(3, this.countSubIslands(new int[][]{
                {1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}
        }, new int[][]{
                {1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}
        }));
        Assert.assertEquals(2, this.countSubIslands(new int[][]{
                {1,0,1,0,1},{1,1,1,1,1},{0,0,0,0,0},{1,1,1,1,1},{1,0,1,0,1}
        }, new int[][]{
                {0,0,0,0,0},{1,1,1,1,1},{0,1,0,1,0},{0,1,0,1,0},{1,0,0,0,1}
        }));
    }

    private void test2() {
        Assert.assertEquals(55, this.numberOfRounds("04:54","18:51"));
        Assert.assertEquals(0, this.numberOfRounds("23:46", "00:01"));
        Assert.assertEquals(0, this.numberOfRounds("00:01", "00:16"));
        Assert.assertEquals(1, this.numberOfRounds("12:01", "12:44"));
        Assert.assertEquals(95, this.numberOfRounds("00:00", "23:59"));
        Assert.assertEquals(40, this.numberOfRounds("20:00", "06:00"));
    }

    private void test() {
        Assert.assertEquals("", this.largestOddNumber("246"));
        Assert.assertEquals("2461", this.largestOddNumber("2461"));
        Assert.assertEquals("1", this.largestOddNumber("1462"));
    }
}
