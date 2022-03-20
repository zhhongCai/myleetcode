package com.theonecai.leetcode.map;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode  2013
 */
public class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> row2Col;

    public DetectSquares() {
        row2Col = new HashMap<>();
    }

    public void add(int[] point) {
        Map<Integer, Integer> colCnt = row2Col.getOrDefault(point[0], new HashMap<>());
        colCnt.put(point[1], colCnt.getOrDefault(point[1], 0) + 1);
        row2Col.put(point[0], colCnt);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        Map<Integer, Integer> colCnt = row2Col.getOrDefault(x, new HashMap<>());
        int ans = 0;
        for (Integer ay : colCnt.keySet()) {
            int len = y - ay;
            int[] xs = new int[]{x - len, x + len};
            int c = colCnt.get(ay);
            for (int i : xs) {
                Map<Integer, Integer> col = row2Col.getOrDefault(i, new HashMap<>());
                int c2 = col.getOrDefault(y, 0);
                int c3 = col.getOrDefault(ay,0);
                ans += c * c2 * c3;
            }
        }

        return ans;
    }
}
