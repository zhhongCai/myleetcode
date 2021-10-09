package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * leetcode  414
 */
public class ThirdMax {
    public int thirdMax2(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (treeSet.contains(nums[i])) {
                continue;
            }
            treeSet.add(nums[i]);
            if (treeSet.size() > 3) {
                treeSet.remove(treeSet.first());
            }

        }
        if (treeSet.size() < 3) {
            return treeSet.last();
        }

        return treeSet.first();
    }
    public int thirdMax(int[] nums) {
        long[] ans = new long[3];
        Arrays.fill(ans, Long.MIN_VALUE);
        for (int num : nums) {
            addToAnsWhenHigher(ans, num);
        }
        if (ans[2] == Long.MIN_VALUE) {
            return (int)ans[0];
        }

        return (int)ans[2];
    }

    private void addToAnsWhenHigher(long[] ans, int num) {
        for (long n : ans) {
            if (n == num) {
                return;
            }
        }
        if (ans[2] < num) {
            ans[2] = num;
        }
        if (ans[1] < ans[2]) {
            swap(ans, 1, 2);
        }
        if (ans[0] < ans[1]) {
            swap(ans, 0, 1);
        }
    }

    private void swap(long[] ans, int i, int j) {
        long t = ans[i];
        ans[i] = ans[j];
        ans[j] = t;
    }

    public static void main(String[] args) {
        ThirdMax thirdMax = new ThirdMax();
        Assert.assertEquals(2, thirdMax.thirdMax(new int[]{1,2,2,5,3,5}));
    }
}
