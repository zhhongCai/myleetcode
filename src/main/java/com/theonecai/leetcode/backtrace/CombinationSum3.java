package com.theonecai.leetcode.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/22 20:51
 * @Description:
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        sum(n, k, 1, new ArrayList<>(k), result);

        return result;
    }

    private void sum(int n, int k, int start, List<Integer> list, List<List<Integer>> result) {
        if (n == 0 || k == 0) {
            if (k == 0 && n == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = start; i < 10; i++) {
            if (list.contains(i)) {
                continue;
            }
            if (n < i || (list.size() > 0 && list.get(list.size() - 1) > i)) {
                continue;
            }
            list.add(i);
            sum(n - i, k - 1, start + 1,  list, result);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        List<List<Integer>> result = null;
        result = combinationSum3.combinationSum3(3, 9);
        result.forEach(System.out::println);
        result = combinationSum3.combinationSum3(2, 10);
        result.forEach(System.out::println);
        result = combinationSum3.combinationSum3(2, 12);
        result.forEach(System.out::println);
    }
}
