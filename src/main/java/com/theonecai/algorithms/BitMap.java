package com.theonecai.algorithms;

import org.junit.Assert;

/**
 * @see sun.jvm.hotspot.utilities.BitMap
 *
 * @Author: theonecai
 * @Date: Create in 2020/7/7 20:47
 * @Description:
 */
public class BitMap {

    private int size;

    private int[] bitSet;

    public BitMap(int bits) {
        this.bitSet = new int[(bits  + 32 - 1) / 32];
        this.size = bits;
    }

    public void atPut(int offset, boolean val) {
        if (offset >= size) {
            throw new IllegalArgumentException("max size=" + this.size);
        }
        int index = indexFor(offset);
        int pos = offset % 8;
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

    public static void main(String[] args) {
        int n = 10;
        BitMap bitMap = new BitMap(n + 1);
        int[] a = ArrayUtil.randIntArray(n);
        long b = System.currentTimeMillis();
        for (int i = 0; i < a.length; i++) {
            bitMap.atPut(a[i], true);
        }
        System.out.println("cost:" + (System.currentTimeMillis() - b));
        for (int i : bitMap.bitSet) {
            System.out.println(Integer.toUnsignedString(i, 2));
        }

        for (int i = 0; i < a.length; i++) {
            Assert.assertTrue(bitMap.at(a[i]));
        }
    }
}
