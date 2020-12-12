package com.theonecai.leetcode.bit;

import java.util.HashMap;
import java.util.Map;

public class MinChangeTimes {
    public int optTime(int n) {
        int c = 0;
        while (n > 1) {
            if ((n & 1) == 0) {
                n >>= 1;
                c++;
            } else {
                if ((n & 2) == 0) {
                    n--;
                    c++;
                } else if (n == 3) {
                    n -= 2;
                    c += 2;
                } else {
                    n++;
                    c++;
                }
            }
        }
        return c;
    }

    private Map<Integer, Integer> map = new HashMap<>();
    public int optTimeDf(int n) {
        if (n == 1) {
            return 0;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int c = 0;
        if ((n & 1) == 0) {
            c += optTime(n >> 1) + 1;
        } else {
            c += Math.min(optTime(n - 1), optTime(n + 1)) + 1;
        }

        map.put(n, c);
        return c;
    }


    public static void main(String[] args) {
        MinChangeTimes minChangeTimes = new MinChangeTimes();
        System.out.println(minChangeTimes.optTime(3));
        System.out.println(minChangeTimes.optTimeDf(3));
        for (int i = 1; i < Integer.MAX_VALUE - 1; i++) {

            if (minChangeTimes.optTime(i) != minChangeTimes.optTimeDf(i)) {
                System.out.println(i);
                break;
            }
        }
    }
}
