package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1338
 * @Author: theonecai
 * @Date: Create in 2020/9/29 20:20
 * @Description:
 */
public class MinSetSize {

    public int minSetSize2(int[] arr) {
        int count = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int[] counts = countMap.values().stream().sorted(Comparator.reverseOrder()).mapToInt(v -> v).toArray();
        int sum = 0;
        int half = arr.length / 2;
        for (int c : counts) {
            count++;
            sum += c;
            if (sum >= half) {
                break;
            }
        }

        return count;
    }

    public int minSetSize(int[] arr) {
        int count = 0;
        int max = 0;
        for (int n : arr) {
            max = Math.max(max, n);
        }
        int[] counts = new int[max + 1];
        for (int n : arr) {
            counts[n]++;
        }
        Arrays.sort(counts);
        int sum = 0;
        int half = arr.length / 2;
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == 0) {
                break;
            }
            sum += counts[i];
            count++;
            if (sum >= half) {
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MinSetSize minSetSize = new MinSetSize();
        Assert.assertEquals(2, minSetSize.minSetSize(new int[]{3,3,3,3,5,5,5,2,2,7}));
    }
}
