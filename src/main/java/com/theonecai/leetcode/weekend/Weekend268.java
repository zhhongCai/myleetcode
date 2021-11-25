package com.theonecai.leetcode.weekend;


import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2021/11/21 10:24
 * @Description:
 */
public class Weekend268 {

    public int maxDistance(int[] colors) {
        int res = 0;
        for (int i = 0; i < colors.length - 1; i++) {
            for (int j = i + 1; j < colors.length; j++) {
                if (colors[i] != colors[j]) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    public int wateringPlants(int[] plants, int capacity) {
        int n = plants.length;
        int step = 0;
        int cur = capacity;
        for (int i = 0; i < n; i++) {
            step++;
            if (cur >= plants[i]) {
                cur -= plants[i];
            }
            if (i < n - 1 && cur < plants[i + 1]) {
                cur = capacity;
                step += 2 * (i + 1);
            }
        }

        return step;
    }

    public class RangeFreqQuery {

        private Map<Integer, List<Integer>> idxMap;

        public RangeFreqQuery(int[] arr) {
            this.idxMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                List<Integer> idxList = idxMap.getOrDefault(arr[i], new ArrayList<>());
                idxList.add(i);
                idxMap.put(arr[i], idxList);
            }
        }

        public int query(int left, int right, int value) {
            if (!idxMap.containsKey(value)) {
                return 0;
            }
            List<Integer> idxList = idxMap.get(value);
            if (left > idxList.get(idxList.size() - 1) || right < idxList.get(0)) {
                return 0;
            }

            int l = find(idxList, left, true);
            int r = find(idxList, right, false);
            return r - l + 1;
        }

        private int find(List<Integer> idxList, int val, boolean ge) {
            int left = 0;
            int right = idxList.size() - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (idxList.get(mid) == val) {
                    return mid;
                } else if (idxList.get(mid) > val) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (!ge) {
                if (idxList.get(left) > val) {
                    left--;
                }
                return left;
            }
            if (idxList.get(left) < val) {
                left++;
            }
            return left;
        }
    }

    public static void main(String[] args) {
        Weekend268 weekend = new Weekend268();
        weekend.test();
        weekend.test2();
        weekend.test3();
        weekend.test4();
        int[] nums = new int[] {1,3,5,7,9,11,12,13,14,15};
        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 4));
        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 6));
        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 0));
        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 16));
        System.out.println(Arrays.binarySearch(nums, 0, nums.length, 15));

    }

    private void test4() {
    }

    private void test3() {
        RangeFreqQuery query = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        Assert.assertEquals(1, query.query(1,2,4));
        Assert.assertEquals(2, query.query(0,11,33));

    }

    private void test2() {
        Assert.assertEquals(14, wateringPlants(new int[]{2,2,3,3}, 5));
        Assert.assertEquals(30, wateringPlants(new int[]{1,1,1,4,2,3}, 4));
        Assert.assertEquals(49, wateringPlants(new int[]{7,7,7,7,7,7,7}, 8));
    }

    private void test() {
    }
}
