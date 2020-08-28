package com.theonecai.leetcode.bit;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/23 10:00
 * @Description:
 */
public class RangeBitwiseAnd {

    public int rangeBitwiseAnd(int m, int n) {
        int result = m;
        for (int i = m + 1; i <= n; i++) {
            result &= i;
        }
        return result;
    }

    public static void main(String[] args) {
        RangeBitwiseAnd rangeBitwiseAnd = new RangeBitwiseAnd();
        /**
         * 5 7
         * 101
         * 110
         * 111
         *
         */
        long a = System.currentTimeMillis();
        System.out.println(rangeBitwiseAnd.rangeBitwiseAnd(1, Integer.MAX_VALUE));
        System.out.println("cost:" + (System.currentTimeMillis() - a));
    }
}
