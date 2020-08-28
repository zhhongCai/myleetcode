package com.theonecai.algorithms;

import static com.theonecai.algorithms.ArrayUtil.print;

/**
 * 归并排序 参考Arrays中实现
 */
public class MergeSort {

    public static void mergeSort(Integer[] data) {

        Integer[] src = new Integer[data.length];
        System.arraycopy(data, 0, src, 0, data.length);

        mergeSort(src, data, 0 , data.length);
    }

    private static void mergeSort(Integer[] src, Integer[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int middle = (end + start) >>> 1;
        //只有一个元素时
        if (middle == start) {
            return;
        }

        mergeSort(data, src, start, middle);
        mergeSort(data, src, middle, end);

        //前后两部分已有序
        if (src[middle - 1] < src[middle]) {
            System.arraycopy(src, start, data, start, end - start);
            return;
        }

        merge(src, data, start, middle, end);
    }

    private static void merge(Integer[] src, Integer[] des, int start, int middle, int end) {
        int desStart = start;
        int desEnd = end;

      /*  System.out.println("merge start=" + start + ",middle=" + middle + ",end=" + end);
        System.out.print("src = ");
        print(src);
        System.out.print("des = ");
        print(des);*/

        for (int i = desStart, left = start, right = middle; i < desEnd; i++) {
            if (right >= end || left < middle && src[left] <= src[right]) {
                des[i] = src[left++];
            } else {
                des[i] = src[right++];
            }
        }

      /*  System.out.print("merge des = ");
        print(des);*/
    }


    public static void main(String[] args) {
        int len = 11;
        Integer[] a = ArrayUtil.randArray(len);
        System.out.println("origin:");
        ArrayUtil.print(a);

        MergeSort.mergeSort(a);

        System.out.println("result:");
        ArrayUtil.print(a);
    }


}
