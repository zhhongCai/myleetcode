package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * leetcode 787
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/20 20:14
 * @Description:
 */
public class TravelAirLine {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }
        Set<String> cityTransfers = new HashSet<>();
        Map<Integer, List<int[]>> cityAirLineMap = new HashMap<>();
        for (int[] edge : flights) {
            cityAirLineMap.computeIfAbsent(edge[0], e -> new ArrayList<>()).add(edge);
        }
        // transferCost[i]记录到达i城市的费用和经过的中转数
        int[][] transferCost = new int[n + 1][K + 1];
        for (int i = 0; i < transferCost.length; i++) {
            for (int j = 0; j < transferCost[0].length; j++) {
                transferCost[i][j] = -1;
                if (i == src) {
                    transferCost[i][j] = 0;
                }
            }
        }

        // int[0]=城市,int[1]=经过第几中转到达int[0]
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            int res = o1[1] - o2[1];
            if (res == 0) {
                return o1[0] - o2[0];
            }
            return res;
        });
        queue.add(new int[]{src, -1});

        int[] first = null;
        while (!queue.isEmpty()) {
            first = queue.poll();

            List<int[]> nextCities = cityAirLineMap.get(first[0]);
            if (nextCities == null || nextCities.isEmpty()) {
                continue;
            }

            for (int[] toNextCity : nextCities) {
                int[] next = new int[]{toNextCity[1], first[1] + 1};
                int currentCost = first[1] == -1 ? 0 : transferCost[toNextCity[0]][first[1]];
                int nextTransferCost = toNextCity[2] + currentCost;
                if (transferCost[next[0]][next[1]] == -1) {
                    transferCost[next[0]][next[1]] = nextTransferCost;
                } else {
                    transferCost[next[0]][next[1]] = Math.min(nextTransferCost, transferCost[next[0]][next[1]]);
                }
                if (next[1] >= K) {
                    continue;
                }
                String key = next[0] + "_" + next[1];
                if (!cityTransfers.contains(key)) {
                    cityTransfers.add(key);
                    queue.add(next);
                }
            }

        }
        int cost = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (transferCost[dst][i] != -1) {
                cost = Math.min(cost, transferCost[dst][i]);
            }
        }
        return cost == Integer.MAX_VALUE ? -1 : cost;
    }

    public static void main(String[] args) {
        TravelAirLine airLine = new TravelAirLine();

        int[][] edges = {
                {1, 2, 100},
                {1, 3, 400},
                {1, 4, 600},
                {2, 5, 300},
                {2, 6, 600},
                {3, 6, 400},
                {4, 3, 200},
                {5, 3, 100},
                {0, 1, 100},
        };
        long a = System.currentTimeMillis();
        Assert.assertEquals(-1, airLine.findCheapestPrice(6, edges, 1, 6, 0));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 1));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 2));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 3));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 4));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 5));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(700, airLine.findCheapestPrice(6, edges, 1, 6, 15));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(0, airLine.findCheapestPrice(6, edges, 1, 1, 5));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        int[][] flight = {
                {0,1,2},
                {1,2,1},
                {2,0,10},
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 0));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 2));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 1));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 3));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 4));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 5));
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        a = System.currentTimeMillis();
        Assert.assertEquals(1, airLine.findCheapestPrice(3, flight, 1, 2, 3));
        System.out.println("cost: " + (System.currentTimeMillis() - a));

        int[][] flight2 = {
                {0,1,2},
                {0,3,2},
                {0,2,7},
                {1,5,1},
                {3,2,7},
                {3,4,7},
                {1,7,8},
                {1,3,10},

                {4,5,2},
                {4,7,5},
                {4,1,6},

                {5,7,10},
                {6,2,2},
                {7,0,6},
        };
        // 4 -> 7 -> 0 -> 3
        Assert.assertEquals(13, airLine.findCheapestPrice(8, flight2, 4, 3, 7));


        int[][] flight3 = {{3,4,7},{6,2,2},{0,2,7},{0,1,2},{1,7,8},{4,5,2},{0,3,2},{7,0,6},{3,2,7},{1,3,10},{1,5,1},{4,1,6},{4,7,5},{5,7,10}};
        a = System.currentTimeMillis();
        Assert.assertEquals(13, airLine.findCheapestPrice(8, flight3, 4, 3, 7));
        System.out.println("cost: " + (System.currentTimeMillis() - a));


        int[][] flight4 =  {{0,12,28},{5,6,39},{8,6,59},{13,15,7},{13,12,38},{10,12,35},{15,3,23},{7,11,26},{9,4,65},
                {10,2,38},{4,7,7},{14,15,31},{2,12,44},{8,10,34},{13,6,29},{5,14,89},{11,16,13},{7,3,46},{10,15,19},
                {12,4,58},{13,16,11},{16,4,76},{2,0,12},{15,0,22},{16,12,13},{7,1,29},{7,14,100},{16,1,14},{9,6,74},
                {11,1,73},{2,11,60},{10,11,85},{2,5,49},{3,4,17},{4,9,77},{16,3,47},{15,6,78},{14,1,90},{10,5,95},
                {1,11,30},{11,0,37},{10,4,86},{0,8,57},{6,14,68},{16,8,3},{13,0,65},{2,13,6},{5,13,5},{8,11,31},
                {6,10,20},{6,2,33},{9,1,3},{14,9,58},{12,3,19},{11,2,74},{12,14,48},{16,11,100},{3,12,38},{12,13,77},
                {10,9,99},{15,13,98},{15,12,71},{1,4,28},{7,0,83},{3,5,100},{8,9,14},{15,11,57},{3,6,65},{1,3,45},
                {14,7,74},{2,10,39},{4,8,73},{13,5,77},{10,0,43},{12,9,92},{8,2,26},{1,7,7},{9,12,10},{13,11,64},
                {8,13,80},{6,12,74},{9,7,35},{0,15,48},{3,7,87},{16,9,42},{5,16,64},{4,5,65},{15,14,70},{12,0,13},
                {16,14,52},{3,10,80},{14,11,85},{15,2,77},{4,11,19},{2,7,49},{10,7,78},{14,6,84},{13,7,50},{11,6,75},
                {5,10,46},{13,8,43},{9,10,49},{7,12,64},{0,10,76},{5,9,77},{8,3,28},{11,9,28},{12,16,87},{12,6,24},
                {9,15,94},{5,7,77},{4,10,18},{7,2,11},{9,5,41}};
        a = System.currentTimeMillis();
        Assert.assertEquals(47, airLine.findCheapestPrice(17, flight4, 13, 4, 13));
        System.out.println("cost: " + (System.currentTimeMillis() - a));

        /**
         * 10
         * {{0,1,20},{1,2,20},{2,3,30},{3,4,30},{4,5,30},{5,6,30},{6,7,30},{7,8,30},{8,9,30},{0,2,9999},{2,4,9998},{4,7,9997}}
         * 0
         * 9
         * 4
         */
        int[][] flight5 = {
                {0,1,20},
                {0,2,9999},
                {1,2,20},
                {2,3,30},
                {2,4,9998},
                {3,4,30},
                {4,5,30},
                {4,7,9997},
                {5,6,30},
                {6,7,30},
                {7,8,30},
                {8,9,30},
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(30054, airLine.findCheapestPrice(10, flight5, 0, 9, 4));
        System.out.println("cost: " + (System.currentTimeMillis() - a));

    }
}
