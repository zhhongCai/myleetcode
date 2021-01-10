package com.theonecai.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 228
 * @Author: theonecai
 * @Date: Create in 2021/1/10 10:18
 * @Description:
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            while (j < nums.length && nums[j] == nums[j - 1] + 1) {
                j++;
            }
            if (j - 1 == i) {
                result.add(nums[i] + "");
            } else {
                result.add(nums[i] + "->" + nums[j - 1]);
            }
            i += j - i;
        }

        return result;
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        System.out.println(summaryRanges.summaryRanges(new int[]{0}));
        System.out.println(summaryRanges.summaryRanges(new int[]{0,1,2,4,5,7}));
        System.out.println(summaryRanges.summaryRanges(new int[]{0,2,3,4,6,8,9}));
        System.out.println(summaryRanges.summaryRanges(new int[]{}));
    }
}
