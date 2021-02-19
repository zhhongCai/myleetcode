package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 1004
 * @Author: theonecai
 * @Date: Create in 2021/2/19 21:43
 * @Description:
 */
public class LongestOnes {

    public int longestOnes2(int[] A, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (right < A.length) {
            if (A[right] == 0) {
                if (queue.size() < K) {
                    queue.offer(result);
                } else {
                    if (queue.isEmpty()) {
                        left = right + 1;
                    } else {
                        left = queue.poll() + 1;
                        queue.offer(right);
                    }
                }
            }
            result = Math.max(result, right - left + 1);
            right++;
        }

        return result;
    }


    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int zeroCount = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                zeroCount++;
            }
            if (zeroCount > K) {
                if (A[left++] == 0) {
                    zeroCount--;
                }
            }
            right++;
        }

        return right - left;
    }

    public static void main(String[] args) {
        LongestOnes longestOnes = new LongestOnes();
        Assert.assertEquals(6, longestOnes.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        Assert.assertEquals(3, longestOnes.longestOnes(new int[]{1,0,1,0}, 1));
        Assert.assertEquals(4, longestOnes.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 0));
        Assert.assertEquals(11, longestOnes.longestOnes(new int[]{1,1,1,1,0,1,1,1,1,1,1}, 1));
        Assert.assertEquals(10, longestOnes.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }

}
