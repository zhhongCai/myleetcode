package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/23 09:53
 * @Description:
 */
public class Weekend203 {

    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Node> countMap = new HashMap<>();
        for (int j = 1; j < rounds.length; j++) {
            if (rounds[j - 1] < rounds[j]) {
                int k = (j == 1 ? rounds[j - 1] : rounds[j - 1] + 1);
                while (k <= rounds[j]) {
                    final int kk = k;
                    countMap.computeIfAbsent(kk, e -> new Node(kk, 0)).count++;
                    k++;
                }
            } else {
                int k = (j == 1 ? 0 : 1);
                int count = n - rounds[j - 1] + rounds[j];
                while (k <= count) {
                    final int kk = (rounds[j - 1] + k) % n;
                    countMap.computeIfAbsent(kk == 0 ? n : kk, e -> new Node(kk == 0 ? n : kk, 0)).count++;
                    k++;
                }
            }
        }

        List<Node> lists = new ArrayList<>(countMap.values());
        Collections.sort(lists);

        int c = -1;
        for (int j = 0; j < lists.size(); j++) {
            if (c == -1) {
                c = lists.get(j).count;
            }
            if (lists.get(j).count < c) {
                break;
            }
            result.add(lists.get(j).n);
        }

        return result;
    }

    static class Node implements Comparable<Node> {
        int n;
        int count;

        public Node(int n, int count) {
            this.n = n;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            int c = o.count - this.count;
            if (c == 0) {
                return this.n - o.n;
            }
            return c;
        }
    }

    public int maxCoins(int[] piles) {
        Arrays.sort(piles);

        int sum = 0;
        int count = piles.length / 3;
        int i = piles.length - 2;
        while (count > 0) {
            sum += piles[i];
            i -= 2;
            count--;
        }

        return sum;
    }

    public int stoneGameV(int[] stoneValue) {
        if (stoneValue.length == 1) {
            return 0;
        }

        long[] sums = new long[stoneValue.length];
        int j = 1;
        sums[0] = stoneValue[0];
        for (int i = 1; i < stoneValue.length; i++) {
            sums[j] = sums[j - 1] + stoneValue[i];
            j++;
        }
        int[][] results = new int[stoneValue.length][stoneValue.length];

        int result = divide(sums, 0, stoneValue.length - 1, results);
        return result;
    }

    private int divide(long[] sums, int start, int end, int[][] results) {
        if (end <= start) {
            return 0;
        }
        if (results[start][end] != 0) {
            return results[start][end];
        }
        long before = start > 0 ? sums[start - 1] : 0;
        if (start + 1 == end) {
            return (int)Math.min(sums[start] - before, sums[end] - sums[start]);
        }

        int leftSum = 0;
        int rightSum = 0;
        int mid = 0;
        int result = 0;
        for (int i = start; i < end; i++) {
            mid = i;
            leftSum = (int)(sums[mid] - before);
            rightSum = (int)(sums[end] - sums[mid]);
            int sum = Math.min(leftSum, rightSum);
            if (leftSum > rightSum) {
                sum += divide(sums, mid + 1, end, results);
            } else if (leftSum < rightSum){
                sum += divide(sums, start, mid, results);
            } else {
                sum += Math.max(divide(sums, start, mid, results),
                        divide(sums, mid + 1, end, results));
            }
            result = Math.max(sum, result);
        }
        results[start][end] = result;

        return result;
    }

    public int findLatestStep(int[] arr, int m) {
        int result = -1;
        TreeSet<Integer> set = new TreeSet<>();
        if (check(arr.length, m, set, 0)) {
            return arr.length;
        }
        int c = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--, c--) {
            if (check(arr.length, m, set, arr[i])) {
                result = c;
                break;
            }
        }

        return result;
    }

    private boolean check(int len, int m, TreeSet<Integer> set, int position) {
        if (position == 0 && m < len) {
            return false;
        }
        Integer higher = set.higher(position);
        higher = higher == null ? len + 1 : higher;
        Integer lower = set.lower(position);
        lower = lower == null ? 0 : lower;

        if (position != 0) {
            set.add(position);
        }

        return position - lower - 1 == m || higher - position - 1 == m;
    }


    public static void main(String[] args) {
        Weekend203 s = new Weekend203();
        int[] nums3 = {3,1,5,2,4};
        Assert.assertEquals(-1, s.findLatestStep(nums3, 2));
        int[] nums = {3,5,1,2,4};
        Assert.assertEquals(-1, s.findLatestStep(nums, 2));
        Assert.assertEquals(4, s.findLatestStep(nums, 1));
        int[] nums2 = {1};
        Assert.assertEquals(1, s.findLatestStep(nums2, 1));

        int[] rounds = {2,1,2,1,2,1,2,1,2};
        List<Integer> integers = s.mostVisited(2, rounds);
        System.out.println(integers);

        int[] piles = {2,4,1,2,7,8};
        Assert.assertEquals(9, s.maxCoins(piles));
        int[] piles2 = {2,4,5};
        Assert.assertEquals(4, s.maxCoins(piles2));
        int[] piles3 = {9,8,7,6,5,1,2,3,4};
        Assert.assertEquals(18, s.maxCoins(piles3));

        int[] stone = {6,2,3,4,5,5};
        Assert.assertEquals(18, s.stoneGameV(stone));

        int[] stone2 = {7,7,7,7,7,7,7};
        Assert.assertEquals(28, s.stoneGameV(stone2));

        int[] stone3 = {9,8,2,4,6,3,5,1,7};
        Assert.assertEquals(34, s.stoneGameV(stone3));

        int[] stone4 = {2,1,1};
        Assert.assertEquals(3, s.stoneGameV(stone4));

        int[] stone5 = {10,9,8,
                7,6,
                5,4,
                3,
                2,1};
        Assert.assertEquals(37, s.stoneGameV(stone5));


        int[] stone6 = {3,2,1,4};
        Assert.assertEquals(7, s.stoneGameV(stone6));

        /**
         * 68 75 25 50 =  218
         * 212
         *
         * 34 29 77 1 2 69
         *
         *
         */
        int[] stone7 = {68,75,25,50,34,29,77,1,2,69};
        Assert.assertEquals(304, s.stoneGameV(stone7));

        int[] stone8 = {98,77,24,49,6,12,2,44,51,96};
        Assert.assertEquals(330, s.stoneGameV(stone8));

        int[] stone9 = {35001,57669,113475,122475,216967,222154,299195,427007,576923,729179,805161,819971,871784,880988,905205,914661,919525,930853};
        Assert.assertEquals(7926980, s.stoneGameV(stone9));
    }
}
