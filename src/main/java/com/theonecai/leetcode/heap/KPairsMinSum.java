package com.theonecai.leetcode.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 373
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/10 19:24
 * @Description:
 */
public class KPairsMinSum {

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] + b[1] - a[0] - a[1]);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (maxHeap.size() < k) {
                    maxHeap.add(new int[]{nums1[i], nums2[j]});
                } else {
                    int[] top = maxHeap.peek();
                    if (nums1[i] + nums2[j] < top[0] + top[1]) {
                        maxHeap.poll();
                        maxHeap.add(new int[]{nums1[i], nums2[j]});
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>(k);
        while (maxHeap.size() > 0) {
            int[] top = maxHeap.poll();
            List<Integer> elem = new ArrayList<>(2);
            elem.add(top[0]);
            elem.add(top[1]);

            result.add(elem);
        }

        return result;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int len = Math.min(k, nums1.length * nums2.length);
        ArrayHeap<Node> maxHeap = new ArrayHeap<>(len);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (maxHeap.size() < k) {
                    maxHeap.push(new Node(nums1[i], nums2[j]));
                } else {
                    if (nums1[i] + nums2[j] < maxHeap.top().getSum()) {
                        maxHeap.updateTop(new Node(nums1[i], nums2[j]));
                    }
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>(len);
        while (maxHeap.size() > 0) {
            Node node = maxHeap.poll();
            List<Integer> elem = new ArrayList<>(2);
            elem.add(node.getVal());
            elem.add(node.getVal2());

            result.add(elem);
        }

        return result;
    }

    static class Node implements Comparable<Node> {
        private int val;
        private int val2;
        private int sum;

        public int getVal() {
            return val;
        }

        public int getVal2() {
            return val2;
        }

        public int getSum() {
            return sum;
        }

        public Node(int val, int val2) {
            this.val = val;
            this.val2 = val2;
            this.sum = val + val2;
        }

        @Override
        public int compareTo(Node o) {
            return this.sum - o.sum;
        }
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

    public static void main(String[] args) {
        KPairsMinSum kPairsMinSum = new KPairsMinSum();
        int[] a = {1, 4, 9};
        int[] b = {3, 13, 15};
        List<List<Integer>> result = kPairsMinSum.kSmallestPairs(a, b, 13);
        System.out.println(result);
    }

}
