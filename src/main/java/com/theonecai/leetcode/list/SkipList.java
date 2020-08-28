package com.theonecai.leetcode.list;

import java.util.Random;

/**
 * leetcode 1206
 *
 * @Author: theonecai
 * @Date: Create in 2020/6/20 09:51
 * @Description:
 */
public class SkipList {
    public static int SKIP_LIST_MAX_LEVEL = 32;

    private SkipListNode head;


    private SkipListNode tail;

    /**
     * 层数
     */
    private int level;

    /**
     * 元素个数
     */
    private long length;

    private Random random;

    public SkipList() {
        this.head = createNode(null, SKIP_LIST_MAX_LEVEL);

        this.level = 1;
        this.length = 0;
        this.random = new Random();
    }

    protected SkipListNode createNode(Integer data, int level) {
        SkipListNode node = new SkipListNode(data, level);

        return node;
    }

    private int randomLevel() {
        int level = 1;
        while ((random.nextInt() & 0xFF) < (0.25 * 0xFF)) {
            level += 1;
        }
        return Math.min(level, SKIP_LIST_MAX_LEVEL);
    }

    public boolean search(int target) {
        SkipListNode x = this.head;
        int i;
        for (i = this.level - 1; i >= 0; i--) {
            while (x.level[i].forward != null && target > x.level[i].forward.data) {
                x = x.level[i].forward;
            }
        }

        x = x.level[0].forward;
        if (x != null && x.data == target) {
            return true;
        }
        return false;
    }

    public void add(int num) {
        int level, i;
        SkipListNode x = this.head;
        SkipListNode[] update = new SkipListNode[SKIP_LIST_MAX_LEVEL];
        for (i = this.level - 1; i >= 0; i--) {
            while (x.level[i].forward != null && num > x.level[i].forward.data) {
                x = x.level[i].forward;
            }
            update[i] = x;
        }

        level = randomLevel();
        if (level > this.level) {
            for (i = this.level; i < level; i++) {
                update[i] = this.head;
            }
            this.level = level;
        }

        x = createNode(num, level);
        for (i = 0; i < level; i++) {
            x.level[i].forward = update[i].level[i].forward;
            update[i].level[i].forward = x;
        }
        this.length++;
    }

    public boolean erase(int num) {
        SkipListNode[] update = new SkipListNode[this.level];
        SkipListNode x = this.head;
        int i;
        for (i = this.level - 1; i >= 0; i--) {
            while (x.level[i].forward != null && x.level[i].forward.data < num) {
                x = x.level[i].forward;
            }
            update[i] = x;
        }

        x = x.level[0].forward;
        if (x != null && x.data == num) {
            for (i = 0; i < this.level; i++) {
                if (update[i].level[i].forward == x) {
                    update[i].level[i].forward = x.level[i].forward;
                }
            }

            if (x.level[0].forward != null) {
                x.level[0].forward.backward = x.backward;
            } else {
                this.tail = x.backward;
            }
            while(this.level > 1 && this.head.level[this.level-1].forward == null) {
                this.level--;
            }
            this.length--;

            return true;
        }

        return false;
    }

    static class SkipListNode  {
        private Integer data;
        private SkipListNode backward;

        private SkipListLevel[] level;

        public SkipListNode(Integer data, int level) {
            this.data = data;
            if (level > 0) {
                this.level = new SkipListLevel[level];
                for (int i = 0; i < level; i++) {
                    this.level[i] = new SkipListLevel();
                }
            }
        }
    }

    static class SkipListLevel  {
        private SkipListNode forward;
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        long a = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            skipList.add(i);
        }
        System.out.println("2 in skiplist ? " + skipList.search(2));

        System.out.println("remove 2 resutl = " + skipList.erase(2));
        System.out.println("2 in skiplist ? " + skipList.search(2));
        System.out.println("cost time: " + (System.currentTimeMillis() - a));
    }
}
