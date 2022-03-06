package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 2100
 */
public class GoodDaysToRobBank {

    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] pre = new int[n];
        int[] next= new int[n];
        pre[0] = 0;
        next[n - 1] = 0;
        for (int i = 1, j = n - 2; i < n; i++, j--) {
            if (security[i] <= security[i - 1]) {
                pre[i] = pre[i - 1] + 1;
            }
            if (security[j] <= security[j + 1]) {
                next[j] = next[j + 1] + 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (pre[i] >= time && next[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        GoodDaysToRobBank robBank = new GoodDaysToRobBank();
        Assert.assertArrayEquals(new int[]{2,3}, robBank.goodDaysToRobBank(new int[]{5,3,3,3,5,6,2}, 2)
            .stream().mapToInt(Integer::intValue).toArray());
        Assert.assertArrayEquals(new int[]{0,1,2,3,4}, robBank.goodDaysToRobBank(new int[]{1,1,1,1,1}, 0)
                .stream().mapToInt(Integer::intValue).toArray());
        Assert.assertArrayEquals(new int[]{}, robBank.goodDaysToRobBank(new int[]{1,2,3,4,5,6}, 2)
                .stream().mapToInt(Integer::intValue).toArray());
        Assert.assertArrayEquals(new int[]{}, robBank.goodDaysToRobBank(new int[]{1}, 5)
                .stream().mapToInt(Integer::intValue).toArray());
    }
}
