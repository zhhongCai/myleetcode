package com.theonecai.algorithms;

import org.junit.Assert;

/**
 * 树状数组
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/10 20:12
 * @Description:
 */
public class BinaryIndexedTree {
    int[] arr;
    int[] sum;

    public BinaryIndexedTree(int[] arr) {
        this.arr = arr;
        this.sum = new int[arr.length + 1];
        // 前缀和
        int[] pre = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            pre[i] = pre[i - 1] + arr[i - 1];
            sum[i] = pre[i] - pre[i - lowbit(i)];
        }
    }

    /**
     * num的二进制表示中从右往左第一个1的位置
     * @param num
     * @return
     */
    private int lowbit(int num) {
        return num & (-num);
    }

    public int getSum(int x) {
        int i = x;
        int s = 0;
        while (i > 0) {
            s += sum[i];
            i -= lowbit(i);
        }
        return s;
    }

    public int getSum(int x, int y) {
        return getSum(y) - getSum(x);
    }

    public void update(int x, int addVal) {
        arr[x] = arr[x] + addVal;
        int i = x;
        while (i < sum.length) {
            sum[i] += addVal;
            i += lowbit(x);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        BinaryIndexedTree biTree = new BinaryIndexedTree(arr);

        ArrayUtil.print(biTree.arr);
        ArrayUtil.print(biTree.sum);

        Assert.assertEquals(10, biTree.getSum(4));
        Assert.assertEquals(11, biTree.getSum(4, 6));
    }
}
