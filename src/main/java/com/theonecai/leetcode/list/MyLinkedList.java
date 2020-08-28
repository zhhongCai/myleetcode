package com.theonecai.leetcode.list;

/**
 * leetcode 707
 * @Author: theonecai
 * @Date: Create in 2020/6/15 19:26
 * @Description:
 */
class MyLinkedList {
    private ListNode head, tail;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.size = 0;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        ListNode node = getNode(index);
        return node == null ? -1 : node.value;
    }

    private ListNode getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode node = head.next;
        int i = 0;
        while (node != null && node != tail) {
            if (i == index) {
                return node;
            }
            node = node.next;
            i++;
        }
        return null;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
        ++size;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        node.next = tail;
        node.pre = tail.pre;
        tail.pre.next = node;
        tail.pre = node;
        ++size;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index > size) {
            return;
        }

        ListNode old = getNode(index);
        ListNode node = new ListNode(val);
        node.next = old;
        node.pre = old.pre;
        old.pre.next = node;
        old.pre = node;
        ++size;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode old = getNode(index);
        old.pre.next = old.next;
        old.next.pre = old.pre;
        old.pre = null;
        old.next = null;
        --size;
    }

    static class ListNode {
        ListNode pre, next;
        int value;

        public ListNode() {
        }

        public ListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.print();
        list.addAtHead(2);
        list.print();
        list.addAtTail(3);
        list.print();
        list.addAtIndex(1, 4);
        list.print();
        list.deleteAtIndex(1);
        list.print();
    }

    public void print() {
        ListNode node = head.next;
        while (node != null && node != tail) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println("");
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
