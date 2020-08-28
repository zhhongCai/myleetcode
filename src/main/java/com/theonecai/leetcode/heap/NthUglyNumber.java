package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * leetcode 264
 * @Author: theonecai
 * @Date: Create in 2020/8/15 21:40
 * @Description:
 */
public class NthUglyNumber {

    public int nthUglyNumber(int n) {
        int[] primeFactors = {2, 3, 5};
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
            for (int i = 0; i < 3; i++) {
                next = top * primeFactors[i];
                if (!set.contains(next)) {
                    minHeap.add(next);
                    set.add(next);
                }
            }

        }

        return (int)nth;
    }

    public int nthUglyNumber2(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int prime2Index = 0;
        int prime3Index = 0;
        int prime5Index = 0;
        int prime2Next;
        int prime3Next;
        int prime5Next;
        int next;
        for (int i = 1; i < n; i++) {
            prime2Next = ugly[prime2Index] * 2;
            prime3Next = ugly[prime3Index] * 3;
            prime5Next = ugly[prime5Index] * 5;
            next = Math.min(prime2Next, Math.min(prime3Next, prime5Next));
            ugly[i] = next;
            if (next == prime2Next) {
                prime2Index++;
            }
            if (next == prime3Next) {
                prime3Index++;
            }
            if (next == prime5Next) {
                prime5Index++;
            }

        }
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        NthUglyNumber nthUglyNumber = new NthUglyNumber();
        Assert.assertEquals(12, nthUglyNumber.nthUglyNumber(10));
        Assert.assertEquals(12, nthUglyNumber.nthUglyNumber2(10));
        Assert.assertEquals(536870912, nthUglyNumber.nthUglyNumber(1407));
        Assert.assertEquals(536870912, nthUglyNumber.nthUglyNumber2(1407));
    }
}
