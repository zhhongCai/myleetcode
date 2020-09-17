package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 1508
 * @Author: theonecai
 * @Date: Create in 2020/9/12 18:46
 * @Description:
 */
public class RangeSum {
    private long mod = 1000000007L;

    public int rangeSum(int[] nums, int n, int left, int right) {
        int [] sums = new int[n * (n + 1) / 2];
        // 前缀和
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int k = 0;
        int idx = 0;
        while (k < n) {
            for (int i = 0; i < n && i + k < n; i++) {
                sums[idx++] = (int)((prefixSum[i + k + 1] - prefixSum[i]) % mod);
            }
            k++;
        }

        Arrays.sort(sums);

        long sum = 0;
        for (int i = left - 1; i < right; i++) {
            sum += sums[i];
        }

        return (int) (sum % mod);
    }

    public int rangeSum2(int[] nums, int n, int left, int right) {
        Arrays.sort(nums);

        // 前缀和
        long[] prefixSum = new long[n + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
            minHeap.add(new int[]{nums[i],i});
        }
        int count = 1;
        long sum = 0;
        while (count <= right) {
            int[] top = minHeap.poll();
            if (count >= left ) {
                sum += top[0];
            }
            if (top[1] < n - 1) {
                minHeap.add(new int[]{top[0] + nums[top[1] + 1], top[1] + 1});
            }
            count++;
        }
        return (int) (sum % mod);
    }

    public static void main(String[] args) {
        RangeSum rangeSum = new RangeSum();
//        Assert.assertEquals(13, rangeSum.rangeSum2(ArrayUtil.randIntArray(10000), 10000, 1,200));
        /**
         * 1 3 6 10
         * 2 5 9
         * 3 7
         * 4
         */
        Assert.assertEquals(13, rangeSum.rangeSum(new int[]{1,2,3,4}, 4, 1,5));
        Assert.assertEquals(13, rangeSum.rangeSum2(new int[]{1,2,3,4}, 4, 1,5));
        Assert.assertEquals(13, rangeSum.rangeSum(new int[]{1,2,3,4,5}, 5, 1,5));
        Assert.assertEquals(13, rangeSum.rangeSum2(new int[]{1,2,3,4,5}, 5, 1,5));
        Assert.assertEquals(1, rangeSum.rangeSum(new int[]{1}, 1, 1,1));
        Assert.assertEquals(1, rangeSum.rangeSum2(new int[]{1}, 1, 1,1));
    }
}
