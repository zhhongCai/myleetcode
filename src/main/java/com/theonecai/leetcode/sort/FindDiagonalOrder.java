package com.theonecai.leetcode.sort;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * leetcode 1424
 * @Author: theonecai
 * @Date: Create in 2020/9/8 20:12
 * @Description:
 */
public class FindDiagonalOrder {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> rows = nums.get(i);
            for (int j = 0; j < rows.size(); j++) {
                nodes.add(new Node(i, j, rows.get(j)));
            }
        }

        Collections.sort(nodes);

        return nodes.stream().mapToInt(Node::getValue).toArray();
    }

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int value;

        public int getValue() {
            return value;
        }

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        /**
         * 行 列
         * 0 0
         * 1 0
         * 0 1
         * 2 0
         * 1 1
         * 0 2
         * 3 0
         * 2 1
         * 1 2
         * 0 3
         * ...
         * @param o
         * @return
         */
        @Override
        public int compareTo(Node o) {
            int rowPlusCol = this.row + this.col;
            int rowPlusCol2 = o.row + o.col;
            int res = rowPlusCol - rowPlusCol2;
            // 行列和相等，按列索引排序
            if (res == 0) {
                return this.col - o.col;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        FindDiagonalOrder findDiagonalOrder = new FindDiagonalOrder();

        List<List<Integer>> list = new ArrayList<>();
        Random random = new Random(100000);
        int count = 0;
        for (int i = 0; i < 10; i++) {
            int[] nums = ArrayUtil.randIntArray(random.nextInt(10));
//            int[] nums = ArrayUtil.randIntArray(10);
            List<Integer> ints = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                ints.add(nums[j]);
            }
            list.add(ints);
        }
        for (List<Integer> ints : list) {
            count += ints.size();
            System.out.println(ints);
        }

        int c = count;
        RunUtil.runAndPrintCostTime(() -> {
            int[] res = findDiagonalOrder.findDiagonalOrder(list);
            System.out.println(Arrays.toString(res));
            Assert.assertEquals(c, res.length);
        });
    }
}
