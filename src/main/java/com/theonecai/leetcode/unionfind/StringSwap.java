package com.theonecai.leetcode.unionfind;

import com.theonecai.algorithms.RandomStringUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 并查集
 *
 * leetcode 1202
 */
public class StringSwap {

    private static class Unionfind {

        private int[] parent;

        public Unionfind(int size, boolean defaultInit) {
            this.parent = new int[size];
            if (defaultInit) {
                initParent();
            }
        }

        public int[] getParent() {
            return this.parent;
        }

        public void initParent() {
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
        }

        public int findParent(int x) {
            int i = x;
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }

        public void union(int x, int y) {
            int xParent = findParent(x);
            int yParent = findParent(y);
            if (xParent != yParent) {
                parent[xParent] = yParent;
            }
        }

        /**
         * x,y是否连通
         * @param x
         * @param y
         * @return
         */
        public boolean isConnected(int x, int y) {
            return findParent(x) == findParent(y);
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int len = s.length();
        Unionfind unionfind = new Unionfind(len, true);
        for (List<Integer> pair : pairs) {
            unionfind.union(pair.get(0), pair.get(1));
        }

        char ch;
        int parent;
        // Map<字符所在集合, Set<字符>>
        Map<Integer, TreeMap<Character, Integer>> parentCharsCountMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            ch = s.charAt(i);

            parent = unionfind.findParent(i);
            TreeMap<Character, Integer> charCountMap = parentCharsCountMap.getOrDefault(parent, new TreeMap<>());
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            parentCharsCountMap.put(parent, charCountMap);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            parent = unionfind.findParent(i);
            TreeMap<Character, Integer> charCountMap = parentCharsCountMap.get(parent);
            ch = charCountMap.firstKey();
            sb.append(ch);

            int c = charCountMap.get(ch);
            if (c == 1) {
                charCountMap.remove(ch);
            } else {
                charCountMap.put(ch, c - 1);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StringSwap stringSwap = new StringSwap();
        //""
        //[[2,4],[5,7],[1,0],[0,0],[4,7],[0,3],[4,1],[1,3]]
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(2, 4));
        pairs.add(Arrays.asList(5, 7));
        pairs.add(Arrays.asList(1, 0));
        pairs.add(Arrays.asList(0, 0));
        pairs.add(Arrays.asList(4, 7));
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(4, 1));
        pairs.add(Arrays.asList(1, 3));
        Assert.assertEquals("ffkqttkv", stringSwap.smallestStringWithSwaps("fqtvkfkt", pairs));

        pairs = new ArrayList<>();
        pairs.add(Arrays.asList(0, 1));
        pairs.add(Arrays.asList(0, 2));
        Assert.assertEquals("abc", stringSwap.smallestStringWithSwaps("cba", pairs));

        pairs.clear();
        Assert.assertEquals("cba", stringSwap.smallestStringWithSwaps("cba", pairs));

        pairs.add(Arrays.asList(0, 1));
        pairs.add(Arrays.asList(0, 3));
        pairs.add(Arrays.asList(1, 2));
        Assert.assertEquals("abcd", stringSwap.smallestStringWithSwaps("dcba", pairs));

        int len = 100000;
        String s = RandomStringUtil.randomString(len);
        pairs.clear();
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[s.charAt(i) - 'a']++;
            if (i != 0) {
                pairs.add(Arrays.asList(i, i - 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                sb.append((char) (i + 'a'));
                counts[i]--;
            }
        }
        List<List<Integer>> p = pairs;
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals(sb.toString(), stringSwap.smallestStringWithSwaps(s, p));
        });
    }
}
