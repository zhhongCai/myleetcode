package com.theonecai.leetcode.trie;


import org.junit.Assert;

/**
 * 支持'a'~'z'
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/13 19:33
 * @Description:
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode('/');
    }

    public void insert(String word) {
        int dataIndex;
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            dataIndex = word.charAt(i) - 'a';
            if (current.next[dataIndex] == null) {
                TrieNode node = new TrieNode(word.charAt(i));
                current.next[dataIndex] = node;
            }
            current = current.next[dataIndex];
        }
        current.isEndingChar = true;
    }

    public boolean search(String word) {
        int dataIndex;
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            dataIndex = word.charAt(i) - 'a';
            if (current.next[dataIndex] == null) {
                return false;
            }
            current = current.next[dataIndex];
        }

        return current.isEndingChar;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        int dataIndex;
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            dataIndex = prefix.charAt(i) - 'a';
            if (current.next[dataIndex] == null) {
                return false;
            }
            current = current.next[dataIndex];
        }

        return true;
    }

    static class TrieNode {
        private char data;
        private TrieNode[] next;
        private boolean isEndingChar;

        public TrieNode(char data) {
            this.data = data;
            this.next = new TrieNode[26];
            this.isEndingChar = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("application");
        Assert.assertTrue(trie.search("application"));
        Assert.assertFalse(trie.search("applications"));
        Assert.assertTrue(trie.startsWith("app"));
        Assert.assertFalse(trie.startsWith("apps"));
        trie.insert("apps");
        Assert.assertTrue(trie.startsWith("apps"));
        Assert.assertTrue(trie.search("apps"));

    }
}
