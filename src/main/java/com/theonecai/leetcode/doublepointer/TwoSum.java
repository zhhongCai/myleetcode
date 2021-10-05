package com.theonecai.leetcode.doublepointer;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 167
 */
public class TwoSum {

    public int[] twoSum2(int[] numbers, int target) {
         int left = 0;
         int right = numbers.length - 1;
         while (left < right) {
             int sum = numbers[left] + numbers[right];
             if (sum == target) {
                 break;
             }
             if (sum < target) {
                left++;
             } else {
                right--;
             }
         }

         return new int[]{left + 1, right + 1};
    }
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int num = target - numbers[i];
            int idx = Arrays.binarySearch(numbers, i + 1, numbers.length, num);
            if (idx >= 0) {
                return new int[]{i + 1, idx + 1};
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        Assert.assertArrayEquals(new int[]{1,2}, twoSum.twoSum(new int[]{0,0,3,4}, 0));
        Assert.assertArrayEquals(new int[]{1,2}, twoSum.twoSum(new int[]{2,7,11,15}, 9));
        Assert.assertArrayEquals(new int[]{1,3}, twoSum.twoSum(new int[]{2,3,4}, 6));
        Assert.assertArrayEquals(new int[]{1,2}, twoSum.twoSum(new int[]{-1,0}, -1));
    }
}
