package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 39
 * @Author: theonecai
 * @Date: Create in 2020/7/14 19:51
 * @Description:
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

//        Arrays.sort(candidates);

        combinationSum(candidates, 0, target, result, new ArrayList<>());

        return result;
    }

    private void combinationSum(int[] candidates, int currentSum, int target, List<List<Integer>> result,
                             List<Integer> list) {
        int remain = target - currentSum;
        if (remain == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] > remain) {
                return;
            }
            if (list.size() > 0 && list.get(list.size() - 1) < candidates[i]) {
                return;
            }
            list.add(candidates[i]);
            combinationSum(candidates, currentSum + candidates[i], target, result, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum candidateTarget = new CombinationSum();
        int[] arr = {1, 2, 3};
        int target = 20;
        List<List<Integer>> list = candidateTarget.combinationSum(arr, target);
        list.forEach(System.out::println);
    }
}
