package com.theonecai.leetcode.bit;

/**
 * leetcode 201
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/23 10:00
 * @Description:
 */
public class RangeBitwiseAnd {

    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) {
            return m;
        }
        int shift = 0;
        while (m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }

        return m << shift;
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
