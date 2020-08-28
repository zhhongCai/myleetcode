package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * @Author: theonecai
 * @Date: Create in 2020/8/27 20:41
 * @Description:
 */
public class RightPartLessCount {

    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> count = new ArrayList<>(nums.length);
        Node[] nodes = createNodes(nums, count);

        Arrays.sort(nodes);

        BitSet bitSet = new BitSet(nums.length + 1);
        for (int i = 0; i < nodes.length; i++) {
            bitSet.set(nodes[i].index);
            count.set(nodes[i].index, (int) sumOneBits(bitSet.get(nodes[i].index + 1, nodes.length)));
        }

        return count;
    }

    private Node[] createNodes(int[] nums, List<Integer> count) {
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
            count.add(0);
        }
        return nodes;
    }

    private long sumOneBits(BitSet bitSet) {
        long count = 0;
        for (long bits : bitSet.toLongArray()) {
            count += Long.bitCount(bits);
        }
        return count;
    }

    static class Node implements Comparable<Node> {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> count = new ArrayList<>(nums.length);
        Node[] nodes = createNodes(nums, count);
        Node[] tmpArray = new Node[nums.length];

        mergeSort(nodes, 0, nums.length - 1, count, tmpArray);

        return count;
    }

    private void mergeSort(Node[] nodes, int start, int end, List<Integer> count, Node[] tmpArray) {
        if (start >= end) {
            return;
        }

        int mid = (end + start) / 2;
        mergeSort(nodes, start, mid, count, tmpArray);
        mergeSort(nodes, mid + 1, end, count, tmpArray);

        merge(nodes, start, mid, end, count, tmpArray);
    }

    private void merge(Node[] nodes, int start, int mid, int end, List<Integer> count, Node[] tmpArray) {
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tmpIndex = start;
        boolean preElemIsRightPart = false;
        int rightPartLessThanLeftCount = 0;
        while (leftIndex <= mid || rightIndex <= end) {
            if (rightIndex > end || (leftIndex <= mid && nodes[leftIndex].value <= nodes[rightIndex].value)) {
                if (!preElemIsRightPart) {
                    rightPartLessThanLeftCount = (rightIndex > end ? end : rightIndex - 1) - mid;
                    count.set(nodes[leftIndex].index,  count.get(nodes[leftIndex].index) + rightPartLessThanLeftCount);
                }
                tmpArray[tmpIndex++] = nodes[leftIndex++];
                preElemIsRightPart = false;
            } else {
                if (leftIndex <= mid) {
                    count.set(nodes[leftIndex].index,  count.get(nodes[leftIndex].index) + 1);
                }
                tmpArray[tmpIndex++] = nodes[rightIndex++];
                preElemIsRightPart = true;
            }
        }

        System.arraycopy(tmpArray, start, nodes, start, end - start + 1);
    }

    public static void main(String[] args) {
        RightPartLessCount lessCount = new RightPartLessCount();
        int[] nums = {5,4,3,2,1};

        System.out.println(lessCount.countSmaller2(nums));
        System.out.println(lessCount.countSmaller(nums));
        int[] nums3 = {5,2,6,1};
        System.out.println(lessCount.countSmaller(nums3));
        System.out.println(lessCount.countSmaller2(nums3));

        int[] nums2 = ArrayUtil.randIntArray(100000);
        long a = System.currentTimeMillis();
        lessCount.countSmaller(nums2);
        System.out.println("cost: " + (System.currentTimeMillis() - a));

        int[] nums4 = ArrayUtil.randIntArray(100000);
        a = System.currentTimeMillis();
        lessCount.countSmaller2(nums4);
        System.out.println("cost: " + (System.currentTimeMillis() - a));

        int[] nums5 = {5,5,5,5};
        System.out.println(lessCount.countSmaller(nums5));
        System.out.println(lessCount.countSmaller2(nums5));


        int[] nums6 = {5};
        System.out.println(lessCount.countSmaller(nums6));
        System.out.println(lessCount.countSmaller2(nums6));
    }
}
