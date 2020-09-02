package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 327
 * @Author: theonecai
 * @Date: Create in 2020/9/1 20:10
 * @Description:
 */
public class CountRangeSum {
    /**
     * 树状数组：
     * prefixSum(i)= nums[0] + nums[1] +...+ nums[i]
     * 0<=i<=j<=nums.length - 1,
     * prefixSum(j) - prefixSum(i - 1) 为nums[i]到nums[j]的区间和
     * 条件： lower <= prefixSum(j) - prefixSum(i - 1) <= upper  (i=0时, prefixSum[-1] = 0)
     * 即: lower + prefixSum(i - 1) <= prefixSum(j) <= upper + prefixSum(i - 1)
     * 条件转换为对前缀和prefixSum数组中元素个数的计算，使用树状数组biTree存储已访问元素个数：
     * 遍历每个prefixSum的元素，(根据排序后的索引)更新到biTree
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // preSum[i] 表示nums[0~i]的和
        long[] prefixSum = new long[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        // 排序后的前缀和
        long[] sortedPrefixSum = Arrays.copyOf(prefixSum, prefixSum.length);
        Arrays.sort(sortedPrefixSum);

        int count = 0;
        int[] biTree = new int[sortedPrefixSum.length + 1];
        // 必须从后往前
        for (int i = prefixSum.length - 1; i >= 0; i--) {
            // 更新biTree
            update(biTree, indexOf(sortedPrefixSum, prefixSum[i]), 1);
            long pre = i == 0 ? 0 : prefixSum[i - 1];
            int lowerIndex = indexOf(sortedPrefixSum, pre + lower - 1);
            int upperIndex = Math.min(indexOf(sortedPrefixSum, pre + upper), sortedPrefixSum.length);
            // 求当前满足条件(lower + preSum(i - 1) <= preSum(j) <= upper + preSum(i - 1))的数的个数
            count += getSum(biTree, upperIndex) - getSum(biTree, lowerIndex);
        }

        return count;
    }

    /**
     * 求出sum在sortedPreSum中的index
     * @param sortedPreSum
     * @param sum
     * @return
     */
    private int indexOf(long[] sortedPreSum, long sum) {
        int left = 0;
        int right = sortedPreSum.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (sortedPreSum[mid] < sum) {
                left = mid + 1;
            } else if (sortedPreSum[mid] > sum){
                right = mid - 1;
            } else {
                // 找到，+1是因为biTree从下标1开始
                return mid + 1;
            }
        }
        //没找到
        return left;
    }

    /**
     * num的二进制表示中从右往左第一个1的位置
     * @param num
     * @return
     */
    private int lowbit(int num) {
        return num & (-num);
    }

    /**
     * biTree中 <= sortedPreSum[index]的数的个数
     * @param biTree
     * @param index
     * @return
     */
    private int getSum(int[] biTree, int index) {
        int i = index;
        int s = 0;
        while (i > 0) {
            s += biTree[i];
            i -= lowbit(i);
        }
        return s;
    }

    private void update(int[] biTree, int index, int value) {
        int i = index;
        while (i < biTree.length) {
            biTree[i] += value;
            i += lowbit(i);
        }
    }


    public static void main(String[] args) {
        CountRangeSum countRangeSum = new CountRangeSum();
        /**
         * preSum:          -2 3 2 1
         * sortedPreSum :   -2 1 2 3
         * biTree: 0 1 1 0 1 0
         * count:  2 +
         *
         */
        int[] nums = {-2, 5, -1, -1};
        Assert.assertEquals(6, countRangeSum.countRangeSum(nums, -2, 2));
        /**
         * -2 3 2
         * -2 2 3
         *
         */
        int[] nums2 = {-2, 5, -1};
        Assert.assertEquals(3, countRangeSum.countRangeSum(nums2, -2, 2));
        int[] nums3 = {1, 1, 1};
        Assert.assertEquals(6, countRangeSum.countRangeSum(nums3, 0, 5));
        int[] nums5 = {1};
        Assert.assertEquals(1, countRangeSum.countRangeSum(nums5, 0, 5));
        int n = 1000000;
        int[] nums4 = new int[n];
        for (int i = 0; i < n; i++) {
            nums4[i] = 1;
        }
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(1784293664, countRangeSum.countRangeSum(nums4, 0, n)));
    }
}
