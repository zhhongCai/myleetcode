package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/12/26 10:24
 * @Description:
 */
public class Weekend273 {

    public boolean isSameAfterReversals(int num) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        int b = Integer.parseInt(sb.reverse().toString());
        sb = new StringBuilder(String.valueOf(b));
        return Integer.parseInt(sb.reverse().toString()) == num;
    }

    private static Map<Character, int[]> direct = new HashMap<>();
    static {
        direct.put('L', new int[]{0, -1});
        direct.put('R', new int[]{0, 1});
        direct.put('U', new int[]{-1, 0});
        direct.put('D', new int[]{1, 0});
    }
    public int[] executeInstructions(int n, int[] startPos, String s) {
        char[] chars = s.toCharArray();
        int[] ans = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ans[i] = move(n, startPos, chars, i);
        }
        return ans;
    }

    private int move(int n, int[] startPos, char[] chs, int st) {
        int[] cur = new int[]{startPos[0], startPos[1]};
        int c = 0;
        for (int j = st; j < chs.length; j++) {
            int[] d = direct.get(chs[j]);
            cur[0] += d[0];
            cur[1] += d[1];
            if (0 <= cur[0] && cur[0] < n && 0 <= cur[1] && cur[1] < n) {
                c++;
                continue;
            }
            break;
        }
        return c;
    }


    public long[] getDistances(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> idx = map.getOrDefault(arr[i], new ArrayList<>());
            idx.add(i);
            map.put(arr[i], idx);
        }
        long[] ans = new long[arr.length];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    long sum = 0;
                    for (int j = 0; j < list.size(); j++) {
                        sum += Math.abs(list.get(i) - list.get(j));
                    }
                    ans[list.get(i)] = sum;
                } else {
                    long pre = ans[list.get(i - 1)];
                    int df =  Math.abs(list.get(i) - list.get(i - 1));
                    ans[list.get(i)] = pre + df * (i - 1) - df * (list.size() - 1 - i);
                }
            }
        }
        return ans;
    }

    private int search(List<Integer> list, int val) {
        int left = 0;
        int right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) >= val) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length / 2;
        int[] low = new int[n];
        int[] high = new int[n];
        for (int i = 0, j = 0, k = 0; i < nums.length; i += 2) {
            if (j == n) {
                high[k++] = nums[i];
                high[k++] = nums[i + 1];
                continue;
            }
            low[j++] = nums[i];
            if (j < n && i < n - 1 && nums[i] == nums[i + 1]) {
                low[j++] = nums[i + 1];
            } else {
                high[k++] = nums[i + 1];
            }
        }
        int k = (high[0] - low[0]) / 2;
        int[] ans = new int[n];
        for (int i = 0; i < low.length; i++) {
            ans[i] = low[i] + k;
        }
        return ans;
    }

    public static void main(String[] args) {
        Weekend273 weekend = new Weekend273();
        weekend.test();
        weekend.test2();
        weekend.test3();
//        weekend.test4();
    }

    private void test4() {

        Assert.assertArrayEquals(new int[]{51,100,149}, recoverArray(new int[]{1,50,99,101,150,199}));
        Assert.assertArrayEquals(new int[]{1,1}, recoverArray(new int[]{1,1,1,1}));
        Assert.assertArrayEquals(new int[]{3,7,11}, recoverArray(new int[]{2,10,6,4,8,12}));
        Assert.assertArrayEquals(new int[]{2,2}, recoverArray(new int[]{1,1,3,3}));
        Assert.assertArrayEquals(new int[]{220}, recoverArray(new int[]{5,435}));
    }

    private void test3() {
        Assert.assertArrayEquals(new long[]{6,4,4,6}, getDistances(new int[]{1,1,1,1}));
        Assert.assertArrayEquals(new long[]{4,2,7,2,4,4,5}, getDistances(new int[]{2,1,3,1,2,3,3}));
        Assert.assertArrayEquals(new long[]{5,0,3,4}, getDistances(new int[]{10,5,10,10}));
    }

    private void test2() {
        Assert.assertArrayEquals(new int[]{1,5,4,3,1,0}, executeInstructions(3, new int[]{0,1}, "RRDDLU"));
        Assert.assertArrayEquals(new int[]{4,1,0,0}, executeInstructions(2, new int[]{1,1}, "LURD"));
        Assert.assertArrayEquals(new int[]{0,0,0,0}, executeInstructions(1, new int[]{0,0}, "LURD"));
    }

    private void test() {
        Assert.assertTrue(isSameAfterReversals(526));
        Assert.assertFalse(isSameAfterReversals(1800));
        Assert.assertTrue(isSameAfterReversals(0));
    }
}
