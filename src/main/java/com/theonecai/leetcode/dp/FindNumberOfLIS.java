package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 673
 */
public class FindNumberOfLIS {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表以nums[i]结尾的递增子序列的长度
        int[] dp = new int[n];
        // count[i]表以nums[i]结尾的递增子序列个数
        int[] count = new int[n];
        int maxLen = 1;
        int res = 1;
        dp[0] = 1;
        count[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]){
                        count[i] += count[j];
                    }
                }
            }
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                res = count[i];
            }  else if (maxLen == dp[i]) {
                res += count[i];
            }
        }
        return res;
    }

    /**
     * 列出最长递增子序列
     * @param nums
     * @return
     */
    public List<List<Integer>> listOfLIS(int[] nums) {
        int n = nums.length;
        // dp[i]表以nums[i]结尾的递增子序列的长度
        int[] dp = new int[n];
        int maxLen = 1;
        dp[0] = 1;
        List<List<Integer>> list = new ArrayList<>();
        list.add(Collections.singletonList(nums[0]));
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (maxLen < dp[i]) {
                maxLen = dp[i];
            }
        }
        List<List<Integer>> res = new ArrayList<>(maxLen);
        for (int i = 0; i < maxLen; i++) {
            res.add(new ArrayList<>());
        }
        int last = n;
        for (int j = maxLen; j > 0; j--) {
            for (int i = last - 1; i >= 0; i--) {
                if (dp[i] == j) {
                    res.get(j - 1).add(nums[i]);
                    last = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindNumberOfLIS numberOfLIS = new FindNumberOfLIS();
        Assert.assertEquals(2, numberOfLIS.findNumberOfLIS(new int[]{1,3,5,4,7}));
        List<List<Integer>> res = numberOfLIS.listOfLIS(new int[]{1, 3, 5, 4, 7});
        for (List<Integer> re : res) {
            System.out.println(res);
        }
        Assert.assertEquals(5, numberOfLIS.findNumberOfLIS(new int[]{2,2,2,2,2}));
    }
}
