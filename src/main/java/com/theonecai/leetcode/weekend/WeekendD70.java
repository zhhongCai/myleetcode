package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: theonecai
 * @Date: Create in 2022/01/22 22:24
 * @Description:
 */
public class WeekendD70 {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int ans = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            ans += cost[i];
            i--;
            if (i >= 0) {
                ans += cost[i];
            }
            i--;
        }
        return ans;
    }

    public int numberOfArrays(int[] differences, int lower, int upper) {
        int left = lower;
        int right = upper;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int r = check(differences, mid, lower, upper);
            if (r == 0){
                right = mid;
            } else if (r < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int first = left;
        left = lower;
        right = upper;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int r = check(differences, mid, lower, upper);
            if (r <= 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (check(differences, left, lower, upper) == 0) {
            left++;
        }
        return left - first;
    }

    private int check(int[] differences, int first, int lower, int upper) {
        int a = first;
        if (a < lower) {
            return -1;
        }
        if (a > upper) {
            return 1;
        }
        for (int difference : differences) {
            a += difference;
            if (a < lower) {
                return -1;
            }
            if (a > upper) {
                return 1;
            }
        }

        return 0;
    }

    private static class Item implements Comparable<Item> {
        int step;
        int price;
        int row;
        int col;

        public Item(int step, int price, int row, int col) {
            this.step = step;
            this.price = price;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Item o) {
            int r = this.step - o.step;
            if (r == 0) {
                r = this.price - o.price;
                if (r == 0) {
                    r = this.row - o.row;
                    if (r == 0) {
                        r = this.col - o.col;
                    }
                }
            }
            return r;
        }
    }


    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        PriorityQueue<Item> heap = new PriorityQueue<>(Comparator.reverseOrder());
        boolean[][] seen = new boolean[m][n];
        int low = pricing[0];
        int high =pricing[1];
        Queue<int[]> queue = new LinkedList<>();
        seen[start[0]][start[1]] = true;
        queue.add(start);
        int p = grid[start[0]][start[1]];
        if (p > 1 && low <= p && p <= high) {
            heap.add(new Item(0, p, start[0], start[1]));
        }
        int[][] directs = new int[][] {
                {-1,0},
                {0,1},
                {1,0},
                {0,-1},
        };
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int[] d : directs) {
                    int r = cur[0] + d[0];
                    int c = cur[1] + d[1];
                    if (0 <= r && r < m && 0 <= c && c < n && grid[r][c] > 0 && !seen[r][c]) {
                        seen[r][c] = true;
                        queue.add(new int[]{r, c});
                        p = grid[r][c];
                        if (p > 1 && low <= p && p <= high) {
                            Item it = new Item(step + 1, p, r, c);
                            if (heap.size() < k) {
                                heap.add(it);
                            } else {
                                if (heap.peek().compareTo(it) > 0) {
                                    heap.poll();
                                    heap.add(it);
                                }
                            }

                        }
                    }
                }
            }
            step++;
        }

        List<List<Integer>> list = new ArrayList<>(heap.size());
        Item[] items = new Item[heap.size()];
        int i = 0;
        while (heap.size() > 0) {
            items[i++] = heap.poll();
        }
        for (int j = i - 1; j >= 0 ; j--) {
            List<Integer> lt = new ArrayList<>();
            lt.add(items[j].row);
            lt.add(items[j].col);
            list.add(lt);
        }
        return list;
    }

    public int numberOfWays(String corridor) {
        int s = 0;
        int n = corridor.length();
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                s++;
            }
        }
        if (s % 2 == 1) {
            return 0;
        }
        if (s == 2) {
            return 1;
        }
        int ans = 0;

        return ans;
    }

    public static void main(String[] args) {
        WeekendD70 weekend = new WeekendD70();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {
        List<List<Integer>> list = highestRankedKItems(new int[][]{{1, 2, 0, 1}, {1, 3, 0, 1}, {0, 2, 5, 1}}, new int[]{2, 5}, new int[]{0, 0}, 3);
        for (List<Integer> i : list) {
            System.out.println(i);
        }
        System.out.println();
        list = highestRankedKItems(new int[][]{{1,2,0,1},{1,3,3,1},{    0,2,5,1}}, new int[]{2, 3}, new int[]{2, 3}, 2);
        for (List<Integer> i : list) {
            System.out.println(i);
        }
        System.out.println();

        list = highestRankedKItems(new int[][]{{1,1,1},{0,0,1},{2,3,4}}, new int[]{2, 3}, new int[]{0, 0}, 3);
        for (List<Integer> i : list) {
            System.out.println(i);
        }
        System.out.println();
    }

    private void test2() {
        Assert.assertEquals(0, numberOfArrays(new int[]{4,-7,2}, 3, 6));
        Assert.assertEquals(4, numberOfArrays(new int[]{3,-4,5,1,-2}, -4, 5));
        Assert.assertEquals(2, numberOfArrays(new int[]{1,-3,4}, 1, 6));

    }

    private void test() {
    }
}
