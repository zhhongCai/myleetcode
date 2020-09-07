package com.theonecai.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/6 10:26
 * @Description:
 */
public class Weekend205 {

    public String modifyString(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                Character pre = i > 0 ? sb.charAt(sb.length() - 1) : null;
                Character next = i + 1 < chars.length ? chars[i + 1] : null;
                sb.append(selectChar(pre, next));
            } else {
                sb.append(chars[i]);
            }
        }

        return sb.toString();
    }

    private char selectChar(Character pre, Character next) {
        int len = 'z' - 'a';
        char ch = 'a';
        for (int i = 0; i < len; i++) {
            ch = (char)('a' + i);
            if (pre == null ||  pre != ch) {
                if (next == null || next != ch) {
                    return ch;
                }
            }
        }
        return ch;
    }

    public int numTriplets(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int count = countNum(nums1, nums2);
        count += countNum(nums2, nums1);

        return count;
    }

    private int countNum(int[] nums, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        long nn = 0;
        int fact = 0;
        for (int i = 0; i < nums.length; i++) {
            nn = (long)nums[i] * (long)nums[i];
            if (map.containsKey(nums[i])) {
                count += map.get(nums[i]);
                continue;
            }
            int c = 0;
            for (int j = 0; j < nums2.length; j++) {
                fact = nums2[j];
                int fact2 =(int) (nn / fact);
                if (nn % fact == 0) {
                    int idx = searchFirst(nums2, fact2);
                    if (idx != -1) {
                        if (idx > j) {
                            c++;
                        }
                        int k = idx + 1;
                        while (k < nums2.length && nums2[k] == nums2[idx]) {
                            if (k > j) {
                                c++;
                            }
                            k++;
                        }
                    }
                }
                if (fact2 < fact) {
                    break;
                }
            }
            map.put(nums[i], c);
            count += c;

        }
        return count;
    }

    public static int searchFirst(int[] nums, int matchValue) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] > matchValue) {
                high = mid - 1;
                continue;
            }
            if (nums[mid] < matchValue) {
                low = mid + 1;
                continue;
            }
            if (nums[mid] == matchValue) {
                if (mid == 0 || nums[mid - 1] != matchValue) {
                    return mid;
                }
                high = mid - 1;
            }
        }

        return -1;
    }

    public int minCost(String s, int[] cost) {
        long sum = 0;
        int max = 0;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = cost[i];
            max = cost[i];
            int j = i + 1;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                if (cost[j] > max) {
                    max = cost[j];
                }
                sum += cost[j];
                j++;
            }
            if (j > i + 1) {
                total += (int)(sum - max);
                i = j - 1;
            }
        }
        return total;
    }


    public static void main(String[] args) {
        Weekend205 weekend205 = new Weekend205();
        Assert.assertEquals("azs", weekend205.modifyString("?zs"));
        Assert.assertEquals("ubvaw", weekend205.modifyString("ubv?w"));
        Assert.assertEquals("jaqgacb", weekend205.modifyString("j?qg??b"));
        Assert.assertEquals("abywaipkja", weekend205.modifyString("??yw?ipkj?"));
        Assert.assertEquals(3, weekend205.minCost("abaac", new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, weekend205.minCost("abc", new int[]{1,2,3}));
        Assert.assertEquals(2, weekend205.minCost("aabaa", new int[]{1,2,3,4,1}));

        int[] nums = {1,1};
        int[] nums2 = {1,1,1};
        Assert.assertEquals(1, weekend205.numTriplets(new int[]{7,4}, new int[]{5,2,8,9}));
        Assert.assertEquals(9, weekend205.numTriplets(nums, nums2));
        Assert.assertEquals(2, weekend205.numTriplets(new int[]{7,7,8,3}, new int[]{1,2,9,7}));
        Assert.assertEquals(0, weekend205.numTriplets(new int[]{4,7,9,11,23}, new int[]{3,5,1024,12,18}));
        Assert.assertEquals(5, weekend205.numTriplets(new int[]{4,1,4,1,12}, new int[]{3,2,5,4}));
        Assert.assertEquals(9, weekend205.numTriplets(new int[]{100000,100000}, new int[]{100000,100000,100000}));

        /**
         * [100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000]
         * [100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000,100000]
         */
    }


}
