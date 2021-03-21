package com.theonecai.leetcode.array;

/**
 * leetcode 1603
 * @Author: theonecai
 * @Date: Create in 2021/3/19 20:31
 * @Description:
 */
public class ParkingSystem {
    private int[] remain;

    public ParkingSystem(int big, int medium, int small) {
        remain = new int[3];
        remain[2] = big;
        remain[1] = medium;
        remain[0] = small;
    }

    public boolean addCar(int carType) {
        if (remain[carType - 1] == 0) {
            return false;
        }
        remain[carType - 1]--;
        return true;
    }
}
