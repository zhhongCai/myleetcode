package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: theonecai
 * @Date: Create in 2022/01/09 10:24
 * @Description:
 */
public class Weekend275 {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            boolean[] row = new boolean[n + 1];
            for (int j = 0; j < n; j++) {
                row[matrix[i][j]] = true;
            }
            for (int j = 1; j < row.length; j++) {
                if (!row[j]) {
                    return false;
                }
            }
            boolean[] col = new boolean[n + 1];
            for (int j = 0; j < n; j++) {
                col[matrix[j][i]] = true;
            }
            for (int j = 1; j < col.length; j++) {
                if (!col[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int minSwaps(int[] nums) {
        int oneCount = 0;
        for (int num : nums) {
            oneCount += num;
        }
        int cur = 0;
        for (int i = 0; i < oneCount; i++) {
            cur += nums[i];
        }

        int n = nums.length;
        int n2 = n * 2;
        int min = Integer.MAX_VALUE;
        for (int i = oneCount; i < n2; i++) {
            cur = cur - nums[(i - oneCount) % n] + nums[i % n];
            min = Math.min(min, oneCount - cur);
        }

        return min;
    }

    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Long> set = new HashSet<>(startWords.length);
        for (String w : startWords) {
            set.addAll(orderStr(w));
        }

        int ans = 0;
        for (String t : targetWords) {
            int[] count = new int[26];
            for (int i = 0; i < t.length(); i++) {
                count[t.charAt(i) - 'a']++;
            }
            long s = 0;
            for (int i = 0; i < count.length; i++) {
                int c = count[i];
                while (c > 0) {
                    s += 1 << i;
                    c--;
                }
            }
            if (set.contains(s)) {
                ans++;
            }
        }
        return ans;
    }

    private Set<Long> orderStr(String w) {
        Set<Long> set = new HashSet<>();
        int[] count = new int[26];
        for (int i = 0; i < w.length(); i++) {
            count[w.charAt(i) - 'a']++;
        }
        long s = 0;
        for (int i = 0; i < count.length; i++) {
            int c = count[i];
            while (c > 0) {
                s += 1 << i;
                c--;
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                set.add(s + (1 << i));
            }
        }
        return set;
    }


    public static void main(String[] args) {
        Weekend275 weekend = new Weekend275();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(1, wordCount(new String[]{"ab","a"}, new String[]{"abc","abcd"}));
        Assert.assertEquals(2, wordCount(new String[]{"ant","act","tack"}, new String[]{"tack","act","acti"}));

    }

    private void test2() {
        Assert.assertEquals(1, minSwaps(new int[]{1,1,1,0,0,1,0,1,1,0}));
        Assert.assertEquals(2, minSwaps(new int[]{1,1,1,0,0,1,0,1,0,0}));
        Assert.assertEquals(1, minSwaps(new int[]{0,1,0,1,1,0,0}));
        Assert.assertEquals(2, minSwaps(new int[]{0,1,1,1,0,0,1,1,0}));
        Assert.assertEquals(0, minSwaps(new int[]{1,1,0,0,1}));
    }

    private void test() {
    }
}
