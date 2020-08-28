package com.theonecai.leetcode.heap;

import com.theonecai.algorithms.ArrayUtil;

/**
 * leetcode 239
 * @Author: theonecai
 * @Date: Create in 2020/6/25 22:37
 * @Description:
 */
public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        ArrayHeap maxHeap = new ArrayHeap(k);
        int len = nums.length - k + 1;
        int[] result = new int[len];
        for (int j = 0; j < len; j++) {
            if (j > 0) {
                if (result[j - 1] == nums[j - 1]) {
                    maxHeap.heapify(nums, j, j + k);
                    result[j] = maxHeap.top();
                } else {
                    if (nums[j + k -1] > result[j - 1]) {
                        result[j] = nums[j + k -1];
                    } else {
                        result[j] = result[j - 1];
                    }
                }
                continue;
            }
            maxHeap.heapify(nums, j, j + k);
            result[j] = maxHeap.top();
        }

        return result;
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

        public void heapify(int[] arr, int start, int end) {
            if (arr == null) {
                return;
            }
            this.size = 0;
            int i = (end - start) / 2;
            for (int j = start; j < end; j++) {
                heap[size++] = arr[j];
            }
            for ( ; i >= 0; i--) {
                fixDown(i);
            }
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
                if ((compare(heap[current], heap[upIndex]) > 0)) {
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
                if (rightIdx < size && compare(heap[leftIdx], heap[rightIdx]) < 0) {
                    downIndex = rightIdx;
                }
                if (compare(heap[current], heap[downIndex]) < 0) {
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
        MaxSlidingWindow win = new MaxSlidingWindow();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] result = win.maxSlidingWindow(nums, 3);

        ArrayHeap maxHeap = new ArrayHeap(3);
        maxHeap.heapify(nums, 0, 3);
        ArrayUtil.print(maxHeap.heap);
        maxHeap.heapify(nums, 1, 4);
        ArrayUtil.print(maxHeap.heap);
        maxHeap.heapify(nums, 2, 5);
        ArrayUtil.print(maxHeap.heap);
        maxHeap.heapify(nums, 3, 6);
        ArrayUtil.print(maxHeap.heap);
        maxHeap.heapify(nums, 4, 7);
        ArrayUtil.print(maxHeap.heap);
        ArrayUtil.print(result);

        long a = System.currentTimeMillis();
        int[] t = ArrayUtil.randIntArray(100000);
//        ArrayUtil.print(t);
        result = win.maxSlidingWindow(t, 100);
//        ArrayUtil.print(result);
        System.out.println("cost:" + (System.currentTimeMillis() - a));
    }
}
