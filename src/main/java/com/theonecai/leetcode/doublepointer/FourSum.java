package com.theonecai.leetcode.doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return list;
        }

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> data = new ArrayList<>(4);
                        data.add(nums[i]);
                        data.add(nums[j]);
                        data.add(nums[left]);
                        data.add(nums[right]);
                        list.add(data);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }

        }

        return list;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        List<List<Integer>> list = fourSum.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        for (List<Integer> ints : list) {
            System.out.println(ints);
        }

        list = fourSum.fourSum(new int[]{2,2,2,2,2}, 8);
        for (List<Integer> ints : list) {
            System.out.println(ints);
        }
    }
}
