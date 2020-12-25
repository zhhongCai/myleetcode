package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 1255
 */
public class MaxScore {

    private int maxScore;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        maxScore = 0;
        int[] lettersCount = new int[26];
        for (char letter : letters) {
            lettersCount[letter - 'a']++;
        }
        
        dfs(words, 0, lettersCount, score);
        
        return maxScore;
    }

    private int dfs(String[] words, int i, int[] lettersCount, int[] score) {
        if (i >= words.length) {
            return 0;
        }
        boolean noLetters = true;
        for (int j = 0; j < lettersCount.length; j++) {
            if (lettersCount[j] > 0) {
                noLetters = false;
            }
        }
        if (noLetters) {
            return 0;
        }
        
        int res = 0;
        String word = words[i];
        int[] counts = Arrays.copyOf(lettersCount, lettersCount.length);
        boolean canWrite = true;
        for (int j = 0; j < word.length(); j++) {
            int idx = word.charAt(j) - 'a';
            if (counts[idx] == 0) {
                canWrite = false;  
            } else {
                res += score[idx];
                counts[idx]--;
            }
        }
        if (canWrite) {
            res = res + dfs(words, i + 1, counts, score);
            maxScore = Math.max(maxScore, res);
        } else {
            res = 0;
        }
        res = Math.max(res, dfs(words, i + 1, lettersCount, score));
        maxScore = Math.max(maxScore, res);
        return res;
    }

    public static void main(String[] args) {
        MaxScore maxScore = new MaxScore();
        Assert.assertEquals(23, maxScore.maxScoreWords(new String[] {
                "dog","cat","dad","good"
        }, new char[]{
                'a','a','c','d','d','d','g','o','o'
        }, new int[]{
                1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0  
        }));
        Assert.assertEquals(0, maxScore.maxScoreWords(new String[] {
                "leetcode"
        }, new char[]{
                'l','e','t','c','o','d'
        }, new int[]{
                0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0
        }));
    }
}
