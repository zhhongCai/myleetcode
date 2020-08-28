package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;

/**
 * leetcode 215
 * @Author: theonecai
 * @Date: Create in 2020/7/19 17:33
 * @Description:
 */
public class FindKthLargest {

    public int findKthLargest(int[] arr, int k) {
        int len = arr.length;
        int p = partition(arr, 0, len - 1);

        // 第k大元素对应从小到大元素索引
        int th = len - k + 1;
        while(p + 1 != th) {
            if (p + 1 > th) {
                p = partition(arr, 0, p);
            } else if (p + 1 < th){
                p = partition(arr, p + 1, arr.length - 1);
            }
        }
        return arr[p];
    }


    private static int partition(int[] array, int start, int end) {
        //取首元素为分区元素
        int pivotIndex = start + (end - start > 0 ? new Random().nextInt(end - start) : 0);
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

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


    public int findKthLargest2(int[] arr, int k) {
        ArrayHeap minHeap = new ArrayHeap(k);
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                minHeap.push(arr[i]);
                continue;
            }
            if (arr[i] > minHeap.top()) {
                minHeap.updateTop(arr[i]);
            }
        }

        return minHeap.top();
    }

    static class ArrayHeap {
        /**
         * 基于数组
         */
        private int[] heap;

        /**
         * 当前元素个数
         */
        private int size;

        public ArrayHeap(int capacity) {
            heap = new int[capacity];
            this.size = 0;
        }

        public int size() {
            return size;
        }

        public int top() {
            if (size == 0) {
                return -1;
            }
            return heap[0];
        }

        public void updateTop(int newData) {
            if (size == 0) {
                push(newData);
                size++;
                return;
            }

            heap[0] = newData;

            fixDown(0);
        }

        public void push(int data) {
            heap[size] = data;
            fixUp(size);
            size++;
        }


        private int fixUp(int index) {
            int upIndex = (index - 1)/2;
            int current = index;
            while (upIndex >= 0) {
                if ((compare(heap[current], heap[upIndex]) < 0)) {
                    swap(current, upIndex);
                } else {
                    break;
                }
                current = upIndex;
                upIndex = (current - 1) / 2;
            }

            return current;
        }

        private int compare(int left, int right) {
            return left - right;
        }

        private void swap(int from, int to) {
            int tmp = heap[from];
            heap[from] = heap[to];
            heap[to] = tmp;
        }

        private int fixDown(int index) {
            int downIndex = index * 2 + 1;

            int current = index;
            while (downIndex < size) {
                int leftIdx = downIndex;
                int rightIdx = downIndex + 1;
                if (rightIdx < size && compare(heap[leftIdx], heap[rightIdx]) > 0) {
                    downIndex = rightIdx;
                }
                if (compare(heap[current], heap[downIndex]) > 0) {
                    swap(current, downIndex);
                } else {
                    break;
                }
                current = downIndex;
                downIndex = current * 2 + 1;
            }
            return current;
        }
    }

    public static void main(String[] args) {
        FindKthLargest largestK = new FindKthLargest();
        int kth;
        int[] arr;
        int n = 0;
        for (int i = 0; i < 1000; i++) {
            n = 10 + i;
            arr = ArrayUtil.randIntArray(n);
            kth = largestK.findKthLargest(arr, n - i);
            Arrays.sort(arr);
            Assert.assertEquals(kth, arr[i]);
        }
    }
}
