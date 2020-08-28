package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * leetcode 47
 * @Author: theonecai
 * @Date: Create in 2020/7/13 20:35
 * @Description:
 */
public class PermuteWithDuplicateNums {

    private List<List<Integer>> lists;
//    private Set<String> existList;
//    private BloomFilter<String> bf;

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();

        }
        lists = new LinkedList<>();
//        existList = new HashSet<>();
//        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), nmn(nums.length) + 1,0.001);
        int[] head = new int[nums.length];
        boolean[] visitedIndexs = new boolean[nums.length];

        Arrays.sort(nums);
        permuteUnique(head, 0, nums, visitedIndexs);
        return lists;
    }

    private long nmn(int length) {
        long val = 1;
        for (int i = 1; i <= length; i++) {
            val *= i;
        }
        return val;
    }

    private void permuteUnique(int[] head, int headCurrentSize, int[] nums, boolean[] visitedIndexs) {
        if (headCurrentSize >= nums.length) {
//            String listStr = getListStr(head);
//            if (existList.contains(listStr)) {
//            if (bf.mightContain(listStr)) {
//                return;
//            }
            List<Integer> list = new ArrayList<>(nums.length);
            for (int h : head) {
                list.add(h);
            }
            lists.add(list);
//            existList.add(listStr);
//            bf.put(listStr);

            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (visitedIndexs[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !visitedIndexs[i - 1]) {
                continue;
            }
            head[headCurrentSize] = nums[i];
            visitedIndexs[i] = true;
            permuteUnique(head, headCurrentSize + 1, nums, visitedIndexs);
            visitedIndexs[i] = false;
        }
    }

    private String getListStr(int[] head) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(head).forEach(sb::append);
        return sb.toString();
    }

    public static void main(String[] args) {
        PermuteWithDuplicateNums p = new PermuteWithDuplicateNums();
        int[] arr = {1,2,1};
        long a = System.currentTimeMillis();
        List<List<Integer>> lists = p.permuteUnique(arr);
        System.out.println("costtime: " + (System.currentTimeMillis() - a));
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
