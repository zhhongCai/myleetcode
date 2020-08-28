package com.theonecai.leetcode.heap;

import com.theonecai.algorithms.JsonUtil;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode 743
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/17 20:47
 * @Description:
 */
public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> nodeMap = new HashMap<>(N);
        // visitedMinTime[i] 表示从K到i的最小延时
        int[] visitedMinTime = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int[] time : times) {
            nodeMap.computeIfAbsent(time[0], e -> new ArrayList<>()).add(time);
        }
        Arrays.fill(visitedMinTime, Integer.MAX_VALUE);
        visitedMinTime[0] = 0;
        visitedMinTime[K] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t[2]));
        if (nodeMap.get(K) == null) {
            return -1;
        }
        queue.add(new int[]{0, K, 0});

        int[] node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            visited[node[0]] = true;

            if (!nodeMap.containsKey(node[1])) {
                continue;
            }
            for (int[] time : nodeMap.get(node[1])) {
                visitedMinTime[time[1]] = Math.min(time[2] + visitedMinTime[time[0]], visitedMinTime[time[1]]);
                if (visited[time[1]]) {
                    continue;
                }
                queue.add(time);
            }
        }

        int delay = 0;
        for (int i = 1; i <= N; i++) {
            if (visitedMinTime[i] == Integer.MAX_VALUE && i != K) {
                return -1;
            }
            if (i != K) {
                delay = Math.max(delay, visitedMinTime[i]);
            }
        }

        return delay;
    }

    public static void main(String[] args) throws IOException {
        NetworkDelayTime delayTime = new NetworkDelayTime();
        long a = System.currentTimeMillis();
        int[][] times = {
                {1,2,1},
                {2,1,3},
        };
        Assert.assertEquals(3, delayTime.networkDelayTime(times, 2, 2));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times2 = {
                {1,2,1},
                {2,3,2},
                {1,3,4}
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(3, delayTime.networkDelayTime(times2, 3, 1));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times3 = {
                {1,2,1},
                {1,4,2},
                {1,3,4}

        };
        a = System.currentTimeMillis();
        Assert.assertEquals(4, delayTime.networkDelayTime(times3, 4, 1));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times4 = {
                {1,2,1},
                {2,3,2},
                {3,4,2},
                {1,3,6},
                {4,3,1},
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(5, delayTime.networkDelayTime(times4, 4, 1));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times5 = {
                {1,2,1},
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(-1, delayTime.networkDelayTime(times5, 2, 2));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times6 = {
         {10,9,79},{15,10,58},{14,4,99},{14,12,29},{12,15,26},{1,15,78},{2,11,88},{7,3,4},{3,1,52},{11,3,91},{11,12,11},
         {5,10,81},{1,7,44},{12,13,52},{3,14,83},{10,4,26},{5,9,72},{5,14,32},{13,10,32},{15,6,2},{3,9,18},{1,11,45},{5,8,98},
         {7,13,33},{1,2,59},{4,11,79},{11,1,12},{8,5,79},{2,14,93},{3,6,53},{11,10,40},{14,2,33},{4,9,61},{3,8,10},{10,7,1},
         {8,3,58},{1,12,20},{5,1,51},{7,1,37},{9,7,34},{9,10,48},{8,4,90},{12,1,92},{6,4,99},{2,15,3},{2,3,80},{2,4,60},
         {15,14,75},{2,7,20},{15,8,20},{5,12,19},{13,3,74},{7,5,6},{9,6,73},{9,14,49},{15,1,56},{8,2,10},{7,9,9},{12,5,67},
         {6,3,29},{9,4,38},{6,9,42},{5,3,57},{3,2,48},{12,6,77},{10,15,15},{12,4,68},{14,1,52},{13,4,80},{4,1,84},{14,10,68},
         {2,12,81},{2,1,31},{6,14,52},{7,8,68},{4,12,73},{8,14,88},{13,5,92},{6,1,3},{9,11,80},{3,15,23},{15,4,84},{5,11,41},
         {7,11,42},{11,7,86},{9,15,63},{1,4,36},{3,13,82},{6,15,91},{13,6,64},{14,11,32},{11,5,68},{6,5,55},{4,5,35},{13,1,1},
         {4,10,47},{12,9,1},{7,10,44},{3,7,23},{8,12,68},{8,6,13},{2,9,19},{10,6,91},{7,12,80},{8,7,12},{4,7,4},{9,2,67},{14,9,29},
         {15,13,80},{6,8,62},{15,12,36},{1,3,48},{2,10,67},{9,13,55},{11,6,62},{8,11,92},{13,15,30},{4,13,97},{5,4,25},{4,2,9},
         {15,5,5},{15,2,45},{10,8,23},{14,5,43},{5,13,98},{14,13,73},{4,8,29},{10,5,0},{11,13,68},{9,12,91},{12,2,56},{9,1,23},
         {14,6,80},{9,5,10},{12,11,89},{5,15,94},{7,2,20},{3,12,89},{2,13,9},{11,2,1},{10,13,85},{6,10,76},{1,10,2},{14,15,20},
         {3,11,15},{11,8,62},{12,7,63},{8,15,91},{8,10,30},{12,3,80},{5,7,94},{13,2,60},{14,8,77},{10,12,67},{13,8,9},{13,11,48},
         {5,6,77},{10,3,51},{4,15,84},{13,12,10},{13,14,28},{4,6,46},{3,10,53},{14,7,48},{10,11,21},{15,11,99},{12,10,93},{11,14,73},
         {15,3,81},{2,5,22},{12,8,20},{6,13,24},{8,13,41},{8,9,98},{2,6,98},{7,15,27},{6,11,15},{7,14,72},{10,14,96},{1,8,18},{1,6,2},
         {3,4,78},{4,3,10},{11,4,54},{12,14,40},{3,5,63},{10,2,94},{1,9,57},{6,12,12},{9,8,37},{8,1,77},{13,7,80},{10,1,25},{9,3,37},
         {4,14,28},{1,13,88},{1,5,45},{7,4,65},{6,2,19},{11,15,37},{7,6,90},{2,8,1},{1,14,63},{5,2,47},{15,9,34},{11,9,41},{15,7,90},
         {13,9,45},{14,3,34},{6,7,44}
        };
        Arrays.sort(times6, (o1, o2) -> {
            int r = o1[0] - o2[0];
            if (r == 0) {
                return o1[1] - o2[1];
            }
            return r;
        });

        a = System.currentTimeMillis();
        Assert.assertEquals(49, delayTime.networkDelayTime(times6, 15, 9));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times7 = {
                {1, 2, 59},
                {1, 3, 86},
                {1, 4, 98},
                {1, 5, 54},
                {2, 1, 0},
                {2, 3, 61},
                {2, 4, 10},
                {2, 5, 89},
                {3, 1, 79},
                {3, 2, 64},
                {3, 4, 33},
                {3, 5, 77},
                {4, 1, 95},
                {4, 2, 76},
                {4, 3, 46},
                {4, 5, 21},
                {5, 1, 34},
                {5, 2, 38},
                {5, 3, 2},
                {5, 4, 44}
        };
        a = System.currentTimeMillis();
        Assert.assertEquals(69, delayTime.networkDelayTime(times7, 5, 1));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        int[][] times8 = {{1,2,1},{2,3,7},{1,3,4},{2,1,2}};
        a = System.currentTimeMillis();
        Assert.assertEquals(6, delayTime.networkDelayTime(times8, 3, 2));
        System.out.println("cost=" + (System.currentTimeMillis() - a));

        ClassLoader loader = delayTime.getClass().getClassLoader();
        URL url = loader.getResource("networkdelaytime-data.txt");
        File file = new File(url.getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String data = br.readLine();
        List<List<Integer>> list = JsonUtil.readValue(data, List.class);
        int[][] times9 = new int[list.size()][3];
        int i = 0;
        for (List<Integer> ints : list) {
            times9[i++] = new int[]{ints.get(0), ints.get(1), ints.get(2)};
        }
        a = System.currentTimeMillis();
        Assert.assertEquals(9, delayTime.networkDelayTime(times9, 75, 25));
        System.out.println("cost=" + (System.currentTimeMillis() - a));
    }


}
