package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 90
 * @Author: theonecai
 * @Date: Create in 2020/7/15 20:08
 * @Description:
 */
public class SubSetWithDuplicateNum {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        subSet(nums, 0, result, new ArrayList<>(nums.length));

        return result;
    }

    private void subSet(int[] nums, int start, List<List<Integer>> result, List<Integer> list) {
        result.add(new ArrayList<>(list));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
//            System.out.println("start=" + start + ",i=" + i + ",list=" + list);
            subSet(nums, i + 1, result, list);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        SubSetWithDuplicateNum subSet = new SubSetWithDuplicateNum();
        int[] arr = {1, 2, 3};
        long a = System.currentTimeMillis();
        List<List<Integer>> result = subSet.subsetsWithDup(arr);
        System.out.println("cost: " + (System.currentTimeMillis() - a) + ", size=" + result.size());
        result.forEach(System.out::println);

        a = System.currentTimeMillis();
        int[] arr2 = {5, 5, 5, 5, 5};
        result = subSet.subsetsWithDup(arr2);
        System.out.println("cost: " + (System.currentTimeMillis() - a) + ", size=" + result.size());
        result.forEach(System.out::println);
    }
}
