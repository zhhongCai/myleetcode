package com.theonecai.leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 451
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/26 11:38
 * @Description:
 */
public class FrequencySort {

    public String frequencySort(String s) {
        Map<Character, Char> map = new HashMap<>(256);
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, new Char(c, 1));
            } else {
                map.get(c).incCount();
            }
        }

        Char[] chars = map.values().toArray(new Char[0]);
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder();
        for (Char ch : chars) {
            while (ch.count-- > 0) {
                sb.append(ch.ch);
            }
        }

        return sb.toString();
    }

    static class Char implements Comparable<Char> {
        private char ch;
        private int count;

        public Char(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        public void incCount() {
            this.count++;
        }

        @Override
        public int compareTo(Char o) {
            return o.count - this.count;
        }
    }


    public static void main(String[] args) {
        FrequencySort frequencySort = new FrequencySort();
        System.out.println(frequencySort.frequencySort("tree"));
    }
}
