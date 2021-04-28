package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * @Author: theonecai
 * @Date: Create in 2021/04/04 10:24
 * @Description:
 */
public class Weekend235 {

    public String truncateSentence(String s, int k) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(strs[i]).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> userTimesMap = new HashMap<>();
        for (int i = 0; i < logs.length; i++) {
            Set<Integer> set = userTimesMap.getOrDefault(logs[i][0], new HashSet<>());
            set.add(logs[i][1]);
            userTimesMap.put(logs[i][0], set);
        }
        List<UserTimes> list = new ArrayList<>(userTimesMap.size());
        for (Map.Entry<Integer, Set<Integer>> entry : userTimesMap.entrySet()) {
            list.add(new UserTimes(entry.getKey(), entry.getValue().size()));
        }
        Collections.sort(list);
        int[] result = new int[k];
        int idx = 0;
        for (int i = 0; i < k; i++) {
            while (idx < list.size() ) {
                UserTimes user = list.get(idx);
                if (user.count == i + 1) {
                    result[i]++;
                    idx++;
                } else {
                    break;
                }
            }
        }

        return result;
    }

    private static class UserTimes implements  Comparable<UserTimes> {
        int id;
        int count;

        public UserTimes(int id, int count) {
            this.id = id;
            this.count = count;
        }

        @Override
        public int compareTo(UserTimes o) {
            return this.count - o.count;
        }
    }

    private int mod = 1000000007;
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] diff = new int[len];
        long result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            set.add(nums1[i]);
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            result += diff[i];
            result %= mod;
        }
        if (result == 0) {
            return 0;
        }

        long maxDf = Integer.MIN_VALUE;
        int[] nums = set.stream().mapToInt(Integer::intValue).sorted().toArray();
        for (int i = 0; i < len; i++) {
            int n = binarySearch(nums, nums2[i]);
            int d =  Math.abs(n - nums2[i]);
            if (d < diff[i]) {
                maxDf = Math.max(diff[i] - d, maxDf);
            }
        }
        if (maxDf != Integer.MIN_VALUE) {
            result -= maxDf;
        }
        result %= mod;
        return (int)result;
    }

    private int binarySearch(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        int mid = 0;
        while (left < right) {
            mid = (right + left) / 2;
            if (nums[mid] > val) {
                right = mid - 1;
            } else if (nums[mid] < val) {
                left = mid + 1;
            } else {
                return val;
            }
        }
        if (Math.abs(nums[mid] - val) <= Math.abs(nums[left] - val))  {
            return nums[mid];
        }
        return nums[left];
    }

    public int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] diff = new int[len];
        long result = 0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            treeSet.add(nums1[i]);
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            result += diff[i];
            result %= mod;
        }
        if (result == 0) {
            return 0;
        }

        long maxDf = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            Integer floor = treeSet.floor(nums2[i]);
            if (floor != null) {
                int d =  Math.abs(floor - nums2[i]);
                if (d < diff[i]) {
                    maxDf = Math.max(diff[i] - d, maxDf);
                }
            }
            Integer higher = treeSet.higher(nums2[i]);
            if (higher != null) {
                int d =  Math.abs(higher - nums2[i]);
                if (d < diff[i]) {
                    maxDf = Math.max(diff[i] - d, maxDf);
                }
            }
        }
        if (maxDf != Integer.MIN_VALUE) {
            result -= maxDf;
        }
        result %= mod;
        return (int)result;
    }

    public int gcd(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }

    private Set<Integer> gcds = new HashSet<>();
    public int countDifferentSubsequenceGCDs(int[] nums) {
        gcds.clear();
        for (int num : nums) {
            gcds.add(num);
        }

        int[] uniqueNums = new int[gcds.size()];
        int i = 0;
        for (Integer gcd : gcds) {
            uniqueNums[i++] = gcd;
        }

        subsets(uniqueNums);

        gcds.add(1);
        return gcds.size();
    }

    public void subsets(int[] nums) {
        subSet(nums, 0, new ArrayList<>());
        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        subSet(nums, 0, list);

    }

    private void subSet(int[] nums, int index, List<Integer> list) {
        if (index >= nums.length) {
            countGcds(list);
            return;
        }
        List<Integer> current = new ArrayList<>(list);
        subSet(nums, index + 1, list);
        if (index < nums.length - 1) {
            current.add(nums[index + 1]);
            subSet(nums, index + 1, current);
        }
    }

    private int countGcds(List<Integer> list) {
        if (list.size() < 2) {
            return 1;
        }
        int maxGcd = gcd(list.get(0), list.get(1));
        if (maxGcd == 1) {
            return maxGcd;
        }
        for (int i = 2; i < list.size(); i++) {
            maxGcd = Math.min(maxGcd, gcd(maxGcd, list.get(i)));
            if (maxGcd == 1) {
                return maxGcd;
            }
        }
        gcds.add(maxGcd);
        return maxGcd;
    }


    public static void main(String[] args) {
        Weekend235 weekend = new Weekend235();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
        Assert.assertEquals(5, this.countDifferentSubsequenceGCDs(new int[]{6,10,3}));
        Assert.assertEquals(7, this.countDifferentSubsequenceGCDs(new int[]{5,15,40,5,6}));
    }

    private void test3() {
        Assert.assertEquals(3, this.minAbsoluteSumDiff(new int[]{1,7,5}, new int[]{2,3,5}));
        Assert.assertEquals(0, this.minAbsoluteSumDiff(new int[]{2,4,6,8,10}, new int[]{2,4,6,8,10}));
        Assert.assertEquals(20, this.minAbsoluteSumDiff(new int[]{1,10,4,4,2,7}, new int[]{9,3,5,1,7,4}));
    }

    private void test2() {
        System.out.println(Arrays.toString(this.findingUsersActiveMinutes(new int[][]{
                {0,5},{1,2},{0,2},{0,5},{1,3}
        }, 5)));
        System.out.println(Arrays.toString(this.findingUsersActiveMinutes(new int[][]{
                {1,1},{2,2},{2,3}
        }, 4)));
    }

    private void test() {
        Assert.assertEquals("aa bb ccd", this.truncateSentence("aa bb ccd dd", 3));
    }
}
