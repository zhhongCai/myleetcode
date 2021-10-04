package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 166
 */
public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        long n = numerator;
        long d = denominator;
        StringBuilder sb = new StringBuilder();

        if (n * d < 0) {
            sb.append("-");
        }
        n = Math.abs(n);
        d = Math.abs(d);

        sb.append(n / d);
        long remain = n % d;
        if (remain == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>(256);

        while (remain != 0) {
            map.put(remain, sb.length());
            remain *= 10;
            n = remain / d;
            remain = remain % d;
            sb.append(n);
            if (map.containsKey(remain)) {
                int i = map.get(remain);
                return String.format("%s(%s)", sb.substring(0, i), sb.substring(i));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        FractionToDecimal fractionToDecimal = new FractionToDecimal();
        Assert.assertEquals("-0.1(6)", fractionToDecimal.fractionToDecimal(-1, 6));
        Assert.assertEquals("0.1(6)", fractionToDecimal.fractionToDecimal(-1, -6));
        Assert.assertEquals("0.(3)", fractionToDecimal.fractionToDecimal(1, 3));
        Assert.assertEquals("0.(6)", fractionToDecimal.fractionToDecimal(2, 3));
        Assert.assertEquals("1", fractionToDecimal.fractionToDecimal(3, 3));
        Assert.assertEquals("0.2", fractionToDecimal.fractionToDecimal(1, 5));
        Assert.assertEquals("0.5", fractionToDecimal.fractionToDecimal(1, 2));
        Assert.assertEquals("0.5", fractionToDecimal.fractionToDecimal(1, 2));
        Assert.assertEquals("2", fractionToDecimal.fractionToDecimal(4, 2));
        Assert.assertEquals("0.(012)", fractionToDecimal.fractionToDecimal(4, 333));

    }
}
