package com.theonecai.leetcode;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: theonecai
 * @Date: Create in 2020/11/08 10:24
 * @Description:
 */
public class Weekend214 {

    public int getMaximumGenerated(int n) {
        if (n == 0) {
            return 0;
        }
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }

    public int minDeletions(String s) {
        if (s.length() == 1) {
            return 0;
        }
        int[] charCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
        }
        Arrays.sort(charCounts);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] == 0) {
                continue;
            }
            if (!set.contains(charCounts[i])) {
                set.add(charCounts[i]);
            }
        }
        int eqSum = 0;
        int lessMaxSum = 0;
        int lessMax = -1;
        while (true) {
            boolean flag = true;
            for (int i = 25; i > 0; i--) {
                if (charCounts[i] == 0) {
                    continue;
                }
                if (charCounts[i] == charCounts[i - 1]) {
                    for (int j = charCounts[i] - 1; j >= 0 ; j--) {
                        if (!set.contains(j) || j == 0) {
                            eqSum += charCounts[i];
                            lessMaxSum += j;
                            lessMax += j;
                            set.add(j);
                            charCounts[i - 1] = j;
                            i--;
                            break;
                        }
                    }
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            Arrays.sort(charCounts);
        }

        return eqSum - lessMaxSum;
    }

    /**
     * 1648
     * @param inventory
     * @param orders
     * @return
     */
    public int maxProfit(int[] inventory, int orders) {
        long mod = 1000000007L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> countMap = new HashMap<>(inventory.length);
        for (int i = 0; i < inventory.length; i++) {
            if (!countMap.containsKey(inventory[i])) {
                maxHeap.offer(inventory[i]);
            }
            countMap.put(inventory[i], countMap.getOrDefault(inventory[i], 0) + 1);
        }
        int count = orders;
        long result = 0L;
        while (count > 0) {
            int top = maxHeap.poll();
            Integer peek = maxHeap.peek();
            if (peek == null) {
                result += (cal(top) - cal(top - count)) % mod;
                break;
            } else {
                if (count > top - peek) {
                    count -= top - peek + 1;
                    result += (cal(top) - cal(peek -1)) % mod;
                    if (peek - 1 > 0) {
                        maxHeap.offer(peek - 1);
                    }
                } else {
                    result += (cal(top) - cal(top - count)) % mod;
                    break;
                }
            }
        }
        return (int)(result % mod);
    }

    public long cal(long n) {
        if (n <= 0) {
            return 0;
        }
        return n * (n + 1) / 2L;
    }


    public static void main(String[] args) {
        Weekend214 weekend214 = new Weekend214();
        weekend214.test();
        weekend214.test2();
        weekend214.test3();
    }

    private void test3() {
        Assert.assertEquals(1441, this.maxProfit(new int[]{76}, 22));
        Assert.assertEquals(14, this.maxProfit(new int[]{2,5}, 4));
        Assert.assertEquals(19, this.maxProfit(new int[]{3,5}, 6));
        Assert.assertEquals(110, this.maxProfit(new int[]{2,8,4,10,6}, 20));
        Assert.assertEquals(21, this.maxProfit(new int[]{1000000000}, 1000000000));
    }

    private void test2() {
        Assert.assertEquals(3, this.minDeletions("abcabc"));
        Assert.assertEquals(2, this.minDeletions("aaabbbcc"));
        Assert.assertEquals(0, this.minDeletions("aab"));
        Assert.assertEquals(2, this.minDeletions("ceabaacb"));
    }

    private void test() {
        Assert.assertEquals(3, this.getMaximumGenerated(7));
    }
}
