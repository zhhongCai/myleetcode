package com.theonecai.leetcode.tree;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/30 16:44
 * @Description:
 */
public class NumOfWays {

    private long mod = 1000000007L;

    /**
     * c[i][j] = i个数取j个的组合
     */
    private long[][] c;

    public NumOfWays() {
        c = new long[1001][1001];
        for (int i = 0; i < c[0].length; i++) {
            c[i][0] = 1;
        }

        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;
            }
        }
    }

    public int numOfWays(int[] nums) {
        return countWays(Arrays.stream(nums).boxed().collect(Collectors.toList())) - 1;
    }

    public int countWays(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 1;
        }
        List<Integer> greater = new ArrayList<>();
        List<Integer> less = new ArrayList<>();
        int first = -1;
        for (Integer num : nums) {
            if (first == -1) {
                first = num;
                continue;
            }
            if (num > first) {
                greater.add(num);
            }
            if (num < first) {
                less.add(num);
            }
        }

        int leftCount = countWays(less);
        int rightCount = countWays(greater);

        return (int) (c[less.size() + greater.size()][greater.size()] * leftCount % mod * rightCount % mod);
    }

    public static void main(String[] args) {
        NumOfWays numOfWays = new NumOfWays();
        int[] nums = {2,1,3};
        Assert.assertEquals(1, numOfWays.numOfWays(nums));

        int[] nums2 = {3,4,5,1,2};
        Assert.assertEquals(5, numOfWays.numOfWays(nums2));

        int[] nums3 = {1,2,3};
        Assert.assertEquals(0, numOfWays.numOfWays(nums3));

        int[] nums4 = {3,1,2,5,4,6};
        Assert.assertEquals(19, numOfWays.numOfWays(nums4));

        int[] nums5 = {9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18};
        Assert.assertEquals(216212978, numOfWays.numOfWays(nums5));
    }
}
