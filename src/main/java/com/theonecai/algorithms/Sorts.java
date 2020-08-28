package com.theonecai.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:+LogCompilation -XX:PrintAssemblyOptions=intel
 * @Author: theonecai
 * @Date: Create in 2020/6/17 13:33
 * @Description:
 */
public class Sorts {

    /**
     * 冒泡排序
     * @param a
     */
    public static void bubbleSort(Integer[] a) {
        int size = a.length;
        int i, j;
        for (i = size - 1; i >= 0; i--) {
            boolean swapFlag = false;
            for (j = i - 1; j >= 0; j--) {
                if (a[i] < a[j]) {
                    swap(a, i, j);
                    swapFlag = true;
                }
            }
            ArrayUtil.print(a);
            if (!swapFlag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 分为两部分：已排序部分,未排序部分
     * 将未排序部分的每个元素，插入到已排序部分的适当位置
     * @param a
     */
    public static void insertSort(Integer[] a) {
        int size = a.length;
        int i, j, currentVal;
        for (i = 1; i < size; i++) {
            currentVal = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (currentVal < a[j]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * 分为两部分：已排序部分,未排序部分
     * 从未排序部分找出最小值，放到已排序部分的尾部
     */
    public static void selectSort(Integer[] a) {
        int size = a.length;
        int i, j, minIndex;
        for (i = 0; i < size; i++) {
            minIndex = i;
            for (j = i + 1; j < size; j++) {
                if (a[minIndex] > a[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(a, i, minIndex);
            }
        }
    }

    private static void swap(Integer[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 桶排序
     * 数据均由发布
     * 求出最大、最小值
     * 分桶：对每个桶再进行排序
     * @param a
     */
    public static void bucketSort(int[] a) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
        }

        int n = a.length + 1;

        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int e : a) {
            int cn = help(e, n, min);
            buckets.get(cn).add(e);
        }
        for (int i = 0; i < n; i++) {
            List<Integer> list = buckets.get(i);
            Collections.sort(list);

//            ArrayUtil.print(list.toArray(new Integer[0]));
        }
    }

    private static int help(int e, int n, int min) {
        return e % n;
    }

    /**
     * 基数排序
     * @param a
     */
    public static void baseCountSort(int[] a) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
        }

        int[] count = new int[max - min + 1];
        Arrays.fill(count, 0);

        for (int e : a) {
            count[e - min]++;
        }

        int c;
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            c = count[i];
            while (c > 0) {
                a[j++] = i + min;
                c--;
            }
        }
    }

    public static void main(String[] args) {
        int len = 15;
        Integer[] a = ArrayUtil.randArray(len);
        System.out.println("origin:");
//        ArrayUtil.print(a);

        long start = System.currentTimeMillis();
//        Sorts.selectSort(a);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));

        System.out.println("result:");
//        ArrayUtil.print(a);

        Integer b[] = ArrayUtil.randArray(len + 1);
//        ArrayUtil.print(b);
        start = System.currentTimeMillis();
//        Sorts.selectSort(b);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
//        ArrayUtil.print(b);

        int c[] = ArrayUtil.randIntArray(len + 1);
        System.out.println("bucket sort before:");
//        ArrayUtil.print(c);
        start = System.currentTimeMillis();
        Sorts.bucketSort(c);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
        System.out.println("bucket sort result:");
//        ArrayUtil.print(b);

        int d[] = ArrayUtil.randIntArray(len + 1);
        ArrayUtil.print(d);
        start = System.currentTimeMillis();
        Sorts.baseCountSort(d);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
        ArrayUtil.print(d);
    }
}
