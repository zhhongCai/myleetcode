package com.theonecai.algorithms;

/**
 * 分治思想
 * @Author: theoncai
 * @Date: Create in 2020/7/3 19:48
 * @Description:
 */
public class DivideAndConquer {

    /**
     * 求数组arr的的逆序对个数
     * @param arr
     * @return
     */
    public int disorderCount(int[] arr) {
        return mergeSortCounting(arr, 0, arr.length - 1);
    }

    /**
     * 合并排序方法统计
     * @param arr
     * @param start
     * @param end : 包括end
     * @return
     */
    private int mergeSortCounting(int[] arr, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = (end + start) / 2;  // equals start + (end - start) / 2
        int sum = 0;
        sum += mergeSortCounting(arr, start, mid);
        sum += mergeSortCounting(arr, mid + 1, end);

        sum += merge(arr, start, mid, end);
        return sum;
    }

    private int merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int index = 0;
        int[] sortedArr = new int[end - start + 1];
        int count = 0;
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                sortedArr[index++] = arr[i++];
            } else {
                count += mid - i + 1;
                sortedArr[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            sortedArr[index++] = arr[i++];
        }
        while (j <= end) {
            sortedArr[index++] = arr[j++];
        }

        for (i = 0; i < sortedArr.length; i++) {
            arr[start + i] = sortedArr[i];
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.randIntArray(10);
        ArrayUtil.print(arr);
        DivideAndConquer dc = new DivideAndConquer();
        System.out.println("disorder: " + dc.disorderCount(arr));
        ArrayUtil.print(arr);

    }
}
