package com.theonecai.leetcode.gredy;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;

/**
 * @Author: theonecai
 * @Date: Create in 2020/9/16 20:57
 * @Description:
 */
public class MaxNumber {

    private static final Num NOT_EXIST = new Num(-1, -1);

    /**
     * 每次取可选范围内最大的数
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        return maxNumber(nums1, nums2, 0, 0, k);
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int start, int start2, int k) {
        if (k == 0) {
            return new int[0];
        }

        int length = nums1.length - start;
        int length2 = nums2.length - start2;
        // nums1的可选范围（start~start+len)
        int len = Math.min(length, length - (k - length2) + 1);
        Num max = getMaxNum(nums1, start, len);

        // nums2的可选范围（start2~start2+len)
        len = Math.min(length2, length2 - (k - length) + 1);
        Num max2 = getMaxNum(nums2, start2, len);

        int[] result = new int[k];
        if (max2 == NOT_EXIST || max.num > max2.num) {
            result[0] = max.num;
            int[] r = maxNumber(nums1, nums2, max.index + 1, start2,  k - 1);
            if (r.length > 0) {
                System.arraycopy(r, 0, result, 1, k - 1);
            }
        } else if (max == NOT_EXIST || max.num < max2.num) {
            result[0] = max2.num;
            int[] r = maxNumber(nums1, nums2, start, max2.index + 1, k - 1);
            if (r.length > 0) {
                System.arraycopy(r, 0, result, 1, k - 1);
            }
        } else {
            result[0] = max2.num;
            int[] r = maxNumber(nums1, nums2, start, max2.index + 1, k - 1);
            if (r.length > 0) {
                System.arraycopy(r, 0, result, 1, k - 1);
            }

            int[] result2 = new int[result.length];
            result2[0] = max.num;
            int[] r2 = maxNumber(nums1, nums2, max.index + 1, start2, k - 1);
            if (r.length > 0) {
                System.arraycopy(r2, 0, result2, 1, k - 1);
            }
            int res = arrayCompare(result, result2);
            if (res > 0) {
                return result;
            } else if (res < 0) {
                return result2;
            }

        }

        return result;
    }

    private int arrayCompare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return 1;
            } else if (a[i] < b[i]) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 由maxNumber2而来
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int start = 0;
        int start2 = 0;
        int length = 0;
        int length2 = 0;
        int len;
        int index = 0;
        Num max;
        Num max2;
        int[] result = new int[k];
        while (k > 0) {
            length = nums1.length - start;
            length2 = nums2.length - start2;
            // nums1的可选范围（start~start+len)
            len = Math.min(length, length - (k - length2) + 1);
            max = getMaxNum(nums1, start, len);

            // nums2的可选范围（start2~start2+len)
            len = Math.min(length2, length2 - (k - length) + 1);
            max2 = getMaxNum(nums2, start2, len);

            if (max.num > max2.num && max != NOT_EXIST) {
                result[index++] = max.num;
                start = max.index + 1;
            } else if (max.num < max2.num) {
                result[index++] = max2.num;
                start2 = max2.index + 1;
            } else {
                result[index] = max.num;
                int[] subResult = maxNumber(Arrays.copyOfRange(nums1, max.index  +1, nums1.length), nums2, k - 1);

                result[index] = max2.num;
                int[] subResult2 = maxNumber(nums1, Arrays.copyOfRange(nums2, max2.index + 1, nums2.length), k - 1);
                int res = arrayCompare(subResult, subResult2);
                if (res > 0) {
                    result[index] = max.num;
                    System.arraycopy(subResult, 0, result, index + 1, subResult.length);
                } else {
                    result[index] = max2.num;
                    System.arraycopy(subResult2, 0, result, index + 1, subResult.length);
                }
                break;
            }
            k--;
        }
        return result;
    }

    private Num getMaxNum(int[] nums, int start, int len) {
        if (len == 0) {
            return NOT_EXIST;
        }
        Num max = new Num(0, start);
        for (int i = start; i < start + len; i++) {
            if (max.num < nums[i]) {
                max.num = nums[i];
                max.index = i;
            }
        }
        return max;
    }

    static class Num implements Comparable<Num> {
        int num;
        int index;

        public Num(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Num o) {
            return o.num - this.num;
        }
    }


    public static void main(String[] args) {
        MaxNumber maxNumber = new MaxNumber();
        int[] nums = ArrayUtil.randIntArray(10000000);
        int[] nums2 = ArrayUtil.randIntArray(10000000);
//        ArrayUtil.print(nums);
//        ArrayUtil.print(nums2);
//        System.out.println();
        int[] res2;
        RunUtil.runAndPrintCostTime(() -> {
            int[] res3 = maxNumber.maxNumber(nums, nums2, 10000000);
//            int[] res = maxNumber.maxNumber2(nums, nums2, 10000);
    //        ArrayUtil.print(res2);
    //        ArrayUtil.print(res2);
        });

        res2 = maxNumber.maxNumber2(new int[]{3, 9}, new int[]{8, 9}, 3);
        Assert.assertEquals(Arrays.toString(new int[]{9, 8, 9}), Arrays.toString(res2));
        res2 = maxNumber.maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3);
        Assert.assertEquals(Arrays.toString(new int[]{9, 8, 9}), Arrays.toString(res2));

        int[] res = maxNumber.maxNumber(new int[]{1,3,9,7,1,5,5,1,8,1}, new int[]{1,4,5,9,7,0,2,5,4,6,}, 20);
        Assert.assertEquals(Arrays.toString(new int[]{1,4,5,9,7,1,3,9,7,1,5,5,1,8,1,0,2,5,4,6}), Arrays.toString(res));
        res = maxNumber.maxNumber2(new int[]{1,3,9,7,1,5,5,1,8,1}, new int[]{1,4,5,9,7,0,2,5,4,6,}, 20);
        Assert.assertEquals(Arrays.toString(new int[]{1,4,5,9,7,1,3,9,7,1,5,5,1,8,1,0,2,5,4,6}), Arrays.toString(res));

        res = maxNumber.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9,8,6,5,3}), Arrays.toString(res));
        res = maxNumber.maxNumber2(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9,8,6,5,3}), Arrays.toString(res));


        res = maxNumber.maxNumber(new int[]{3, 4, 6, 5,9}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9, 9, 3, 8, 3}), Arrays.toString(res));
        res = maxNumber.maxNumber2(new int[]{3, 4, 6, 5,9}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9, 9, 3, 8, 3}), Arrays.toString(res));

        res2 = maxNumber.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{6, 7, 6, 0, 4}), Arrays.toString(res2));
        res2 = maxNumber.maxNumber2(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{6, 7, 6, 0, 4}), Arrays.toString(res2));

        res2 = maxNumber.maxNumber(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{2,3,4}, 6);
        Assert.assertEquals(Arrays.toString(new int[]{7,8,9,2,3,4}), Arrays.toString(res2));
        res2 = maxNumber.maxNumber2(new int[]{9,8,7,6,5,4,3,2}, new int[]{2,3,4}, 6);
        Assert.assertEquals(Arrays.toString(new int[]{9,8,7,6,5,4}), Arrays.toString(res2));


    }
}
