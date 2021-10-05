package com.theonecai.leetcode.string;

import org.junit.Assert;

/**
 * 165
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        int n = version1.length();
        int m = version2.length();
        int aLeft = 0;
        int bLeft = 0;
        while (aLeft < n || bLeft < m) {
            int x = 0;
            for (; aLeft < n && version1.charAt(aLeft) != '.'; aLeft++) {
                x = x * 10 + version1.charAt(aLeft) - '0';
            }
            int y = 0;
            for (; bLeft < m && version2.charAt(bLeft) != '.'; bLeft++) {
                y = y * 10 + version2.charAt(bLeft) - '0';
            }
            if (x != y) {
                return x > y ? 1 : -1;
            }
            aLeft++;
            bLeft++;
        }
        return 0;
    }

    public int compareVersion2(String version1, String version2) {
        String[] v = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.max(v.length, v2.length);
        for (int i = 0; i < len; i++) {
            int va = 0;
            if (i < v.length) {
                va = Integer.parseInt(v[i]);
            }
            int vb = 0;
            if (i < v2.length) {
                vb = Integer.parseInt(v2[i]);
            }
            if (va != vb) {
                return va > vb ? 1 : -1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        CompareVersion compareVersion = new CompareVersion();
        Assert.assertEquals(-1, compareVersion.compareVersion("1.0.1", "1.001"));
        Assert.assertEquals(0, compareVersion.compareVersion("1.0", "1.0.0"));
        Assert.assertEquals(-1, compareVersion.compareVersion("0.01", "1.0"));
        Assert.assertEquals(1, compareVersion.compareVersion("1.0.1", "1.0"));
        Assert.assertEquals(-1, compareVersion.compareVersion("7.5.2.4", "7.5.3"));
    }
}
