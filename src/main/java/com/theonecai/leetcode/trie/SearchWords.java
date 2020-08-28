package com.theonecai.leetcode.trie;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/7/17 19:21
 * @Description:
 */
public class SearchWords {

    /**
     * 是否访问过标记
     */
    private boolean[][] visited;

    /**
     * 已找到单词数
     */
    private int foundWordCount;

    /**
     * 单词总数
     */
    private int totalWords;

    public List<String> findWords(char[][] board, String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        foundWordCount = 0;
        totalWords = words.length;
        visited = new boolean[board.length][board[0].length];
        List<String> result = new ArrayList<>(words.length);

        search(board, trie.root, result);

        return result;
    }

    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode('/');
        }

        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                if (!current.nextMap.containsKey(ch)) {
                    TrieNode node = new TrieNode(ch);
                    current.nextMap.put(ch, node);
                }
                current = current.nextMap.get(ch);
            }
            current.isEndingChar = true;
            current.word = word;
            current.occurCount++;
        }

        static class TrieNode {
            private char data;
            private Map<Character, TrieNode> nextMap;
            /**
             * 是否结束字符
             */
            private boolean isEndingChar;
            /**
             * 是结束字符时，冗余单词
             */
            private String word;
            /**
             * 是否匹配上了
             */
            private boolean matched;
            /**
             * 出现次数
             */
            private int occurCount;

            public TrieNode(char data) {
                this.data = data;
                this.nextMap = new HashMap<>();
                this.isEndingChar = false;
                this.matched = false;
                this.occurCount = 0;
            }
        }

    }

    public void search(char[][] board, Trie.TrieNode root, List<String> result) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (foundWordCount == totalWords) {
                    return;
                }
                for (Trie.TrieNode trieNode : root.nextMap.values()) {
                    if (board[row][col] == trieNode.data) {
                        visited[row][col] = true;
                        search(board, row, col, trieNode, result);
                        visited[row][col] = false;
                    }
                }
            }
        }
    }

    private void search(char[][] chars, int row, int col, Trie.TrieNode currentNode, List<String> result) {
        if (currentNode == null || foundWordCount == totalWords) {
            return;
        }

        if (chars[row][col] != currentNode.data) {
            return;
        }

        if (currentNode.isEndingChar && !currentNode.matched) {
            int count = currentNode.occurCount;
            foundWordCount += count;
            while (count-- > 0) {
                result.add(currentNode.word);
            }
            currentNode.matched = true;
        }

        visited[row][col] = true;
        for (Trie.TrieNode nextNode : currentNode.nextMap.values()) {
            if (row < chars.length - 1 && !visited[row + 1][col]) {
                search(chars, row + 1, col, nextNode, result);
                visited[row + 1][col] = false;
            }
            if (col < chars[0].length - 1 && !visited[row][col + 1]) {
                search(chars, row, col + 1, nextNode, result);
                visited[row][col + 1] = false;
            }
            if (row > 0 && !visited[row - 1][col]) {
                search(chars, row - 1, col, nextNode, result);
                visited[row - 1][col] = false;
            }
            if (col > 0 && !visited[row][col - 1]) {
                search(chars, row, col - 1, nextNode, result);
                visited[row][col - 1] = false;
            }
        }
    }

    public static void main(String[] args) {
        SearchWords searchWord = new SearchWords();
        String[] words = {"abcb", "ablb", "ghlh", "ghqh", "ableg", "lloshl", "ooo", "aaaa", "ooaao", "youare", "fqaaqf", "aaa", "aa", "a",
                "aaaa", "aaa", "ooo"};
        char[][] chars = {
                {'a', 'b', 'c', 'd', 'e'},
                {'e', 'l', 'l', 'o', 's'},
                {'g', 'h', 'e', 'l', 'h'},
                {'f', 'q', 'l', 'l', 'o'},
                {'f', 'q', 'a', 'a', 'o'},
                {'f', 'q', 'a', 'a', 'o'},
        };
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<String> result = searchWord.findWords(chars, words);
        stopWatch.stop();
        System.out.println("cost:" + stopWatch.getTime());
        result.forEach(System.out::println);
        Assert.assertEquals(12, result.size());
        Assert.assertFalse(result.contains("abcb"));
        Assert.assertFalse(result.contains("youare"));
        Assert.assertFalse(result.contains("ablb"));
        Assert.assertFalse(result.contains("ghlh"));
        Assert.assertFalse(result.contains("ghqh"));

        char[][] chars2 = {{'y', 'o', 'u', 'a', 'r', 'e','a', 'g', 'o','o','d','m','a','n'}};
        String[] wds = {"you", "are", "a", "good", "man", "are", "ar", "a", "youau"};
        stopWatch.reset();
        stopWatch.start();
        result = searchWord.findWords(chars2, wds);
        stopWatch.stop();
        System.out.println("cost:" + stopWatch.getTime());
        result.forEach(System.out::println);
        Assert.assertEquals(8, result.size());
    }
}
