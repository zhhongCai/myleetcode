package com.theonecai.leetcode.bit;

import org.junit.Assert;

/**
 * leetcode 1442
 */
public class CountTriplets {

    public int countTriplets(int[] arr) {
        int[] prefixXor = new int[arr.length];
        prefixXor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixXor[i] = prefixXor[i - 1] ^ arr[i];
        }

        int a;
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int k = i + 1; k < arr.length; k++) {
                a = (i == 0 ? 0 : prefixXor[i - 1]) ^ prefixXor[k];
                if (a == 0) {
                    count += k - i;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountTriplets countTriplets = new CountTriplets();
        Assert.assertEquals(4, countTriplets.countTriplets(new int[]{2,3,1,6,7}));
        Assert.assertEquals(10, countTriplets.countTriplets(new int[]{1,1,1,1,1}));
        Assert.assertEquals(3, countTriplets.countTriplets(new int[]{1,3,5,7,9}));
        Assert.assertEquals(0, countTriplets.countTriplets(new int[]{2,3}));
        Assert.assertEquals(8, countTriplets.countTriplets(new int[]{7,11,12,9,5,2,7,17,22}));
    }
}
