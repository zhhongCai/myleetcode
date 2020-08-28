package com.theonecai.algorithms;

import java.io.IOException;
import java.util.Random;

/**
 * 快排
 * @Author: caizhh
 * @Date: Create in 19-4-9 上午 10:01
 * @Description:
 */
public class QuickSort<T extends Comparable<? super T>> {

    public void quickSort(T [] a) {

        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(T[] a, int start, int end) {
        if (start >= end) {
            return;
        }

//        int pos = partition(a, start, end);
        int pos = partition2(a, start, end);

        quickSort(a, start, pos - 1);
        //+1是因为pos位置已经第pos小的数字了(有序了)
        quickSort(a, pos + 1, end);
    }

    private int partition(T[] a, int start, int end) {
        //取首元素为分区元素
        T pivot = a[start];
        // 从左往右当前比pivot大的下标,a[start~low]为比pivot小的元素
        int low = start;
        // 从右往左当前比pivot小的下标,a[hight+1~end]为比pivot大的元素
        int high = end;

        // 注意先从右往左找，后从左往右找：这样当low == high时，保证a[low]小于pivot，此处即为pivot排序后位置
        while (low < high) {
            while (high > low && a[high].compareTo(pivot) >= 0) {
                high--;
            }
            while (low < high && a[low].compareTo(pivot) <= 0) {
                low++;
            }
            if (low < high) {
                swap(a, low, high);
            }
        }
        if (low != start) {
            swap(a, low, start);
        }
//        System.out.print("start=" + start + ", end=" + end + ", low=" + low + ",hight=" + high + ", pivot=" + pivot + ": ");
//        print(a);
        return low;
    }

    private int partition2(T[] a, int start, int end) {
        //取首元素为分区元素
        int pivotIndex = start;
        T pivot = a[pivotIndex];
        // 从左往右当前比pivot大(等于，注意相等时也需要交换)的下标,a[start~low]为比pivot小的元素
        int low = start;
        // 从右往左当前比pivot小的下标,a[hight~end]为比pivot大的元素
        int high = end;

        //注意先从左往右，后从右往左
        while (low < high) {
            while (low < high && a[low].compareTo(pivot) < 0) {
                low++;
            }
            while (high > low && a[high].compareTo(pivot) >= 0) {
                high--;
            }
            if (low < high) {
                if (low == pivotIndex) {
                    pivotIndex = high;
                }
                swap(a, low, high);
            }
        }
        if (low != pivotIndex) {
            swap(a, low, pivotIndex);
        }
//        System.out.print("start=" + start + ", end=" + end + ", low=" + low + ",hight=" + high + ", pivot=" + pivot + ": ");
//        print(a);
        return low;
    }

    private void swap(T[] a, int i, int pos) {
        if (i == pos) {
            return;
        }
        T t = a[pos];
        a[pos] = a[i];
        a[i] = t;
    }

    public static void main(String[] args) throws IOException {
        int len = 25;
        Integer[] a = new Integer[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            a[i] = random.nextInt(len*3);
        }
        System.out.println("origin:");
        ArrayUtil.print(a);

        QuickSort<Integer> sort = new QuickSort<>();
        sort.quickSort(a);

        System.out.println("result:");
        ArrayUtil.print(a);

        Integer b[] = ArrayUtil.randArray(len);
        ArrayUtil.print(b);
        sort.quickSort(b);
        ArrayUtil.print(b);
    }
}
