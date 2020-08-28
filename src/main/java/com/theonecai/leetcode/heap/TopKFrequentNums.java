package com.theonecai.leetcode.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 347
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/6 21:02
 * @Description:
 */
public class TopKFrequentNums {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return nums;
        }

        Map<Integer, Integer> numCount = new HashMap<>(nums.length);
        for (int num : nums) {
            numCount.merge(num, 1, Integer::sum);
        }

        ArrayHeap<Node> minHeap = new ArrayHeap<>(k, false);
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            if (i < k) {
                minHeap.push(new Node(entry.getKey(), entry.getValue()));
            } else {
                if (entry.getValue() > minHeap.top().count) {
                    minHeap.updateTop(new Node(entry.getKey(), entry.getValue()));
                }
            }
            i++;
        }

        int[] knum = new int[k];
        i = 0;
        for (Object n: minHeap.heap) {
            knum[i++] = ((Node) n).num;
        }
        return knum;
    }

    static class ArrayHeap<T extends Comparable<? super T>> {
        /**
         * 基于数组
         */
        private Object[] heap;

        /**
         * 当前元素个数
         */
        private int size;

        /**
         * 是否最大堆
         */
        private boolean maxHeap;

        public boolean isMaxHeap() {
            return maxHeap;
        }

        public ArrayHeap(int capacity) {
            this(capacity, true);
        }

        public ArrayHeap(int capacity, boolean maxHeap) {
            heap = new Object[capacity];
            this.size = 0;
            this.maxHeap = maxHeap;
        }

        public int size() {
            return size;
        }

        /**
         * 堆化
         *
         * @param arr
         */
        public void heapify(T[] arr, int len) {
            if (arr == null) {
                return;
            }
            int i = len / 2;
            for (int j = 0; j < len; j++) {
                heap[j] = arr[j];
                size++;
            }
            for (; i >= 0; i--) {
                fixDown(i);
            }
        }

        public T top() {
            if (size == 0) {
                return null;
            }
            return (T) heap[0];
        }

        public void updateTop(T newData) {
            if (size == 0) {
                push(newData);
                return;
            }

            heap[0] = newData;

            fixDown(0);
        }

        public void push(T data) {
            heap[size] = data;
            fixUp(size);
            size++;
        }

        public T poll() {
            if (size <= 0) {
                return null;
            }
            Object data = heap[0];
            heap[0] = heap[size - 1];
            heap[--size] = null;
            fixDown(0);
            return (T) data;
        }

        private int fixUp(int index) {
            int upIndex = (index - 1) / 2;
            int current = index;
            while (upIndex >= 0) {
                if ((maxHeap && compare(heap[current], heap[upIndex]) > 0) ||
                        (!maxHeap && compare(heap[current], heap[upIndex]) < 0)) {
                    swap(current, upIndex);
                } else {
                    break;
                }
                current = upIndex;
                upIndex = (current - 1) / 2;
            }

            return current;
        }

        private int compare(Object left, Object right) {
            return ((Comparable) left).compareTo(right);
        }

        private void swap(int from, int to) {
            Object tmp = heap[from];
            heap[from] = heap[to];
            heap[to] = tmp;
        }

        private int fixDown(int index) {
            int downIndex = index * 2 + 1;

            int current = index;
            while (downIndex < size) {
                int leftIdx = downIndex;
                int rightIdx = downIndex + 1;
                if (rightIdx < size && ((maxHeap && compare(heap[leftIdx], heap[rightIdx]) < 0) ||
                        (!maxHeap && compare(heap[leftIdx], heap[rightIdx]) > 0))) {
                    downIndex = rightIdx;
                }
                if ((maxHeap && compare(heap[current], heap[downIndex]) < 0) ||
                        (!maxHeap && compare(heap[current], heap[downIndex]) > 0)) {
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

    static class Node implements Comparable<Node> {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    public static void main(String[] args) {
        TopKFrequentNums topK = new TopKFrequentNums();
        int[] nums = {1, 2, 3, 1, 1, 2, 3, 3, 4, 5, 1};
        int[] list = topK.topKFrequent(nums, 3);
    }
}
