package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 1029
 * @Author: theonecai
 * @Date: Create in 2020/9/22 08:23
 * @Description:
 */
public class TwoCitySchedCost {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt(o -> o[0] - o[1]));

        int cost = 0;
        for (int i = 0; i < costs.length; i++) {
            if (i < costs.length / 2) {
                cost += costs[i][0];
            } else {
                cost += costs[i][1];
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        TwoCitySchedCost citySchedCost = new TwoCitySchedCost();
        Assert.assertEquals(1859, citySchedCost.twoCitySchedCost(new int[][]{{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}}));
        Assert.assertEquals(110, citySchedCost.twoCitySchedCost(new int[][]{{10,20},{30,200},{400,50},{30,20}}));
    }
}
