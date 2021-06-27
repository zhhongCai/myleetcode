package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 5799
 * @Author: theonecai
 * @Date: Create in 6/27/21 16:59
 * @Description:
 */
public class WonderfulSubstrings {
    public long wonderfulSubstrings(String word) {
        long[] count = new long[(1 << 10) + 1];
        count[0] = 1;
        long res = 0;
        int mask = 0;
        for (int i = 0; i < word.length(); i++) {
            int k = word.charAt(i) - 'a';
            mask ^= (1 << k);
            res += count[mask];
            for (int j = 0; j < 10; j++) {
                int preMask = mask ^ (1 << j);
                res += count[preMask];
            }
            count[mask]++;
        }
        return res;
    }

    public static void main(String[] args) {
        WonderfulSubstrings wonderfulSubstrings = new WonderfulSubstrings();
        Assert.assertEquals(4, wonderfulSubstrings.wonderfulSubstrings("aba"));
        Assert.assertEquals(9, wonderfulSubstrings.wonderfulSubstrings("aabb"));
        Assert.assertEquals(2, wonderfulSubstrings.wonderfulSubstrings("he"));
        Assert.assertEquals(9, wonderfulSubstrings.wonderfulSubstrings("ababc"));
    }
}
