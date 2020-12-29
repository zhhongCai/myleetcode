package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2020/12/27 10:24
 * @Description:
 */
public class Weekend221 {

    public boolean halvesAreAlike(String s) {
        Set<Character> characterSet = new HashSet<>(10);
        characterSet.add('a');
        characterSet.add('e');
        characterSet.add('i');
        characterSet.add('o');
        characterSet.add('u');
        characterSet.add('A');
        characterSet.add('E');
        characterSet.add('I');
        characterSet.add('O');
        characterSet.add('U');
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        int suf = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (characterSet.contains(ch)) {
                if (i < s.length() / 2) {
                    pre++;
                } else {
                    suf++;
                }
            }
        }

       return pre == suf;
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int count = 0;
        int day = 0;
        for (; day < apples.length; day++) {
            if (apples[day] > 0) {
                minHeap.offer(new int[]{day + days[day], apples[day]});
            }
            while (!minHeap.isEmpty()) {
                int[] top = minHeap.poll();
                if (top[0] <= day) {
                    continue;
                }
                count++;
                if (top[0] - 1 > day && top[1] > 1) {
                    minHeap.offer(new int[]{top[0], top[1] - 1});
                }
                break;
            }
        }
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            if (top[0] <= day) {
                continue;
            }
            if (minHeap.isEmpty()) {
                count += Math.min(top[0] - day, top[1]);
                break;
            }
            count++;
            if (top[0] - 1 > day && top[1] > 1) {
                minHeap.offer(new int[]{top[0], top[1] - 1});
            }
            day++;
        }

        return count;
    }

    public static void main(String[] args) {
        Weekend221 weekend221 = new Weekend221();
        weekend221.test();
        weekend221.test2();
        weekend221.test3();
        weekend221.test4();
    }

    private void test4() {

    }

    private void test3() {
    }

    private void test2() {
        //[]
        //[3,1,1,0,0,2]
        Assert.assertEquals(5, this.eatenApples(new int[]{3,1,1,0,0,2}, new int[]{3,1,1,0,0,2}));
        Assert.assertEquals(5, this.eatenApples(new int[]{3,0,0,0,0,2}, new int[]{3,0,0,0,0,2}));
        //[9,10,1,7,0,2,1,4,1,7,0,11,0,11,0,0,9,11,11,2,0,5,5]
        //[]
        Assert.assertEquals(31, this.eatenApples(new int[]{9,10,1,7,0,2,1,4,1,7,0,11,0,11,0,0,9,11,11,2,0,5,5},
                new int[]{3,19,1,14,0,4,1,8,2,7,0,13,0,13,0,0,2,2,13,1,0,3,7}));
        Assert.assertEquals(7, this.eatenApples(new int[]{1,2,3,5,2}, new int[]{3,2,1,4,2}));
    }

    private void test() {
        Assert.assertFalse(this.halvesAreAlike("tkPAdxpMfJiltOerItiv"));
        Assert.assertTrue(this.halvesAreAlike("Ieai"));
        Assert.assertTrue(this.halvesAreAlike("book"));
        Assert.assertFalse(this.halvesAreAlike("textbook"));
        Assert.assertFalse(this.halvesAreAlike("MerryChristmas"));
        Assert.assertTrue(this.halvesAreAlike("AbCdEfGh"));
    }
}
