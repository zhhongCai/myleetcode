package com.theonecai.leetcode.bit;

import com.theonecai.leetcode.util.RunUtil;

import java.util.*;

public class FindRepeatedDnaSequences {
    //public List<String> findRepeatedDnaSequences(String s) {
    public List<String> findRepeatedDnaSequences(String s) {
        int subLen = 10;

        Map<Character, Integer> map = new HashMap<>(4);
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);

        Set<Integer> existsSet = new HashSet<>();
        Set<String> strSet = new HashSet<>();

        int bitmask = 0;
        for (int i = 0; i < subLen; i++) {
            bitmask |= map.get(s.charAt(i));
            bitmask <<= 2;
        }
        existsSet.add(bitmask);
        for (int i = 1; i < s.length() - subLen; i++) {
            bitmask <<= 2;
            bitmask |= map.get(s.charAt(i + subLen));
            bitmask &= 0x0fffff;
            if (existsSet.contains(bitmask)) {
                strSet.add(s.substring(i, i + 10));
            } else {
                existsSet.add(bitmask);
            }
        }
        return new ArrayList<>(strSet);
    }

    public static void main(String[] args) {
        FindRepeatedDnaSequences findRepeatedDnaSequences = new FindRepeatedDnaSequences();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append('A');
        }
        RunUtil.runAndPrintCostTime(() -> {
            System.out.println(findRepeatedDnaSequences.findRepeatedDnaSequences(sb.toString()));
        });

        System.out.println(findRepeatedDnaSequences.findRepeatedDnaSequences("AAAACCCCAAAAAACCCCAAAA"));
    }
}
