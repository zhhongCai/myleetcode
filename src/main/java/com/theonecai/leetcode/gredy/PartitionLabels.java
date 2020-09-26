package com.theonecai.leetcode.gredy;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 763
 * @Author: theonecai
 * @Date: Create in 2020/9/26 11:06
 * @Description:
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26];
        char ch;
        int last = 0;
        for (int i = 0; i < S.length(); i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < S.length(); i++) {
            ch = S.charAt(i);
            last = lastIndex[ch - 'a'];
            if (last == i) {
                result.add(1);
                continue;
            }

            for (int j = i + 1; j < last; j++) {
                last = Math.max(last, lastIndex[S.charAt(j) -'a']);
            }
            result.add(last - i + 1);
            i = last;
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
        System.out.println(partitionLabels.partitionLabels("caedbdedda"));
        System.out.println(partitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
    }
}
