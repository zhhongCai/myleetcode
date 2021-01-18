package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: theonecai
 * @Date: Create in 2021/01/17 10:24
 * @Description:
 */
public class Weekend224 {

    public int countGoodRectangles(int[][] rectangles) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < rectangles.length; i++) {
            int k = Math.min(rectangles[i][0], rectangles[i][1]);
            count.put(k, count.getOrDefault(k, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> list = count.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());
        return list.get(list.size() - 1).getValue();
    }

    public int tupleSameProduct(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int a;
        int b;
        int c;
        int d;
        for (int i = 0; i < nums.length - 3; i++) {
            a = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                c = nums[j];
                if (c > a * nums[nums.length - 1]) {
                    break;

                }
                for (int k = j + 1; k < nums.length - 1; k++) {
                    d = nums[k];
                    if (c*d > a * nums[nums.length - 1]) {
                        break;
                    }
                    if ((c * d) %  a == 0) {
                        b = c * d / a;
                        if (b == a || b == c || b == d) {
                            continue;
                        }

                        int index = Arrays.binarySearch(nums, k, nums.length, b);
                        if (index >= 0) {
                            count += 8;

                        }
                    }
                }
            }
        }
        return count;
    }

    public int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < rows; i++) {
            int[] rowHeights = matrix[i];
            Arrays.sort(rowHeights);
            for (int j = rowHeights.length - 1; j >= 0; j--) {
                count = Math.max(count, (cols - j) * rowHeights[j]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Weekend224 weekend224 = new Weekend224();
        weekend224.test();
        weekend224.test2();
        weekend224.test3();
        weekend224.test4();
    }

    private void test4() {
    }

    private void test3() {
        Assert.assertEquals(4, this.largestSubmatrix(new int[][]{
                {0,0,1},{1,1,1},{1,0,1}
        }));
    }

    private void test2() {

        Assert.assertEquals(16, this.tupleSameProduct(new int[]{1,2,4,5,10}));
        Assert.assertEquals(8, this.tupleSameProduct(new int[]{2,3,4,6}));
        Assert.assertEquals(40, this.tupleSameProduct(new int[]{2,3,4,6,8,12}));
        Assert.assertEquals(0, this.tupleSameProduct(new int[]{2,3,5,7}));
    }

    private void test() {
        Assert.assertEquals(3, this.countGoodRectangles(new int[][]{
                {5,8},{3,9},{5,12},{16,5}
        }));
        Assert.assertEquals(3, this.countGoodRectangles(new int[][]{
                {2,3},{3,7},{4,3},{3,7}
        }));
    }
}
