package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 274.H
 * @Author: theonecai
 * @Date: Create in 2020/8/27 19:50
 * @Description:
 */
public class PaperHvalue {

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);

        return binarySearch(citations);
    }

    private int binarySearch(int[] references) {
        int hvalue = 0;
        int paperCount;
        int left = 0;
        int right = references.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            paperCount = references.length - mid;
            if (references[mid] >= paperCount) {
                hvalue = paperCount;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return hvalue;
    }

    public static void main(String[] args) {
        PaperHvalue hvalue = new PaperHvalue();
        int[] nums = {3, 4, 6, 9, 1};
        Assert.assertEquals(3, hvalue.hIndex(nums));

        int[] nums2 = {3};
        Assert.assertEquals(1, hvalue.hIndex(nums2));
    }
}
