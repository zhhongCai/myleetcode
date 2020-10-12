package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 318
 * @Author: theonecai
 * @Date: Create in 2020/10/12 21:07
 * @Description:
 */
public class MaxProduct {

    public int maxProduct(String[] words) {
        if (words.length == 1) {
            return 0;
        }
        int bitSet = 0;
        int[] bitsets = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            bitSet = 0;
            for (int j = 0; j < words[i].length(); j++) {
                bitSet |= (1 << (words[i].charAt(j) - 'a'));
            }
            bitsets[i] = bitSet;
        }
        int len = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bitsets[i] & bitsets[j]) == 0) {
                    len = Math.max(len, words[i].length() * words[j].length());
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        Assert.assertEquals(0,
                maxProduct.maxProduct(new String[]{""}));
        Assert.assertEquals(0,
                maxProduct.maxProduct(new String[]{"", ""}));
        Assert.assertEquals(16,
                maxProduct.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
        Assert.assertEquals(4,
                maxProduct.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}));
        Assert.assertEquals(0,
                maxProduct.maxProduct(new String[]{"a","aa","aaa","aaaa"}));
    }
}
