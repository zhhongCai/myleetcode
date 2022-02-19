package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2022/01/23 10:24
 * @Description:
 */
public class Weekend277 {

    public int countElements(int[] nums) {

        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[0] && nums[i] < nums[nums.length - 1]) {
                ans++;
            }
        }
        return ans;
    }


    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int ng = 0;
        int p = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i += 2) {
            while(nums[ng] < 0) {
                ng++;
            }
            while (nums[p] > 0) {
                p++;
            }
            ans[i] = nums[ng];
            ans[i + 1] = nums[p];
            ng++;
            p++;
        }

        return ans;
    }

    public List<Integer> findLonely(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (n == 1) {
                    ans.add(nums[i]);
                } else {
                    if (nums[i] + 1 != nums[i + 1] && nums[i] != nums[i + 1]) {
                        ans.add(nums[i]);
                    }
                }
            } else if (i == n - 1) {
                if (nums[i - 1] + 1 != nums[i] && nums[i - 1] != nums[i]) {
                    ans.add(nums[i]);
                }
            } else {
                if (nums[i - 1] + 1 != nums[i] && nums[i] + 1 != nums[i + 1] && nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    ans.add(nums[i]);
                }
            }
        }
        return ans;
    }

    public int maximumGood(int[][] statements) {
        int n = statements.length;
        int state = (1 << n) - 1;
        int max = 0;
        for (int i = state; i > 0; i--) {
            if (check(i, statements)) {
                max = Math.max(max, Integer.bitCount(i));
            }
        }
        return max;
    }

    private boolean check(int state, int[][] statements) {
        int n = statements.length;
        for (int i = 0; i < n; i++) {
            int iState = (state >> i) & 1;
            if (iState == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (statements[i][j] == 2) {
                    continue;
                }
                int jState = (state >> j) & 1;
                if (jState != statements[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Weekend277 weekend = new Weekend277();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

        Assert.assertEquals(1, maximumGood(new int[][]{{2,2,2,2},{1,2,1,0},{0,2,2,2},{0,0,0,2}}));
        Assert.assertEquals(2, maximumGood(new int[][]{{2,0,2,2,0},{2,2,2,1,2},{2,2,2,1,2},{1,2,0,2,2},{1,0,2,1,2}}));
        Assert.assertEquals(2, maximumGood(new int[][]{{2,1,2},{1,2,2},{2,0,2}}));
        Assert.assertEquals(1, maximumGood(new int[][]{{2,0},{0,2}}));
    }

    private void test3() {
    }

    private void test2() {
    }

    private void test() {
    }
}
