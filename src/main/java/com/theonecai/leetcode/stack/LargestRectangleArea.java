package com.theonecai.leetcode.stack;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Stack;

/**
 * leetcode 84
 *
 * @Author: thonecai
 * @Date: Create in 2020/7/28 20:02
 * @Description:
 */
public class LargestRectangleArea {

    public int maxSquare2(int[] nums) {
        int max = 0;
        int m = 0;
        int low = 0;
        for (int i = 0; i < nums.length; i++) {
            m = nums[i];
            low = nums[i];
            for (int j = i - 1; j >= 0 && nums[j] > 0; j--) {
                low = Math.min(low, nums[j]);
                int t = (i - j + 1) * low;
                if (m < t) {
                    m = t;
                }
            }
            if (max < m) {
                max = m;
            }
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return 0;
        }
        // 递增栈,存索引
        Stack<Integer> incStack = new Stack<>();
        int popIndex;
        int maxRange;
        int max = heights[0];
        incStack.push(0);

        int i = 1;
        while (!incStack.isEmpty()) {
            while ((i < heights.length && heights[incStack.peek()] > heights[i]) || i == heights.length) {
                popIndex = incStack.pop();
                if (heights[popIndex] > 0) {
                    maxRange = incStack.isEmpty() ? i : (i - incStack.peek() - 1);
                    max = Math.max(max, heights[popIndex] * maxRange);
                }
                if (incStack.isEmpty()) {
                    break;
                }
            }
            if (i < heights.length) {
                incStack.push(i++);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LargestRectangleArea largestRectangleArea = new LargestRectangleArea();
        int[] nums = {0, 0, 0, 0, 0, 0, 0};
        Assert.assertEquals(0, largestRectangleArea.largestRectangleArea(nums));
        Assert.assertEquals(0, largestRectangleArea.maxSquare2(nums));

        int[] nums3 = {1,1,1,1,1,1,1,1,1,1};
        Assert.assertEquals(10, largestRectangleArea.largestRectangleArea(nums3));
        Assert.assertEquals(10, largestRectangleArea.maxSquare2(nums3));

        int[] nums4 = {1,9,1,3,7,2,6,3,7,8};
        Assert.assertEquals(14, largestRectangleArea.largestRectangleArea(nums4));
        Assert.assertEquals(14, largestRectangleArea.maxSquare2(nums4));

        int[] nums5 = {7,2,6,0,1,6,2,6,5,2};
        Assert.assertEquals(10, largestRectangleArea.largestRectangleArea(nums5));
        Assert.assertEquals(10, largestRectangleArea.maxSquare2(nums5));

        int[] nums6 = {7,6,4,3,9,8,4,6,3,4};
        Assert.assertEquals(30, largestRectangleArea.largestRectangleArea(nums6));
        Assert.assertEquals(30, largestRectangleArea.maxSquare2(nums6));

        int[] nums7 = {7,6,4,3,9,8,4,6,3,4};
        Assert.assertEquals(30, largestRectangleArea.largestRectangleArea(nums7));
        Assert.assertEquals(30, largestRectangleArea.maxSquare2(nums7));

        int[] nums8 = {2,9,9,7,4,3,0,9,0,9};
        Assert.assertEquals(21, largestRectangleArea.largestRectangleArea(nums8));
        Assert.assertEquals(21, largestRectangleArea.maxSquare2(nums8));

        int[] nums9 = {9,8,1,2,5,0,4,0,0,3};
        Assert.assertEquals(16, largestRectangleArea.largestRectangleArea(nums9));
        Assert.assertEquals(16, largestRectangleArea.maxSquare2(nums9));

        for (int i = 0; i < 1; i++) {
            int[] nums2 = ArrayUtil.randIntArray(100000);
//            ArrayUtil.print(nums2);
            long a = System.currentTimeMillis();
            int r = largestRectangleArea.largestRectangleArea(nums2);
            System.out.println("cost:" + (System.currentTimeMillis() - a));
            int r2 = largestRectangleArea.maxSquare2(nums2);
//            System.out.println("r=" + r + ",r2=" + r2);
            Assert.assertEquals(r, r2);

        }
    }
}
