package com.theonecai.leetcode.gredy;

import org.junit.Assert;

/**
 * leetcode 765
 * @Author: thtonecai
 * @Date: Create in 2020/9/20 15:30
 * @Description:
 */
public class MinSwapsCouples {

    public int minSwapsCouples(int[] row) {
        return minSwapsCouplesGreedy(row);
    }

    private int minSwapsCouplesGreedy(int[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i += 2) {
            int lover = getLover(row[i]);
            if (lover == row[i + 1]) {
                continue;
            }
            int index = search(row, i + 2, lover);
            swap(row, i + 1, index);
            count++;
        }
        return count;
    }

    private int search(int[] row, int start, int lover) {
        for (int i = start; i < row.length; i++) {
            if (lover == row[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getLover(int people) {
        int lover;
        if (people % 2 == 0) {
            lover = people + 1;
        } else {
            lover = people - 1;
        }
        return lover;
    }

    private void swap(int[] row, int i, int j) {
        int tmp = row[i];
        row[i] = row[j];
        row[j] = tmp;
    }

    public int minSwapsCouples2(int[] row) {
        int[] unionFindSet = new int[row.length / 2];
        for (int i = 0; i < unionFindSet.length; i++) {
            unionFindSet[i] = i;
        }

        for (int i = 0; i < row.length; i += 2) {
            int lover = getLover(row[i]);
            if (lover == row[i + 1]) {
                continue;
            }
            addToSet(row[i], row[i + 1], unionFindSet);
        }
        int count = 0;
        for (int i = 0; i < unionFindSet.length; i++) {
            if (find(unionFindSet, i) == i) {
                count++;
            }
        }

        return unionFindSet.length - count;
    }

    private void addToSet(int first, int second, int[] unionFindSet) {
        int firstRoot = find(unionFindSet, first / 2);
        int secondRoot = find(unionFindSet, second / 2);
        unionFindSet[firstRoot] = secondRoot;
    }

    private int find(int[] unionFindSet, int num) {
        int i = num;
        while (unionFindSet[i] != i) {
            i = unionFindSet[i];
        }
        if (i != num) {
            unionFindSet[num] = i;
        }
        return i;
    }

    public static void main(String[] args) {
        MinSwapsCouples minSwapsCouples = new MinSwapsCouples();
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{5,4,2,6,3,1,0,7}), minSwapsCouples.minSwapsCouples(new int[]{5,4,2,6,3,1,0,7}));
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{0,2,4,6,7,1,3,5}), minSwapsCouples.minSwapsCouples(new int[]{0,2,4,6,7,1,3,5}));
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{0,2,3,1,4,5,6,7,8,10,9,11}), minSwapsCouples.minSwapsCouples(new int[]{0,2,3,1,4,5,6,7,8,10,9,11}));
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{0,2,1,3}), minSwapsCouples.minSwapsCouples(new int[]{0,2,1,3}));
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{0,2,4,3,1,5}), minSwapsCouples.minSwapsCouples(new int[]{0,2,4,3,1,5}));
        Assert.assertEquals(minSwapsCouples.minSwapsCouples2(new int[]{0,2,3,1,4,5}), minSwapsCouples.minSwapsCouples(new int[]{0,2,3,1,4,5}));
    }
}
