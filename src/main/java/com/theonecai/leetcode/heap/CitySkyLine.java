package com.theonecai.leetcode.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 218
 *
 * @Author: theonecai
 * @Date: Create in 2020/8/7 19:28
 * @Description:
 */
public class CitySkyLine {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        return divideAndConquer(buildings, 0, buildings.length - 1);
    }

    private List<List<Integer>> divideAndConquer(int[][] buildings, int start, int end) {
        if (start == end) {
            return calculatePointer(buildings[start]);
        }

        int mid = (end + start) / 2;
        List<List<Integer>> leftPart = divideAndConquer(buildings, start, mid);
        List<List<Integer>> rightPart = divideAndConquer(buildings,mid + 1, end);

        return merge(leftPart, rightPart);
    }

    private List<Integer> toList(Integer... nums) {
        return Arrays.asList(nums);
    }

    private List<List<Integer>> calculatePointer(int[] building) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(toList(building[0], building[2]));
        result.add(toList(building[1], 0));
        return result;
    }

    private List<List<Integer>> merge(List<List<Integer>> leftPart, List<List<Integer>> rightPart) {
        List<List<Integer>> result = new ArrayList<>(leftPart.size() + rightPart.size());
        int leftIndex = 0;
        int rightIndex = 0;
        int leftHeight = 0;
        int rightHeight = 0;
        long left = 0;
        long right = 0;
        long x = 0;
        int h = 0;
        List<Integer> tmp = null;
        while (leftIndex < leftPart.size() || rightIndex < rightPart.size()) {
            left = leftIndex < leftPart.size() ? leftPart.get(leftIndex).get(0) : Long.MAX_VALUE;
            right = rightIndex < rightPart.size() ? rightPart.get(rightIndex).get(0) : Long.MAX_VALUE;
            if (left < right ) {
                x = left;
                leftHeight = leftPart.get(leftIndex).get(1);
                leftIndex++;
            } else if (left > right) {
                x = right;
                rightHeight = rightPart.get(rightIndex).get(1);
                rightIndex++;
            } else {
                x = leftPart.get(leftIndex).get(0);
                leftHeight = leftPart.get(leftIndex).get(1);
                rightHeight = rightPart.get(rightIndex).get(1);
                leftIndex++;
                rightIndex++;
            }
            h = Math.max(leftHeight, rightHeight);

            if (result.isEmpty() || result.get(result.size() - 1).get(1) != h) {
                result.add(toList((int)x, h));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        CitySkyLine citySkyLine = new CitySkyLine();
        /**
         * (2,10) (3,15) (7,0) ;  (5,12) (12,0) (15,10) (20,0); (19,8) (24,0);
         * (2,10) (3,15) (7,12) (12,0) (15,10) (20,0); (19,8) (24,0);
         *
         * [[2,10],[3,15],[7,15],[9,12],[12,0],[15,10],[20,8],[24,0]]
         * [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
         */
//        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        System.out.println(citySkyLine.getSkyline(buildings));
//        System.out.println();
//
//        int[][] b2 = {{2, 6, 11}, {5, 10, 12}, {7, 9, 14}};
//        System.out.println(citySkyLine.getSkyline(b2));
//        System.out.println();
//
//        int[][] buildings2 = {{2,4,10}, {3,7,15}, {5,12,12}, {11, 16, 11}, {15,20,10},{19,24,8}};
//        System.out.println(citySkyLine.getSkyline(buildings2));
//        System.out.println();
//
//        int[][] buildings3 = {{1, 2, 3}, {2, 4, 6}, {3, 8, 5}};
//        System.out.println(citySkyLine.getSkyline(buildings3));
//        System.out.println();

        int[][] buildings4 = {{1,2,1},{2147483646,2147483647,2147483647}};
        System.out.println(citySkyLine.getSkyline(buildings4));
        System.out.println();

        int[][] buildings5 = {{1, 10, 10}, {2, 4, 6}, {3, 5, 7}, {4, 10, 9}};
        System.out.println(citySkyLine.getSkyline(buildings5));
        System.out.println();


        int[][] buildings6 = {{0, 2, 3}, {2, 4, 3}, {4, 6, 3}};
        System.out.println(citySkyLine.getSkyline(buildings6));
        System.out.println();

    }
}
