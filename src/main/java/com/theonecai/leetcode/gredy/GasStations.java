package com.theonecai.leetcode.gredy;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

/**
 * leetcode 134
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/11 19:20
 * @Description:
 */
public class GasStations {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        long currentRemain = 0;
        long totalRemain = 0;
        for (int i = 0; i < gas.length; i++) {
            totalRemain += gas[i] - cost[i];
            currentRemain += gas[i] - cost[i];
            if (currentRemain < 0) {
                currentRemain = 0;
                start = i + 1;
            }
        }

        return totalRemain < 0 ? -1 : start;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int[] remains = new int[gas.length];
        long totalRemain = 0;
        for (int i = 0; i < gas.length; i++) {
            remains[i] = gas[i] - cost[i];
            totalRemain += remains[i];
        }
        if (totalRemain < 0) {
            return -1;
        }

        int remain = 0;
        int preRemain = -1;
        for (int i = 0; i < gas.length; i++) {
            remain = gas[i] - cost[i];

            if (remain >= 0 && preRemain < 0 && isReachable(remains, i)) {
                return i;
            }
            preRemain = remain;

        }

        return -1;
    }


    private boolean isReachable(int[] remains, int startIndex) {
        int remain = 0;
        int i = 0;
        int j = 0;
        while (i < remains.length) {
            j = (i + startIndex) % remains.length;
            remain += remains[j];
            if (remain < 0) {
                return false;
            }
            i++;
        }
        return true;
    }


    public static void main(String[] args) {
        GasStations gasStations = new GasStations();
        /**
         * -2 -2 -2 3 3
         */
        int[] gas = {1, 2, 3, 1, 8};
        int[] cost = {3, 4, 5, 1, 2};
        int p = gasStations.canCompleteCircuit(gas, cost);
        Assert.assertEquals(3, p);
        p = gasStations.canCompleteCircuit2(gas, cost);
        Assert.assertEquals(3, p);

        /**
         * -2 -2 -3 3 3
         */
        long sum = 0;
        long sum2 = 0;
        int n = 4;
        for (int i = 0; i < n; i++) {
            int[] gas2  = ArrayUtil.randIntArray(10000000);
            int[] cost2 = ArrayUtil.randIntArray(10000000);
            long a = System.currentTimeMillis();
            p = gasStations.canCompleteCircuit(gas2, cost2);
            sum += System.currentTimeMillis() - a;
            a = System.currentTimeMillis();
            int p2 = gasStations.canCompleteCircuit2(gas2, cost2);
            sum2 += System.currentTimeMillis() - a;
            if (p != p2) {
                System.out.println("p=" + p + ",p2=" + p2 + ", i=" + i);
            }
        }
        System.out.println("sum=" + sum + ", avg=" + sum/n);
        System.out.println("sum2=" + sum2 + ", avg2=" + sum2/n);
    }
}
