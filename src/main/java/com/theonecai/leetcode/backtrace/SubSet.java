package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 78
 * @Author: theonecai
 * @Date: Create in 2020/7/15 20:08
 * @Description:
 */
public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();

        subSet(nums, 0, result, new ArrayList<>());
        List<Integer> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        subSet(nums, 0, result, list);

        return result;
    }

    private void subSet(int[] nums, int index, List<List<Integer>> result, List<Integer> list) {
        if (index >= nums.length) {
            result.add(list);
            return;
        }
        List<Integer> current = new ArrayList<>(list);
        subSet(nums, index + 1, result, list);
        if (index < nums.length - 1) {
            current.add(nums[index + 1]);
            subSet(nums, index + 1, result, current);
        }
    }


    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        long a = System.currentTimeMillis();
        List<List<Integer>> result = subSet.subsets(arr);
        System.out.println("cost: " + (System.currentTimeMillis() - a) + ", size=" + result.size());
//        result.forEach(System.out::println);
    }
}
