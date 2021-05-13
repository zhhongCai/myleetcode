package com.theonecai.leetcode.dfs;

import org.junit.Assert;

/**
 * leetcode 87
 * @Author: theonecai
 * @Date: Create in 4/28/21 20:49
 * @Description:
 */
public class IsScramble {

    public boolean isScramble2(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        // 长度不一致
        if (n != m) {
            return false;
        }

        // 相同
        if (s1.equals(s2)) {
            return true;
        }

        // 含相同个数的字符
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            boolean res = (isScramble2(s1.substring(0, i), s2.substring(0, i)) && isScramble2(s1.substring(i), s2.substring(i)))
                    || (isScramble2(s1.substring(0, i), s2.substring(n - i)) && isScramble2(s1.substring(i), s2.substring(0, n - i)));
            if (res) {
                return true;
            }
        }

        return false;
    }

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        if (n != s2.length()) {
            return false;
        }

        if (s1.equals(s2)) {
            return true;
        }

        // dp[i][j][len] 表示s1从i开始len长度的字符串和s2从j开始len长度的字符串是否可变换
        // dp[i][j][k] = (dp[i][j][k] && dp[i+k][j+k][len - k]) || (dp[i][j+len-k][k] && dp[i + k][j][len - k]) ,
        // 其中1<=k<len
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k < len; k++) {
                        dp[i][j][len] = (dp[i][j][k] && dp[i + k][j + k][len - k]) ||
                                (dp[i][j + len - k][k] && dp[i + k][j][len - k]);
                        if (dp[i][j][len]) {
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }

    public static void main(String[] args) {
        IsScramble isScramble = new IsScramble();
        Assert.assertTrue(isScramble.isScramble("abcdefghijklmnopqrstuvw", "ghijklmnoabcdefpqrstuvw"));
        Assert.assertTrue(isScramble.isScramble("abb", "bba"));
        Assert.assertTrue(isScramble.isScramble("great", "rgeat"));
        Assert.assertTrue(isScramble.isScramble("abc", "abc"));
        Assert.assertFalse(isScramble.isScramble("abcde", "caebd"));
    }
}
