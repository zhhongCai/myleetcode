package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2021/12/19 10:24
 * @Description:
 */
public class Weekend272 {

    public String firstPalindrome(String[] words) {
        for (String word : words) {
            int i = 0;
            int j = word.length() - 1;
            while (i < j && word.charAt(i) == word.charAt(j)) {
                i++;
                j--;
            }
            if (i >= j) {
                return word;
            }
        }
        return "";
    }

    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int space : spaces) {
            sb.append(s.substring(i, space)).append(" ");
            i = space;
        }
        sb.append(s.substring(i));
        return sb.toString();
    }

    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        for (int i = 0; i < prices.length; i++) {
            int j = i + 1;
            while (j < prices.length && prices[j - 1] == prices[j] + 1) {
                j++;
            }
            j = Math.min(prices.length, j);
            long n = j - i;
            ans += n * (n + 1) / 2;
            i = j - 1;
        }

        return ans;
    }

    public int kIncreasing(int[] arr, int k) {
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int j = i + k;
            List<Integer> nums = new ArrayList<>();
            nums.add(arr[i]);
            while (j < arr.length) {
                nums.add(arr[j]);
                j += k;
            }
            ans += longIncSeq(nums);
        }

       return ans;
    }

    /**
     * LIS
     * @param nums
     * @return
     */
    private int longIncSeq(List<Integer> nums) {
        List<Integer> lis = new ArrayList<>();
        for (Integer num : nums) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) <= num) {
                lis.add(num);
                continue;
            }
            int i = find(lis, num);
            lis.set(i, num);
        }
        return nums.size() - lis.size();
    }

    private int find(List<Integer> lis, Integer num) {
        int left = 0;
        int right = lis.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (lis.get(mid) <= num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Weekend272 weekend = new Weekend272();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

        Assert.assertEquals(4, kIncreasing(new int[]{2,2,2,2,2,1,1,4,4,3,3,3,3,3}, 1));
        Assert.assertEquals(12, kIncreasing(new int[]{12,6,12,6,14,2,13,17,3,8,11,7,4,11,18,8,8,3}, 1));
        Assert.assertEquals(3, kIncreasing(new int[]{4,4,4,4,4,3,2,1}, 2));
        Assert.assertEquals(3, kIncreasing(new int[]{5,4,3,2,1}, 2));
        Assert.assertEquals(0, kIncreasing(new int[]{1,2,3,4,5}, 1));
        Assert.assertEquals(4, kIncreasing(new int[]{5,4,3,2,1}, 1));
        Assert.assertEquals(0, kIncreasing(new int[]{4,1,5,2,6,2}, 2));
        Assert.assertEquals(2, kIncreasing(new int[]{4,1,5,2,6,2}, 3));

    }

    private void test3() {

        Assert.assertEquals(11, getDescentPeriods(new int[]{4,3,2,1,4}));
        Assert.assertEquals(7, getDescentPeriods(new int[]{3,2,1,4}));
    }

    private void test2() {
        Assert.assertEquals(" s p a c i n g", addSpaces("spacing", new int[]{0,1,2,3,4,5,6}));
    }

    private void test() {
        Assert.assertEquals("cc", firstPalindrome(new String[]{"cc"}));
        Assert.assertEquals("c", firstPalindrome(new String[]{"c"}));
    }
}
