package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.TreeSet;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/24 18:55
 * @Description:
 */
public class FindLatestStep {

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
        FindLatestStep step = new FindLatestStep();

        int[] nums3 = {3,1,5,2,4};
        Assert.assertEquals(-1, step.findLatestStep(nums3, 2));
        int[] nums = {3,5,1,2,4};
        Assert.assertEquals(-1, step.findLatestStep(nums, 2));
        Assert.assertEquals(4, step.findLatestStep(nums, 1));
        int[] nums2 = {1};
        Assert.assertEquals(1, step.findLatestStep(nums2, 1));
    }
}
