package com.theonecai.leetcode.dp;

import org.junit.Assert;

/**
 * leetcode 474
 * @Author: theonecai
 * @Date: Create in 6/6/21 08:42
 * @Description:
 */
public class FindMaxForm {

    public int findMaxForm2(String[] strs, int m, int n) {
        /**
         * dp[i][zero][one]表示前0~i字符串中最大子集大小： zero <= m && one <= n;
         * dp[i][zero][one] = max(dp[i - 1][zero][one],dp[i-1][zero - zeroOneCount.get(i-1)[0][one - zeroOneCount.get(i-1)[1]] + 1),
         *     其中zero >= zeroOneCount.get(i-1)[0] 且 one >= zeroOneCount.get(i-1)[1];
         *
         */
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        int[] count = new int[2];
        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i - 1];
            count[0] = 0;
            count[1] = 0;
            for (int j = 0; j < str.length(); j++) {
                count[str.charAt(j) - '0']++;
            }
            for (int zero = 0; zero < dp[0].length; zero++) {
                for (int one = 0; one < dp[0][0].length; one++) {
                    dp[i][zero][one] = dp[i - 1][zero][one];
                    if (zero >= count[0] && one >= count[1]) {
                        dp[i][zero][one] = Math.max(dp[i][zero][one], dp[i - 1][zero - count[0]][one - count[1]] + 1);
                    }
                }
            }
        }

        return dp[dp.length - 1][m][n];
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int[] count = new int[2];
        for (String str : strs) {
            count[0] = 0;
            count[1] = 0;
            for (int j = 0; j < str.length(); j++) {
                count[str.charAt(j) - '0']++;
            }
            for (int zero = m; zero >= count[0]; zero--) {
                for (int one = n; one >= count[1]; one--) {
                    dp[zero][one] = Math.max(dp[zero][one], dp[zero - count[0]][one - count[1]] + 1);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        FindMaxForm findMaxForm = new FindMaxForm();
        Assert.assertEquals(3, findMaxForm.findMaxForm(new String[]{"10","0001","111001","1","0"}, 3, 4));
        Assert.assertEquals(2, findMaxForm.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
        Assert.assertEquals(4, findMaxForm.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }
}
