package com.theonecai.algorithms;

/**
 * a-z字典树
 * @Author: theonecai
 * @Date: Create in 2020/6/29 19:52
 * @Description:
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode('/');
    }

    public void add(String word) {
        TrieNode current = root;
        int index;
        for (char c : word.toCharArray()) {
            if (current.children == null) {
                current.children = new TrieNode[26];
            }
            index = c - 'a';
            if (current.children[index] == null) {
                TrieNode node = new TrieNode(c);
                current.children[index] = node;
            }
            current = current.children[index];
        }
        current.isEndingChar = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        int index;
        for (char c : word.toCharArray()) {
            index = c - 'a';
            if (current.children == null) {
                return false;
            }
            if (current.children[index].data == c) {
                current = current.children[index];
            }
        }
        return current.isEndingChar;
    }

    static class TrieNode {
        private char data;
        private boolean isEndingChar = false;
        private TrieNode[] children;

        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        String a = "hello";
        Trie trie = new Trie();
        trie.add("hello");
        trie.add("world");
        trie.add("this");
        trie.add("is");
        trie.add("good");
        System.out.println(trie.search(a));
        System.out.println(trie.search("is"));
        System.out.println(trie.search("good"));
        System.out.println(trie.search("goods"));
    }
}
