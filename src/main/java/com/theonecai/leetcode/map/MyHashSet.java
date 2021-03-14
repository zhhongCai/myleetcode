package com.theonecai.leetcode.map;

import org.junit.Assert;

/**
 * leetcode 705
 * @Author: theonecai
 * @Date: Create in 2021/3/13 21:43
 * @Description:
 */
public class MyHashSet {

    private BitMap bitMap;

    /** Initialize your data structure here. */
    public MyHashSet() {
        bitMap = new BitMap(1000002);
    }

    public void add(int key) {
        bitMap.atPut(key, true);
    }

    public void remove(int key) {
        bitMap.atPut(key, false);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return bitMap.at(key);
    }

    private static class BitMap {

        private int size;

        private int[] bitSet;

        public BitMap(int bits) {
            this.bitSet = new int[(bits + 32 - 1) / 32];
            this.size = bits;
        }

        public void atPut(int offset, boolean val) {
            if (offset >= size) {
                throw new IllegalArgumentException("max size=" + this.size);
            }
            int index = indexFor(offset);
            int pos = offset % 32;
            if (val) {
                bitSet[index] = setNthBit(this.bitSet[index], pos);
            } else {
                bitSet[index] = clearBits(this.bitSet[index], pos);
            }
        }

        public boolean at(int offset) {
            if (offset >= size) {
                throw new IllegalArgumentException("max size=" + this.size);
            }

            return isSetNthBit(offset);
        }

        private int clearBits(int c, int n) {
            return c & ~nthBit(n);
        }

        private int setNthBit(int c, int n) {
            return c | nthBit(n);
        }

        private int nthBit(int n) {
            return n > 32 ? 0 : 1 << n;
        }

        private int indexFor(int offset) {
            return offset / 32;
        }

        private boolean isSetNthBit(int offset) {
            return (this.bitSet[offset / 32] & nthBit(offset % 32)) != 0;
        }
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(1111);
        set.add(211);
        set.add(311);
        Assert.assertTrue(set.contains(1111));
        Assert.assertTrue(set.contains(211));
        Assert.assertTrue(set.contains(311));
        set.remove(2);
        Assert.assertFalse(set.contains(21));
        set.add(2);
        Assert.assertTrue(set.contains(2));
    }

}
