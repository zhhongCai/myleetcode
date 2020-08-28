package com.theonecai.leetcode.heap;

/**
 * leetcode 295
 * @Author: theonecai
 * @Date: Create in 2020/6/26 12:13
 * @Description:
 */
public class MedianFinder {
    private LinkedHeap<Integer> maxHeap;
    private LinkedHeap<Integer> minHeap;
    private int count;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.maxHeap = new LinkedHeap<>(true);
        this.minHeap = new LinkedHeap<>(false);
        this.count = 0;
    }

    public void addNum(int num) {
        if (count == 0) {
            maxHeap.push(num);
        } else {
            int tmp = num;
            if (maxHeap.getSize() > minHeap.getSize()) {
                if (num < maxHeap.top()) {
                    tmp = maxHeap.top();
                    maxHeap.updateTop(num);
                }
                minHeap.push(tmp);
            } else {
                if (num > minHeap.top()) {
                    tmp = minHeap.top();
                    minHeap.updateTop(num);
                }
                maxHeap.push(tmp);
            }
        }

        this.count++;
    }

    public double findMedian() {
        if (count % 2 == 0) {
            return (maxHeap.top() + minHeap.top()) / 2.0;
        } else {
            return maxHeap.top();
        }
    }

    static class LinkedHeap<T extends Comparable<? super T>> {

        /**
         * 基于链表
         */
        private Node<T> head;

        /**
         * 尾节点
         */
        private Node<T> tail;
        /**
         * 当前元素个数
         */
        private int size;

        /**
         * 是否最大堆
         */
        boolean maxHeap;

        /**
         * 根据index查找时的路径
         */
        private int[] parentIndexPath;

        public LinkedHeap() {
            this(true);
        }

        public LinkedHeap(boolean maxHeap) {
            this.maxHeap = maxHeap;
            this.parentIndexPath = new int[32];
        }

        public int getSize() {
            return size;
        }

        /**
         * 堆化
         *
         * @param arr
         */
        public void heapify(T[] arr) {
            if (arr == null) {
                return;
            }
            int len = arr.length;
            for (int i = 0; i < len; i++) {
                push(arr[i]);
            }
        }

        public void updateTop(T newData) {
            if (size == 0) {
                return;
            }

            head.val = newData;

            fixDown(head);
        }

        public void push(T data) {
            Node<T> node = new Node<>(data);
            if (head == null) {
                head = node;
                tail = node;
                ++size;
                return;
            }

            Node<T> parent = findParentForPush();
            node.parent = parent;
            if (parent.left == null) {
                parent.left = node;
            } else if (parent.right == null) {
                parent.right = node;
            } else {
                // 不会走到这里
            }

            ++size;
            tail = node;

            fixUp(node);
        }

        private Node<T> findParentForPush() {
            int parentIndex = (size + 1) / 2;
            return findByIndex(parentIndex);
        }

        /**
         * 若当前节点index为currentIndex,其左子节点index为2*currentIndex,其右子节点index为2*currentIndex + 1
         *
         * @param index
         * @return
         */
        private Node<T> findByIndex(int index) {
            Node<T> node = head;
            if (index == 1) {
                return node;
            }

            int i;
            int len = parentIndexPath.length;
            int parentIndex = index >> 1;
            parentIndexPath[0] = index;
            for (i = 1; i < len; i++) {
                parentIndexPath[i] = parentIndex;
                if (parentIndexPath[i] == 1) {
                    break;
                }
                parentIndex >>= 1;
            }
            Node<T> current = node;
            int currentIndex = 1;
            for (int j = i; j >= 0; j--) {
                if (currentIndex == index) {
                    return current;
                }
                if (current.left == null) {
                    return null;
                }
                currentIndex = currentIndex << 1;
                if (currentIndex == parentIndexPath[j - 1]) {
                    current = current.left;
                    continue;
                }
                if ((currentIndex + 1) == parentIndexPath[j - 1]) {
                    current = current.right;
                    currentIndex = currentIndex + 1;
                }
            }

            return null;
        }

        public T top() {
            if (head != null) {
                return head.val;
            }
            return null;
        }

        /**
         * 取出头的值返回，设置头的新值为尾节点的值，删除尾节点，设置tail, 然后fixDown(head)
         *
         * @return
         */
        public T poll() {
            if (size == 0) {
                return null;
            }
            T val = head.val;
            if (size == 1) {
                --size;
                head = null;
                tail = null;
                return val;
            }

            head.val = tail.val;
            Node<T> tailNode = tail;
            Node<T> tailParent = tail.parent;
            tailNode.parent = null;
            tailNode.left = null;
            tailNode.right = null;
            --size;

            if (tailParent != null) {
                if (tailParent.right == tailNode) {
                    tailParent.right = null;
                    tail = tailParent.left;
                }
                if (tailParent.left == tailNode) {
                    tailParent.left = null;
                    tail = findByIndex(size);
                }
            }
            tailNode = null;

            fixDown(head);

            return val;
        }

        public void fixDown(Node<T> node) {
            if (node.left == null) {
                return;
            }
            Node<T> left, right, down;
            Node<T> current = node;
            while (current != null) {
                left = current.left;
                if (left == null) {
                    break;
                }
                down = left;
                right = current.right;
                if (maxHeap) {
                    if (right != null && compare(right.val, left.val) > 0) {
                        down = right;
                    }
                    if (compare(current.val, down.val) < 0) {
                        swap(current, down);
                    } else {
                        break;
                    }
                } else {
                    if (right != null && compare(right.val, left.val) < 0) {
                        down = right;
                    }
                    if (compare(current.val, down.val) > 0) {
                        swap(current, down);
                    } else {
                        break;
                    }
                }
                current = down;
            }
        }

        public void fixUp(Node<T> node) {
            Node<T> parent = node.parent;
            Node<T> current = node;
            while (parent != null) {
                if (maxHeap) {
                    if (compare(current.val, parent.val) > 0) {
                        swap(current, parent);
                    } else {
                        break;
                    }
                } else {
                    if (compare(current.val, parent.val) < 0) {
                        swap(current, parent);
                    } else {
                        break;
                    }
                }
                current = parent;
                parent = parent.parent;
            }
        }

        private int compare(Object left, Object right) {
            return ((Comparable) left).compareTo(right);
        }

        /**
         * 只交换值
         *
         * @param node
         * @param node2
         */
        private void swap(Node<T> node, Node<T> node2) {
            T tmp = node.val;
            node.val = node2.val;
            node2.val = tmp;
        }
    }

    static class Node<T> {
        private T val;
        private Node<T> left, right, parent;

        public Node(T val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MedianFinder f = new MedianFinder();
        f.addNum(1);
        System.out.println(f.findMedian());
        f.addNum(2);
        System.out.println(f.findMedian());
        f.addNum(3);
        System.out.println(f.findMedian());
        f.addNum(4);
        System.out.println(f.findMedian());
        f.addNum(5);
        System.out.println(f.findMedian());
        f.addNum(11);
        System.out.println(f.findMedian());
        f.addNum(8);
        System.out.println(f.findMedian());
    }
}
