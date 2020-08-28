package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/14 19:51
 * @Description:
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];

        combinationSum2(candidates, 0, target, result, new ArrayList<>(), visited);

        return result;
    }

    private void combinationSum2(int[] candidates, int currentSum, int target, List<List<Integer>> result,
                             List<Integer> list, boolean[] visited) {
        int remain = target - currentSum;
        if (remain == 0) {
            result.add( new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (visited[i]) {
                continue;
            }
            if  (remain < candidates[i]) {
                return;
            }
            if (i > 0 && candidates[i - 1] == candidates[i] && !visited[i - 1]) {
                continue;
            }
            if (list.size() > 0 && candidates[i] < list.get(list.size() - 1)) {
                continue;
            }
            list.add(candidates[i]);
            visited[i] = true;
            combinationSum2(candidates, currentSum + candidates[i], target, result, list, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum2 candidateTarget = new CombinationSum2();
        int[] arr = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> list = candidateTarget.combinationSum2(arr, target);
        list.forEach(System.out::println);
        int[] arr2 = {1, 2, 3, 4, 1};
        list = candidateTarget.combinationSum2(arr2, target);
        list.forEach(System.out::println);
    }
}
