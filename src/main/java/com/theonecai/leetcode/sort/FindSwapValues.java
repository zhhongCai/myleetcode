package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 16.21
 * @Author: theonecai
 * @Date: Create in 2020/9/10 21:26
 * @Description:
 */
public class FindSwapValues {

    public int[] findSwapValues(int[] array1, int[] array2) {
        long diff = 0;
        int len = Math.max(array1.length, array2.length);
        for (int i = 0; i < len; i++) {
            if (i < array1.length) {
                diff += array1[i];
            }
            if (i < array2.length) {
                diff -= array2[i];
            }
        }
        if (diff == 0) {
            return new int[0];
        }

        Arrays.sort(array1);

        /**
         * sum1为array1总和
         * sum2为array2总和
         * a in array1, b in array2:
         * 交换a,b后相等
         * sum1 - a + b = sum2 + a - b;
         * sum1 - sum2 = 2a - 2b = diff;
         * => a = (diff + 2b) / 2;
         */
        for (int b : array2) {
            long tmp = diff + 2 * b;
            long a = tmp / 2;
            if (a > Integer.MAX_VALUE) {
                return new int[0];
            }
            if (tmp % 2 == 0 && search(array1, (int) a) != -1) {
                return new int[]{(int) a, b};
            }
        }

        return new int[0];
    }

    private int search(int[] array1, int a) {
        int low = 0;
        int high = array1.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (array1[mid] == a) {
                return mid;
            } else if (array1[mid] > a) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindSwapValues findSwapValues = new FindSwapValues();
        int[] res = findSwapValues.findSwapValues(new int[]{519, 886, 282, 382, 662, 4718, 258, 719, 494, 795},
                new int[]{52, 20, 78, 50, 38, 96, 81, 20});
        System.out.println(Arrays.toString(res));
        Assert.assertEquals("[4718, 78]", Arrays.toString(res));

        int[] arr = ArrayUtil.randIntArray(10);
        int[] arr2 = ArrayUtil.randIntArray(10);
        RunUtil.runAndPrintCostTime(() -> {
            int[] res2 = findSwapValues.findSwapValues(arr, arr2);
            System.out.println(Arrays.toString(res2));
        });
    }
}
