package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 502
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/19 20:24
 * @Description:
 */
public class LeetcodeIPO {

    /**
     * 贪心：每次在可选范围内(保存在maxHeap中)选收益最大(maxHeap.poll())的那个项目
     * @param k
     * @param W
     * @param Profits
     * @param Capital
     * @return
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int currentMaxValue = W;
        List<Project> projects = new LinkedList<>();
        for (int i = 0; i < Profits.length; i++) {
            projects.add(new Project(Profits[i], Capital[i]));
        }
        // 根据capital排序
        projects.sort((Comparator.comparingInt(o -> o.capital)));

        PriorityQueue<Project> maxHeap = new PriorityQueue<>();
        projectsAddToHeapIfSelectable(currentMaxValue, projects, maxHeap);

        Project project;
        while (!maxHeap.isEmpty() && k > 0) {
            project = maxHeap.poll();
            currentMaxValue += project.profit;

            projectsAddToHeapIfSelectable(currentMaxValue, projects, maxHeap);

            k--;
        }

        return currentMaxValue;
    }

    private void projectsAddToHeapIfSelectable(int currentMaxValue, List<Project> remainProjects,
                                               PriorityQueue<Project> maxHeap) {
        Project project;
        Iterator<Project> it = remainProjects.iterator();
        while (it.hasNext()) {
            project = it.next();
            if (project.capital > currentMaxValue) {
                break;
            }
            maxHeap.add(project);
            it.remove();;
        }
    }

    static class Project implements Comparable<Project> {
        int profit;
        int capital;

        public Project(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }

        @Override
        public int compareTo(Project o) {
            int p = o.profit - this.profit;
            if (p == 0) {
                return this.capital - o.capital;
            }
            return p;
        }
    }

    public static void main(String[] args) {
        LeetcodeIPO ipo = new LeetcodeIPO();
        int[] capitals = {0, 1, 1};
        int[] profits = {1, 2, 3};
        System.out.println(Integer.MAX_VALUE);
        Assert.assertEquals(6, ipo.findMaximizedCapital(3, 0, profits, capitals));
    }
}
