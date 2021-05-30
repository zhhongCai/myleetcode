package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * 1819
 * @Author: theonecai
 * @Date: Create in 5/30/21 20:13
 * @Description:
 */
public class CountDifferentSubsequenceGCDs {

    private boolean[] f;
    private int max;

    public int countDifferentSubsequenceGCDs(int[] nums) {
        f = new boolean[200010];
        max = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            f[nums[i]] = true;
        }
        for (int i = 1; i <= max; i++) {
            if (check(i)) {
                res++;
            }
        }
        return res;
    }

    private boolean check(int x) {
        int t = 0;
        for (int i = x; i <= max; i += x) {
            if (f[i]) {
                if (t == 0) {
                    t = i / x;
                } else {
                    t = gcd(t, i / x);
                }
            }
        }
        return t == 1;
    }

    private int gcd(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    public static void main(String[] args) {
        CountDifferentSubsequenceGCDs gcds = new CountDifferentSubsequenceGCDs();
        Assert.assertEquals(5, gcds.countDifferentSubsequenceGCDs(new int[]{6,10,3}));
        Assert.assertEquals(7, gcds.countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}));
    }
}
