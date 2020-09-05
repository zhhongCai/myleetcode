package com.theonecai.leetcode.sort;

import java.util.Arrays;

/**
 * leetcode 1054 同 342
 * @Author: theonecai
 * @Date: Create in 2020/9/5 10:19
 * @Description:
 */
public class RearrangeBarcodes {
    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes == null || barcodes.length < 2) {
            return barcodes;
        }

        Arrays.sort(barcodes);

        toOrderedPairs(barcodes);

        return barcodes;
    }

    private void toOrderedPairs(int[] nums) {
        int[] result = new int[nums.length];
        int mid = (nums.length + 1) / 2;
        for (int i = 0, odd = mid - 1, event = nums.length - 1 ; i < nums.length; i += 2, odd--, event--) {
            result[i] = nums[odd];
            if (i + 1 < nums.length) {
                result[i + 1] = nums[event];
            }
        }
        if (mid > 1 && result[mid] ==  result[mid - 1] && mid + 1 < nums.length) {
            int tmp = result[mid];
            result[mid] = result[mid + 1];
            result[mid + 1] = tmp;
        }

        System.arraycopy(result, 0, nums, 0, result.length);
    }

    public static void main(String[] args) {
        RearrangeBarcodes rearrangeBarcodes = new RearrangeBarcodes();
        int[] barcodes = {1,1,1,2,2,2};
        System.out.println(Arrays.toString(rearrangeBarcodes.rearrangeBarcodes(barcodes)));
        int[] barcodes2 = {1,1,1,1,2,2,3,3};
        System.out.println(Arrays.toString(rearrangeBarcodes.rearrangeBarcodes(barcodes2)));
        int[] barcodes3 = {2,2,2,1,5};
        System.out.println(Arrays.toString(rearrangeBarcodes.rearrangeBarcodes(barcodes3)));
        int[] barcodes4 = {2,2,1,3};
        System.out.println(Arrays.toString(rearrangeBarcodes.rearrangeBarcodes(barcodes4)));
    }
}
