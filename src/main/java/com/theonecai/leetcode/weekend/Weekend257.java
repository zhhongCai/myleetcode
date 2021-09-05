package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2021/09/05 10:24
 * @Description:
 */
public class Weekend257 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    int d = nums[i] + nums[j] + nums[k];
                    for (int l = k + 1; l < n; l++) {
                        if (d == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int numberOfWeakCharacters(int[][] properties) {

        Arrays.sort(properties,  (o, p) -> o[0] == p[0] ? o[1] - p[1] : p[0] - o[0]);

        int c = 0;
        int max = 0;
        for (int[] property : properties) {
            c += max > property[1] ? 1 : 0;
            max = Math.max(max, property[1]);
        }

        return c;
    }

      public static void main(String[] args) {
        Weekend257 weekend = new Weekend257();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
    }

    private void test3() {
    }

    private void test2() {
        Assert.assertEquals(84, numberOfWeakCharacters(new int[][]{
                {29,26},{92,1},{81,64},{41,54},{56,74},{76,39},{16,69},{78,84},{65,39},{7,2},{62,96},{16,22},{47,34},
                {16,22},{66,26},{93,49},{55,16},{41,21},{11,73},{17,1},{63,81},{90,37},{83,50},{7,97},{86,14},{68,67},
                {65,63},{35,32},{100,1},{23,4},{17,6},{74,52},{98,90},{4,15},{31,36},{69,53},{17,33},{80,56},{8,100},
                {94,85},{89,76},{14,76},{31,85},{89,54},{73,69},{55,7},{73,13},{31,100},{29,55},{82,6},{12,66},{17,72},
                {45,50},{99,73},{41,10},{89,16},{69,35},{72,34},{85,49},{12,5},{61,42},{32,28},{10,55},{61,19},{17,4},
                {48,59},{15,44},{7,48},{1,92},{68,12},{23,6},{5,92},{70,49},{59,3},{9,62},{50,6},{75,39},{7,79},{35,80},
                {94,41},{25,43},{44,16},{3,68},{87,62},{70,62},{68,49},{8,46},{16,7},{16,60},{42,48}
        }));
        Assert.assertEquals(2, numberOfWeakCharacters(new int[][]{
                {7,9},{10,7},{6,9},{10,4},{7,5},{7,10}
        }));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{
                {1,1},{2,1},{2,2},{1,2}
        }));
        Assert.assertEquals(0, numberOfWeakCharacters(new int[][]{
                {5,5},{6,3},{3,6}
        }));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{
                {2,2},{3,3}
        }));
        Assert.assertEquals(1, numberOfWeakCharacters(new int[][]{
                {1,5},{10,4},{4,3}
        }));
    }

    private void test() {

        Assert.assertEquals(1, countQuadruplets(new int[]{28,8,49,85,37,90,20,8}));
    }
}
