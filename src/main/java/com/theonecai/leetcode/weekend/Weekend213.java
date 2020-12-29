package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: theonecai
 * @Date: Create in 2020/11/01 10:24
 * @Description:
 */
public class Weekend213 {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        boolean[] visited = new boolean[pieces.length];
        Arrays.fill(visited, false);
        int i = 0;
        for (; i < arr.length; i++) {
            int len = 0;
            for (int j = 0; j < pieces.length; j++) {
                if (!visited[j]) {
                    int[] tmp = pieces[j];
                    int k = 0;
                    for (; k < tmp.length; k++) {
                        if (arr[i + len] == tmp[k]) {
                            len++;
                        }
                    }
                    if (len == tmp.length) {
                        visited[j] = true;
                        break;
                    } else {
                        len = 0;
                    }
                }
            }
            if (len == 0) {
                return false;
            } else {
                i += len - 1;
            }
        }
        return i == arr.length;
    }

    private int count = 0;
    private char[] chars = new char[] {'a','e','i','o','u'};
    public int countVowelStrings(int n) {
        count = 0;
        List<Character> list = new ArrayList<>(n);
        dfs(n, list);
        return count;
    }

    private void dfs(int n, List<Character> list) {
        if (n == 0) {
            count++;
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!list.isEmpty() && list.get(list.size() - 1) > chars[i]) {
                continue;
            }
            list.add(chars[i]);
            dfs(n - 1, list);
            list.remove(list.size() - 1);
        }
    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if (heights.length == 1) {
            return 0;
        }
        if (ladders >= heights.length - 1) {
            return heights.length - 1;
        }

        int[] diff = new int[heights.length];
        diff[0] = 0;
        int sum = 0;
        for (int i = 1; i < heights.length; i++) {
            diff[i] = Math.max(0, heights[i] - heights[i - 1]);
            sum += diff[i];
        }
        if (sum <= bricks) {
            return heights.length - 1;
        }

        int index = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 1; i < diff.length && ladders > 0; i++) {
            if (diff[i] > 0) {
                minHeap.offer(diff[i]);
                ladders--;
            }
            index++;
        }
//        System.out.println(Arrays.toString(diff));
        for (int i = index + 1; i < diff.length; i++) {
            if (diff[i] > 0) {
                minHeap.offer(diff[i]);
                if (minHeap.peek() <= bricks) {
                    bricks -= minHeap.poll();
                } else {
                    break;
                }
            }
            index++;
        }

        return index;
    }


    public static void main(String[] args) {
        Weekend213 weekend213 = new Weekend213();
        weekend213.test();
        weekend213.test2();
        weekend213.test3();
    }

    private void test3() {
        //[18,18,13,17,2]
        //29
        //2
        Assert.assertEquals(4, this.furthestBuilding(new int[]{18,18,13,17,2}, 29, 2));
        Assert.assertEquals(7, this.furthestBuilding(new int[]{4,12,2,7,3,18,20,3,19}, 10, 2));
        Assert.assertEquals(4, this.furthestBuilding(new int[]{4,2,7,6,9,14,12}, 5, 1));
        Assert.assertEquals(3, this.furthestBuilding(new int[]{14,3,19,3}, 17, 0));
    }

    private void test2() {
        Assert.assertEquals(5, this.countVowelStrings(1));
        Assert.assertEquals(15, this.countVowelStrings(2));
        Assert.assertEquals(66045, this.countVowelStrings(33));
        Assert.assertEquals(316251, this.countVowelStrings(50));
    }

    private void test() {
        Assert.assertTrue(this.canFormArray(new int[]{15,88}, new int[][]{{88},{15}}));
        Assert.assertTrue(this.canFormArray(new int[]{85}, new int[][]{{85}}));
        Assert.assertFalse(this.canFormArray(new int[]{49,18,16}, new int[][]{{16,18,49}}));
        Assert.assertTrue(this.canFormArray(new int[]{91,4,64,78}, new int[][]{
                {78},{4,64},{91}
        }));
        Assert.assertFalse(this.canFormArray(new int[]{1,3,5,7}, new int[][]{
                {2,4,6,8}
        }));
    }
}
