package com.theonecai.leetcode.bfs;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 127
 * @Author: theonecai
 * @Date: Create in 6/25/21 22:40
 * @Description:
 */
public class LadderLength {

    private int n;
    private Set<String> wordSet;
    private List<Set<Character>> validChars;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        n = beginWord.length();
        validChars = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            validChars.add(new HashSet<>(26));
        }
        wordSet = new HashSet<>(wordList.size());
        for (String word : wordList) {
            wordSet.add(word);
            for (int i = 0; i < n; i++) {
                validChars.get(i).add(word.charAt(i));
            }
        }
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Queue<String> backQueue = new LinkedList<>();
        Map<String, Integer> timesMap = new HashMap<>();
        Map<String, Integer> backTimesMap = new HashMap<>();
        queue.add(beginWord);
        timesMap.put(beginWord, 1);
        backQueue.add(endWord);
        backTimesMap.put(endWord, 0);

        while (!queue.isEmpty() && !backQueue.isEmpty()) {
            int t = -1;
            if (queue.size() > backQueue.size()) {
                t = updateQueue(backQueue, backTimesMap, timesMap);
            } else {
                t = updateQueue(queue, timesMap, backTimesMap);
            }
            if (t != -1) {
                return t;
            }
        }

        return 0;
    }

    private int updateQueue(Queue<String> queue, Map<String, Integer> timesMap, Map<String, Integer> backTmesMap) {
        String cur = queue.poll();
        char[] nextChar = cur.toCharArray().clone();
        for (int i = 0; i < n; i++) {
            char ch = cur.charAt(i);
            for (Character next : validChars.get(i)) {
                if (next == ch) {
                    continue;
                }
                nextChar[i] = next;
                String nextWord = new String(nextChar);
                if (timesMap.containsKey(nextWord) || !wordSet.contains(nextWord)) {
                    continue;
                }
                queue.add(nextWord);
                timesMap.put(nextWord, timesMap.get(cur) + 1);
                if (backTmesMap.containsKey(nextWord)) {
                    return timesMap.get(cur) + backTmesMap.get(nextWord) + 1;
                }
            }
            nextChar[i] = ch;
        }
        return -1;
    }

    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        Assert.assertEquals(0, ladderLength.ladderLength("hot", "dog", Arrays.asList("hot","dog")));
        Assert.assertEquals(5, ladderLength.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        Assert.assertEquals(0, ladderLength.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","ceg")));
    }
}
