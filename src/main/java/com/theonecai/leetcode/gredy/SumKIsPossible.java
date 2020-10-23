package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2020/10/23 22:06
 * @Description:
 */
public class SumKIsPossible {

    public boolean isPossible(int[] target) {
        long sum = 0L;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : target) {
            sum += num;
            maxHeap.offer(num);
        }
        if (maxHeap.isEmpty()) {
            return false;
        }
        Integer top = maxHeap.poll();
        if (top == 1) {
            return true;
        }
        if (top < sum - top + 1)  {
            return false;
        }
        while (top != null && top  != 1) {
            long n = top - (sum - top);
            Integer t = maxHeap.peek();
            sum = top;
            if (n <= 0) {
                return false;
            }
            maxHeap.offer((int)n);
            top = maxHeap.poll();
        }

        return true;
    }

    public static void main(String[] args) {
        SumKIsPossible sumKIsPossible = new SumKIsPossible();
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{1,1000000000}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{9,3,5}));
        Assert.assertFalse(sumKIsPossible.isPossible(new int[]{1,1,1,2}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{8,5}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{1,1,1}));
    }
}
