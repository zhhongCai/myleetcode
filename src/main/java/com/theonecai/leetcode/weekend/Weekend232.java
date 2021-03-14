package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2021/03/14 10:24
 * @Description:
 */
public class Weekend232 {

    public boolean areAlmostEqual(String s1, String s2) {
        int count = 0;
        int[] charCount = new int[26];
        int[] charCount2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            charCount[s1.charAt(i) - 'a']++;
            charCount2[s2.charAt(i) - 'a']++;
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] != charCount2[i]) {
                return false;
            }
        }

        return count <= 2;
    }

    public int findCenter(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            map.put(edge[0], map.getOrDefault(edge[0], 0) + 1);
            map.put(edge[1], map.getOrDefault(edge[1], 0) + 1);
        }
        int u = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                u = entry.getKey();
            }
        }
        return u;
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<ClassInfo> maxHeap = new PriorityQueue<>(classes.length);
        double rate = 0.0;
        for (int[] aClass : classes) {
            maxHeap.add(new ClassInfo(aClass[0], aClass[1]));
            rate += (double) aClass[0] / aClass[1];
        }

        while (extraStudents-- > 0) {
            ClassInfo top = maxHeap.poll();
            rate += top.addRate;
            top.addPassStudent(1);
            maxHeap.add(top);
        }
//        for (ClassInfo classInfo : maxHeap) {
//            rate += (double) classInfo.pass / classInfo.total;
//        }
        return rate / classes.length;
    }

    private static class ClassInfo implements Comparable<ClassInfo> {
        private int pass;
        private int total;
        private double addRate;

        public ClassInfo(int pass, int total) {
            this.pass = pass;
            this.total = total;
            this.addRate = calcAddRate(pass, total);
        }

        private double calcAddRate(int pass, int total) {
            return (double)(pass + 1) / (total + 1) - (double)pass / total;
        }

        public void addPassStudent(int n) {
            this.pass += n;
            this.total += n;
            this.addRate = calcAddRate(pass, total);
        }

        @Override
        public int compareTo(ClassInfo o) {
            return this.addRate < o.addRate ? 1 : -1;
        }
    }

    public int maximumScore(int[] nums, int k) {
        int min = nums[k];
        for (int i = k - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
            } else {
                nums[i] = min;
            }
        }
        min = nums[k];
        for (int i = k + 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            } else {
                nums[i] = min;
            }
        }

        int max = Integer.MIN_VALUE;
        min = Math.min(nums[0], nums[nums.length - 1]);
        min = Math.min(nums[k], min);
        for (int num = nums[k], left = k, right = k; num >= min; num--) {
            while (left > 0 && nums[left - 1] >= num) {
                left--;
            }
            while (right < nums.length - 1 && nums[right + 1] >= num) {
                right++;
            }
            max = Math.max(max, num * (right - left + 1));
        }

        return max;
    }

    public static void main(String[] args) {
        Weekend232 weekend = new Weekend232();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        Assert.assertEquals(15, this.maximumScore(new int[]{1,4,3,7,4,5}, 3));
        Assert.assertEquals(20, this.maximumScore(new int[]{5,5,4,5,4,1,1,1}, 0));
    }

    private void test3() {
        Assert.assertEquals(0.53485, this.maxAverageRatio(new int[][] {
                {2,4},
                {3,9},
                {4,5},
                {2,10},
        }, 4), 0.00001);
        Assert.assertEquals(0.78333, this.maxAverageRatio(new int[][] {
                {1,2},
                {3,5},
                {2,2},
        }, 2), 0.00001);
    }

    private void test2() {
        Assert.assertEquals(2, this.findCenter(new int[][]{
                {1,2},
                {3,2},
                {4,2},
        }));
    }

    private void test() {
        Assert.assertTrue(this.areAlmostEqual("bank", "kanb"));
        Assert.assertFalse(this.areAlmostEqual("bank", "kanc"));
        Assert.assertTrue(this.areAlmostEqual("bank", "bank"));
    }
}
