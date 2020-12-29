package com.theonecai.leetcode.bit;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode 1521
 *
 */
public class ClosestToTarget {

    public int closestToTarget(int[] nums, int target) {
        int result = Math.abs(nums[0] - target);
        Set<Integer> preSet = new HashSet<>(20);
        preSet.add(nums[0]);
        for (int num : nums) {
            result = Math.min(result, Math.abs(num - target));
            Set<Integer> nextSet = new HashSet<>(20);
            nextSet.add(num);
            for (Integer n : preSet) {
                int t = n & num;
                if (nextSet.contains(t)) {
                    continue;
                }
                result = Math.min(result, Math.abs(t - target));
                nextSet.add(t);
            }
            preSet = nextSet;
        }
        return result;
    }

    public static void main(String[] args) {
        ClosestToTarget closestToTarget = new ClosestToTarget();
        Assert.assertEquals(2, closestToTarget.closestToTarget(new int[]{9,12,3,7,15}, 5));
    }
}
