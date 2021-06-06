package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 6/6/21 17:25
 * @Description:
 */
public class MinFlips {
    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public int minFlips(String s) {
        char[] target = new char[]{'0','1'};
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != target[i & 1]) {
                count++;
            }
        }
        int res = Math.min(count, n - count);
        if (res == 0) {
            return 0;
        }
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != target[i & 1]) {
                count--;
            }
            if (s.charAt((i + n) % n) != target[(i + n) & 1]){
                count++;
            }
            res = Math.min(res, Math.min(count, n - count));
        }

        return res;
    }

    public static void main(String[] args) {
        MinFlips minFlips = new MinFlips();
        Assert.assertEquals(5, minFlips.minFlips("10001100101000000"));
        Assert.assertEquals(2, minFlips.minFlips("01001001101"));
        Assert.assertEquals(2, minFlips.minFlips("1111"));
        Assert.assertEquals(1, minFlips.minFlips("11110"));
        Assert.assertEquals(2, minFlips.minFlips("111101"));
        Assert.assertEquals(0, minFlips.minFlips("10101"));
        Assert.assertEquals(2, minFlips.minFlips("111000"));
        Assert.assertEquals(0, minFlips.minFlips("010"));
        Assert.assertEquals(0, minFlips.minFlips("0101"));
        Assert.assertEquals(1, minFlips.minFlips("1110"));
    }
}
