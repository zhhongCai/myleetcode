package com.theonecai.leetcode.others;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 2069
 */
public class Robot {
    private String[] dir = new String[]{"East","North","West","South"};
    private List<Integer> directions;
    private int[][] points;
    private boolean moved;
    private int idx;


    public Robot(int width, int height) {
        moved = false;
        idx = 0;
        directions = new ArrayList<>(2  * (width + height - 2));
        points = new int[2  * (width + height - 2)][2];
        directions.add(3);
        int index = 0;
        points[index++] = new int[]{0, 0};
        for (int i = 1; i < width; i++) {
            directions.add(0);
            points[index++] = new int[]{i, 0};
        }
        for (int i = 1; i < height; i++) {
            directions.add(1);
            points[index++] = new int[]{width - 1, i};
        }
        for (int i = width - 2; i >= 0; i--) {
            directions.add(2);
            points[index++] = new int[]{i, height - 1};
        }
        for (int i = height - 2; i > 0; i--) {
            directions.add(3);
            points[index++] = new int[]{0, i};
        }
        for (int i = 0; i < points.length; i++) {
            System.out.println(Arrays.toString(points[i]) + directions.get(i));
        }
    }

    public void step(int num) {
        this.moved = true;
        idx = (idx + num) % directions.size();
    }

    public int[] getPos() {
        return points[idx];
    }

    public String getDir() {
        if (!moved) {
            return "East";
        }
        return dir[directions.get(idx)];
    }

    public static void main(String[] args) {
        Robot robot = new Robot(20, 13);
        robot.step(12);
        robot.step(21);
        Assert.assertArrayEquals(new int[]{17,12}, robot.getPos());
        Assert.assertEquals("West", robot.getDir());
        robot.step(17);
        Assert.assertArrayEquals(new int[]{0,12}, robot.getPos());
        Assert.assertEquals("West", robot.getDir());
        Assert.assertArrayEquals(new int[]{0,12}, robot.getPos());
        Assert.assertEquals("West", robot.getDir());
    }
}
