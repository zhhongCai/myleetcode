package com.theonecai.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1036
 */
public class IsEscapePossible {
    private int n = 1000000;
    private int[][] direct = new int[][] {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };
    private boolean found;
    private int max;

    public boolean isEscapePossible(int[][] blocked, int[] s, int[] t) {
        found = false;
        max = blocked.length * (blocked.length - 1) / 2;
        Set<Long> bset = new HashSet<>(blocked.length);
        for (int[] b : blocked) {
            bset.add(toIndex(b));
        }

        return bfs(s, t, bset) && bfs(t, s, bset);
    }

    private boolean bfs(int[] s, int[] t, Set<Long> set) {
        if (found) {
            return found;
        }
        Set<Long> seen = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(s);
        seen.add(toIndex(s));

        while (!q.isEmpty() && seen.size() <= max) {
            int[] cur = q.poll();
            for (int[] d : direct) {
                int r = cur[0] +d[0];
                int c = cur[1] + d[1];
                if (!inRange(r, c)) {
                    continue;
                }
                int[] next = new int[] {r, c};
                long idx = toIndex(next);
                if (!set.contains(idx) && !seen.contains(idx)) {
                    if ( r == t[0] && c == t[1]) {
                        found = true;
                        return true;
                    }
                    q.add(next);
                    seen.add(idx);
                }
            }
            if (q.size() > set.size()){
                found = true;
                return true;
            }
        }

        return seen.size() > max;
    }

    private long toIndex(int[] b) {
        return b[0] * n + b[1];
    }

    private boolean inRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
