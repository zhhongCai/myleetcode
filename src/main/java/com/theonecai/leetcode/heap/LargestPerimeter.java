package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 976
 * @Author: theonecai
 * @Date: Create in 2020/11/29 10:19
 * @Description:
 */
public class LargestPerimeter {

    public int largestPerimeter(int[] A) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < A.length; i++) {
            maxHeap.offer(A[i]);
        }
        int a = maxHeap.poll();
        int b = maxHeap.poll();
        int c = maxHeap.poll();
        while (!maxHeap.isEmpty() && a >= b + c) {
            a = b;
            b = c;
            c = maxHeap.poll();
        }
        if (a >= b + c) {
            return 0;
        }
        return a + b + c;
    }

    public static void main(String[] args) {
        LargestPerimeter largestPerimeter = new LargestPerimeter();
        Assert.assertEquals(5, largestPerimeter.largestPerimeter(new int[] {2,1,2}));
        Assert.assertEquals(0, largestPerimeter.largestPerimeter(new int[] {1,1,2}));
        Assert.assertEquals(10, largestPerimeter.largestPerimeter(new int[] {3,2,3,4}));
        Assert.assertEquals(8, largestPerimeter.largestPerimeter(new int[] {3,6,2,3}));
    }
}
