package com.theonecai.leetcode.bitsect;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;

/**
 * leetcode 4
 * @Author: theonecai
 * @Date: Create in 2020/7/7 19:40
 * @Description:
 */
public class TowSortedArrayFindMid {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return 0;
        }

        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = nums1.length;

        int leftPartTotal = (nums1.length + nums2 .length + 1) / 2;
        while (left < right) {
            int i = left + (right - left + 1) / 2;
            int j = leftPartTotal - i;
            if (nums1[i - 1] > nums2[j]) {
                right = i - 1;
            } else {
                left = i;
            }
        }

        int i = left;
        int j = leftPartTotal - i;
        int rightPartMin = i == nums1.length ? Integer.MAX_VALUE : nums1[i];
        int leftPartMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int rightPartMin2 = j == nums2.length ? Integer.MAX_VALUE : nums2[j];
        int leftPartMax2 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];

        if ((nums1.length + nums2.length) % 2 == 1) {
            return Math.max(leftPartMax, leftPartMax2);
        } else {
            return (Math.max(leftPartMax, leftPartMax2) + Math.min(rightPartMin, rightPartMin2)) / 2.0;
        }
    }


    public static void main(String[] args) {
        TowSortedArrayFindMid find = new TowSortedArrayFindMid();

        long st;
        Random random = new Random(10000);
        for (int k = 0; k < 1000; k++) {
            int[] aa = ArrayUtil.randIntArray(random.nextInt(5) + 1);
            int[] bb = ArrayUtil.randIntArray(random.nextInt(6) + 1);
            Arrays.sort(aa);
            Arrays.sort(bb);
            int[] c = new int[aa.length + bb.length];
            int idx = 0;
            for (int i : aa) {
                c[idx++] = i;
            }
            for (int i : bb) {
                c[idx++] = i;
            }
            Arrays.sort(c);
            try {

                st = System.currentTimeMillis();
                if (c.length % 2 == 0) {
                    Assert.assertEquals((c[c.length/2] + c[c.length/2 - 1]) / 2.0,  find.findMedianSortedArrays(aa, bb), 0.0001);
                } else {
                    Assert.assertEquals(c[c.length/2],  find.findMedianSortedArrays(aa, bb), 0.0001);
                }
//                System.out.println("costtime: " + (System.currentTimeMillis() - st));
            } catch (Throwable e) {
                ArrayUtil.print(aa);
                ArrayUtil.print(bb);
                ArrayUtil.print(c);
                e.printStackTrace();
                break;
            }
        }

    }
}
