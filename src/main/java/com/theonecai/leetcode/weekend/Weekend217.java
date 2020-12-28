package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @Author: theonecai
 * @Date: Create in 2020/11/29 10:24
 * @Description:
 */
public class Weekend217 {

    public int maximumWealth(int[][] accounts) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0;
            for (int j = 0; j < accounts[0].length; j++) {
                sum += accounts[i][j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    public int[] mostCompetitive(int[] nums, int k) {
         Stack<Integer> stack = new Stack<>();
         stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int top = stack.peek();
            if (top < nums[i] && stack.size() < k) {
                stack.push(nums[i]);
            } else {
                int remain = nums.length - i;
                int needed = k - stack.size();
                while (!stack.isEmpty() && stack.peek() > nums[i] && remain - needed > 0) {
                    stack.pop();
                    needed = k - stack.size();
                }
                if (stack.size() < k) {
                    stack.push(nums[i]);
                }
            }
        }
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class PairNum implements Comparable<PairNum> {
        int a;
        int b;
        int sum;

        public PairNum(int a, int b, int maxSum) {
            this.a = a;
            this.b = b;
            this.sum = this.a + this.b;
        }

        @Override
        public int compareTo(PairNum o) {
            return this.sum - o.sum;
        }
    }

    public int minMoves(int[] nums, int limit) {
        PairNum[] sum = new PairNum[nums.length / 2];
        Map<Integer, Integer> count = new HashMap<>(sum.length);
        for (int i = 0; i < sum.length; i++) {
            sum[i] = new PairNum(nums[i], nums[nums.length - 1 - i], getMaxSum(nums[i], nums[nums.length - 1 -i], limit));
            count.put(sum[i].sum, count.getOrDefault(sum[i].sum, 0) + 1);
        }

        if (count.size() == 1) {
            return 0;
        }

        List<Map.Entry<Integer, Integer>> list = count.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
        int i = 0;
        int expectSum = list.get(i).getKey();

        int m = nums.length;
        for (int j = 0; j < sum.length; j++) {
            if (expectSum == sum[j].sum) {
                m -= 2;
                continue;
            }
            if (expectSum > 2 * limit) {
                if (sum[j].a > limit || sum[j].b > limit) {
                    m -= 1;
                }
            } else {
                if (expectSum - sum[j].a <= sum[j].b || expectSum - sum[j].b <= sum[j].a) {
                    m  -= 1;
                }
            }

        }
        return m;
    }

    private int getMaxSum(int a, int b, int limit) {
        if (a > limit) {
            if (b > limit) {
                return a + b;
            } else {
                return a + limit;
            }
        } else {
            if (b > limit) {
                return b + limit;
            } else {
                return limit * 2;
            }
        }
    }


    public static void main(String[] args) {
        Weekend217 weekend217 = new Weekend217();
        weekend217.test();
        weekend217.test2();
        weekend217.test3();
        weekend217.test4();
    }

    private void test4() {

    }

    private void test3() {
        Assert.assertEquals(4, this.minMoves(new int[]{28,50,76,80,64,30,32,84,53,8}, 84));
        Assert.assertEquals(1, this.minMoves(new int[]{1,2,4,3}, 4));
        Assert.assertEquals(2, this.minMoves(new int[]{1,2,2,1}, 2));
        Assert.assertEquals(0, this.minMoves(new int[]{1,2,1,2}, 2));

    }

    private void test2() {
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{2,4,3,3,5,1,9,6}, 4)));
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{3,5,2,6}, 2)));
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{5,4,3,2,1}, 2)));
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{1,2,3,4,5}, 2)));
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{1,2,3,4,5}, 1)));
        System.out.println(Arrays.toString(this.mostCompetitive(new int[]{6,5,4,3,2,1}, 1)));
    }

    private void test() {
    }
}
