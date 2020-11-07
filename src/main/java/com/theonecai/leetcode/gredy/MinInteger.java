package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 1505
 * @Author: theonecai
 * @Date: Create in 2020/10/29 19:50
 * @Description:
 */
public class MinInteger {

    public String minInteger(String num, int k) {
        int len = num.length();
        StringBuilder numStr = new StringBuilder();
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(len + 1);
        Deque<Integer>[] numIndex = new Deque[10];
        for (int i = 0; i < 10; i++) {
            numIndex[i] = new LinkedList<>();
        }
        for (int i = 0; i < len; i++) {
            numIndex[num.charAt(i) - '0'].add(i + 1);
        }
        for (int i = 1; i <= len; i++) {
            int index = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < 10; j++) {
                if (numIndex[j].isEmpty()) {
                    continue;
                }
                int switchCount = binaryIndexedTree.getSum(numIndex[j].peek() + 1, len);
                int newIndex = numIndex[j].peek() + switchCount;
                int dist = newIndex - i;
                if (index > newIndex) {
                    index = newIndex;
                    idx = j;
                }
                if (dist <= k) {
                    numStr.append(j);
                    binaryIndexedTree.update(numIndex[j].poll(), 1);
                    k -= dist;
                    idx = -1;
                    break;
                }
            }
            if (idx != -1) {
                numStr.append(idx);
                numIndex[idx].poll();
            }
        }

        return numStr.toString();
    }

    private static class BinaryIndexedTree {
        int[] sum;

        public BinaryIndexedTree(int n) {
            this.sum = new int[n];
        }

        /**
         * num的二进制表示中从右往左第一个1的位置
         *
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

        public int getSum(int start, int end) {
            return getSum(end) - getSum(start - 1);
        }

        public void update(int x, int addVal) {
            int i = x;
            while (i < sum.length) {
                sum[i] += addVal;
                i += lowbit(i);
            }
        }
    }

    public static void main(String[] args) {
        MinInteger minInteger = new MinInteger();
        //0943895
        //0394895,0349895
        Assert.assertEquals("0345989723478563548", minInteger.minInteger("9438957234785635408", 23));
        Assert.assertEquals("1342", minInteger.minInteger("4321", 4));
        Assert.assertEquals("010", minInteger.minInteger("100", 1));
        Assert.assertEquals("36789", minInteger.minInteger("36789", 100000));
        Assert.assertEquals("36789", minInteger.minInteger("36789", 100000));
        Assert.assertEquals("22", minInteger.minInteger("22", 22));
    }
}
