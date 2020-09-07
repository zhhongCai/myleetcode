package com.theonecai.leetcode.sort;

import com.theonecai.leetcode.util.RunUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * leetcode 710
 * @Author: theonecai
 * @Date: Create in 2020/9/5 10:14
 * @Description:
 */
public class RandomNumNotInBackList {
    private int n;
    private int[] cachedValue;
    private Map<Integer, Integer> blockToAllow;
    private Random random;

    public RandomNumNotInBackList(int N, int[] blacklist) {
        this.n = N;
        random = new Random(n);
        blockToAllow = new HashMap<>(blacklist.length);

        Arrays.sort(blacklist);

        // 白名单的数少于10w个算出所有
        if (n - blacklist.length < 100000) {
            useCachedValue(blacklist);
        } else {
            useBlockToAllow(blacklist);
        }
    }

    private void useCachedValue(int[] blacklist) {
        cachedValue = new int[n - blacklist.length];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (search(blacklist, 0, i) != -1) {
                continue;
            }
            cachedValue[j++] = i;
        }
    }

    /**
     * 将黑名单数值映射为白名单数值
     * @param blacklist
     */
    private void useBlockToAllow(int[] blacklist) {
        int blockMin = blacklist[0];
        int blockMax = blacklist[blacklist.length - 1];
        int low = 0;
        int high = blockMax + 1;
        int mix = blockMin + 1;
        for (int i = 0; i < blacklist.length; i++) {
            if (low < blockMin || high < n) {
                blockToAllow.put(blacklist[i], low < blockMin ? low : high);
                if (low < blockMin) {
                    low++;
                } else {
                    high++;
                }
            } else {
                int st = 0;
                while ((st = search(blacklist, st, mix)) != -1) {
                    mix++;
                }
                blockToAllow.put(blacklist[i], mix);
            }
        }
    }

    public static int search(int[] sortedNums, int start, int matchValue) {
        int low = start;
        int high = sortedNums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (sortedNums[mid] == matchValue) {
                return mid;
            }
            if (sortedNums[mid] > matchValue) {
                high = mid - 1;
                continue;
            }
            if (sortedNums[mid] < matchValue) {
                low = mid + 1;
            }
        }

        return -1;
    }

    public int pick() {
        int num = random.nextInt(n);
        if (cachedValue != null) {
            return cachedValue[num % cachedValue.length];
        }
        return blockToAllow.getOrDefault(num, num);
    }

    public static void main(String[] args) {
        int n = 1000;
        Random random = new Random();
        Set<Integer> blockSet = new HashSet<>();
        int blockN = 600;
        while (blockN > 0) {
            int num = random.nextInt(n);
            if (blockSet.contains(num)) {
                continue;
            }
            blockSet.add(num);
            blockN--;
        }

        RandomNumNotInBackList randomNum = new RandomNumNotInBackList(n,
                blockSet.stream().mapToInt(Integer::valueOf).toArray());

        RunUtil.runAndPrintCostTime(() -> {
            for (int i = 0; i < 100; i++) {
//                Assert.assertFalse(blockSet.contains(randomNum.pick()));
                System.out.println(randomNum.pick());
            }
        });
    }
}
