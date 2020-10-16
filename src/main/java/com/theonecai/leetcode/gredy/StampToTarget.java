package com.theonecai.leetcode.gredy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 936
 * @Author: theonecai
 * @Date: Create in 2020/19/17 19:47
 * @Description:
 */
public class StampToTarget {

    private int updateCount;

    public int[] movesToStamp(String stamp, String target) {
        this.updateCount = 0;
        if (stamp.length() > target.length()) {
            return new int[0];
        }
        if (stamp.length() == target.length()) {
            if (stamp.equals(target)) {
                return new int[]{0};
            } else {
                return new int[0];
            }
        }

        char[] targets = target.toCharArray();
        int index = target.indexOf(stamp);
        if (index == -1) {
            return new int[0];
        }
        List<Integer> step = new ArrayList<>();
        step.add(index);
        updateToMathAll(targets, index, stamp.length());

        while (this.updateCount < target.length()) {
            boolean updated = false;
            for (int i = 0; i <= targets.length - stamp.length(); i++) {
                int cmp = compare(targets, i, stamp);
                if (cmp == 0) {
                    step.add(i);
                    updateToMathAll(targets, i, stamp.length());
                    updated = true;
                }
            }
            if (!updated) {
                return new int[0];
            }
        }
        int[] result = new int[step.size()];
        int j = result.length - 1;
        for (Integer s : step) {
            result[j--] = s;
        }

        return result;
    }

    private int compare(char[] targets, int i, String stamp) {
        int qCount = 0;
        int idx = 0;
        for (int j = i; j < i + stamp.length(); j++,idx++) {
            if (targets[j] == '?') {
                qCount++;
                continue;
            }
            if (targets[j] != stamp.charAt(idx))  {
                return -1;
            }
        }
        if (qCount == stamp.length()) {
            return 1;
        }

        return 0;
    }

    private void updateToMathAll(char[] targets, int start, int len) {
        for (int i = start; i < start + len; i++) {
            if (targets[i] != '?') {
               targets[i] = '?';
               this.updateCount++;
            }
        }
    }

    public static void main(String[] args) {
        StampToTarget stampToTarget = new StampToTarget();
        System.out.println(Arrays.toString(stampToTarget.movesToStamp("mda", "mdadddaaaa")));
        System.out.println(Arrays.toString(stampToTarget.movesToStamp("abc", "aabcc")));
        System.out.println(Arrays.toString(stampToTarget.movesToStamp("abc", "ababc")));
        System.out.println(Arrays.toString(stampToTarget.movesToStamp("abca", "aabcaca")));
    }
}