package com.theonecai.leetcode.gredy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * leetcode 870
 * @Author: theonecai
 * @Date: Create in 2020/9/27 20:22
 * @Description:
 */
public class AdvantageCount {

    public int[] advantageCount(int[] A, int[] B) {
        Map<Integer, Integer> countMap = new HashMap<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : A) {
            if (!countMap.containsKey(num)) {
                countMap.put(num, 0);
            }
            treeSet.add(num);
            countMap.put(num, countMap.get(num) + 1);
        }
        int[] result = new int[A.length];
        int index = 0;
        for (int i = 0; i < B.length; i++) {
            Integer num = treeSet.higher(B[i]);
            if (num == null) {
                num = treeSet.first();
            }
            result[index++] = num;
            countMap.put(num, countMap.get(num) - 1);
            if (countMap.get(num) == 0) {
                treeSet.remove(num);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AdvantageCount advantageCount = new AdvantageCount();
        System.out.println(Arrays.toString(advantageCount.advantageCount(new int[]{2,7,11,15}, new int[]{1,10,4,11})));
        System.out.println(Arrays.toString(advantageCount.advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11})));
        System.out.println(Arrays.toString(advantageCount.advantageCount(new int[]{2,0,4,1,2}, new int[]{1,3,0,0,2})));
    }
}
