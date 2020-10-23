package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 1433
 * @Author: theonecai
 * @Date: Create in 2020/10/17 13:51
 * @Description:
 */
public class CheckIfCanBreak {

    public boolean checkIfCanBreak(String s1, String s2) {
        int[] str = new int[26];
        int[] str2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            str[s1.charAt(i) - 'a']++;
            str2[s2.charAt(i) - 'a']++;
        }
        int[] prefixSum = new int[26];
        int[] prefixSum2 = new int[26];
        prefixSum[0] = str[0];
        prefixSum2[0] = str2[0];
        for (int i = 1; i < str.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + str[i];
            prefixSum2[i] = prefixSum2[i - 1] + str2[i];
        }

        return check(prefixSum, prefixSum2) || check(prefixSum2, prefixSum);
    }

    private boolean check(int[] prefixSum, int[] prefixSum2) {
        for (int i = 0; i < 26; i++) {
            if (prefixSum[i] < prefixSum2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CheckIfCanBreak checkIfCanBreak = new CheckIfCanBreak();
        Assert.assertFalse(checkIfCanBreak.checkIfCanBreak("abe", "acd"));
        Assert.assertTrue(checkIfCanBreak.checkIfCanBreak("abc", "xya"));
        Assert.assertTrue(checkIfCanBreak.checkIfCanBreak("leetcode", "interview"));
    }
}
