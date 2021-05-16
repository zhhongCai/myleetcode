package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 13
 * @Author: theonecai
 * @Date: Create in 5/15/21 22:18
 * @Description:
 */
public class RomanToInt {
    private static Map<String, Integer> map = new HashMap<>(16);
    static  {
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
    }
    public int romanToInt2(String s) {
        int index = 0;
        int result = 0;
        while (index < s.length()) {
            if (index + 1 < s.length()) {
                Integer val = map.get(s.substring(index, index + 2));
                if (val != null) {
                    index += 2;
                    result += val;
                    continue;
                }
                if (s.charAt(index) == 'I') {
                    result += map.get(s.substring(index));
                    break;
                }
            }
            result += map.get("" + s.charAt(index));
            index++;
        }

        return result;
    }

    public int romanToInt(String s) {
        int pre = map.get(String.valueOf(s.charAt(0)));
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            int current = map.get(String.valueOf(s.charAt(i)));
            if (current > pre) {
                result += pre - current;
            } else {
                if ('I' == s.charAt(i)) {
                    result += map.get(s.substring(i));
                    break;
                } else {
                    result += pre;
                }
            }
            pre = current;
        }

        return result;
    }

    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        Assert.assertEquals(1994, romanToInt.romanToInt("MCMXCIV"));
        Assert.assertEquals(3, romanToInt.romanToInt("III"));
        Assert.assertEquals(4, romanToInt.romanToInt("IV"));
        Assert.assertEquals(9, romanToInt.romanToInt("IX"));
        Assert.assertEquals(58, romanToInt.romanToInt("LVIII"));
    }
}
