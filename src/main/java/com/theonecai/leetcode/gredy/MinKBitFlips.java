package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 995
 * @Author: theonecai
 * @Date: Create in 2020/10/15 22:06
 * @Description:
 */
public class MinKBitFlips {

    public int minKBitFlips(int[] A, int K) {
        int count = 0;
        Deque<Integer> queue = new LinkedList<>();
        int right = 0;
        while (right < A.length) {
            while (!queue.isEmpty() && (queue.getFirst() + K) <= right) {
                queue.removeFirst();
            }
            if (queue.size() % 2 == A[right]) {
                if (right + K > A.length) {
                    return -1;
                }
                count++;
                queue.addLast(right);
            }
            right++;
        }

        return count;
    }

    public int minKBitFlips2(int[] A, int K) {
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                count++;
                if (i + K > A.length) {
                    return -1;
                }
                for (int j = i; j < i + K; j++) {
                    A[j] ^= 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MinKBitFlips flips = new MinKBitFlips();
        Assert.assertEquals(-1, flips.minKBitFlips(new int[]{0,1,1}, 2));
        Assert.assertEquals(2, flips.minKBitFlips(new int[]{0,1,0}, 1));
        Assert.assertEquals(-1, flips.minKBitFlips(new int[]{1,1,0}, 2));
        Assert.assertEquals(3, flips.minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3));
    }
}
