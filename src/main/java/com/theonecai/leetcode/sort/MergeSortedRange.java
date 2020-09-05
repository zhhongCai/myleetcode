package com.theonecai.leetcode.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 57
 * @Author: theonecai
 * @Date: Create in 2020/9/4 20:03
 * @Description:
 */
public class MergeSortedRange {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new LinkedList<>();
        if (intervals == null || intervals.length == 0) {
            if (newInterval != null) {
                list.add(newInterval);
            }
            return list.toArray(new int[0][]);
        }

        int[] current = null;
        boolean newIntervalProcessed = false;
        for (int i = 0; i < intervals.length; i++) {
            current = intervals[i];
            // newInterval跟current有交叉
            if ((newInterval[0] >= current[0] && newInterval[0] <= current[1]) ||
                    (newInterval[1] >= current[0] && newInterval[1] <= current[1]) ||
                    (current[0] > newInterval[0] && current[1] < newInterval[1])) {
                current[0] = Math.min(current[0], newInterval[0]);
                current[1] = Math.max(current[1], newInterval[1]);
                newIntervalProcessed = true;
                int j = i + 1;
                while (j < intervals.length) {
                    int[] next = intervals[j];
                    if (next[0] >= current[0] && next[0] <=current[1]) {
                        current[0] = Math.min(current[0], next[0]);
                        current[1] = Math.max(current[1], next[1]);
                        j++;
                        i++;
                        continue;
                    }
                    break;
                }
                if (j > i + 1) {
                    i--;
                }
            } else if (newInterval[1] < current[0] && !newIntervalProcessed)  {
                list.add(newInterval);
                newIntervalProcessed  = true;
            }
            list.add(current);
        }
        if (!newIntervalProcessed) {
            list.add(newInterval);
        }

        return list.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        MergeSortedRange mergeSortedRange = new MergeSortedRange();
        int[][] intervals ={
                {1,2},
                {3,4},
                {7,8},
                {9,10}
        };
        int[][] arr = mergeSortedRange.insert(intervals, new int[]{3,12});
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
