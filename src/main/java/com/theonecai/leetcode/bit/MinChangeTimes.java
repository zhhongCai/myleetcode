package com.theonecai.leetcode.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 397
 */
public class MinChangeTimes {
    public int integerReplacement(int n) {
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
                    if (n == Integer.MAX_VALUE) {
                        n--;
                        c++;
                        continue;
                    }
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
            c += integerReplacement(n >> 1) + 1;
        } else {
            c += Math.min(integerReplacement(n - 1), integerReplacement(n + 1)) + 1;
        }

        map.put(n, c);
        return c;
    }


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        MinChangeTimes minChangeTimes = new MinChangeTimes();
        System.out.println(minChangeTimes.integerReplacement(3));
        System.out.println(minChangeTimes.optTimeDf(3));
       /* for (int i = 1; i < Integer.MAX_VALUE - 1; i++) {

            if (minChangeTimes.integerReplacement(i) != minChangeTimes.optTimeDf(i)) {
                System.out.println(i);
                break;
            }
        }*/
    }
}
