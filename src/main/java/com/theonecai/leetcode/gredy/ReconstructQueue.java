package com.theonecai.leetcode.gredy;

import java.util.Arrays;

/**
 * leetcode 406
 * @Author: theonecai
 * @Date: Create in 2020/9/16 19:18
 * @Description:
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            int res = o1[1] - o2[1];
            if (res == 0) {
                return o1[0] - o2[0];
            }
            return res;
        });
        for (int i = 1; i < people.length; i++) {
            int[] p = people[i];
            int count = 0;
            int j = 0;
            while (j < i) {
                if (people[j][0] >= p[0]) {
                    count++;
                }
                if (count > p[1]) {
                    break;
                }
                j++;
            }
            if (j < i) {
                System.arraycopy(people, j, people, j + 1, i - j);
                people[j] = p;
            }

        }

        return people;
    }

    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        /**
         * [5, 0]
         * [7, 0]
         * [5, 2]
         * [6, 1]
         * [4, 5]
         * [7, 1]
         */
        int[][] people = {{7,0},{4,5},{7,1},{5,0},{6,1},{5,2}};
        int[][] p = reconstructQueue.reconstructQueue(people);
        for (int[] ints : p) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
