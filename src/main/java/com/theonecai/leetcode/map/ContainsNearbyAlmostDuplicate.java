package com.theonecai.leetcode.map;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 220
 */
public class ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long celling = treeSet.ceiling((long)nums[i] - (long)t);
            if (celling != null && celling <= (long)nums[i] + (long)t) {
                return true;
            }
            treeSet.add((long)nums[i]);
            if (i >= k) {
                treeSet.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    /**
     * 219
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            if (i >= k) {
                if (set.size() <= k) {
                    return true;
                }
                set.remove(nums[i - k]);
            }
        }
        if (nums.length <= k) {
            return set.size() < nums.length;
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate duplicate = new ContainsNearbyAlmostDuplicate();
        Assert.assertFalse(duplicate.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
        Assert.assertTrue(duplicate.containsNearbyAlmostDuplicate(new int[]{1,2,3}, 1, 2));
        Assert.assertTrue(duplicate.containsNearbyDuplicate(new int[]{3,3}, 2));
    }
}
