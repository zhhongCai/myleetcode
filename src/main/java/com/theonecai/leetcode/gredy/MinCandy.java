package com.theonecai.leetcode.gredy;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 135
 * @Author: theonecai
 * @Date: Create in 2020/9/14 20:36
 * @Description:
 */
public class MinCandy {

    public int candy(int[] ratings) {
        int[] count = new int[ratings.length];
        Arrays.fill(count, 1);
        for (int i = 1, j = ratings.length - 2; i < ratings.length; i++, j--) {
            if (ratings[i] > ratings[i - 1]) {
                count[i] = Math.max(count[i], count[i - 1] + 1);
            }
            if (ratings[j] > ratings[j + 1]) {
                count[j] = Math.max(count[j], count[j + 1] + 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += count[i];
        }

        return sum;
    }

    public int candy2(int[] ratings) {
        int sum = 0;
        int currentGEL;
        // ratings[-1]=-1
        int preGEL = 1;
        int up = 0;
        int down = 0;
        for (int i = 1; i < ratings.length; i++) {
            currentGEL = Integer.compare(ratings[i], ratings[i - 1]);
            if ((currentGEL == 0 && preGEL > 0) || (preGEL < 0 && currentGEL >= 0)) {
                sum += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }

            if (currentGEL > 0) {
                up++;
            } else if (currentGEL < 0) {
                down++;
            } else {
                sum++;
            }

            preGEL = currentGEL;
        }
        sum += count(up) + count(down) + Math.max(up, down) + 1;

        return sum;
    }

    private int count(int n) {
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        MinCandy minCandy = new MinCandy();
        Assert.assertEquals(6, minCandy.candy(new int[]{3,2,1}));
        Assert.assertEquals(6, minCandy.candy2(new int[]{3,2,1}));
        Assert.assertEquals(7, minCandy.candy(new int[]{1,3,2,2,1}));
        Assert.assertEquals(7, minCandy.candy2(new int[]{1,3,2,2,1}));
        Assert.assertEquals(5, minCandy.candy(new int[]{1,0,2}));
        Assert.assertEquals(5, minCandy.candy2(new int[]{1,0,2}));
        Assert.assertEquals(6, minCandy.candy(new int[]{1,2,3}));
        Assert.assertEquals(6, minCandy.candy2(new int[]{1,2,3}));
        Assert.assertEquals(18, minCandy.candy(new int[]{8,8,4,1,1,2,8,2,1,5}));
        Assert.assertEquals(18, minCandy.candy2(new int[]{8,8,4,1,1,2,8,2,1,5}));
        Assert.assertEquals(16, minCandy.candy(new int[]{8,4,1,9,1,1,6,5,5,0}));
        Assert.assertEquals(16, minCandy.candy2(new int[]{8,4,1,9,1,1,6,5,5,0}));
        Assert.assertEquals(20, minCandy.candy(new int[]{7,3,0,8,1,3,6,9,3,3}));
        Assert.assertEquals(20, minCandy.candy2(new int[]{7,3,0,8,1,3,6,9,3,3}));
        for (int i = 0; i < 10; i++) {
            int[] nums = ArrayUtil.randIntArray(1000000);
            Assert.assertEquals(minCandy.candy(nums), minCandy.candy2(nums));
        }
    }
}
