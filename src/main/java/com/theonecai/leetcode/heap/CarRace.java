package com.theonecai.leetcode.heap;

import org.junit.Assert;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/21 21:24
 * @Description:
 */
public class CarRace {
    private long position;
    private long speed;

    private int cmdCount;

    public int minCmdCount(long target) {
        position = 0;
        speed = 1;
        cmdCount = 0;

        int c = 0;
        while (position != target && c++ < 1000) {
            if (position < target) {
                if (speed < 0) {
                    retreat();
                } else {
                    accelerate();
                }
            } else {
                if (speed < 0) {
                    accelerate();
                } else {
                    retreat();
                }
            }
        }

        System.out.println();
        return cmdCount;
    }

    /**
     * 2^(k-1) < target < 2^k
     *
     *
     * speed > 0
     * c[i] = min(c[i-1] + RA
     * speed < 0
     * @param target
     * @return
     */
    public int minCmdCount2(long target) {
        cmdCount = 0;
        return cmdCount;
    }


    public void accelerate() {
        position += speed;
        speed *= 2;
        cmdCount++;
        System.out.println("A" + position + "," + speed);
    }

    public void retreat() {
        speed = speed > 0 ? -1 : 1;
        cmdCount++;
        System.out.println("R" + position + "," + speed);
    }

    public static void main(String[] args) {
        CarRace carRace = new CarRace();
        Assert.assertEquals(0, carRace.minCmdCount(0));
        Assert.assertEquals(2, carRace.minCmdCount(3));
        Assert.assertEquals(5, carRace.minCmdCount(6));
        Assert.assertEquals(5, carRace.minCmdCount(-2));
        long a = System.currentTimeMillis();
        Assert.assertEquals(31, carRace.minCmdCount(Integer.MAX_VALUE));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(63, carRace.minCmdCount(Long.MAX_VALUE));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(33, carRace.minCmdCount(Integer.MAX_VALUE - 1));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(4, carRace.minCmdCount(2));
        Assert.assertEquals(6, carRace.minCmdCount(4));
        //A1,2;A3,4;R3,-1;A2,-2;R2,1;A3,2;A5,4
        Assert.assertEquals(7, carRace.minCmdCount(5));
        Assert.assertEquals(5, carRace.minCmdCount(6));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
    }
}
