package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 1354
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
            return sum == target.length;
        }
        if (top < sum - top + 1)  {
            return false;
        }
        while (top != null && top  != 1) {
            if (top < sum - top + 1)  {
                return false;
            }
            long remain = sum - top;
            if (remain == 0) {
                return false;
            }
            int n;
            if (top % remain == 0) {
                n = (int) (top - (remain * Math.max(1, top / remain - 1)));
            } else {
                n = (int) (top - remain * (top / remain));
            }
            if (n == 0) {
                return false;
            }
            sum = remain + n;
            maxHeap.offer(n);
            top = maxHeap.poll();
        }

        return true;
    }

    public static void main(String[] args) {
        SumKIsPossible sumKIsPossible = new SumKIsPossible();
        Assert.assertFalse(sumKIsPossible.isPossible(new int[]{1,49,11,1,25,1,7}));
        Assert.assertFalse(sumKIsPossible.isPossible(new int[]{1, 2 ,100}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{9,3,5}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{1,1000000000}));
        Assert.assertFalse(sumKIsPossible.isPossible(new int[]{1,1,1,2}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{8,5}));
        Assert.assertTrue(sumKIsPossible.isPossible(new int[]{1,1,1}));
    }
}
