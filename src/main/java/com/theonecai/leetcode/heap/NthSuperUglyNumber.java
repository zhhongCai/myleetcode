package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * leetcode 313
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/15 22:14
 * @Description:
 */
public class NthSuperUglyNumber {

    public int nthSuperUglyNumber2(int n, int[] primes) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>(n);
        Set<Long> set = new HashSet<>(n);
        set.add(1L);
        minHeap.add(1L);
        int count = 0;
        long top = 0;
        long nth = 1;
        long next = 0;
        while (count < n) {
            top = minHeap.poll();
            count++;
            if (count >= n) {
                nth = top;
                break;
            }
            for (int i = 0; i < primes.length; i++) {
                next = top * primes[i];
                if (!set.contains(next)) {
                    minHeap.add(next);
                    set.add(next);
                }
            }

        }

        return (int)nth;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int k = primes.length;
        int[] primeIndex = new int[k];
        int[] primeNext = new int[k];
        int next;
        for (int i = 1; i < n; i++) {
            next = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                primeNext[j] = ugly[primeIndex[j]] * primes[j];
                if (next > primeNext[j]) {
                    next = primeNext[j];
                }
            }
            ugly[i] = next;
            for (int j = 0; j < k; j++) {
                if (next == primeNext[j]) {
                    primeIndex[j]++;
                }
            }
        }
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        NthSuperUglyNumber superUglyNumber = new NthSuperUglyNumber();
        int[] primes = {2,7,13,19};
        Assert.assertEquals(32, superUglyNumber.nthSuperUglyNumber2(12, primes));
        Assert.assertEquals(32, superUglyNumber.nthSuperUglyNumber(12, primes));
    }
}
