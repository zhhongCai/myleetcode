package com.theonecai.leetcode.bit;


import java.util.HashSet;
import java.util.Set;

public class SingleNum {

    public int findSingleNum2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        return set.toArray(new Integer[1])[0];
    }

    public int findSingleNum(int[] nums) {
        int num = 0;
        for (int n : nums) {
            num ^= n;
        }

        return num;
    }


    public static void main(String[] args) {
        SingleNum singleNum = new SingleNum();
        System.out.println(singleNum.findSingleNum(new int[] {1}));
        System.out.println(singleNum.findSingleNum(new int[] {1,2,2,3,3}));
        System.out.println(singleNum.findSingleNum(new int[] {1,2,2}));
        System.out.println(singleNum.findSingleNum(new int[] {2,1,2,1,3}));
    }
}
