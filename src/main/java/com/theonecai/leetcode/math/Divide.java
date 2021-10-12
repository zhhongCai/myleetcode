package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * leetcode 29
 */
public class Divide {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        int flag = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            flag = -1;
        }
        int de = dividend > 0 ? -dividend : dividend;
        int ds = divisor > 0 ? -divisor : divisor;
        if(ds == -1) {
            return flag > 0 ? (de == Integer.MIN_VALUE ? Integer.MAX_VALUE : -de) : de;
        }
        int res = 0;
        while(de <= ds){
            int c = 1;
            int n = ds;
            while(n >= de){
                if (n < de - n){
                    break;
                }
                n += n;
                c += c;
            }
            res += c;
            de -= n;
        }

        return flag > 0 ? res : -res;
    }

    public static void main(String[] args) {
        Divide divide = new Divide();
        Assert.assertEquals(Integer.MAX_VALUE, divide.divide(Integer.MIN_VALUE, -1));
    }
}
