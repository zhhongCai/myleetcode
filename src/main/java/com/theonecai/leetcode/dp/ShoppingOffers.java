package com.theonecai.leetcode.dp;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 638
 */
public class ShoppingOffers {

    private Map<String, Integer> memo;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = needs.size();
        List<List<Integer>> validSpecial = new ArrayList<>();
        for (List<Integer> sp : special) {
            if (compare(sp, needs, n) <= 0) {
                validSpecial.add(sp);
            }
        }
        memo = new HashMap<>();
        return dfs(price, validSpecial, needs);
    }

    private int dfs(List<Integer> price, List<List<Integer>> validSpecial, List<Integer> needs) {
        String key = needs.toString();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int val = 0;
        int n = needs.size();
        for (int i = 0; i < n; i++) {
            val += needs.get(i) * price.get(i);
        }
        for (int i = 0; i < validSpecial.size(); i++) {
            List<Integer> sp = validSpecial.get(i);
            if (compare(sp, needs, n) <= 0) {
                List<Integer> next = new ArrayList<>(needs.size());
                for (int j = 0; j < n; j++) {
                    next.add(needs.get(j) - sp.get(j));
                }
                val = Math.min(val, dfs(price, validSpecial, next) + sp.get(n));
            }
        }

        memo.put(key, val);
        return val;
    }

    private int compare(List<Integer> v, List<Integer> needs, int n) {
        for (int i = 0; i < n; i++) {
            if (v.get(i) > needs.get(i)) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        ShoppingOffers shoppingOffers = new ShoppingOffers();
        List<Integer> p = Arrays.asList(2,5);
        List<List<Integer>> sp = new ArrayList<>();
        sp.add(Arrays.asList(3,0,5));
        sp.add(Arrays.asList(1,2,10));
        List<Integer> needs = Arrays.asList(3,2);
        Assert.assertEquals(14, shoppingOffers.shoppingOffers(p, sp, needs));
    }
}
