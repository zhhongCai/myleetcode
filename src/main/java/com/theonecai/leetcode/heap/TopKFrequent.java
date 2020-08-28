package com.theonecai.leetcode.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * leetcode 692
 * @Author: theonecai
 * @Date: Create in 2020/6/26 12:51
 * @Description:
 */
public class TopKFrequent {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0 || k == 0) {
            return Collections.emptyList();
        }

        Map<String, Integer> wordCount = new HashMap<>(words.length);
        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }

        ArrayHeap<Node> minHeap = new ArrayHeap<>(k, false);
        int len = wordCount.size();
        Node node = null;
        Iterator<Map.Entry<String, Integer>> it = wordCount.entrySet().iterator();
        for (int i = 0; i < len && it.hasNext(); i++) {
            Map.Entry<String, Integer> wd = it.next();
            node = new Node(wd.getKey(), wd.getValue());
            if (i < k) {
                minHeap.push(node);
            } else {
                if (node.compareTo(minHeap.top()) > 0) {
                    minHeap.updateTop(node);
                }
            }
        }
        String[] result = new String[k];
        int i = 0;
        while (minHeap.size > 0) {
            result[i++] = minHeap.poll().wd;
        }
        List<String> list = new ArrayList<>(k);
        for (i = k - 1; i>= 0; i--) {
            list.add(result[i]);
        }
        return list;
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
        String wd;
        int count;

        public Node(String wd, int count) {
            this.wd = wd;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            int t = this.count - o.count;
            if (t != 0) {
                return t;
            }
            return o.wd.compareTo(this.wd);
        }
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent = new TopKFrequent();
        String[] wds = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> result = topKFrequent.topKFrequent(wds, 2);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();


        String[] wds2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        result = topKFrequent.topKFrequent(wds2, 4);
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
    }
}