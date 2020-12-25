package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.*;

/**
 * leetcode 1239
 */
public class MaxLength {

    private int maxLen;

    private int ALL_MASK = (1 << 26) - 1;

    public int maxLength(List<String> arr) {
        Map<String, Integer> strMask = new HashMap<>(arr.size());
        Iterator<String> it = arr.iterator();
        Set<Integer> exists = new HashSet<>();
        maxLen = 0;
        while (it.hasNext()) {
            String str = it.next();
            exists.clear();
            int mask = 0;
            for (int i = 0; i < str.length(); i++) {
                int k = str.charAt(i) - 'a';
                if (exists.contains(k)) {
                    mask = -1;
                    it.remove();
                    break;
                }
                exists.add(k);
                mask |= 1 << k;
            }
            if (mask != -1) {
                strMask.put(str, mask);
                maxLen = Math.max(maxLen, str.length());
            }
        }

        if (arr.size() > 1) {
            dfs(arr, 0, 0, strMask);
        }

        return maxLen;
    }

    private int dfs(List<String> arr, int index, int mask, Map<String, Integer> strMask) {
        if (mask >= ALL_MASK || index >= arr.size()) {
            return 0;
        }
        int len = 0;
        int m = strMask.get(arr.get(index));
//        System.out.println("mask: " + Integer.toBinaryString(mask) + ",m: " + Integer.toBinaryString(m));

        if ((m & mask) == 0) {
            len = arr.get(index).length() + dfs(arr, index + 1, m | mask, strMask);
        }

        len = Math.max(len, dfs(arr, index + 1, mask, strMask));
        maxLen = Math.max(maxLen, len);

        return len;
    }

    public static void main(String[] args) {
        MaxLength maxLength = new MaxLength();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("abc");
        list.add("d");
        list.add("de");
        list.add("def");
        Assert.assertEquals(6, maxLength.maxLength(list));
        list.clear();
        list.add("un");
        list.add("iq");
        list.add("ue");
        Assert.assertEquals(4, maxLength.maxLength(list));
        list.clear();
        list.add("cha");
        list.add("r");
        list.add("act");
        list.add("ers");
        Assert.assertEquals(6, maxLength.maxLength(list));
        list.clear();
        list.add("abcdefghijklmnopqrstuvwxyz");
        Assert.assertEquals(26, maxLength.maxLength(list));
    }
}
