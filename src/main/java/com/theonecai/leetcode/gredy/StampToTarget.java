package com.theonecai.leetcode.gredy;

import java.util.Arrays;

public class StampToTarget {

    public int[] stampToTarget(String stamp, String target) {
        if (stamp.length() > target.length()) {
            return new int[0];
        }
        if (stamp.length() == target.length()) {
            if (stamp.equals(target)) {
                return new int[]{ 0 };
            } else {
                return new int[0];
            }
        }

        int index = target.indexOf(stamp);
        if (index == -1) {
            return new int[0];
        }


        return new int[0];
    }

    public static void main(String[] args) {
        StampToTarget stampToTarget = new StampToTarget();
        System.out.println(Arrays.toString(stampToTarget.stampToTarget("abc", "aabcc")));
    }
}