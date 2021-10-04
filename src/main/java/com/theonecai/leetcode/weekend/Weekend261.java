package com.theonecai.leetcode.weekend;


import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2021/10/03 10:24
 * @Description:
 */
public class Weekend261 {
    public int minimumMoves(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'X') {
                i+=2;
                res++;
            }
        }
        return res;
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = (m + n) * mean;
        int mTotal = 0;
        for (int roll : rolls) {
            mTotal += roll;
        }
        int remain = sum - mTotal;
        if (remain < n || remain / n > 6) {
            return new int[0];
        }
        int[] nums = new int[n];
        int base = remain / n;
        int t = remain % n;
        for (int i = 0; i < n; i++) {
            nums[i] = base + (i < t ? 1 : 0);
            if (nums[i] > 6) {
                return new int[0];
            }
        }
        return nums;
    }

    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int v : stones) {
            count[v % 3]++;
        }
        for (int i = 0; i < count.length; i++) {
            count[i] %= 2;
        }
        int c = Math.min(count[0], count[1]);
        count[0] -= c;
        count[1] -= c;
        if (count[0] > 0) {
            c = Math.min(count[0], count[2]);
            count[0] -= c;
            count[2] -= c;
        }
        boolean event = stones.length % 2 == 0;
        return false;
    }

    private String result;
    private char ch;
    private int rp;
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int n = s.length();
        char[] chars = s.toCharArray();

        result = "";
        ch = letter;
        rp = repetition;

        dfs(chars, new StringBuilder(), 0, k, new boolean[n], 0);

        return result;
    }

    private void dfs(char[] chars, StringBuilder res, int pre, int k, boolean[] seen, int letterCount) {
        if (pre >= chars.length) {
            return;
        }
        if (res.length() == k) {
            if (letterCount >= rp) {
                if (result.equals("")) {
                    result = res.toString();
                } else {
                    String str = res.toString();
                    if (result.compareTo(str) > 0) {
                        result = str;
                    }
                }
            }
            return;
        }

        for (int i = pre; i < chars.length; i++) {
            if (seen[i]) {
                continue;
            }

            res.append(chars[i]);
            seen[i] = true;
            letterCount += chars[i] == ch ? 1 : 0;
            dfs(chars, res, pre + 1, k, seen, letterCount);
            res.deleteCharAt(res.length() - 1);
            seen[i] = false;
        }
    }


    public static void main(String[] args) {
        Weekend261 weekend = new Weekend261();
        weekend.test();
        weekend.test2();
//        weekend.test3();
        weekend.test4();
    }

    private void test4() {

        Assert.assertEquals("ecde", smallestSubsequence("leetcode", 4, 'e', 2));
        Assert.assertEquals("eet", smallestSubsequence("leet", 3, 'e', 1));
        Assert.assertEquals("bb", smallestSubsequence("bb", 2, 'b', 2));
    }

    private void test3() {
        Assert.assertTrue(stoneGameIX(new int[]{1,2,3,4}));
        Assert.assertTrue(stoneGameIX(new int[]{1,2,3}));
        Assert.assertTrue(stoneGameIX(new int[]{2,1}));
        Assert.assertFalse(stoneGameIX(new int[]{2}));
        Assert.assertTrue(stoneGameIX(new int[]{5,1,2,4,3}));
    }

    private void test2() {
        Assert.assertArrayEquals(new int[]{}, missingRolls(new int[]{3,5,3}, 5, 3));
        Assert.assertArrayEquals(new int[]{3,2,2,2}, missingRolls(new int[]{1,5,6}, 3, 4));
        Assert.assertArrayEquals(new int[]{}, missingRolls(new int[]{1,2,3,4}, 6, 4));
        Assert.assertArrayEquals(new int[]{5}, missingRolls(new int[]{1}, 3, 1));
    }

    private void test() {

    }
}
