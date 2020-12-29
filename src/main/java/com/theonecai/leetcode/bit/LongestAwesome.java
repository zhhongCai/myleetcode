package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 1542
 */
public class LongestAwesome {

    public int longestAwesome(String s) {
        int result = 0;
        int[] pre = new int[1 << 11];
        Arrays.fill(pre, -2);
        int status = 0;
        pre[0] = -1;
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            status ^= 1 << (ch - '0');
            if (Integer.bitCount(status) <= 1) {
                result = i + 1;
                pre[status] = i;
                continue;
            }
            if (pre[status] != -2) {
                result = Math.max(result, i - pre[status]);
            } else {
                pre[status] = i;
            }
            for (int j = 0; j < 10; j++) {
                int st = status ^ (1 << j);
                if (pre[st] != -2) {
                    result = Math.max(result, i - pre[st]);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        LongestAwesome longestAwesome = new LongestAwesome();
        Assert.assertEquals(5, longestAwesome.longestAwesome("11111"));
        Assert.assertEquals(5, longestAwesome.longestAwesome("11211"));
        Assert.assertEquals(7, longestAwesome.longestAwesome("1123311"));
        Assert.assertEquals(1, longestAwesome.longestAwesome("123456"));
        Assert.assertEquals(29, longestAwesome.longestAwesome("1212312312231233311111232221233456"));
    }
}
