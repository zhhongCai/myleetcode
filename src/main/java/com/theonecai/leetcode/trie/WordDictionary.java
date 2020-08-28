package com.theonecai.leetcode.trie;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 11
 * @Author: thonecai
 * @Date: Create in 2020/7/20 21:42
 * @Description:
 */
public class WordDictionary {

    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode('/');
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode current = root;
        Character ch;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            if (!current.nextMap.containsKey(ch)) {
                TrieNode node = new TrieNode(ch);
                current.nextMap.put(ch, node);
            }
            current = current.nextMap.get(ch);
        }
        current.isEndingChar = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode node) {
        TrieNode current = node;
        Character ch = null;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i);
            if (ch == '.') {
                if (i == word.length() - 1) {
                    return true;
                }
                if (current.nextMap.isEmpty()) {
                    return false;
                }
                for (TrieNode next : current.nextMap.values()) {
                    if (search(word.substring(i + 1),  next)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!current.nextMap.containsKey(ch)) {
                    return false;
                }
            }
            current = current.nextMap.get(word.charAt(i)) ;
        }

        return current.isEndingChar;
    }

    static class TrieNode {
        private char data;
        private Map<Character, TrieNode> nextMap;
        /**
         * 是否结束字符
         */
        private boolean isEndingChar;

        public TrieNode(char data) {
            this.data = data;
            this.nextMap = new HashMap<>();
            this.isEndingChar = false;
        }
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("dad") ;
        dictionary.addWord("mad");
        Assert.assertFalse(dictionary.search("pad"));
        Assert.assertTrue(dictionary.search("bad"));
        Assert.assertTrue(dictionary.search(".ad"));
        Assert.assertTrue(dictionary.search("b.."));
        Assert.assertFalse(dictionary.search("...c"));
    }
}
