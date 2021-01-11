package com.theonecai.leetcode.unionfind;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 并查集
 *
 * 1202
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
                i = parent[i];
            }
            parent[x] = i;
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

    public String swapString(String s, int[][] pairs) {
        if (s == null || s.length() < 2) {
            return s;
        }
        Unionfind unionfind = new Unionfind(s.length(), true);
        char[] chars = s.toCharArray();
        for (int[] pair : pairs) {
            unionfind.union(pair[0], pair[1]);
        }
        Map<Character, Set<Integer>> charIndexMap = new HashMap<>(26);
        for (int i = 0; i < chars.length; i++) {
            Set<Integer> indexList = charIndexMap.getOrDefault(chars[i], new TreeSet<>());
            indexList.add(i);
            charIndexMap.put(chars[i], indexList);
        }

        for (int i = 0; i < chars.length; i++) {
            boolean swapped = false;
            for (char ch = 'a'; ch < chars[i]; ch++) {
                Set<Integer> list = charIndexMap.get(ch);
                if (list == null || list.size() == 0) {
                    continue;
                }
                Iterator<Integer> it = list.iterator();
                while (it.hasNext()) {
                    int idx = it.next();
                    if (unionfind.isConnected(i, idx)) {
                        it.remove();
                        Set<Integer> currentCharIndexList = charIndexMap.get(chars[i]);
                        currentCharIndexList.remove(i);
                        currentCharIndexList.add(idx);
                        swap(chars, i, idx);
                        swapped = true;
                        break;
                    }
                }
                if (swapped) {
                    break;
                }
            }
            if (!swapped) {
                charIndexMap.get(chars[i]).remove(i);
            }
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
    }

    public static void main(String[] args) {
        StringSwap stringSwap = new StringSwap();
        Assert.assertEquals("cba", stringSwap.swapString("cba", new int[][]{
        }));
        Assert.assertEquals("abc", stringSwap.swapString("cba", new int[][]{
                {0,1},{1,2}
        }));
        Assert.assertEquals("abcd", stringSwap.swapString("dcab", new int[][]{
                {0,3},{1,2},{0,2}
        }));
        Assert.assertEquals("adbc", stringSwap.swapString("dacb", new int[][]{
                {0,1},{2,3}
        }));
    }
}
