package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 1434
 */
public class NumberWays {

    public int numberWays(List<List<Integer>> hats) {
        Map<Integer, List<Integer>> hatToPerson = new HashMap<>(40);
        for (int i = 0; i < hats.size(); i++) {
            for (Integer h : hats.get(i)) {
                List<Integer> person = hatToPerson.getOrDefault(h - 1, new ArrayList<>());
                person.add(i);
                hatToPerson.put(h - 1, person);
            }
        }

        // dp[i] 表示i的二进制位为1的人分配到了帽子的分配方案数
        int[] dp = new int[1 << hats.size()];
        dp[0] = 1;
        for (Map.Entry<Integer, List<Integer>> entry : hatToPerson.entrySet()) {
            for (int i = dp.length - 1; i >= 0; i--) {
                for (Integer p : entry.getValue()) {
                    int pMask = 1 << p;
                    if ((i & pMask) != 0) {
                        // dp[i]的方案数可以从dp[i - pMask]累加求得
                        dp[i] += dp[i - pMask];
                        dp[i] %= 1000000007;
                    }
                }
            }
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        NumberWays numberWays = new NumberWays();
        List<List<Integer>> hats = new ArrayList<>();
        List<Integer> hat = new ArrayList<>();
        hat.add(3);
        hat.add(4);
        hats.add(hat);
        hat = new ArrayList<>();
        hat.add(4);
        hat.add(5);
        hats.add(hat);
        hat = new ArrayList<>();
        hat.add(5);
        hats.add(hat);
        Assert.assertEquals(1, numberWays.numberWays(hats));
    }
}
