package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode 1648
 * @Author: theonecai
 * @Date: Create in 2020/11/9 21:36
 * @Description:
 */
public class MaxProfit {

    public int maxProfit(int[] inventory, int orders) {
        long mod = 1000000007L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Long> countMap = new HashMap<>(inventory.length);
        for (int i = 0; i < inventory.length; i++) {
            if (!countMap.containsKey(inventory[i])) {
                maxHeap.offer(inventory[i]);
            }
            countMap.put(inventory[i], countMap.getOrDefault(inventory[i], 0L) + 1);
        }
        int count = orders;
        long result = 0L;
        maxHeap.offer(0);
        countMap.put(0, 0L);
        while (count > 0) {
            int top = maxHeap.poll();
            int peek = maxHeap.peek();
            long num = (top - peek) * countMap.get(top);
            if (num <= count) {
                result += (top + peek + 1) * num / 2 % mod;
                count -= num;
            } else {
                long quotient = count / countMap.get(top);
                long remainder = count % - countMap.get(top);
                result += (top + (top - quotient) + 1) * countMap.get(top) * quotient / 2;
                result %= mod;
                result += (top - quotient) * remainder;
                count = 0;
            }
            countMap.put(peek, countMap.get(peek) + countMap.get(top));
        }
        return (int)(result % mod);
    }


    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        Assert.assertEquals(14, maxProfit.maxProfit(new int[]{2,5}, 4));
        Assert.assertEquals(1441, maxProfit.maxProfit(new int[]{76}, 22));
        Assert.assertEquals(19, maxProfit.maxProfit(new int[]{3,5}, 6));
        Assert.assertEquals(110, maxProfit.maxProfit(new int[]{2,8,4,10,6}, 20));
        Assert.assertEquals(21, maxProfit.maxProfit(new int[]{1000000000}, 1000000000));
    }
}
