package com.theonecai.algorithms;

import org.junit.Assert;

import java.util.Arrays;

/**
 * 字符串匹配算法: BM算法
 * @Author: theonecai
 * @Date: Create in 2020/6/24 20:04
 * @Description:
 */
public class StringSearchBM {

    public final static int MAX_SIZE = 256;

    /**
     * 坏字符在模式串中的最后位置
     * @param pattern
     * @return
     */
    private static int[] preBadCharLastIndex(char[] pattern) {
        int[] badCharLastIndex = new int[MAX_SIZE];

        Arrays.fill(badCharLastIndex, -1);


        for (int i = 0; i < pattern.length; i++) {
            badCharLastIndex[pattern[i]] = i;
        }

        return badCharLastIndex;
    }

    public static int search(char[] str, char[] pattern) {
        int[] badCharLastIndex = preBadCharLastIndex(pattern);

//        for (int i = 0; i < pattern.length; i++) {
//            System.out.println("badCharLastIndex: '" + pattern[i] + "', " + badCharLastIndex[pattern[i]]);
//        }

        int[] suffix = new int[pattern.length];
        boolean[] prefix = new boolean[pattern.length];
        preGoodShift(pattern, suffix, prefix);

//        for (int i = 0; i < pattern.length; i++) {
//            System.out.print("suffix[" + i + "]=" + suffix[i]);
//            System.out.println(",prefix[" + i + "]=" + prefix[i]);
//        }

        for (int i = pattern.length - 1; i < str.length;) {
            int j;
            int k = i;
            for (j = pattern.length - 1; j >= 0; j--, k--) {
                if (str[k] != pattern[j]) {
                    break;
                }
            }
            if (j == -1) {
                return i - pattern.length + 1;
            }

            int goodShift = 0;
            if (j < pattern.length - 1) {
                goodShift = goodShift(j, pattern.length, suffix, prefix);
            }
            int badCharShift = badCharShift(j, badCharLastIndex[str[k]], pattern.length);
            System.out.print("goodShift=" + goodShift + ", badCharShift=" + badCharShift);
            int shift = Math.max(badCharShift, goodShift);
            System.out.println(", char: '" + str[k] + "', shift=" + shift);
            i += shift;
        }

        return -1;
    }

    /**
     * 根据好后缀生成的shift
     * @param patternCurrentIndex
     * @param patternLength
     * @param suffix
     * @param prefix
     * @return
     */
    private static int goodShift(int patternCurrentIndex, int patternLength, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = patternLength - 1 - patternCurrentIndex;
        if (suffix[k] != -1) {
            return patternCurrentIndex - suffix[k];
        }
        //坏字符的下标是patternCurrentIndex,那好后缀的下标起始就是patternCurrentIndex+1,
        //其子后缀串的起始下标就只能是patternCurrentIndex+2
        for (int r = patternCurrentIndex + 2; r < patternCurrentIndex; r++) {
            if (prefix[patternCurrentIndex - r]) {
                return r;
            }
        }

        return patternLength;
    }


    /**
     * 坏字符后移位数 = 坏字符在模式中失配的位置 - 坏字符在模式中最后一次出现的位置
     * @param patternCurrentIndex
     * @param badCharLastIndex
     * @param patternLen
     * @return
     */
    private static int badCharShift(int patternCurrentIndex, int badCharLastIndex, int patternLen) {
        return Math.max(1, badCharLastIndex == -1 ? patternLen : patternCurrentIndex - badCharLastIndex);
    }

    /**
     *
     * @param pattern : 待匹配的模式串
     * @param suffix  : suffix[k]表示长度为k的好后缀子串(最后)出现在模式串中的起始位置
     * @param prefix  ：prefix[k]表示模式串中与长度为k的好后缀相同的前缀子串是否存在
     */
    private static void preGoodShift(char[] pattern, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < pattern.length; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        // 求0~i 和长度为k的好后缀共同的子串
        for (int i = 0; i < pattern.length - 1; i++) {
            int j = i;
            int k = 0;
            while(j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) {
                prefix[i] = true;
            }
        }

    }

    public static void main(String[] args) {
        String str = RandomStringUtil.randomString(200000008);
        String pattern = str.substring(10000001, 20000000);

        int p = StringSearchBM.search(str.toCharArray(), pattern.toCharArray());
        Assert.assertEquals(10000001, p);
//        System.out.println("source str: " + str);
//        System.out.println("pattern   : " + pattern);
//        if (p != -1) {
//            System.out.println("find position: " + p + ": " + str.substring(0, p) + " " + pattern + " " + str.substring(p + pattern.length()));
//        } else {
//            System.out.println("notfound");
//        }
    }
}
