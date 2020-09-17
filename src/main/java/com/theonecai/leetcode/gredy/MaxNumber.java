package com.theonecai.leetcode.gredy;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * leetcode 321
 * @Author: theonecai
 * @Date: Create in 2020/9/16 20:57
 * @Description:
 */
public class MaxNumber {

    private static final Num NOT_EXIST = new Num(-1, -1);

    private int n;
    private int m;

    private int[] nums1;
    private int[] nums2;

    public int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int[] result = new int[0];
        int maxLen = Math.min(nums1.length, k);
        int i = Math.max(0, k - nums2.length);
        while (i <= maxLen) {
            // nums1中取i个最大数
            int[] part = getMaxNums(nums1, i);
            // nums2中取k-i个最大数
            int[] part2 = getMaxNums(nums2, k - i);

            // 合并
            int[] res = merge(part, part2);

            // 更新结果
            result = arrayCompare(result, res) < 0 ? res : result;
            i++;
        }
        return result;
    }

    private int[] merge(int[] left, int[] right) {
        if (left.length == 0) {
            return right;
        }
        if (right.length == 0) {
            return left;
        }
        int len = left.length + right.length;
        int leftIndex = 0;
        int rightIndex = 0;

        int[] result = new int[len];
        int index = 0;
        while (len-- > 0) {
            if (rightIndex >= right.length || (leftIndex < left.length &&  left[leftIndex] > right[rightIndex])) {
                result[index++] = left[leftIndex++];
            } else if (leftIndex >= left.length || left[leftIndex] < right[rightIndex]) {
                result[index++] = right[rightIndex++];
            } else {
               int res = arrayCompare(left, leftIndex + 1, right, rightIndex + 1);
               if (res > 0) {
                   result[index++] = left[leftIndex++];
               } else {
                   result[index++] = right[rightIndex++];
               }
            }
        }

        return result;
    }

    private int arrayCompare(int[] left, int start, int[] right, int start2) {
        int res = 0;
        while (start < left.length && start2 < right.length) {
            if (left[start] > right[start2]) {
                res = 1;
                break;
            }
            if (left[start] < right[start2]) {
                res = -1;
                break;
            }
            start++;
            start2++;
        }
        if (res != 0) {
            return res;
        }
        if (start == left.length && start2 == right.length) {
            return 0;
        }
        res = start == left.length ? -1 : 1;
        return res;
    }

    /**
     * 取nums中相对位置不变的len个数，拼出最大数
     * @param nums
     * @param len
     * @return
     */
    private int[] getMaxNums(int[] nums, int len) {
        if (len == 0) {
            return new int[0];
        }
        int remain = nums.length - len;
        if (remain == 0) {
            return nums;
        }
        int[] result = new int[len];
        int index = 0;
        for (int num : nums) {
            while (index > 0 && remain > 0 && num > result[index - 1]) {
                index--;
                remain--;
            }
            if (index == len && remain > 0 && num <= result[index - 1]) {
                remain--;
                continue;
            }
            result[index++] = num;
        }
        return result;
    }

    private int arrayCompare(int[] a, int[] b) {
        if (a.length == 0) {
            return -1;
        }
        if (b.length == 0) {
            return 1;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return 1;
            } else if (a[i] < b[i]) {
                return -1;
            }
        }
        return 0;
    }


    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        //十个桶，每个桶里存索引
        List<TreeSet<Integer>> list = new ArrayList<>(10);
        List<TreeSet<Integer>> list2 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(new TreeSet<>());
            list2.add(new TreeSet<>());
        }
        this.n = nums1.length;
        this.m = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;

        for (int i = 0; i < nums1.length || i < nums2.length; i++) {
            if (i <nums1.length) {
                list.get(nums1[i]).add(i);
            }
            if (i < nums2.length) {
                list2.get(nums2[i]).add(i);
            }
        }


        return maxNumberGreedy(list, list2, 0, 0, k);
    }

    public int[] maxNumberGreedy(List<TreeSet<Integer>> list, List<TreeSet<Integer>> list2,
                                     int start, int start2, int k) {
        if (k <= 0) {
            return new int[0];
        }
        int length = 0;
        int length2 = 0;
        int len;
        int len2;
        int index = 0;
        Num max;
        Num max2;
        int[] result = new int[k];
        while (k > 0) {
            length = n - start;
            length2 = m - start2;
            len = Math.min(length, length - (k - length2) + 1);
            // nums1的可选范围（start~start+len),取最大值
            max = getMaxNum(list, start, start + len);

            len2 = Math.min(length2, length2 - (k - length) + 1);
            // nums2的可选范围（start2~start2+len)，取最大值
            max2 = getMaxNum(list2, start2, start2 + len2);

            if (max != NOT_EXIST && (max2 == NOT_EXIST || max.num > max2.num)) {
                result[index++] = max.num;
                start = max.index + 1;
            } else if (max2 != NOT_EXIST && (max == NOT_EXIST || max.num < max2.num)) {
                result[index++] = max2.num;
                start2 = max2.index + 1;
            } else {
                int res;
                if (max.index == nums1.length - 1 && max2.index == nums1.length - 1) {
                    res = arrayCompare(nums1, 0, nums2, 0);
                    if (res > 0) {
                        result[index++] = max.num;
                        start = max.index + 1;
                    } else {
                        result[index++] = max2.num;
                        start2 = max2.index + 1;
                    }
                } else {
                    res = arrayCompare(nums1, max.index + 1, nums2, max2.index + 1);
                    if (res > 0) {
                        result[index++] = max.num;
                        start = max.index + 1;
                    } else {
                        result[index++] = max2.num;
                        start2 = max2.index + 1;
                    }
                }
            }
            k--;
        }

        return result;
    }

    private Num getMaxNum(List<TreeSet<Integer>> list, int from, int to) {
        if (to <= 0) {
            return NOT_EXIST;
        }
        TreeSet<Integer> set;
        for (int i = 9; i >= 0; i--) {
            set = list.get(i);
            if (set.isEmpty()) {
                continue;
            }
            Integer index = set.ceiling(from);
            if (index == null || index >= to) {
                continue;
            }
            return new Num(i, index);
        }
        return NOT_EXIST;
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
        int[] res2;

        int[] nums7 = {2,9,7,7,9,2,5,5,1,9,6,8,4,5,1,3,3,1,4,8,7,1,8,2,2,9,6,9,9,7,4,0,6,5,9,0,4,7,6,8,0,1,6};
        System.out.println(Arrays.toString(maxNumber.getMaxNums(nums7, 7)));
        System.out.println(Arrays.toString(maxNumber.getMaxNums(nums7, 8)));
        int[] nums72 = {2};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums72, nums7, 44)),
                Arrays.toString(maxNumber.maxNumber(nums72, nums7, 44)));

        int[] nums5 = {2, 3, 4, 1};
        int[] nums52 = {2, 3, 4, 1};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums5, nums52, 8)),
                Arrays.toString(maxNumber.maxNumber(nums5, nums52, 8)));

        int[] nums6 = {2, 3, 4};
        int[] nums62 = {2, 3, 4, 1};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums6, nums62, 7)),
                Arrays.toString(maxNumber.maxNumber(nums6, nums62, 7)));

        int[] nums = {1, 8, 9};
        int[] nums2 = {2, 3, 9};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums, nums2, 5)),
                Arrays.toString(maxNumber.maxNumber(nums, nums2, 5)));

        int[] nums12 = {8, 9};
        int[] nums22 = {3, 9};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums12, nums22, 3)),
                Arrays.toString(maxNumber.maxNumber(nums12, nums22, 3)));

        int[] nums3 = {3, 9};
        int[] nums32 = {8, 9};
        Assert.assertEquals(Arrays.toString(maxNumber.maxNumber2(nums3, nums32, 3)),
                Arrays.toString(maxNumber.maxNumber(nums3, nums32, 3)));

        res2 = maxNumber.maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3);
        Assert.assertEquals(Arrays.toString(new int[]{9, 8, 9}), Arrays.toString(res2));

        int len = 1000000;
        int[] nums4 = new int[len];
        int[] nums42 = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            nums4[i] = random.nextInt(10);
            nums42[i] = random.nextInt(10);
        }

//        ArrayUtil.print(nums);
//        ArrayUtil.print(nums2);
        RunUtil.runAndPrintCostTime(() -> {
            int[] res3 = maxNumber.maxNumber(nums4, nums42, 100000);
//            ArrayUtil.print(res3);
        });

        int[] res = maxNumber.maxNumber(new int[]{1,3,9,7,1,5,5,1,8,1}, new int[]{1,4,5,9,7,0,2,5,4,6,}, 20);
        Assert.assertEquals(Arrays.toString(new int[]{1,4,5,9,7,1,3,9,7,1,5,5,1,8,1,0,2,5,4,6}), Arrays.toString(res));

        res = maxNumber.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9,8,6,5,3}), Arrays.toString(res));


        res = maxNumber.maxNumber(new int[]{3, 4, 6, 5,9}, new int[]{9, 1, 2, 3, 8, 3}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{9, 9, 3, 8, 3}), Arrays.toString(res));

        res2 = maxNumber.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5);
        Assert.assertEquals(Arrays.toString(new int[]{6, 7, 6, 0, 4}), Arrays.toString(res2));

        res2 = maxNumber.maxNumber(new int[]{1,2,3,4,5,6,7,8,9}, new int[]{2,3,4}, 6);
        Assert.assertEquals(Arrays.toString(new int[]{7,8,9,2,3,4}), Arrays.toString(res2));


        res2 = maxNumber.maxNumber(new int[]{1,1,1,1,1,1,1,1}, new int[]{2,2,2}, 11);
        Assert.assertEquals(Arrays.toString(new int[]{2,2,2,1,1,1,1,1,1,1,1}), Arrays.toString(res2));
    }
}
