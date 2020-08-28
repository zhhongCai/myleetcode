package com.theonecai.algorithms;

import java.util.Arrays;

import static com.theonecai.algorithms.ArrayUtil.print;

/**
 * 查找无序数组中第K大的数
 * @Author: theonecai
 * @Date: Create in 2019-04-09 18:33
 * @Description:
 */
public class FindKth {
    public static int findKth(Integer[] array, int k) {
        int len = array.length;
        int p = partition(array, 0, len - 1);

        // 第k大元素对应从小到大元素索引
        int th = len - k + 1;
        while(p + 1 != th) {
            if (p + 1 > th) {
                p = partition(array, 0, p);
            } else if (p + 1 < th){
                p = partition(array, p + 1, array.length - 1);
            }
        }
        return array[p];
    }

    /**
     * 对数组构造元素个数为k的最小堆
     * @param array
     * @param k
     * @return
     */
    public static int findKthWithMinHeap(Integer[] array, int k) {
        int len = array.length;
        ArrayHeap<Integer> minHeap = new ArrayHeap<>(k, false);
        minHeap.heapify(array, k);
        for (int i = k; i < len; i++) {
            if (array[i] > minHeap.top()) {
                minHeap.updateTop(array[i]);
            }
        }

        return minHeap.top();
    }

    private static int partition(Integer[] array, int start, int end) {
        //取首元素为分区元素
        int pivotIndex = start;
        int pivot = array[pivotIndex];
        // 从左往右当前比pivot大(等于，注意相等时也需要交换)的下标,a[start~low]为比pivot小的元素
        int low = start;
        // 从右往左当前比pivot小的下标,a[hight~end]为比pivot大的元素
        int high = end;

        //注意先从左往右，后从右往左
        while (low < high) {
            while (low < high && array[low] < pivot) {
                low++;
            }
            while (high > low && array[high] >= pivot) {
                high--;
            }
            if (low < high) {
                if (low == pivotIndex) {
                    pivotIndex = high;
                }
                swap(array, low, high);
            }
        }
        if (low != pivotIndex) {
            swap(array, low, pivotIndex);
        }
//        System.out.print("start=" + start + ", end=" + end + ", low=" + low + ",hight=" + high + ", pivot=" + pivot + ": ");
//        print(a);
        return low;
    }

    private static void swap(Integer[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int len = 23;
        Integer[] a = ArrayUtil.randArray(len);
        System.out.println("origin:");
        print(a);
        Integer[] b = Arrays.copyOf(a, len);
        Integer[] c = Arrays.copyOf(a, len);

        int k = 5;
        System.out.print("the " + k + "th is ");
        System.out.println(FindKth.findKth(a, k));
        System.out.print("minheap the " + k + "th is ");
        System.out.println(FindKth.findKthWithMinHeap(a, k));

        QuickSort<Integer> sort = new QuickSort<>();
        sort.quickSort(b);
        System.out.println("quick sort:");
        print(b);

        MergeSort.mergeSort(c);
        System.out.println("merge sort:");
        print(c);
    }

}
