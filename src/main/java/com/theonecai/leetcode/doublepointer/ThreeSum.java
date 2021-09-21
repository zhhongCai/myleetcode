package com.theonecai.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 15
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                if (s == 0) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (s > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int c = -nums[i] - nums[j];
                int idx = Arrays.binarySearch(nums, j + 1, n, c);
                if (idx >= 0) {
                    String key = nums[i] + "_" + nums[j] + "_" + c;
                    if (!set.contains(key)) {
                        List<Integer> list = new ArrayList<>(3);
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(c);
                        res.add(list);
                        set.add(key);
                    }
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> list = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> ints : list) {
            System.out.println(ints);
        }
    }
}
