package com.theonecai.leetcode.weekend;


import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2021/08/01 10:24
 * @Description:
 */
public class Weekend252 {
    public boolean isThree(int n) {
        int c = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                c++;
            }
        }
        return c == 3;
    }

    public long numberOfWeeks(int[] m) {
        if (m.length == 1) {
            return 1;
        }
        long sum = 0;
        long max = 0;
        for (int n : m) {
            sum += n;
            max = Math.max(max, n);
        }
        if (sum - max < max) {
            return (sum - max) * 2 + 1;
        }

        return sum;
    }


    public static void main(String[] args) {
        Weekend252 weekend = new Weekend252();
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
        /**
         *
         Arrays.sort(milestones);
         long count = 0;
         int left = 0;
         int right = milestones.length - 1;
         while (left < right) {
         if (milestones[left] > milestones[right]) {
         count += milestones[right] * 2;
         milestones[left] -= milestones[right];
         milestones[right] -= milestones[right];
         right--;
         } else if (milestones[left] == milestones[right]) {
         count += milestones[left] * 2;
         milestones[left] = 0;
         milestones[right] = 0;
         left++;
         right--;
         } else {
         count += milestones[left] * 2;
         milestones[right] -= milestones[left];
         milestones[left] -= milestones[left];
         left++;
         }
         }
         if (milestones[right] > 0) {
         count++;
         }
    2 6
         */
        Assert.assertEquals(40, this.numberOfWeeks(new int[]{5,7,5,7,9,7}));
        Assert.assertEquals(16, this.numberOfWeeks(new int[]{4,5,5,2}));
        Assert.assertEquals(29, this.numberOfWeeks(new int[]{9,3,6,8,2,1}));
        Assert.assertEquals(6, this.numberOfWeeks(new int[]{1,2,3}));
        Assert.assertEquals(7, this.numberOfWeeks(new int[]{5,1,2}));
        Assert.assertEquals(28, this.numberOfWeeks(new int[]{1,2,3,4,5,6,7}));
    }

    private void test() {
    }
}
