package com.theonecai.leetcode.bit;

import java.util.Arrays;

public class TwoSingleNum {

    public int[] find(int[] nums) {
        // 假设两个数为x,y,则bitmask= x ^ y
        int bitmask = 0;
        for (int num : nums) {
            bitmask ^= num;
        }
        // x或y中的不同的1位
        int diff = bitmask & (-bitmask);
        int x = 0;
        for (int num : nums) {
            // 可能是x(或y)
            if ((diff & num) > 0) {
               x ^= num;
            }
        }
        return  new int[] {x, bitmask ^ x};
    }

    public static void main(String[] args) {
        TwoSingleNum twoSingleNum = new TwoSingleNum();
        System.out.println(Arrays.toString(twoSingleNum.find(new int[]{1,2,1,2,3,4})));
    }
}
