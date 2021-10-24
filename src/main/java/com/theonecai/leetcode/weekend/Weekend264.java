package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/10/24 10:24
 * @Description:
 */
public class Weekend264 {

    public int countValidWords(String sentence) {
        String[] strs = sentence.split(" ");
        int res = 0;
        for (String str : strs) {
            if (check(str)) {
                res++;
            }
        }

        return res;
    }

    private boolean check(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ('0' <= ch && ch <= '9') {
                return false;
            }
        }
        int idx = str.indexOf("-");
        if (idx == 0 || idx == str.length() - 1) {
            return false;
        }
        if (str.indexOf('-', idx + 1) >= 0) {
            return false;
        }
        if (idx > 0 && (str.charAt(idx - 1) < 'a' || str.charAt(idx - 1) > 'z' || str.charAt(idx + 1) < 'a' ||
                str.charAt(idx + 1) > 'z')) {
            return false;
        }

        idx = str.indexOf(".");
        if (idx >= 0 && idx != str.length() - 1) {
            return false;
        }
        idx = str.indexOf("!");
        if (idx >= 0 && idx != str.length() - 1) {
            return false;
        }
        idx = str.indexOf(",");
        if (idx >= 0 && idx != str.length() - 1) {
            return false;
        }
        return true;
    }

    public int nextBeautifulNumber(int n) {
        int t = n + 1;
        while (!checkNum(t)) {
            t++;
        }
        return t;
    }

    private boolean checkNum(int t) {
        int[] count = new int[10];
        while (t > 0) {
            count[t % 10]++;
            t /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if (count[i] != 0 && count[i] != i) {
                return false;
            }
        }
        return true;
    }

    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        if (n == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int i = 1; i < n; i++) {
            int p = parents[i];
            List<Integer> pc = map.getOrDefault(p, new ArrayList<>());
            pc.add(i);
            map.put(p, pc);
        }
        int[][] count = new int[n][2];
        int total = dfs(map, 0, count);
        long res = 0;
        Map<Long, Integer> resCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = parents[i];
            int pc = 1;
            if (p != -1) {
                pc = total - (count[i][0] + count[i][1] + 1);
            }
            long m =  (long)(count[i][0] == 0 ? 1 : count[i][0]) * (long)(count[i][1] == 0 ? 1 : count[i][1]) * (long)pc;
            res = Math.max(res, m);
            resCount.put(m, resCount.getOrDefault(m, 0) + 1);
        }

        return resCount.get(res);
    }

    private int dfs(Map<Integer, List<Integer>> tree, int i, int[][] count) {
        List<Integer> child = tree.get(i);
        if (child == null || child.isEmpty()) {
            return 1;
        }
        int left = child.get(0);
        int cnt = dfs(tree, left, count);
        count[i][0] = cnt;

        if (child.size() > 1) {
            int right = child.get(1);
            int rightCount = dfs(tree, right, count);
            count[i][1] = rightCount;
            cnt += rightCount;
        }
        return cnt + 1;
    }

    public static void main(String[] args) {
        Weekend264 weekend = new Weekend264();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {

        Assert.assertEquals(2, countHighestScoreNodes(new int[] {-1,0,1,2}));
        Assert.assertEquals(0, countHighestScoreNodes(new int[] {-1}));
        Assert.assertEquals(3, countHighestScoreNodes(new int[] {-1,2,0,2,0}));

        Assert.assertEquals(2, countHighestScoreNodes(new int[] {-1,2,0}));

    }

    private void test2() {
        Assert.assertEquals(22, nextBeautifulNumber(1));
        Assert.assertEquals(22, nextBeautifulNumber(2));
        Assert.assertEquals(3133, nextBeautifulNumber(3000));
        Assert.assertEquals(3133, nextBeautifulNumber(3000));
    }

    private void test() {
        Assert.assertEquals(0, countValidWords("c-g-d"));
        Assert.assertEquals(3, countValidWords("cat and  dog"));
        Assert.assertEquals(0, countValidWords("!this  1-s b8d!"));
        Assert.assertEquals(5, countValidWords("alice and  bob are playing stone-game10"));
        Assert.assertEquals(6, countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
        Assert.assertEquals(7, countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener ."));
    }
}
