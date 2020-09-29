package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 881
 * @Author: theonecai
 * @Date: Create in 2020/9/28 20:11
 * @Description:
 */
public class NumRescueBoats {

    public int numRescueBoats(int[] people, int limit){
        int count = 0;
        int[] bucket = new int[limit + 1];
        for (int person : people) {
            bucket[person]++;
        }

        for (int i = bucket.length - 1; i > 0; i--) {
            while (bucket[i] > 0) {
                bucket[i]--;
                int remain = limit - i;
                while (remain > 0 && bucket[remain] == 0) {
                    remain--;
                }
                if (remain > 0) {
                    bucket[remain]--;
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumRescueBoats numRescueBoats = new NumRescueBoats();
        Assert.assertEquals(3, numRescueBoats.numRescueBoats(new int[]{3,2,2,3,2}, 6));
        Assert.assertEquals(1, numRescueBoats.numRescueBoats(new int[]{2,2}, 6));
        Assert.assertEquals(3, numRescueBoats.numRescueBoats(new int[]{3,2,2,1}, 3));
        Assert.assertEquals(2, numRescueBoats.numRescueBoats(new int[]{1,2,3}, 3));
    }
}
