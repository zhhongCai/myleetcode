package com.theonecai.leetcode.weekend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekendD72 {

    public int countPairs(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] && (i * j % k) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[0];
        }
        long mid = num / 3;

        return new long[] {mid - 1, mid, mid + 1};
    }

    public List<Long> maximumEvenSplit(long finalSum) {
         List<Long> list = new ArrayList<>();
         if (finalSum % 2 == 1) {
             return list;
         }
         if (finalSum < 5) {
             list.add(finalSum);
             return list;
         }

         long n = finalSum - 2;
         long c = 4;
         list.add(2L);
         while (n > 0) {
             if (n / 2 <= c) {
                 list.add(n);
                 break;
             }
             list.add(c);
             n -= c;
             c += 2;
         }

         return list;
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[nums2[i]] = i;
        }

        long ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int x = nums1[i];
            for (int j = i + 1; j < n - 1; j++) {
                int y = nums1[j];
                for (int k = j + 1; k < n; k++) {
                    int z = nums1[k];
                    int px = index[x];
                    int py = index[y];
                    int pz = index[z];
                    if (px < py && py < pz) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        WeekendD72 weekend = new WeekendD72();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
    }

    private void test4() {

    }

    private void test3() {
        System.out.println(maximumEvenSplit(2));;
        System.out.println(maximumEvenSplit(4));;
        System.out.println(maximumEvenSplit(6));;
        System.out.println(maximumEvenSplit(12));;
        System.out.println(maximumEvenSplit(7));;
        System.out.println(maximumEvenSplit(28));;
//        System.out.println(maximumEvenSplit(10000000000L));;
    }

    private void test2() {
    }

    private void test() {

    }
}
