package com.theonecai.leetcode.dp;

import com.theonecai.algorithms.ArrayUtil;
import com.theonecai.leetcode.util.RunUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1235
 * @Author: theonecai
 * @Date: Create in 2020/9/3 19:46
 * @Description:
 */
public class PartTimeJob {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs);

        /**
         * dp[i] =
         * 若jobs[preJobIndex[i - 1]]的时间和jobs[i]的时间没有交叉： d[i - 1] + jobs[i].profit
         * 交叉了: max(dp[i - 1], d[j] + jobs[i].profit),其中j<=i-1,且jobs[i]和jobs[j]不交叉的最大j;
         */
        int[] dp = new int[jobs.length];

        Job preJob = null;
        for (int i = 0; i < jobs.length; i++) {
            // 考虑job[i]为第一份工作的情况
            dp[i] = Math.max(i == 0 ? 0 : dp[i - 1], jobs[i].profit);
            for (int j = i - 1; j >= 0; j--) {
                preJob = jobs[j];
                if (preJob.endTime <= jobs[i].startTime) {
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i].profit);
                    break;
                }
            }
        }
        return dp[jobs.length - 1];
    }

    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs);

        return jobScheduling(jobs, 0, -1, new HashMap<>());
    }

    private int jobScheduling(Job[] jobs, Integer i, Integer lastJobIndex, Map<String, Integer> profitMemo) {
        if (i >= jobs.length) {
            return 0;
        }
        if (profitMemo.containsKey(i.toString() + lastJobIndex.toString())) {
            return profitMemo.get(i.toString() + lastJobIndex.toString());
        }
        int p = 0;
        if (lastJobIndex >= 0 && jobs[i].startTime < jobs[lastJobIndex].endTime) {
            p = jobScheduling(jobs, i + 1, lastJobIndex, profitMemo);
            profitMemo.put(i.toString() + lastJobIndex.toString(), p);
            return p;
        }

        p = Math.max(jobs[i].profit + jobScheduling(jobs, i + 1, i, profitMemo),
                jobScheduling(jobs, i + 1, lastJobIndex, profitMemo));
        profitMemo.put(i.toString() + lastJobIndex.toString(), p);
        return p;
    }

    static class Job implements Comparable<Job> {
        /**
         * 开始时间
         */
        int startTime;
        /**
         * 结束时间
         */
        int endTime;
        /**
         * 收入
         */
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            // 按结束时间排序，这样可以确保jobs[i]和jobs[i-1]交叉时，
            // 找到j<=i-1,且jobs[i]和jobs[j]不交叉的最大j,dp[j] + jobs[i].profit为选中job[i]的最大收入
            return this.endTime - o.endTime;
        }
    }

    public static void main(String[] args) {
        PartTimeJob partTimeJob = new PartTimeJob();
        int[] startTime = {2, 1, 3, 4, 6, 7};
        int[] endTime = {3, 5, 10, 6, 9, 10};
        int[] profit = {20, 20, 100, 70, 60, 10};
        Assert.assertEquals(150, partTimeJob.jobScheduling(startTime, endTime, profit));

        int[] startTime3 = {1, 1, 1, 1, 1};
        int[] endTime3 = {3, 5, 10, 6, 9};
        int[] profit3 = {20, 20, 100, 70, 60};
        Assert.assertEquals(partTimeJob.jobScheduling2(startTime3, endTime3, profit3), partTimeJob.jobScheduling(startTime3, endTime3, profit3));

        int[] startTime2 = ArrayUtil.randIntArray(5000);
        int[] endTime2 = ArrayUtil.randIntArray(5000);
        int[] profit2 = ArrayUtil.randIntArray(5000);
        RunUtil.runAndPrintCostTime(() ->
                Assert.assertEquals(100, partTimeJob.jobScheduling2(startTime2, endTime2, profit2)));
    }
}
