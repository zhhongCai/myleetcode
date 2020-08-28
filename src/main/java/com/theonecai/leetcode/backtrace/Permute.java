package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * leetcode 46 全排列
 * @Author: theonecai
 * @Date: Create in 2020/7/4 15:35
 * @Description:
 */
public class Permute {

    private List<List<Integer>> lists;


    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();

        }
        lists = new LinkedList<>();
        int[] head = new int[nums.length];

        for (int num : nums) {
            head[0] = num;
            permute(head, 1, nums);
        }
        return lists;
    }

    private void permute(int[] head, int headCurrentSize, int[] nums) {
        if (headCurrentSize >= nums.length) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int h : head) {
                list.add(h);
            }
            lists.add(list);

            return;
        }
        Set<Integer> set = new HashSet<>(headCurrentSize);
        for (int i = 0; i < headCurrentSize; i++) {
            set.add(head[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            head[headCurrentSize] = nums[i];
            permute(head, headCurrentSize + 1, nums);
        }
    }

    public static void main(String[] args) {
        Permute p = new Permute();
        int[] arr = {1,2,3,4};
        long a = System.currentTimeMillis();
        List<List<Integer>> lists = p.permute(arr);
        System.out.println("costtime: " + (System.currentTimeMillis() - a));
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
