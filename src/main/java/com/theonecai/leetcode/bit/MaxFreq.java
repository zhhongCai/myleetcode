package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1297
 */
public class MaxFreq {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length() - minSize + 1; i++) {
            int mask = 0;
            char[] chars = new char[minSize];
            for (int j = i; j < i + minSize; j++) {
                int k = s.charAt(j) - 'a';
                mask |= 1 << k;
                chars[j - i] = s.charAt(j);
            }
            if (Integer.bitCount(mask) <= maxLetters) {
                String key = new String(chars);
                countMap.put(key, countMap.getOrDefault(key, 0) + 1);
            }
        }
        if (countMap.isEmpty()) {
            return 0;
        }
        return countMap.values().stream().max(Integer::compareTo).get();
    }

    public static void main(String[] args) {
        MaxFreq maxFreq = new MaxFreq();
        Assert.assertEquals(2, maxFreq.maxFreq("aababcaab", 2, 3, 4));
        Assert.assertEquals(2, maxFreq.maxFreq("aaaa", 1, 3, 3));
        Assert.assertEquals(3, maxFreq.maxFreq("aabcabcab", 2, 2, 3));
        Assert.assertEquals(0, maxFreq.maxFreq("abcde", 2, 3, 3));
    }
}
