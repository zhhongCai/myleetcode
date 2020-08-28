package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1172
 * @Author: theonecai
 * @Date: Create in 2020/7/5 09:12
 * @Description:
 */
public class DinnerPlates {
    private LinkedHeap<Stack> minHeap;
    private LinkedHeap<Stack> maxHeap;
    private Map<Integer, Stack> stackMap;

    private int stackCapacity;

    public DinnerPlates(int capacity) {
        this.stackCapacity = capacity;
        this.stackMap = new HashMap<>();
        minHeap = new LinkedHeap<>(false, (o1, o2) -> {
            if (o1.getIndex() > o2.getIndex()) {
                if (o2.getSize() < stackCapacity) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (o1.getSize() < stackCapacity) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        maxHeap = new LinkedHeap<>(true, (o1, o2) -> {
            if (o1.getIndex() > o2.getIndex()) {
                if (o1.getSize() > 0) {
                    return 1;
                } else {
                    if (o2.getSize() > 0) {
                        return -1;
                    }
                    return 1;
                }
            } else {
                if (o2.getSize() > 0) {
                    return -1;
                } else {
                    if (o1.getSize() > 0) {
                        return 1;
                    }
                    return -1;
                }
            }
        });

        newStack(null);
    }

    private void newStack(Integer val) {
        Stack stack = new Stack(stackCapacity, minHeap.size);
        if (val != null) {
            stack.push(val);
        }
        minHeap.push(stack);
        maxHeap.push(stack);
        stackMap.put(stack.getIndex(), stack);
    }

    public void push(int val) {
        LinkedHeap.Node<Stack> node = minHeap.top();
        if (node.val.push(val)) {
            if (node.val.size >= stackCapacity) {
                minHeap.fixDown(node);
            }

            Stack stack = maxHeap.poll();
            maxHeap.push(stack);
            return;
        }

        newStack(val);
    }

    public int pop() {
        LinkedHeap.Node<Stack> node = maxHeap.top();
        int val = node.val.pop();
        if (node.val.size == 0) {
            maxHeap.fixDown(node);
        }

        Stack stack = minHeap.poll();
        minHeap.push(stack);
        return val;
    }

    public int popAtStack(int index) {
        if (stackMap.containsKey(index)) {
            Stack stack = stackMap.get(index);
            int val = stack.pop();
            if (val != -1) {
                // TODO
                Stack s = minHeap.poll();
                minHeap.push(s);
                s = maxHeap.poll();
                maxHeap.push(s);
            }
            return val;
        }
        return -1;
    }

    private LinkedHeap.Node<Stack> findByStackIndex(LinkedHeap.Node<Stack> head) {
        return null;
    }

    static class Stack {
        private int[] elements;
        private int capacity;
        private int size;
        private int top;
        private int index;

        private int getSize() {
            return this.size;
        }

        private int getIndex() {
            return this.index;
        }

        public Stack(int capacity, int index) {
            this.capacity = capacity;
            elements = new int[capacity];
            this.index = index;
            this.size = 0;
            this.top = -1;
        }

        public boolean push(int val) {
            if (size < capacity) {
                elements[++top]  = val;
                size++;
                return true;
            }
            return false;
        }

        public int pop() {
            if (top < 0) {
                return -1;
            }
            size--;
            return elements[top--];
        }
    }

    static class LinkedHeap<T> {

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

        private final Comparator<? super T> comparator;

        public LinkedHeap(boolean maxHeap, Comparator<? super T> comparator) {
            this.maxHeap = maxHeap;
            this.parentIndexPath = new int[32];
            this.comparator = comparator;
            this.size = 0;
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

        public Node<T> top() {
            return head;
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

        public Node<T> fixDown(Node<T> node) {
            if (node.left == null) {
                return node;
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
            return current;
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
            return comparator.compare((T)left, (T)right);
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

        static class Node<T> {
            private T val;
            private Node<T> left, right, parent;

            public Node(T val) {
                this.val = val;
            }
        }

    }

    public static void main(String[] args) {
        DinnerPlates dinnerPlates = new DinnerPlates(2);
        Assert.assertEquals(-1, dinnerPlates.pop());
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        dinnerPlates.push(4);
        dinnerPlates.push(5);
        Assert.assertEquals(2, dinnerPlates.popAtStack(0));
        dinnerPlates.push(20);
        dinnerPlates.push(21);
        Assert.assertEquals(20, dinnerPlates.popAtStack(0));
        Assert.assertEquals(21, dinnerPlates.popAtStack(2));
        Assert.assertEquals(5, dinnerPlates.pop());
        Assert.assertEquals(4, dinnerPlates.pop());
        Assert.assertEquals(3, dinnerPlates.pop());
        Assert.assertEquals(1, dinnerPlates.pop());
        Assert.assertEquals(-1, dinnerPlates.pop());
        long a = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++)  {
            dinnerPlates.push(3);
        }
        long b = System.currentTimeMillis();
        System.out.println("cost: " + (b - a));
        for (int i = 0; i < 200000; i++) {
            dinnerPlates.pop();
        }
        System.out.println("cost: " + (System.currentTimeMillis() - b));

        dinnerPlates = new DinnerPlates(1);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        Assert.assertEquals(2, dinnerPlates.popAtStack(1));
        Assert.assertEquals(3, dinnerPlates.pop());
        Assert.assertEquals(1, dinnerPlates.pop());

        dinnerPlates = new DinnerPlates(2);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        dinnerPlates.push(3);
        dinnerPlates.push(4);
        dinnerPlates.push(7);
        Assert.assertEquals(-1, dinnerPlates.popAtStack(8));
        dinnerPlates.push(20);
        dinnerPlates.push(21);
        Assert.assertEquals(2, dinnerPlates.popAtStack(0));
        Assert.assertEquals(20, dinnerPlates.popAtStack(2));
        Assert.assertEquals(21, dinnerPlates.pop());
        Assert.assertEquals(7, dinnerPlates.pop());
        Assert.assertEquals(4, dinnerPlates.pop());
        Assert.assertEquals(3, dinnerPlates.pop());
        Assert.assertEquals(1, dinnerPlates.pop());

        dinnerPlates = new DinnerPlates(1);
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        Assert.assertEquals(2, dinnerPlates.popAtStack(1));
        Assert.assertEquals(1, dinnerPlates.pop());
        dinnerPlates.push(1);
        dinnerPlates.push(2);
        Assert.assertEquals(2, dinnerPlates.pop());
        Assert.assertEquals(1, dinnerPlates.pop());

        String[] cmd = {"push","push","push","push","push","push","push","push","popAtStack","popAtStack","popAtStack",
                "popAtStack","popAtStack","popAtStack","popAtStack","popAtStack","popAtStack","popAtStack","push","push",
                "push","push","push","push","push","push","pop","pop","pop","pop","pop","pop","pop","pop","pop","pop"};
        int[] data = {472,106,497,498,73,115,437,461,3,3,1,3,0,2,2,1,1,3,197,239,129,449,460,240,386,343};
        int[] result = {461,437,498,-1,106,115,73,497,-1,-1,343,386,240,460,449,129,239,197,472,-1};

        DinnerPlates dp = new DinnerPlates(2);
        dp.push(472);
        dp.push(106);
        dp.push(497);
        dp.push(498);
        dp.push(73);
        dp.push(115);
        dp.push(437);
        dp.push(461);

        Assert.assertEquals(461, dp.popAtStack(3));
        Assert.assertEquals(437, dp.popAtStack(3));
        Assert.assertEquals(498, dp.popAtStack(1));
        Assert.assertEquals(-1, dp.popAtStack(3));
        Assert.assertEquals(106, dp.popAtStack(0));
        Assert.assertEquals(115, dp.popAtStack(2));
        Assert.assertEquals(73, dp.popAtStack(2));
        Assert.assertEquals(497, dp.popAtStack(1));
        Assert.assertEquals(-1, dp.popAtStack(1));
        Assert.assertEquals(-1, dp.popAtStack(3));

        dp.push(197);
        dp.push(239);
        dp.push(129);
        dp.push(449);
        dp.push(460);
        dp.push(240);
        dp.push(386);
        dp.push(343);

        Assert.assertEquals(343, dp.pop());
        Assert.assertEquals(386, dp.pop());
        Assert.assertEquals(240, dp.pop());
        Assert.assertEquals(460, dp.pop());
        Assert.assertEquals(449, dp.pop());
        Assert.assertEquals(129, dp.pop());
        Assert.assertEquals(239, dp.pop());
        Assert.assertEquals(197, dp.pop());
        Assert.assertEquals(472, dp.pop());
        Assert.assertEquals(-1, dp.pop());

        dp = new DinnerPlates(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        dp.push(5);
        dp.push(6);
        dp.push(7);

        Assert.assertEquals(6, dp.popAtStack(2));
        Assert.assertEquals(5, dp.popAtStack(2));
        Assert.assertEquals(4, dp.popAtStack(1));
        Assert.assertEquals(3, dp.popAtStack(1));
        Assert.assertEquals(2, dp.popAtStack(0));

        dp.push(8);
        dp.push(9);

        Assert.assertEquals(7, dp.pop());
        Assert.assertEquals(9, dp.pop());
        Assert.assertEquals(8, dp.pop());
        Assert.assertEquals(1, dp.pop());
        Assert.assertEquals(-1, dp.pop());

    }                            ;
}
