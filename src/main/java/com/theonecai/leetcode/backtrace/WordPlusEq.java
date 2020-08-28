package com.theonecai.leetcode.backtrace;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 1307
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/22 21:15
 * @Description:
 */
public class WordPlusEq {
    private long[] baseArr = {
            1,
            10,
            100,
            1000,
            10000,
            100000,
            1000000,
            10000000,
            100000000,
            1000000000,
            10000000000L,
            100000000000L,
            1000000000000L,
            10000000000000L,
            100000000000000L,
            1000000000000000L,
            10000000000000000L,
            100000000000000000L,
            1000000000000000000L
    };

    private boolean found;
    private int maxLen;
    private List<Character> chars;
    private int[] charMap;
    private boolean[] firstChars;
    private long sumLeft = 0;
    private long sum = 0;
    private String[] reversedWords;
    private String reversedResult;
    private int[] levelCharCount;

    public boolean isSolvable(String[] words, String result) {
        init(words, result);

        if (maxLen > result.length() || maxLen < result.length() - 1) {
            return false;
        }
        maxLen = result.length();

        combination(10, chars.size(), new int[chars.size()], 0, new boolean[10]);

        return found;
    }

    private void init(String[] words, String result) {
        found = false;
        maxLen = 0;
        chars = new ArrayList<>(10);
        firstChars = new boolean[26];
        charMap = new int[26];
        levelCharCount = new int[result.length()];

        reverse(words, result);

        for (String word : words) {
            if (maxLen < word.length()) {
                maxLen = word.length();
            }
        }
        int level;
        for (int i = 0; i < reversedResult.length(); i++) {
            level = 0;
            for (String reversedWord : reversedWords) {
                if (i < reversedWord.length()) {
                    level++;
                    if (i == reversedWord.length() - 1) {
                        firstChars[reversedWord.charAt(i) - 'A'] = true;
                    }
                    if (!chars.contains(reversedWord.charAt(i))) {
                        chars.add(reversedWord.charAt(i));
                    }
                }
            }

            level++;
            levelCharCount[i] = level;
            if (!chars.contains(reversedResult.charAt(i))) {
                chars.add(reversedResult.charAt(i));
            }
        }
    }

    private void reverse(String[] words, String result) {
        reversedResult = reverse(result);

        reversedWords = new String[words.length];
        int j = 0;
        for (String word : words) {
            reversedWords[j++] = reverse(word);
        }
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    private void combination(int n, int k, int[] head, int index, boolean[] visited) {
        if (found) {
            return;
        }
        if (k == 0) {
            checkCurrentCharMap(head, reversedWords, reversedResult);
            return;
        }
        for (int i = 0; i < n && !found; i++) {
            if (visited[i]) {
                continue;
            }
            if (i == 0 && firstChars[chars.get(index) - 'A']) {
                continue;
            }
            head[index] = i;
            if (!checkLevel(head, index)) {
                continue;
            }
            visited[i] = true;
            combination(n, k - 1, head, index + 1, visited);
            visited[i] = false;
        }
    }

    private boolean checkLevel(int[] head, int index) {
        if (index == 0) {
            return true;
        }

        for (int i = 0; i <= index; i++) {
            charMap[chars.get(i) - 'A'] = head[i];
        }
        sumLeft = 0;
        sum = 0;

        int levels = 0;
        for (int i = 0; i < maxLen; i++) {
            if (levelCharCount[i] == 0) {
                break;
            }

            levels += levelCharCount[i];
            if (index + 1 >= levels) {
                if (!calculateAt(reversedWords, reversedResult, i)) {
                    return false;
                }
            } else {
                break;
            }
        }
        return true;
    }


    private void checkCurrentCharMap(int[] head, String[] words, String result) {
        for (int i = 0; i < chars.size(); i++) {
            charMap[chars.get(i) - 'A'] = head[i];
        }
        found = calculate(words, result);
    }

    private boolean calculate(String[] words, String result) {
        int currentIndex = 0;
        sumLeft = 0;
        sum = 0;

        while (currentIndex < maxLen) {
            if (!calculateAt(words, result, currentIndex)) {
                return false;
            }
            currentIndex++;
        }

        return sum == sumLeft;
    }

    private boolean calculateAt(String[] words, String result, int currentIndex) {
        char ch;
        long base = baseArr[currentIndex];
        int left = 0;
        for (String word : words) {
            if (currentIndex < word.length()) {
                ch = word.charAt(currentIndex);
                long val = charMap[ch - 'A'];
                left += val;
            }
        }
        ch = result.charAt(currentIndex);
        long val = charMap[ch - 'A'];

        long overflow = sumLeft / base;
        sumLeft += left * base;
        sum += val * base;

        if (currentIndex < maxLen - 1) {
            return val == (left + overflow) % 10;
        }

        return sumLeft == sum;
    }

    public static void main(String[] args) {
        WordPlusEq wordPlusEq = new WordPlusEq();
        String[] words = {"SEND", "MORE"};
        String result = "MONEY";
        long a = System.currentTimeMillis();
        Assert.assertTrue(wordPlusEq.isSolvable(words, result));
        printResult(wordPlusEq, result, words);

        String[] words2 = {"SIX", "SEVEN", "SEVEN"};
        result = "TWENTY";
        Assert.assertTrue(wordPlusEq.isSolvable(words2, result));
        printResult(wordPlusEq, result, words2);

        String[] words3 = {"A", "A", "B"};
        result = "C";
        Assert.assertTrue(wordPlusEq.isSolvable(words3, result));
        printResult(wordPlusEq, result, words3);


        String[] words4 = {"THIS","IS","TOO"};
        result = "FUNNY";
        Assert.assertTrue(wordPlusEq.isSolvable(words4, result));
        printResult(wordPlusEq, result, words4);

        String[] words5 = {"LEET","CODE"};
        result = "POINT";
        Assert.assertFalse(wordPlusEq.isSolvable(words5, result));

        String[] words6 = {"BUT","ITS","STILL"};
        result = "FUNNY";
        Assert.assertTrue(wordPlusEq.isSolvable(words6, result));
        printResult(wordPlusEq, result, words6);

        String[] words7 = {"AB","CD","EF"};
        result = "GHIJ";
        Assert.assertFalse(wordPlusEq.isSolvable(words7, result));
        printResult(wordPlusEq, result, words7);

        System.out.println("cost:" + (System.currentTimeMillis() - a));

/*
G=0
B=1
H=2
J=3
I=4
D=5
A=6
F=7
C=8
E=9
*/

        wordPlusEq.maxLen = 4;
        wordPlusEq.charMap = new int[26];
        wordPlusEq.charMap['G' - 'A'] = 0;
        wordPlusEq.charMap['B' - 'A'] = 1;
        wordPlusEq.charMap['H' - 'A'] = 2;
        wordPlusEq.charMap['J' - 'A'] = 3;
        wordPlusEq.charMap['I' - 'A'] = 4;
        wordPlusEq.charMap['D' - 'A'] = 5;
        wordPlusEq.charMap['A' - 'A'] = 6;
        wordPlusEq.charMap['F' - 'A'] = 7;
        wordPlusEq.charMap['C' - 'A'] = 8;
        wordPlusEq.charMap['E' - 'A'] = 9;
        wordPlusEq.reverse(words7, result);
        wordPlusEq.calculate(wordPlusEq.reversedWords, wordPlusEq.reversedResult);

    }

    private static void printResult(WordPlusEq wordPlusEq, String result, String[] words) {
        for (String word : words) {
            System.out.println(word);
            for (char c : word.toCharArray()) {
                System.out.print(wordPlusEq.charMap[c - 'A']);
            }
            System.out.println();
        }
        System.out.println("------------");
        System.out.println(result);
        for (char c : result.toCharArray()) {
            System.out.print(wordPlusEq.charMap[c - 'A']);
        }
        System.out.println('\n');
    }
}
