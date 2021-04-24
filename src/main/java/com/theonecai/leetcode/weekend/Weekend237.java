package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2021/04/18 10:24
 * @Description:
 */
public class Weekend237 {

    public boolean checkIfPangram(String sentence) {
           boolean[] alph = new boolean[26];
        for (int i = 0; i < sentence.length(); i++) {
            alph[sentence.charAt(i) - 'a'] = true;
        }

        for (boolean b : alph) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public int maxIceCream(int[] costs, int coins) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < costs.length; i++) {
            minHeap.add(costs[i]);
        }

        int remain = coins;
        int count = 0;
        while (remain >= 0 && !minHeap.isEmpty()) {
            int c = minHeap.poll();
            if (c > remain) {
                break;
            }
            remain -= c;
            count++;
        }
        return count;
    }

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        PriorityQueue<TaskInfo> queue = new PriorityQueue<>((a, b) -> {
            int r = a.enqueueTime - b.enqueueTime;
            if (r == 0) {
                r = a.processingTime - b.processingTime;
                if (r == 0) {
                    return a.index - b.index;
                }
            }
            return r;
        });
        for (int i = 0; i < n; i++) {
            queue.add(new TaskInfo(tasks[i][0], tasks[i][1], i));
        }
        int[] result = new int[n];
        int index = 0;

        PriorityQueue<TaskInfo> minHeap = new PriorityQueue<>((a, b) -> {
           if (a.processingTime == b.processingTime) {
               return a.index - b.index;
           }
           return a.processingTime - b.processingTime;
        });
        TaskInfo task;
        long current = queue.peek().enqueueTime;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && current >= queue.peek().enqueueTime) {
                minHeap.add(queue.poll());
            }
            if (minHeap.isEmpty() && !queue.isEmpty()) {
                task = queue.poll();
                result[index++] = task.index;
                current = task.enqueueTime + task.processingTime;
                continue;
            }
            task = minHeap.poll();
            result[index++] = task.index;
            current =current + task.processingTime;
        }
        while (!minHeap.isEmpty()) {
            task = minHeap.poll();
            result[index++] = task.index;
        }

        return result;
    }

    private static class TaskInfo {
        private int enqueueTime;
        private int processingTime;
        private int index;

        public TaskInfo(int enqueueTime, int processingTime, int index) {
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
            this.index = index;
        }
    }

    public int getXORSum(int[] arr1, int[] arr2) {
        int allXor = arr2[0];
        for (int i = 1; i < arr2.length; i++) {
            allXor ^= arr2[i];
        }

        int result = arr1[0] & allXor;
        for (int i = 1; i < arr1.length; i++) {
            result ^= (arr1[i] & allXor);
        }


        return result;
    }

    public static void main(String[] args) {
        Weekend237 weekend = new Weekend237();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {
//        Assert.assertEquals(7, this.getXORSum(new int[]{1,2,3,111}, new int[]{6,5}));
        Assert.assertEquals(0, this.getXORSum(new int[]{1,2,3}, new int[]{6,5}));
        Assert.assertEquals(4, this.getXORSum(new int[]{12}, new int[]{4}));
        Assert.assertEquals(81790984, this.getXORSum(new int[]{818492001,823729238,2261353,747144854,478230859,285970256,774747711,860954509,245631564,634746160},
                new int[]{967900366,340837476}));

    }

    private void test3() {
        System.out.println(Arrays.toString(this.getOrder(new int[][]{
                {5,2},{7,2},{9,4},{6,3},{5,10},{1,1}
        })));
        //[6,1,2,9,4,10,0,11,5,13,3,8,12,7]
        System.out.println(Arrays.toString(this.getOrder(new int[][]{
                {19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}
        })));
        System.out.println(Arrays.toString(this.getOrder(new int[][]{
                {1,2},{2,4},{3,2},{4,1}
        })));
        System.out.println(Arrays.toString(this.getOrder(new int[][]{
                {7,10},{7,12},{7,5},{7,4},{7,2}
        })));
    }

    private void test2() {
        Assert.assertEquals(4, this.maxIceCream(new int[]{1,3,2,4,1}, 7));
        Assert.assertEquals(6, this.maxIceCream(new int[]{1,6,3,1,2,5}, 20));
        Assert.assertEquals(0, this.maxIceCream(new int[]{10,6,8,7,7,8}, 5  ));
    }

    private void test() {
    }
}
