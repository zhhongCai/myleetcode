package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 5893
 */
public class SmallestSubsequence {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        // count[i] 表示i~n-1重letter出现次数
        int[] count = new int[n];
        char[] chars = s.toCharArray();

        // 26个字母出现的位置
        List<List<Integer>> indexList = new ArrayList<>(26);
        // 26个字母当前访问的位置
        int[] index = new int[26];
        for (int i = 0; i < 26; i++) {
            indexList.add(new ArrayList<>());
        }

        count[n - 1] = chars[n - 1] == letter ? 1 : 0;
        for (int i = n - 1, idx = 0; i >= 0; i--, idx++) {
            indexList.get(chars[idx] - 'a').add(idx);
            if (i == n - 1) {
                count[i] = chars[i] == letter ? 1 : 0;
                continue;
            }
            count[i] = chars[i] == letter ? count[i + 1] + 1 : count[i + 1];
        }
        // 前一个字符的在字符串的索引
        int pre = -1;
        for (int i = 0; i < k; i++) {
            if (k - i == repetition) {
                while (repetition-- > 0) {
                    sb.append(letter);
                }
                return sb.toString();
            }
            for (int j = 0; j < 26; j++) {
                List<Integer> list = indexList.get(j);
                if (list.size() <= index[j] || list.isEmpty()) {
                    continue;
                }
                int idx = list.get(index[j]);
                while (idx <= pre) {
                    index[j]++;

                    if (list.size() <= index[j]) {
                        break;
                    }
                    idx = list.get(index[j]);
                }
                char ch = (char)(j + 'a');
                int need = ch == letter ? repetition - 1 : repetition;
                if (list.size() > index[j] && need <= count[list.get(index[j])] && k - 1 - i >= need &&  n - idx >= k - i) {
                    if (ch == letter) {
                        repetition--;
                    }
                    sb.append(ch);
                    pre = list.get(index[j]);
                    index[j]++;
                    break;
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        SmallestSubsequence subsequence = new SmallestSubsequence();

        Assert.assertEquals("abefiimnoyytywzy", subsequence.smallestSubsequence("wuynymkihfdcbabefiiymnoyyytywzy", 16, 'y', 4));
        Assert.assertEquals("mmmmxmmm", subsequence.smallestSubsequence("mmmxmxymmm", 8, 'm', 4));
        Assert.assertEquals("abb", subsequence.smallestSubsequence("aaabbbcccddd", 3, 'b', 2));
        Assert.assertEquals("ecde", subsequence.smallestSubsequence("leetcode", 4, 'e', 2));
        Assert.assertEquals("eet", subsequence.smallestSubsequence("leet", 3, 'e', 2));
        Assert.assertEquals("bb", subsequence.smallestSubsequence("bb", 2, 'b', 2));
        Assert.assertEquals("aaaab", subsequence.smallestSubsequence("aaaabbcc", 5, 'a', 4));
    }
}
