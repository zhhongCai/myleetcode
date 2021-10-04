package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/10/02 22:24
 * @Description:
 */
public class WeekendD62 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }
        int[][] nums = new int[m][n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = original[idx++];
            }
        }
        return nums;
    }

    public int numOfPairs(String[] nums, String target) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((nums[i] + nums[j]).equals(target)) {
                    res++;
                }
                if ((nums[j] + nums[i]).equals(target)) {
                    res++;
                }
            }
        }
        return res;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        if (n == k) {
            return n;
        }
        char[] answers = answerKey.toCharArray();
        return Math.max(calc(answers, k, n, 'F'), calc(answers, k, n, 'T'));
    }

    private int calc(char[] answers, int k, int n, char notTarget) {
        Deque<Integer> reversedIndex = new LinkedList<>();
        int left = 0;
        int res = k;
        for (int i = 0; i < n; i++) {
            if (answers[i] == notTarget) {
                if (reversedIndex.size() == k) {
                    left = reversedIndex.removeFirst() + 1;
                }
                reversedIndex.add(i);
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n];
        long total = nums[0];
        preSum[0] = nums[0];
        Map<Long, Integer> countRight = new HashMap<>();
        countRight.put((long)(k - nums[0]), 1);
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
            total += nums[i];
            long key = k - nums[i];
            countRight.put(key, countRight.getOrDefault(key, 0) + 1);
        }
        int res = 0;
        Map<Long, Integer> countLeft = new HashMap<>();

        int max = 0;
        for (int i = 1; i < n; i++) {
            long leftSum = preSum[i - 1];
            long rightSum = total - leftSum;
            res = 0;
            if (leftSum == rightSum) {
                res++;
            }
            // 替换左边: leftSum + k - nums[j] == rightSum (0 <= j < i)
            long key = k - nums[i - 1];
            countLeft.put(key, countLeft.getOrDefault(key, 0) + 1);

            key = rightSum - leftSum;
            int leftCount = countLeft.getOrDefault(key, 0);

            // 替换右边: leftSum == rightSum + k - nums[j] (i <= j < n)
            key = k - nums[i - 1];
            int c = countRight.getOrDefault(key, 0);
            if (c <= 1) {
                countRight.remove(key);
            } else {
                countRight.put(key, c - 1);
            }
            key = leftSum - rightSum;
            int rightCount = countRight.getOrDefault(key, 0);
            max = Math.max(max, Math.max(leftCount, rightCount) + res);
        }

        return max;
    }


    public static void main(String[] args) {
        WeekendD62 weekend = new WeekendD62();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        //3 -1 | 2
        //2 | -1 3
        //0
        Assert.assertEquals(33, waysToPartition(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,30827,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 0));
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 0;
        }
        Assert.assertEquals(nums.length - 1, waysToPartition(nums, 1));
        Assert.assertEquals(6, waysToPartition(new int[]{0,0,0,0,0,0,0}, 2));
        Assert.assertEquals(1, waysToPartition(new int[]{2,-1}, 2));
        Assert.assertEquals(1, waysToPartition(new int[]{2,-1,2}, 3));
        Assert.assertEquals(2, waysToPartition(new int[]{0,0,0}, 1));
        Assert.assertEquals(4, waysToPartition(new int[]{22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14}, -33));

    }

    private void test3() {
        Assert.assertEquals(5, maxConsecutiveAnswers("TFTFTFTFTF", 2));
        Assert.assertEquals(85, maxConsecutiveAnswers("FTFFTFTFTTFTTFTTFFTTFFTTTTTFTTTFTFFTTFFFFFTTTTFTTTTTTTTTFTTFFTTFTFFTTTFFFFFTTTFFTTTTFTFTFFTTFTTTTTTF", 32));

        Assert.assertEquals(4, maxConsecutiveAnswers("TTFF", 2));
        Assert.assertEquals(3, maxConsecutiveAnswers("TFFT", 1));
        Assert.assertEquals(5, maxConsecutiveAnswers("TTFTTFTT", 1));
    }

    private void test2() {
    }

    private void test() {
    }
}
