package com.theonecai.leetcode.gredy;

import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode 630
 * @Author: theonecai
 * @Date: Create in 2020/9/18 21:00
 * @Description:
 */
public class ScheduleCourse {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (o1, o2) -> {
            int res = o1[1] - o2[1];
            if (res == 0) {
                return o1[0] - o2[0];
            }
            return res;
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int start = 0;
        for (int[] cours : courses) {
            if (start + cours[0] <= cours[1]) {
                start = start + cours[0];
                maxHeap.offer(cours[0]);
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > cours[0]) {
                start += cours[0] - maxHeap.poll();
                maxHeap.offer(cours[0]);
            }
        }
        return maxHeap.size();
    }

    public static void main(String[] args) {
        ScheduleCourse scheduleCourse = new ScheduleCourse();
        int[][] course = {{100, 200},{200, 1300},{1000, 1250},{2000, 3200}};
        Assert.assertEquals(3, scheduleCourse.scheduleCourse(course));
    }
}
