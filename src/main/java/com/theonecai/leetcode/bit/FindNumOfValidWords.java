package com.theonecai.leetcode.bit;

import java.util.*;

/**
 * leetcode 1178
 */
public class FindNumOfValidWords {

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> answers = new ArrayList<>(puzzles.length);
        Map<Integer, Integer> wordsMaskCountMap = new HashMap<>();
        for (String str : words) {
            int mask = 0;
            for (int i = 0; i < str.length(); i++) {
                mask |= 1 << (str.charAt(i) - 'a');
            }
            wordsMaskCountMap.put(mask, wordsMaskCountMap.getOrDefault(mask, 0) + 1);
        }

        for (int i = 0; i < puzzles.length; i++) {
            int puzzleMask = toBitMasks(puzzles[i]);
            int answer = 0;
            int firstMask = 1 << (puzzles[i].charAt(0) - 'a');
            for (int wordMask = puzzleMask; wordMask != 0; wordMask = (wordMask - 1) & puzzleMask) {
                Integer wordMaskCount = wordsMaskCountMap.get(wordMask);
                if (wordMaskCount != null && (wordMask & firstMask) == firstMask) {
                    answer += wordMaskCount;
                }
            }

            answers.add(answer);
        }

        return answers;
    }

    /**
     * 每个字母使用1位来表示: a: 1, b: 10, c: 100,...
     * @param str
     * @return
     */
    private int toBitMasks(String str) {
        int mask = 0;
        for (int i = 0; i < str.length(); i++) {
            mask |= 1 << (str.charAt(i) - 'a');
        }
        return mask;
    }

    public static void main(String[] args) {
        FindNumOfValidWords findNumOfValidWords = new FindNumOfValidWords();
        //[0,1,3,2,0]
        System.out.println(findNumOfValidWords.findNumOfValidWords(new String[] {
                "apple","pleas","please"
        }, new String[]{
		    "aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"
        }));

        //[1,1,3,2,4,0]
        System.out.println(findNumOfValidWords.findNumOfValidWords(new String[]{
                "aaaa","asas","able","ability","actt","actor","access"

        }, new String[] {
                "aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"
        }));
    }
}
