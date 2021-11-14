package com.theonecai.leetcode.trie;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class MapSum {
    private Map<String, Integer> count;

    private TrieNode trie;

    public MapSum() {
        count = new HashMap<>();
        trie = new TrieNode();
    }

    public void insert(String key, int val) {
        add(key, val - count.getOrDefault(key, 0));
        count.put(key, val);
    }

    public int sum(String prefix) {
        return search(prefix);
    }

    private static class TrieNode {
        private int prefixTotal;

        private TrieNode[] next;

        public TrieNode() {
            this.next = new TrieNode[26];
        }
    }

    public void add(String word, int diff) {
        TrieNode node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (node.next[idx] == null) {
                node.next[idx] = new TrieNode();
            }
            node.next[idx].prefixTotal += diff;
            node = node.next[idx];
        }
    }

    public int search(String word) {
        TrieNode node = trie;
        int total = 0;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (node.next[idx] == null) {
                return 0;
            }
            total = node.next[idx].prefixTotal;
            node = node.next[idx];
        }
        return total;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        Assert.assertEquals(3, mapSum.search("ap"));
        mapSum.insert("app", 2);
        Assert.assertEquals(5, mapSum.search("ap"));
    }
}
