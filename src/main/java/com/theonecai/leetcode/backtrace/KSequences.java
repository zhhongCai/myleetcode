package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leetcode 77
 * @Author: theonecai
 * @Date: Create in 2020/7/16 22:18
 * @Description:
 */
public class KSequences {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        combine(nums, k, result);

        return result;
    }

    public void combine(int[] nums, int k, List<List<Integer>> lists) {
        int[] head = new int[k];

        for (int i = 0; i <= nums.length - k; i++) {
            head[0] = nums[i];
            combine(nums, i + 1, head, 1, lists);
        }
    }

    private void combine(int[] nums, int nIndex, int[] head, int index, List<List<Integer>> lists) {
        if (index == head.length) {
            lists.add(Arrays.stream(head).boxed().collect(Collectors.toList()));
            return;
        }
        if (nIndex < nums.length) {
            head[index] = nums[nIndex];
            combine(nums, nIndex + 1, head, index + 1, lists);
            if (nums.length - nIndex -1 > head.length - index - 1) {
                combine(nums, nIndex + 1, head, index, lists);
            }
        }
    }

    public static void main(String[] args) {
        KSequences ks = new KSequences();
        int n = 10;
        for (int i = 0; i < n; i++) {
            List<List<Integer>> result = ks.combine(n, i);
            result.forEach(System.out::println);
            System.out.println();
        }
    }

}
