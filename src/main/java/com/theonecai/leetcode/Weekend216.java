package com.theonecai.leetcode;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/11/22 10:24
 * @Description:
 */
public class Weekend216 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder a = new StringBuilder("");
        StringBuilder b = new StringBuilder("");
        for (int i = 0; i < word1.length; i++) {
            a.append(word1[i]);
        }
        for (int i = 0; i < word2.length; i++) {
            b.append(word2[i]);
        }
        return  a.toString().equals(b.toString());
    }

    public String getSmallestString(int n, int k) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        if (n == k) {
            return sb.toString();
        }
        int sum = n;
        int last = sb.length() - 1;
        while (sum <= k && last >= 0) {
            int kk = k - sum + 1;
            if (kk >= 26) {
                sb.replace(last, last + 1, "z");
                sum += 25;
            } else {
                sb.replace(last, last + 1, String.valueOf((char) (kk + 'a' - 1)));
                return sb.toString();
            }
            last--;
        }

        return sb.toString();
    }

    public int waysToMakeFair(int[] nums) {
        int sum = 0;
        int[] suffixSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i -= 2) {
            suffixSum[i] =  nums[i] + (i + 1 < nums.length ? suffixSum[i + 1] : 0);
            if (i - 1 >= 0) {
                suffixSum[i - 1] = suffixSum[i];
            }
        }

        int count = 0;
        int preEvenSum = 0;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if ((sum - nums[i]) % 2 == 0) {
                int half = (sum - nums[i]) / 2;
                if (preEvenSum > half) {
                    if (i % 2 == 0) {
                        preEvenSum += nums[i];
                    }
                    continue;
                }
                int ss = i + 1 < nums.length ? suffixSum[i + 1] : 0;
                if (nums.length % 2 == 0) {
                    if (preEvenSum + ss == half) {
                        count++;
                    }
                } else {
                    if (preEvenSum + sum - preSum - ss == half) {
                        count++;
                    }
                }


            }
            if (i % 2 == 0) {
                preEvenSum += nums[i];
            }
        }
        return count;
    }

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, ((o1, o2) -> o2[1] - o2[0] - (o1[1] - o1[0])));
        int result = tasks[0][1];
        int remain = tasks[0][1] - tasks[0][0];
        for (int i = 1; i < tasks.length; i++) {
            if (remain < tasks[i][1]) {
                result += tasks[i][1] - remain;
                remain = tasks[i][1] - tasks[i][0];
            } else {
                remain -= tasks[i][0];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Weekend216 weekend216 = new Weekend216();
        weekend216.test();
        weekend216.test2();
        weekend216.test3();
        weekend216.test4();
    }

    private void test4() {
        Assert.assertEquals(32, this.minimumEffort(new int[][]{
                {1,3},{2,4},{10,11},{10,12},{8,9},
        }));
        Assert.assertEquals(8, this.minimumEffort(new int[][]{
                {1,2},
                {2,4},
                {4,8},
        }));
        Assert.assertEquals(27, this.minimumEffort(new int[][]{
                {1,7},{2,8},{3,9},{4,10},{5,11},{6,12},
        }));
    }

    private void test3() {
        Assert.assertEquals(3, this.waysToMakeFair(new int[]{1,1,1}));
        Assert.assertEquals(1, this.waysToMakeFair(new int[]{2,1,6,4}));
        Assert.assertEquals(1, this.waysToMakeFair(new int[]{2,1,6,4}));
        Assert.assertEquals(0, this.waysToMakeFair(new int[]{1,2,3}));

    }

    private void test2() {

        System.out.println(this.getSmallestString(5, 73));
        System.out.println(this.getSmallestString(3, 27));
    }

    private void test() {
        Assert.assertTrue(this.arrayStringsAreEqual(new String[]{"a","b"}, new String[]{"a","b"}));
    }
}
