package com.theonecai.leetcode.weekend;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: theonecai
 * @Date: Create in 2020/10/25 10:24
 * @Description:
 */
public class Weekend212 {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int[] count = new int[26];
        count[keysPressed.charAt(0) - 'a'] = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            count[keysPressed.charAt(i) - 'a'] = Math.max(count[keysPressed.charAt(i) - 'a'],
                    releaseTimes[i] - releaseTimes[i - 1]);
        }
        int max = 0;
        char ch = keysPressed.charAt(0);
        for (int i = 25; i >= 0; i--) {
            if (max  < count[i]) {
                max = count[i];
                ch = (char)(i + 'a');
            }
        }
        return ch;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        Map<String, Boolean> result = new HashMap<>(l.length);
        List<Boolean> list = new ArrayList<>(l.length);
        for (int i = 0; i < l.length; i++) {
            String key = l[i] + "_" + r[i];
            if (result.containsKey(key)) {
                list.add(result.get(key));
                continue;
            }
            boolean ret = check(nums, l[i], r[i]);
            result.put(key, ret);
            list.add(ret);
        }
        return list;
    }

    private boolean check(int[] nums, int start, int end) {
        if (end == start + 1) {
            return true;
        }
        int[] arr = Arrays.copyOfRange(nums, start, end + 1);
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (diff != arr[i] - arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int minimumEffortPath(int[][] heights) {
        Deque<int[]> queue = new LinkedList<>();
        int[][] result = new int[heights.length][heights[0].length];

        queue.addLast(new int[]{0,0});
        for (int i = 0; i < result.length; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }
        result[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.removeFirst();

            visit(heights, current, result, queue);
        }
        return result[heights.length - 1][heights[0].length - 1];
    }

    private void visit(int[][] heights, int[] current, int[][] result, Deque<int[]> queue) {
        int col;
        int row;
        int h;
        if (current[0] > 0) {
            row = current[0] - 1;
            col = current[1];
            h = Math.abs(heights[current[0]][current[1]] - heights[row][col]);
            processNext(current, result, queue, new int[] {row, col}, h);
        }
        if (current[0] < heights.length - 1) {
            row = current[0] + 1;
            col = current[1];
            h = Math.abs(heights[current[0]][current[1]] - heights[row][col]);
            processNext(current, result, queue, new int[] {row, col}, h);
        }
        if (current[1] > 0) {
            row = current[0];
            col = current[1] - 1;
            h = Math.abs(heights[current[0]][current[1]] - heights[row][col]);
            processNext(current, result, queue, new int[] {row, col}, h);
        }
        if (current[1] < heights[0].length - 1) {
            row = current[0];
            col = current[1] + 1;
            h = Math.abs(heights[current[0]][current[1]] - heights[row][col]);
            processNext(current, result, queue, new int[] {row, col}, h);
        }
    }

    private void processNext(int[] current, int[][] result, Deque<int[]> queue, int[] next, int h) {
        h = Math.max(result[current[0]][current[1]], h);
        if (h < result[next[0]][next[1]]) {
            queue.addLast(next);
        }
        result[next[0]][next[1]] = Math.min(result[next[0]][next[1]], h);
    }


    public static void main(String[] args) {
        Weekend212 weekend211 = new Weekend212();
        weekend211.test();
        weekend211.test2();
        weekend211.test3();
    }

    private void test3() {
        Assert.assertEquals(0, this.minimumEffortPath(new int[][]{
                {1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}
        }));
        Assert.assertEquals(9, this.minimumEffortPath(new int[][]{
                {1,10,6,7,9,10,4,9}
        }));
        Assert.assertEquals(2, this.minimumEffortPath(new int[][]{
                {1,2,2},{3,8,2},{5,3,5}
        }));
        Assert.assertEquals(1, this.minimumEffortPath(new int[][]{
                {1,2,3},{3,8,4},{5,3,5}
        }));
    }

    private void test2() {
        List<Boolean> list = this.checkArithmeticSubarrays(new int[]{4, 6, 5, 9, 3, 7}, new int[]{0, 0, 2}, new int[]{2, 3, 5});
        System.out.println(list);
        list = this.checkArithmeticSubarrays(new int[]{-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10},  new int[]{0,1,6,4,8,7}, new int[]{4,4,9,7,9,10});
        System.out.println(list);
    }

    private void test() {
        Assert.assertEquals('c', this.slowestKey(new int[]{9,29,49,50}, "cbcd"));
        Assert.assertEquals('a', this.slowestKey(new int[]{12,23,36,46,62}, "spuda"));
    }
}
