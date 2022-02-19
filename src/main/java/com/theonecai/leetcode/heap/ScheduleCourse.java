package com.theonecai.leetcode.heap;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 630
 */
public class ScheduleCourse {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int time = 0;
        for (int i = 0; i < courses.length; i++) {
            int[] course = courses[i];
            if (time + course[0] <= course[1]) {
                time += course[0];
                maxHeap.add(course[0]);
                continue;
            }
            if (!maxHeap.isEmpty() && maxHeap.peek() > course[0]) {
                time -= maxHeap.poll();
                maxHeap.add(course[0]);
                time += course[0];
            }
        }
        return maxHeap.size();
    }

    public static void main(String[] args) {
        ScheduleCourse scheduleCourse = new ScheduleCourse();

        Assert.assertEquals(3, scheduleCourse.scheduleCourse(new int[][]{
                {8,16},{6,17},{5,16},{6,10},{1,13}
        }));
        Assert.assertEquals(3, scheduleCourse.scheduleCourse(new int[][]{
                {100, 200},{200, 1300},{1000, 1250},{2000, 3200}
        }));
        Assert.assertEquals(1, scheduleCourse.scheduleCourse(new int[][]{
                {1,2}
        }));
        Assert.assertEquals(0, scheduleCourse.scheduleCourse(new int[][]{
                {3,2},{4,3}
        }));
    }
}
