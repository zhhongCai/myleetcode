package com.theonecai.leetcode.stack;

import com.theonecai.algorithms.ArrayUtil;

import java.util.Stack;

/**
 * leetcode 42
 * @Author: theonecai
 * @Date: Create in 2020/7/1 19:46
 * @Description:
 */
public class Trap {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int start = 0;
        int end = -1;
        int i;
        int sum= 0;
        for (i = 0; i < height.length - 1; i++) {
            end = -1;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] >= height[start]) {
                    end = j;
                    break;
                }
            }
            if (end == -1) {
                for (int j = i + 1; j < height.length - 1; j++) {
                    //下降后又上升了
                    if (height[j] <= height[j + 1]) {
                        int k = j + 1;
                        while (k < height.length - 1 && height[k] <= height[k + 1]) {
                            k++;
                        }
                        end = k;

                        k += 1;

                        //局部最高点后是否有更高点
                        int max = -1;
                        int maxIdx = end;
                        while (k < height.length) {
                            if (height[k] > max) {
                                max = height[k];
                                maxIdx = k;
                            }
                            k++;
                        }
                        if (height[end] < height[maxIdx]) {
                            end = maxIdx;
                        }
                        break;
                    }
                }
            }
            if (start + 1 < end) {
                int secondMax = Math.min(height[start], height[end]);
                if (secondMax < 1) {
                    start = end;
                    i = end - 1;
                    continue;
                }
                for (int j = start + 1; j < end; j++) {
                    if (secondMax - height[j] > 0) {
                        sum += secondMax - height[j];
                    }
                }
                start = end;
                i = end - 1;
            } else {
                start++;
            }
        }

        return sum;
    }

    public int trapStack(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int sum = 0;
        Stack<Integer> descStack = new Stack<>();
        int currentIndex = 1;
        int min;
        int top;
        descStack.push(0);
        while (currentIndex < height.length) {
            while (descStack.size() > 0 && height[descStack.peek()] < height[currentIndex]) {
                top = descStack.pop();
                if (!descStack.isEmpty()) {
                    min = Math.min(height[currentIndex], height[descStack.peek()]);
                    sum += (min - height[top]) * (currentIndex - top);
                    if (top > descStack.peek() + 1) {
                        sum += (min - height[top]) * (top - descStack.peek() - 1);
                    }
                }
            }
            descStack.push(currentIndex++);
        }

        return sum;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        ArrayUtil.print(a);
        System.out.println(trap.trap(a));
        System.out.println(trap.trapStack(a));

        int[] b = {0,2,0,2,1,0,1,3,2,1,2,4};
        ArrayUtil.print(b);
        System.out.println(trap.trap(b));
        System.out.println(trap.trapStack(b));

        int[] c = {0,1,2,3,4,5,6,7,8,9,10,10};
        ArrayUtil.print(c);
        System.out.println(trap.trap(c));
        System.out.println(trap.trapStack(c));

        int[] d = {10,9,8,7,6,5,4,3,2,1,0};
        ArrayUtil.print(d);
        System.out.println(trap.trap(d));
        System.out.println(trap.trapStack(d));

        int[] e = {10,9,8,7,6,5,4,3,2,1,10};
        ArrayUtil.print(e);
        System.out.println(trap.trap(e));
        System.out.println(trap.trapStack(e));

        int[] f = {1,2,3,4,5,15,5,4,3,2,0};
        ArrayUtil.print(f);
        System.out.println(trap.trap(f));
        System.out.println(trap.trapStack(f));

        int[] g = {4,2,3};
        ArrayUtil.print(g);
        System.out.println(trap.trap(g));
        System.out.println(trap.trapStack(g));

        int[] h = {3,2,4};
        ArrayUtil.print(h);
        System.out.println(trap.trap(h));
        System.out.println(trap.trapStack(h));

        int[] h2 = {3,2,4,5,4,0,1,2};
        ArrayUtil.print(h2);
        System.out.println(trap.trap(h2));
        System.out.println(trap.trapStack(h2));

        int[] h3 = {9,6,8,8,5,6,3};
        ArrayUtil.print(h3);
        System.out.println(trap.trap(h3));
        System.out.println(trap.trapStack(h3));

        int[] h4 = {9,6,7,8,8,5,6,3};
        ArrayUtil.print(h4);
        System.out.println(trap.trap(h4));
        System.out.println(trap.trapStack(h4));

        int[] h5 = {5,2,1,2,1,5};
        ArrayUtil.print(h5);
        System.out.println(trap.trap(h5));
        System.out.println(trap.trapStack(h5));
        int[] h6 = {5,2,6,2,1,5};
        ArrayUtil.print(h6);
        System.out.println(trap.trap(h6));
        System.out.println(trap.trapStack(h6));

        int[] h7 = {2,8,5,5,6,1,7,4,5};
        ArrayUtil.print(h7);
        System.out.println(trap.trap(h7));
        System.out.println(trap.trapStack(h7));

        int[] h8 = {2,8,5,5,6,1,7,4,5,7,0};
        ArrayUtil.print(h8);
        System.out.println(trap.trap(h8));
        System.out.println(trap.trapStack(h8));

    }
}
