package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

/**
 * leetcode 75
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/22 20:32
 * @Description:
 */
public class SortColors {

    public int[] sortColors(int[] nums) {
        int zeroLastIndex = -1;
        int towFirstIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (i > 0 && nums[i - 1] > nums[i]) {
                    int currentZeroIndex = i;
                    if (towFirstIndex  > -1) {
                        // 交换第一个2和当前0
                        swap(nums, i, towFirstIndex);
                        currentZeroIndex = towFirstIndex;
                        towFirstIndex++;
                    }
                    // 和最后一个0的后一个元素交换
                    swap(nums, currentZeroIndex, zeroLastIndex + 1);
                }
                zeroLastIndex++;
            } else if (nums[i] == 1) {
                if (i > 0 && nums[i - 1] > nums[i]) {
                    swap(nums, i, towFirstIndex);
                    towFirstIndex++;
                }
            } else {
                if (towFirstIndex == -1) {
                    towFirstIndex = i;
                }
            }
        }
        return nums;
    }

    private void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        SortColors sortColor = new SortColors();
        int[] colors = {0,1,2};
        Assert.assertTrue(sortColor.checkOrdered(sortColor.sortColors(colors)));
        int[] colors2 = {2,1,0,0,1,1,0,2,0,1,0,1,1,2};
        Assert.assertTrue(sortColor.checkOrdered(sortColor.sortColors(colors2)));
        int[] colors3 = {0,0,1,1,2,2};
        Assert.assertTrue(sortColor.checkOrdered(sortColor.sortColors(colors3)));
        for (int i = 0; i < 100; i++) {
            int[] colors4 = ArrayUtil.randIntArray(1000000, 3);
            long a = System.currentTimeMillis();
            int[] result = sortColor.sortColors(colors4);
            System.out.println("cost: " + (System.currentTimeMillis() - a));
            Assert.assertTrue(sortColor.checkOrdered(result));
        }
        int n = 10000000;
        int[] colors5 = new int[n * 3];
        int j= 0;
        for (int i = 0; i < n; i++) {
            colors5[j++] = 1;
            colors5[j++] = 2;
            colors5[j++] = 0;
        }

        long a = System.currentTimeMillis();
        int[] result = sortColor.sortColors(colors5);
        System.out.println("cost: " + (System.currentTimeMillis() - a));
        Assert.assertTrue(sortColor.checkOrdered(result));

    }

    private boolean checkOrdered(int[] colors) {
        for (int i = 1; i < colors.length; i++) {
            if (colors[i] < colors[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
