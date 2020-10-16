package com.theonecai.leetcode.gredy;

import java.util.Arrays;

/**
 * leetcode 1053
 * @Author: theonecai Ø
 * @Date: Create in 2020/10/13 21:22
 * @Description:
 */
public class PrevPermOpt1 {
    /**
     * 从后往前找第一个出现A[i] > A[i + 1]的i,取A[i+1~A.length()-1]中比A[i]小的最大数位置j,交换A[i],A[j]
     * @param A
     * @return
     */
    public int[] prevPermOpt1(int[] A) {
        if (A.length < 2) {
            return A;
        }
        int maxIndex = A.length - 1;
        int leftIndex = -1;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                if (A[i] <= A[maxIndex]) {
                    maxIndex = i + 1;
                    for (int j = i + 1; j < A.length; j++) {
                        if (A[j] > A[maxIndex] && A[j] < A[i]) {
                            maxIndex = j;
                        }
                    }
                }
                leftIndex = i;
                break;
            }
            if (A[i] > A[maxIndex]) {
                maxIndex = i;
            }
        }
        if (leftIndex == -1) {
            leftIndex = 0;
            maxIndex = A[1] < A[0] ? 1 : 0;
            for (int i = 1; i < A.length; i++) {
                if (A[i] > A[maxIndex] && A[i] < A[0]) {
                    maxIndex = i;
                }
            }
        }
        int tmp = A[leftIndex];
        A[leftIndex] = A[maxIndex];
        A[maxIndex] = tmp;
        return A;
    }

    public static void main(String[] args) {
        PrevPermOpt1 prevPermOpt1 = new PrevPermOpt1();
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{646,613,446,528,731,788})));
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{4,5,3,1,1,3})));
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{3,1,1,3})));
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{3,2,1})));
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{1,1,5})));
        System.out.println(Arrays.toString(prevPermOpt1.prevPermOpt1(new int[]{1,9,4,6,7})));
    }
}
