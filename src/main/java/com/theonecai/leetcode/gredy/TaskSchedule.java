package com.theonecai.leetcode.gredy;

import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 621
 * @Author: theonecai
 * @Date: Create in 2020/9/16 19:56
 * @Description:
 */
public class TaskSchedule {

    public int leastInterval(char[] tasks, int n) {
        Integer[] taskCount = new Integer[26];
        Arrays.fill(taskCount, 0);
        for (char task : tasks) {
            taskCount[task - 'A']++;
        }
        int totalTime = 0;
        int preTaskTypeCount = 0;
        while (true) {
            int taskTypeCount = 0;
            Arrays.sort(taskCount, Comparator.reverseOrder());
            for (int i = 0; i < taskCount.length; i++) {
                if (taskCount[i] == 0) {
                    continue;
                }
                taskTypeCount++;
                taskCount[i]--;
                if (taskTypeCount == n + 1) {
                    break;
                }
            }
            if (taskTypeCount == 0) {
                if (preTaskTypeCount != 0 && preTaskTypeCount <= n) {
                    totalTime -= n + 1 - preTaskTypeCount;
                }
                break;
            }
            if (taskTypeCount > n) {
                totalTime += taskTypeCount;
            } else {
                totalTime += n + 1;
            }
            preTaskTypeCount = taskTypeCount;
        }

        return totalTime;
    }

    public static void main(String[] args) {
        TaskSchedule taskSchedule = new TaskSchedule();
        /**
         * ABCADEAFGA--A--A
         */
        Assert.assertEquals(16, taskSchedule.leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
        Assert.assertEquals(10, taskSchedule.leastInterval(new char[]{'A','A','B','B','A','A'}, 2));
        Assert.assertEquals(7, taskSchedule.leastInterval(new char[]{'A','A','B','B','A','A'}, 1));
        Assert.assertEquals(8, taskSchedule.leastInterval(new char[]{'A','A','B','B','A','B'}, 2));
        Assert.assertEquals(9, taskSchedule.leastInterval(new char[]{'A','A','B','B','A','B', 'C','C','C'}, 2));
        Assert.assertEquals(10, taskSchedule.leastInterval(new char[]{'A','A','B','B','A','B'}, 3));
        Assert.assertEquals(5, taskSchedule.leastInterval(new char[]{'A','A','A'}, 1));
        char[] tasks = new char[10000];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = (char)('A' + i % 26);
        }
        RunUtil.runAndPrintCostTime(() -> {
            System.out.println(taskSchedule.leastInterval(tasks, 30));
        });
    }
}
