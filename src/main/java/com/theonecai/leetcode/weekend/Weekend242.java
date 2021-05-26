package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: theonecai
 * @Date: Create in 2021/05/23 10:24
 * @Description:
 */
public class Weekend242 {

    public boolean checkZeroOnes(String s) {
        char[] chars = s.toCharArray();
        int oneCount = 0;
        int zeroCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                int j = i + 1;
                int c = 1;
                while (j < chars.length && chars[j] == '1') {
                    j++;
                    c++;
                }
                i = j - 1;
                oneCount = Math.max(oneCount, c);
            } else {
                int j = i + 1;
                int c = 1;
                while (j < chars.length && chars[j] == '0') {
                    j++;
                    c++;
                }
                i = j - 1;
                zeroCount = Math.max(zeroCount, c);
            }
        }

        return oneCount > zeroCount;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length == 1) {
            double speed = (double) dist[0] / hour;
            if (speed > Math.floor(speed)) {
                speed = Math.floor(speed + 1.0);
            }
            return (int)speed;
        }
        if (dist.length - 1 > hour) {
            return -1;
        }
        int left = 1;
        int right = 10000000;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(mid, dist, hour)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int speed, int[] dist, double hour) {
        double cost = 0.0;
        for (int i = 0; i < dist.length; i++) {
            cost += (double)dist[i] / (double)speed;
            if (i != dist.length - 1) {
                if (cost > Math.floor(cost)) {
                    cost = Math.floor(cost + 1.0);
                }
            }
        }
        return cost <= hour;
    }

    private Map<Integer, Boolean> memo;
    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        if (s.charAt(len - 1) == '1') {
            return false;
        }
        TreeMap<Integer, Boolean> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                treeMap.put(i, Boolean.TRUE);
            }
        }
        memo = new HashMap<>();
        return canReach(s, 0, minJump, maxJump, treeMap);
    }

    private boolean canReach(String s, int current, int minJump, int maxJump, TreeMap<Integer, Boolean> treeMap) {
        if (memo.containsKey(current)) {
            return memo.get(current);
        }
        if (current == s.length() - 1) {
            memo.put(current, Boolean.TRUE);
            return true;
        }
        if (current >= s.length()) {
            memo.put(current, Boolean.FALSE);
            return false;
        }

        Integer h = treeMap.floorKey(maxJump);
        Integer low = treeMap.ceilingKey(minJump);
        if (h == null && low == null) {
            memo.put(current, Boolean.FALSE);
            return false;
        }
        h = h == null ? maxJump : h;
        treeMap.ceilingKey(minJump);
        while (low != null && low <=  h) {

            boolean res = canReach(s, current + low, minJump, maxJump, treeMap);
            if (res) {
                return true;
            } else {
                memo.put(current, Boolean.FALSE);
            }
            low = treeMap.ceilingKey(low + 1);
            if (low == null) {
                break;
            }
        }

        memo.put(current, Boolean.FALSE);
        return false;
    }


    public static void main(String[] args) {
        Weekend242 weekend = new Weekend242();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        StringBuilder sb = new StringBuilder("0");

        for (int i = 0; i < 100000; i++) {
            if (Math.random() > 0.5) {
                sb.append('0');
            } else {
                sb.append('0');
            }
        }
        Assert.assertTrue(this.canReach(sb.toString(), 2, 7));
        Assert.assertTrue(this.canReach("0000000000", 2, 5));
        Assert.assertTrue(this.canReach("011010", 2, 3));
        Assert.assertFalse(this.canReach("01101110", 2, 3));
    }

    private void test2() {
        Assert.assertEquals(20, this.minSpeedOnTime(new int[]{1,2,3,4,5,6}, 5.3));
        Assert.assertEquals(10000000, this.minSpeedOnTime(new int[]{1,1,100000}, 2.01));
        Assert.assertEquals(20, this.minSpeedOnTime(new int[]{6}, 0.3));
        Assert.assertEquals(1, this.minSpeedOnTime(new int[]{1,3,2}, 6));
        Assert.assertEquals(3, this.minSpeedOnTime(new int[]{1,3,2}, 2.7));
        Assert.assertEquals(-1, this.minSpeedOnTime(new int[]{1,3,2}, 1.9));
    }

    private void test() {
        Assert.assertFalse(this.checkZeroOnes("110100010"));
        Assert.assertTrue(this.checkZeroOnes("1101"));
        Assert.assertFalse(this.checkZeroOnes("11110000"));
        Assert.assertTrue(this.checkZeroOnes("1111000"));
        Assert.assertFalse(this.checkZeroOnes("111100100100000"));
    }
}
