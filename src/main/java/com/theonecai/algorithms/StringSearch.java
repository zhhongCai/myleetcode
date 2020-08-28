package com.theonecai.algorithms;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/6/30 08:49
 * @Description:
 */
public class StringSearch {

    /**
     * BF算法：暴力搜索，朴素搜索
     * @param str
     * @param pattern
     * @return
     */
    public static int bfSearch(String str, String pattern) {
        int j;
        for (int i = 0; i < str.length() - pattern.length(); i++) {
            for (j = 0; j < pattern.length(); j++) {
                if (str.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j >= pattern.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * RK算法: BF + hash
     * @param str
     * @param pattern
     * @return
     */
    public static int rkSearch(String str, String pattern) {
        int patternHash = pattern.hashCode();
        int j;
        int subStrHash;
        for (int i = 0; i < str.length() - pattern.length(); i++) {
            subStrHash = str.substring(i, i + pattern.length()).hashCode();
            if (patternHash != subStrHash) {
                continue;
            }
            for (j = 0; j < pattern.length(); j++) {
                if (str.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j >= pattern.length()) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int len = 13000000;
        int start = 10000234;
        String str = RandomStringUtil.randomString(len);
        String pattern = str.substring(start, start + 10000);
        long a = System.currentTimeMillis();
        Assert.assertEquals(start, StringSearch.bfSearch(str, pattern));
        System.out.println("bf cost:" + (System.currentTimeMillis() - a));
        Assert.assertEquals(start, StringSearch.rkSearch(str, pattern));
        System.out.println("rk cost:" + (System.currentTimeMillis() - a));
    }
}
