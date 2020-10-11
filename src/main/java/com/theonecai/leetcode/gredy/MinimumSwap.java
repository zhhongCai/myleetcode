package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * 1247
 * @Author: theonecai
 * @Date: Create in 2020/10/7 17:42
 * @Description:
 */
public class MinimumSwap {
    public int minimumSwap(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return 0;
        }
        int xyCount = 0;
        int yxCount = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xyCount++;
            } else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yxCount++;
            }
        }
        if ((xyCount + yxCount) % 2 == 1) {
            return -1;
        }
        if (xyCount % 2 != 0) {
            return xyCount / 2 + yxCount / 2 + 2;
        }
        return xyCount / 2 + yxCount / 2;
    }

    public static void main(String[] args) {
        MinimumSwap minimumSwap = new MinimumSwap();
        Assert.assertEquals(1, minimumSwap.minimumSwap("xx", "yy"));
        Assert.assertEquals(1, minimumSwap.minimumSwap("xx", "yy"));
    }
}
