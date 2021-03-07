package com.theonecai.leetcode.weekend;


import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2021/03/07 10:24
 * @Description:
 */
public class Weekend231 {

    public boolean checkOnesSegment(String s) {
        int flag = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (flag == -1) {
                    flag = 0;
                } else if (flag == 1) {
                    return false;
                }
            } else {
                if (flag == 0) {
                    flag = 1;
                }
            }
        }
        return true;
    }

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long remain = goal - sum;
        int count = 0;
        while (remain != 0) {
            if (remain < 0) {
                remain = -remain;
            }
            long n = remain / limit;
            remain -= n * limit;
            count += n;
            if (remain == 0) {
                break;
            }
            remain -= Math.min(limit, remain);
            count++;
        }

        return count;
    }

    public int countRestrictedPaths(int n, int[][] edges) {
        return 0;
    }


    public static void main(String[] args) {
        Weekend231 weekend = new Weekend231();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
    }

    private void test2() {
        Assert.assertEquals(2, this.minElements(new int[]{1,-1,1}, 3, -4));
        Assert.assertEquals(0, this.minElements(new int[]{1,-1,1}, 3, 1));
        Assert.assertEquals(1, this.minElements(new int[]{1,-10,9,1}, 100, 0));
    }

    private void test() {
        Assert.assertTrue(this.checkOnesSegment("1100"));
        Assert.assertFalse(this.checkOnesSegment("11010"));
    }
}
