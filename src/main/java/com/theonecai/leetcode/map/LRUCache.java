package com.theonecai.leetcode.map;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 146
 *
 * @Author: theonecai
 * @Date: Create in 2020/6/15 19:06
 * @Description:
 */
public class LRUCache {
    Entry head;
    Entry tail;

    private int capacity;

    private int size;

    private Map<Integer, Entry> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
        cache = new HashMap<>(capacity);
    }

    public int get(int key) {
        Entry tmp = cache.get(key);
        if (tmp != null) {
            moveToHead(tmp);
            return tmp.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Entry entry = cache.get(key);
        if (entry != null) {
            entry.value = value;
            moveToHead(entry);
            return;
        }

        entry = new Entry(key, value);
        cache.put(key, entry);
        size++;
        if (head == null) {
            head = entry;
            tail = entry;
            return;
        }

        entry.next = head;
        head.pre = entry;
        head = entry;

        if (size > capacity) {
            removeTail();
        }
    }

    private void removeTail() {
        cache.remove(tail.key);
        Entry pre = tail.pre;
        if (pre != null) {
            if (pre.next != null) {
                pre.next.pre = null;
            }
            pre.next = null;
        }
        tail = pre;
        size--;
    }

    private void moveToHead(Entry entry) {
        if (entry == head) {
            return;
        }

        if (entry == tail) {
            tail = entry.pre;
            tail.next = null;
        } else {
            if (entry.pre != null) {
                entry.pre.next = entry.next;
            }
            if (entry.next != null) {
                entry.next.pre = entry.pre;
            }
        }

        entry.pre = null;
        entry.next = head;
        head.pre = entry;
        head = entry;
    }

    static class Entry {
        Entry pre, next;
        int key, value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        int val = lruCache.get(1);
        System.out.println("val = " + val);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println("key = 1, value= " + lruCache.get(1));
        lruCache.put(3, 3);
        val = lruCache.get(2);
        System.out.println("val = " + val);
        lruCache.put(4, 4);
        System.out.println("key = 1, value= " + lruCache.get(1));
        System.out.println("key = 3, value= " + lruCache.get(3));
        System.out.println("key = 4, value= " + lruCache.get(4));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 */