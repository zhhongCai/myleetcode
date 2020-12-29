package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 205
 * @Author: theonecai
 * @Date: Create in 2020/12/27 20:30
 * @Description:
 */
public class IsIsomorphic {

    public boolean isIsomorphic(String s, String t) {
        // Map<s.charAt(i), i>
        Map<Character, Integer> map = new HashMap<>();
        // Map<t.charAt(i), i>
        Map<Character, Integer> indexMap = new HashMap<>();
        char sCh;
        char tCh;
        for (int i = 0; i < s.length(); i++) {
            sCh = s.charAt(i);
            tCh = t.charAt(i);
            if (map.containsKey(sCh)) {
                if (t.charAt(map.get(sCh)) != tCh) {
                    return false;
                }
            } else {
                if (indexMap.containsKey(tCh) && sCh != s.charAt(indexMap.get(tCh))) {
                    return false;
                }
                map.put(sCh, i);
                indexMap.put(tCh, i);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsIsomorphic isIsomorphic = new IsIsomorphic();
        Assert.assertFalse(isIsomorphic.isIsomorphic("ab", "aa"));
        Assert.assertTrue(isIsomorphic.isIsomorphic("egg", "add"));
        Assert.assertFalse(isIsomorphic.isIsomorphic("foo", "bar"));
        Assert.assertTrue(isIsomorphic.isIsomorphic("paper", "title"));
    }
}
