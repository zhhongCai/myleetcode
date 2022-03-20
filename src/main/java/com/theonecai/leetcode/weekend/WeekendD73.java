package com.theonecai.leetcode.weekend;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class WeekendD73 {

    public int mostFrequent(int[] nums, int key) {
        int n = nums.length;
        int[] count = new int[1001];
        for (int j = 0; j < n - 1; j++) {
            if (key == nums[j]) {
                count[nums[j + 1]]++;
            }
        }
        int max = 1;
        for (int j = 1; j < count.length; j++) {
            if (count[max] < count[j]) {
                max = j;
            }
        }
        return max;
    }

    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = map(mapping, nums[i]);
            arr[i][1] = i;
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[arr[i][1]];
        }
        return ans;
    }

    private int map(int[] mapping, int num) {
        if (num < 10) {
            return mapping[num];
        }
        int n = 0;
        int base = 1;
        while (num > 0) {
            n += base * mapping[num % 10];
            base *= 10;
            num /= 10;
        }
        return n;
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        int[] degree = new int[n];
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            degree[edge[1]]++;
            children[edge[0]].add(edge[1]);
        }

        List<TreeSet<Integer>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new TreeSet<>());
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (children[u].isEmpty()) {
                continue;
            }
            for (Integer v : children[u]) {
                degree[v]--;
                TreeSet<Integer> set = list.get(v);
                set.add(u);
                set.addAll(list.get(u));
                if (degree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>(n);
        for (TreeSet<Integer> set : list) {
            ans.add(new ArrayList<>(set));
        }

        return ans;
    }

    public static void main(String[] args) {
        WeekendD73 weekend = new WeekendD73();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {
        List<List<Integer>> list = getAncestors(8, new int[][]{
                {0,3},{0,4},{1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}
        });
        for (List<Integer> ints : list) {
            System.out.println(ints);
        }
        list = getAncestors(5, new int[][]{
                {0,1},{0,2},{0,3},{0,4},{1,2},{1,3},{1,4},{2,3},{2,4},{3,4}
        });
        for (List<Integer> ints : list) {
            System.out.println(ints);
        }
    }

    private void test2() {
        Assert.assertArrayEquals(new int[]{9,8,7,6,5,4,3,2,1,0}, sortJumbled(new int[]{9,8,7,6,5,4,3,2,1,0}, new int[]{0,1,2,3,4,5,6,7,8,9}));
        Assert.assertArrayEquals(new int[]{338,38,991}, sortJumbled(new int[]{8,9,4,0,2,1,3,5,7,6}, new int[]{991,338,38}));
        Assert.assertArrayEquals(new int[]{123,456,789}, sortJumbled(new int[]{0,1,2,3,4,5,6,7,8,9}, new int[]{789,456,123}));}

    private void test() {

    }
}
