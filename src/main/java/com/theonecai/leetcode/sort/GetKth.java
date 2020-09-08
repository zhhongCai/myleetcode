package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 1387
 * @Author: theonecai
 * @Date: Create in 2020/9/7 19:59
 * @Description:
 */
public class GetKth {

    public int getKth(int lo, int hi, int k) {
        int[][] nums = new int[hi - lo + 1][2];
        for (int i = 0; i < nums.length; i++) {
            nums[i][0] = lo + i;
            nums[i][1] = calculate(nums[i][0]);
        }

        Arrays.sort(nums, Comparator.comparingInt(o -> o[1]));

        return nums[k - 1][0];
    }

    private int calculate(int num) {
        int count = 0;
        int result = num;
        while (result != 1) {
            result = result % 2 == 0 ? result / 2 : (result * 3 + 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        GetKth getKth = new GetKth();
        Assert.assertEquals(2, getKth.getKth(1, 5, 2));
        Assert.assertEquals(13, getKth.getKth(12, 15, 2));
        Assert.assertEquals(1, getKth.getKth(1, 1, 1));
        Assert.assertEquals(7, getKth.getKth(7, 11, 4));
        Assert.assertEquals(13, getKth.getKth(10, 20, 5));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(570, getKth.getKth(1, 1000, 777)));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(570, getKth.getKth(1, 1000, 777)));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(570, getKth.getKth(1, 1000, 777)));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(570, getKth.getKth(1, 1000, 777)));
        RunUtil.runAndPrintCostTime(() -> Assert.assertEquals(570, getKth.getKth(1, 1000, 777)));
    }
}
