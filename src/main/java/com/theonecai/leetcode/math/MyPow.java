package com.theonecai.leetcode.math;

import org.junit.Assert;

/**
 * leetcode 50
 * @Author: theonecai
 * @Date: Create in 2021/3/14 09:55
 * @Description:
 */
public class MyPow {

    public double myPow(double x, int n) {
        return myPow(x, (long) n);
    }

    public double myPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1.0 / myPow(x, (long) -n);
        }
        double result = myPow(x, n / 2);

        return n % 2 == 0 ? result * result : result * result * x;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        Assert.assertEquals(1.0, myPow.myPow(1.0,-2147483648), 0.0001);
        Assert.assertEquals(0, myPow.myPow(0.00001,2147483647), 0.0001);
        Assert.assertEquals(Double.POSITIVE_INFINITY, myPow.myPow(0.00001,-22222), 0.0001);
        Assert.assertEquals(1.0 / 12.0, myPow.myPow(12.0, -1), 0.0001);
        Assert.assertEquals(1.0 / 144.0, myPow.myPow(12.0, -2), 0.0001);
        Assert.assertEquals(1.0, myPow.myPow(12.0, 0), 0.0001);
        Assert.assertEquals(1.0, myPow.myPow(12.0, 0), 0.0001);
        Assert.assertEquals(144.0, myPow.myPow(12.0, 2), 0.0001);
    }
}
