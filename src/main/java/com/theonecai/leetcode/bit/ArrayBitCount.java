package com.theonecai.leetcode.bit;


public class ArrayBitCount {

    public int[] bitCount(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = Integer.bitCount(i);
//            result[i] = popCount(i);
        }
        return result;
    }

   private int popCount(int n) {
        int c = 0;
        while (n > 0) {
            // 最后一位1置为0
            n &= n - 1;
            c++;
        }
        return c;
    }

    public int[] bitCountDp(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            // i为奇数 由 (i - 1) + 1构成
            if ((i % 1) == 1) {
                result[i] = result[i - 1] + 1;
            } else {
                int j = i - 1;
                // j的二进制从右往第一次出现0时1的个数
                int c = 0;
                while ((j & 1) != 0) {
                    j >>= 1;
                    c++;
                }
                result[i] = result[i - 1] - c + 1;
            }

        }
        return result;
    }

    public int[] bitCountDp2(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            result[i] = result[i - (i & (-i))] + 1;

        }
        return result;
    }

    public int[] bitCountDp3(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {
            // (i & (i - 1))表示i的二进制从右往左第一个1置为0
            result[i] = result[i & (i - 1)] + 1;

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-3));
        ArrayBitCount arrayBitCount = new ArrayBitCount();
        int[] a = arrayBitCount.bitCount(1000000);
        int[] b = arrayBitCount.bitCountDp(1000000);
        int[] c = arrayBitCount.bitCountDp2(1000000);
        int[] d = arrayBitCount.bitCountDp3(1000000);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i] && a[i] != c[i] && a[i] != d[i]) {
                System.out.println("i=" + i + "," + a[i] + "," + b[i] + "," + c[i] + "," + d[i]);
            }
        }
    }
}
