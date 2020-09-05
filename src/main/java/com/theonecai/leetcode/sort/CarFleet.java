package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 853
 * @Author: theonecai
 * @Date: Create in 2020/9/5 09:50
 * @Description:
 */
public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || position.length == 0) {
            return 0;
        }

        Car[] cars = new Car[position.length];
        for (int i = 0; i < position.length; i++) {
            cars[i] = new Car(position[i], speed[i], target - position[i]);
        }

        Arrays.sort(cars);

        int fleet = 1;
        Car preCar = cars[0];
        for (int i = 1; i < cars.length; i++) {
            if (preCar.position == cars[i].position ||
                preCar.costTime >= cars[i].costTime) {
                continue;
            }
            fleet++;
            preCar = cars[i];
        }

        return fleet;
    }

    static class Car implements Comparable<Car> {
        int position;
        double costTime;

        public Car(int position, int speed, int dist) {
            this.position = position;
            costTime = dist / (double)speed;
        }

        @Override
        public int compareTo(Car o) {
            return o.position - this.position;
        }
    }

    public static void main(String[] args) {
        CarFleet carFleet = new CarFleet();
        int[] position = {10,8,0,5,3};
        int[] speed = {2,4,1,1,3};
        Assert.assertEquals(3, carFleet.carFleet(12, position, speed));
    }
}
