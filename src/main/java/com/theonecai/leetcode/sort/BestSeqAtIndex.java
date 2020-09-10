package com.theonecai.leetcode.sort;

import org.junit.Assert;

import java.util.Arrays;

/**
 * leetcode 17.08
 * @Author: theonecai
 * @Date: Create in 2020/9/10 19:14
 * @Description:
 */
public class BestSeqAtIndex {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        if (height == null || height.length == 0) {
            return 0;
        }
        People[] people = new People[height.length];
        for (int i = 0; i < height.length; i++) {
            people[i] = new People(height[i], weight[i]);
        }

        Arrays.sort(people);

        int count = 0;
        People pre = null;
        for (People current : people) {
            if (pre == null || (current.height > pre.height && current.weight > pre.weight)) {
                pre = current;
                count++;
            }
        }
        return count;
    }

    static class People implements Comparable<People> {
        int height;
        int weight;

        public People(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(People o) {
            int res = this.height - o.height;
            if (res == 0) {
                return this.weight - o.weight;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        BestSeqAtIndex bestSeqAtIndex = new BestSeqAtIndex();
        int[] weight = {2868,5485,1356,1306,6017,8941,7535,4941,6331,6181};
        int[] height = {5042,3995,7985,1651,5991,7036,9391,428,7561,8594};
        Assert.assertEquals(5, bestSeqAtIndex.bestSeqAtIndex(height, weight));
    }

}
