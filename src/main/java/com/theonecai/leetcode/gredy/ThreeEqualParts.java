package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 927
 * @Author: tbeonecai
 * @Date: Create in 2020/9/27 20:47
 * @Description:
 */
public class ThreeEqualParts {

    public int[] threeEqualParts(int[] A) {
        int oneCount = 0;
        for (int num : A) {
            if (num == 1) {
                oneCount++;
            }
        }
        if (oneCount == 0) {
            return new int[]{0, 2};
        }
        if (oneCount % 3 != 0) {
            return new int[]{-1, -1};
        }

        int count = 0;
        int firstPartFirstOneIndex = -1;
        int secondPartFirstOneIndex = -1;
        int thirdPartFirstOneIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                if (firstPartFirstOneIndex == -1) {
                    firstPartFirstOneIndex = i;
                }
                if (count == oneCount / 3) {
                    secondPartFirstOneIndex = i;
                }
                if (count == oneCount / 3 * 2) {
                    thirdPartFirstOneIndex = i;
                }
                count++;
            }
        }
        int len = checkValue(A, firstPartFirstOneIndex, secondPartFirstOneIndex, thirdPartFirstOneIndex);
        if (len > 0) {
            return new int[]{firstPartFirstOneIndex + len - 1,
                    secondPartFirstOneIndex + len};
        } else {
            return new int[]{-1, -1};
        }
    }

    private int checkValue ( int[] nums, int firstPartIndex, int secondePartIndex, int thirdPartIndex){
        int len = 0;
        int i = 0;
        while (i < nums.length - thirdPartIndex) {
            if (nums[firstPartIndex + i] != nums[secondePartIndex + i] ||
                    nums[firstPartIndex + i] != nums[thirdPartIndex + i]) {
                return -1;
            }

            len++;
            i++;
        }
        return len;
    }

    public static void main(String[] args) {
        ThreeEqualParts parts = new ThreeEqualParts();
        int[] res = parts.threeEqualParts(new int[]{1,0,1,0,1});
        System.out.println(Arrays.toString(res));
        Assert.assertTrue(Arrays.equals(new int[]{0, 3}, res));
        Assert.assertTrue(Arrays.equals(new int[]{-1, -1}, parts.threeEqualParts(new int[]{1,1,0,1,1})));
    }
}


