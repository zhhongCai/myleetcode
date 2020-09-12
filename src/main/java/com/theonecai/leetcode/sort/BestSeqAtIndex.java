package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

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

        // 按高度排序
        Arrays.sort(people);

        return longestIncSubSeq(people);
    }

    /**
     * 对体重，二分求最长递增子串
     * @param people
     * @return
     */
    private int longestIncSubSeq(People[] people) {
        int[] top = new int[people.length];
        int piles = 0;
        for (People person : people) {
            int w = person.weight;
            int low = 0;
            int high = piles;
            int mid = 0;
            while (low < high) {
                mid = (low + high) / 2;
                if (top[mid] > w) {
                    high = mid;
                } else if (top[mid] < w) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (low == piles) {
                piles++;
            }
            top[low] = w;
        }

        return piles;
    }

    public int bestSeqAtIndex2(int[] height, int[] weight) {
        if (height == null || height.length == 0) {
            return 0;
        }
        People[] people = new People[height.length];
        for (int i = 0; i < height.length; i++) {
            people[i] = new People(height[i], weight[i]);
        }

        // 按高度排序
        Arrays.sort(people);

        /**
         * count[i]=count[j] + 1, count[i]表示处理第i个人时的最多人数,j表示之前i-1个人中
         * 体重比i小且count[j]最大的那个j,如果不存在count[i]=1
         */
        int[] count = new int[height.length];
        TreeSet<People> treeSet = new TreeSet<>(Comparator.comparingInt(o -> o.weight));
        People p = people[0];
        p.setIndex(0);
        treeSet.add(p);
        count[0] = 1;
        int max = count[0];
        for (int i = 1; i < people.length; i++) {
            p = people[i];
            p.setIndex(i);
            People maxPeople = null;
            int c = 0;
            People pp = p;
            while ((pp = treeSet.lower(pp)) != null) {
                if (count[pp.index] > c) {
                    c = count[pp.index];
                    maxPeople = pp;
                }
            }
            if (maxPeople == null) {
                count[i] = 1;
            } else {
                count[i] = count[maxPeople.index] + 1;
            }
            treeSet.add(p);
            max = Math.max(count[i], max);
        }

//        for (int i = 0; i < people.length; i++) {
//            People person = people[i];
//            System.out.println(person.height + "," + person.weight + ", " + count[i]);
//        }
//        System.out.println();

        return max;
    }

    static class People implements Comparable<People> {
        int height;
        int weight;
        int index;

        public void setIndex(int index) {
            this.index = index;
        }

        public People(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(People o) {
            int res = this.height - o.height;
            if (res == 0) {
                return o.weight - this.weight;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        BestSeqAtIndex bestSeqAtIndex = new BestSeqAtIndex();
        int[] height = {2868,5485,1356,1306,6017,8941,7535,4941,6331,6181};
        int[] weight = {5042,3995,7985,1651,5991,7036,9391,428,7561,8594};
        Assert.assertEquals(5, bestSeqAtIndex.bestSeqAtIndex(height, weight));

        int[] height2 = {7993,4630,2152,7609,6012,5878,4325,3955,4326,1883};
        int[] weight2 = {3793,8079,8452,1378,4102,3204,6633,4417,1374,7015};
        Assert.assertEquals(3, bestSeqAtIndex.bestSeqAtIndex(height2, weight2));
        Assert.assertEquals(5, bestSeqAtIndex.bestSeqAtIndex(
                new int[]{5401,9628,3367,6600,6983,7853,5715,2654,4453,8619},
                new int[]{3614,1553,2731,7894,8689,182,7632,4465,8932,4304}));

        Assert.assertEquals(4, bestSeqAtIndex.bestSeqAtIndex(
                new int[]{8378,8535,8998,3766,648,6184,5506,5648,3907,6773},
                new int[]{9644,849,3232,3259,5229,314,5593,9600,6695,4340}));

        int[] h3 = new int[10000];
        int[] w3 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            h3[i] = i;
            w3[i] = 10000 - i;
        }
        RunUtil.runAndPrintCostTime(() -> {
            Assert.assertEquals(1, bestSeqAtIndex.bestSeqAtIndex(h3, w3));
        });

    }


}
