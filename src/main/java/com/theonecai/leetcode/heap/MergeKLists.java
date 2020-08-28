package com.theonecai.leetcode.heap;

/**
 * leetcode 23: 最小堆
 *
 * @Author: theonecai
 * @Date: Create in 2020/6/23 19:53
 * @Description:
 */
public class MergeKLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode[] nodes = new ListNode[lists.length];
        int len = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            nodes[len++] = lists[i];
        }
        if (len == 0) {
            return null;
        }

        ArrayHeap minHeap = new ArrayHeap(len);
        minHeap.heapify(nodes, nodes.length);

        ListNode result = null;
        ListNode resultTail = null;
        ListNode next = null;
        while (minHeap.size() > 0) {
            ListNode top = minHeap.top();
            next = top.next;
            if (result == null) {
                result = top;
                resultTail = result;
            } else {
                resultTail.next = top;
                resultTail = top;
            }

            // 所有链表都读完了
            if (next == null) {
                minHeap.poll();
                continue;
            }

            minHeap.updateTop(next);
        }

        return result;
    }

    static class ArrayHeap {
        /**
         * 基于数组
         */
        private ListNode[] heap;

        /**
         * 当前元素个数
         */
        private int size;

        public ArrayHeap(int capacity) {
            heap = new ListNode[capacity + 1];
            this.size = 0;
        }

        public int size() {
            return size;
        }

        /**
         * 堆化
         * @param arr
         */
        public void heapify(ListNode[] arr, int len) {
            if (arr == null) {
                return;
            }
            int i = len / 2;
            for (int j = 1; j <= len; j++) {
                if (arr[j-1] != null) {
                    heap[j] = arr[j - 1];
                    size++;
                }
            }
            for ( ; i >= 1; i--) {
                fixDown(i);
            }
        }

        public ListNode top() {
            if (size == 0) {
                return null;
            }
            return heap[1];
        }

        public void updateTop(ListNode newData) {
            if (size == 0) {
                push(newData);
                return;
            }

            heap[1] = newData;

            fixDown(1);
        }

        public void push(ListNode data) {
            heap[++size] = data;
            fixUp(size);
        }

        public ListNode poll() {
            if (size <= 0) {
                return null;
            }
            ListNode data = heap[1];
            heap[1] = heap[size];
            heap[size--] = null;
            fixDown(1);
            return data;
        }

        private int fixUp(int index) {
            int upIndex = index/2;
            int current = index;
            while (upIndex > 0) {
                if ((compare(heap[current], heap[upIndex]) < 0)) {
                    swap(current, upIndex);
                } else {
                    break;
                }
                current = upIndex;
                upIndex = current / 2;
            }

            return current;
        }

        private int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }

        private void swap(int from, int to) {
            ListNode tmp = heap[from];
            heap[from] = heap[to];
            heap[to] = tmp;
        }

        private int fixDown(int index) {
            int downIndex = index * 2;

            int current = index;
            while (downIndex <= size) {
                int leftIdx = downIndex;
                int rightIdx = downIndex + 1;
                if (rightIdx <=size && compare(heap[leftIdx], heap[rightIdx]) > 0) {
                    downIndex = rightIdx;
                }
                if (compare(heap[current], heap[downIndex]) > 0) {
                    swap(current, downIndex);
                } else {
                    break;
                }
                current = downIndex;
                downIndex = current * 2;
            }
            return current;
        }
    }

    static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        MergeKLists mergeKLists = new MergeKLists();

        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);

        ListNode list = mergeKLists.mergeKLists(lists);
        while (list != null) {
            System.out.print(list.val + " ");
            list = list.next;
        }
        System.out.println();
    }
}
