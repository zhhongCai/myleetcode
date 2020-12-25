package com.theonecai.leetcode.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * 1434
 */
public class NumberWays {

    public int numberWays(List<List<Integer>> hats) {
        List<Long> hatsMask = new ArrayList<>(hats.size());
        for (List<Integer> hat : hats) {
            long mask = 0;
            for (Integer h : hat) {
                mask |= 1 << (h - 1);
            }
            hatsMask.add(mask);
        }
        return dfs(hatsMask, 0, 0);
    }

    private int dfs(List<Long> hatsMask, int index, int usedMask) {
        if (index >= hatsMask.size()) {
            return 1;
        }
        int res = 0;

        return 0;
    }
}
