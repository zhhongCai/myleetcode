package com.theonecai.algorithms;

import java.util.Random;

/**
 * 参考redis中skiplist: server.h, t_zset.c
 *
 * @Author: thonecai
 * @Date: Create in 2020/6/18 11:14
 * @Description:
 */
public class SkipList<T extends Comparable<? super T>> {
    public static int SKIP_LIST_MAX_LEVEL = 64;

    private SkipListNode<T> head;


    private SkipListNode<T> tail;

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
        this.head = createNode(null, 0, SKIP_LIST_MAX_LEVEL);

        this.level = 1;
        this.length = 0;
        this.random = new Random();
    }

    protected SkipListNode<T> createNode(T data, double score, int level) {
        SkipListNode<T> node = new SkipListNode<>(data, 0, level);
        node.score = score;

        return node;
    }

    private int randomLevel() {
        int level = 1;
        while ((random.nextLong() & 0xFFFF) < (0.25 * 0xFFFF)) {
            level += 1;
        }
        return Math.min(level, SKIP_LIST_MAX_LEVEL);
    }

    public void insert(T data, double score) {
        int level, i;
        SkipListNode<T>[] update = new SkipListNode[SKIP_LIST_MAX_LEVEL];
        // 记录每层的插入位置
//        int[] rank = new int[SKIP_LIST_MAX_LEVEL];
        SkipListNode<T> x;

        x = this.head;
        for (i = this.level - 1; i >= 0; i--) {
//            rank[i] = i == this.level -1 ? 0 : rank[i + 1];
            while (x.level[i].forward != null &&
                    (x.level[i].forward.score < score ||
                            (x.level[i].forward.score == score && data.compareTo(x.level[i].forward.data) > 0))) {
//                rank[i] += x.level[i].span;
                x = x.level[i].forward;
            }
            update[i] = x;
        }

        level = randomLevel();
        if (level > this.level) {
            for (i = this.level; i < level; i++) {
//                rank[i] = 0;
                update[i] = this.head;
//                update[i].level[i].span = this.length;
            }
            this.level = level;
        }

        x = createNode(data, score, level);
        for (i = 0; i < level; i++) {
            x.level[i].forward = update[i].level[i].forward;
            update[i].level[i].forward = x;

//            x.level[i].span = update[i].level[i].span - (rank[0] - rank[i]);
//            update[i].level[i].span = (rank[0] - rank[i]) + 1;
        }
        this.length++;


    }

    public boolean search(T data) {
        SkipListNode<T> x = this.head;
        int i;
        for (i = this.level - 1; i >= 0; i--) {
            while (x.level[i].forward != null && data.compareTo(x.level[i].forward.data) > 0) {
                x = x.level[i].forward;
            }
        }

        x = x.level[0].forward;
        if (x != null && x.data.compareTo(data) == 0) {
            return true;
        }
        return false;
    }

    public int remove(T data) {
        SkipListNode<T>[] update = new SkipListNode[this.level];
        SkipListNode<T> x = this.head;
        int i;
        for (i = this.level - 1; i >= 0; i--) {
            while (x.level[i].forward != null && data.compareTo(x.level[i].forward.data) > 0) {
                x = x.level[i].forward;
            }
            update[i] = x;
        }

        x = x.level[0].forward;
        if (x != null && x.data.compareTo(data) == 0) {
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
            while(this.level > 1 && this.head.level[this.level-1].forward == null)
                this.level--;
            this.length--;

            return 1;
        }

        return 0;
    }

    static class SkipListNode<T extends Comparable<? super T>>  {
        private T data;
        private SkipListNode<T> backward;
        private double score;

        private SkipListLevel<T>[] level;

        public SkipListNode(T data, double score, int level) {
            this.data = data;
            this.score = score;
            if (level > 0) {
                this.level = new SkipListLevel[level];
                for (int i = 0; i < level; i++) {
                    this.level[i] = new SkipListLevel<>();
                    this.level[i].span = 0;
                }
            }
        }
    }

    static class SkipListLevel<T extends Comparable<? super T>>  {
        private SkipListNode<T> forward;
        private long span;
    }

    public static void main(String[] args) {
        SkipList<Integer> skipList = new SkipList<>();
        long a = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            skipList.insert(i, i);
        }

        System.out.println("2 in skiplist ? " + skipList.search(2));

        System.out.println("remove 2 resutl = " + skipList.remove(2));
        System.out.println("2 in skiplist ? " + skipList.search(2));
        System.out.println("cost time: " + (System.currentTimeMillis() - a));
    }

}
