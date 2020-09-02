package com.theonecai.leetcode.util;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/1 19:30
 * @Description:
 */
public interface RunAndPrintCostTime {

    default void run() {
        long start = System.currentTimeMillis();
        try {
            runInternal();
        } catch (Error | Exception e) {
            e.printStackTrace();
        }
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    void runInternal();
}
